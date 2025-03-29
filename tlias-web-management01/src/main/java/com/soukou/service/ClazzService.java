package com.soukou.service;

import com.soukou.pojo.*;

import java.util.List;

public interface ClazzService {
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    void save(Clazz clazz);

    Clazz getInfo(Integer id);

    void update(Clazz clazz);

    void delete(Integer id);

    List<Clazz> findAll();
}
