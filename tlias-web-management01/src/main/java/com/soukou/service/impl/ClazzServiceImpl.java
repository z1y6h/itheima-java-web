package com.soukou.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.soukou.exception.BusinessException;
import com.soukou.mapper.ClazzMapper;
import com.soukou.mapper.EmpMapper;
import com.soukou.mapper.StudentMapper;
import com.soukou.pojo.*;
import com.soukou.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        //1. 设置PageHelper分页参数
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
        //2. 执行查询
        List<Clazz> clazzList = clazzMapper.list(clazzQueryParam);
        // 在这里，将班级状态status，显示为：未开班、已结课、在读中 这三种。
        //如果：
        //  - 当前时间 > 结课时间：状态未 已结课。
        //  - 当前时间 < 开课时间：状态未 未开班。
        //  - 否则，就是 在读中。
        for (Clazz clazz : clazzList) {
            if (clazz.getEndDate().isBefore(ChronoLocalDate.from(LocalDateTime.now()))) {
                clazz.setStatus("已结课");
            } else if (clazz.getBeginDate().isAfter(ChronoLocalDate.from(LocalDateTime.now()))) {
                clazz.setStatus("未开班");
            } else {
                clazz.setStatus("在读中");
            }
        }
        //3. 封装分页结果(强转为Page对象)
        Page<Clazz> p = (Page<Clazz>)clazzList;
        return new PageResult<Clazz>(p.getTotal(), p.getResult());

    }

    @Override
    public void save(Clazz clazz) {
        //1.补全基础属性
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        //2.保存员工基本信息
        clazzMapper.insert(clazz);

    }

    // 查询回显
    @Override
    public Clazz getInfo(Integer id) {
        return clazzMapper.getById(id);
    }

    @Override
    public void update(Clazz clazz) {
        //1.补全基础属性
        clazz.setUpdateTime(LocalDateTime.now());
        //2.更新班级基本信息
        clazzMapper.update(clazz);
    }

    @Override
    public void delete(Integer id) {
        // 要求如果该班级下关联的有学生，是不允许删除的，并提示错误信息："对不起, 该班级下有学生, 不能直接删除"。 (提示：可以通过自定义异常 + 全局异常处理器实现)
        Integer count = studentMapper.countByClazzId(id);
        if(count > 0){
            throw new BusinessException("班级下有学员, 不能直接删除~");
        }
        clazzMapper.delete(id);
    }

    @Override
    public List<Clazz> findAll() {
        return clazzMapper.findAll();
    }


}
