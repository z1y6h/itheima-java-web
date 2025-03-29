package com.soukou.service;

import com.soukou.pojo.Emp;
import com.soukou.pojo.EmpQueryParam;
import com.soukou.pojo.LoginInfo;
import com.soukou.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {

    /**     * 分页条件查询     */
   // PageResult page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);
    PageResult<Emp> page(EmpQueryParam empQueryParam);


    /**     * 新增员工     */
    void save(Emp emp);

    /**
     * 批量删除员工
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据ID查询员工的详细信息
     */
    Emp getInfo(Integer id);

    /**
     * 更新员工信息
     * @param emp
     */
    void update(Emp emp);

    /**
     * 查询所有的员工数据
     */
    List<Emp> list();

    LoginInfo login(Emp emp);
}
