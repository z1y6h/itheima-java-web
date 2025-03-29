package com.soukou.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.soukou.mapper.StudentMapper;
import com.soukou.pojo.Clazz;
import com.soukou.pojo.PageResult;
import com.soukou.pojo.Student;
import com.soukou.pojo.StudentQueryParam;
import com.soukou.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult<Student> page(StudentQueryParam studentQueryParam) {
        //1. 设置PageHelper分页参数
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());
        //2. 执行查询
        List<Student> studentList = studentMapper.list(studentQueryParam);
        //3. 封装分页结果(强转为Page对象)
        Page<Student> p = (Page<Student>)studentList;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Override
    public void save(Student student) {
        //1.补全基础属性
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        //2.保存员工基本信息
        studentMapper.insert(student);

    }

    @Override
    public Student getInfo(Integer id) {
        return studentMapper.getById(id);
    }

    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        studentMapper.deleteByIds(ids);
    }

    @Override
    public void violation(Integer id, Integer score) {
        // 根据id，将学生的violation_count加1，violation_score加score
        studentMapper.violation(id, score);
    }
}
