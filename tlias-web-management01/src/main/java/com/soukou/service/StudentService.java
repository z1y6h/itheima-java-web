package com.soukou.service;

import com.soukou.pojo.PageResult;
import com.soukou.pojo.Student;
import com.soukou.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {


    PageResult<Student> page(StudentQueryParam studentQueryParam);

    void save(Student student);

    Student getInfo(Integer id);

    void update(Student student);

    void deleteByIds(List<Integer> ids);

    void violation(Integer id, Integer score);
}
