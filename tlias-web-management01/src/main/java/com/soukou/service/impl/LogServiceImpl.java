package com.soukou.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.soukou.mapper.OperateLogMapper;
import com.soukou.pojo.Clazz;
import com.soukou.pojo.LogQueryParam;
import com.soukou.pojo.OperateLog;
import com.soukou.pojo.PageResult;
import com.soukou.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Override
    public PageResult<OperateLog> page(LogQueryParam logQueryParam) {
        //1. 设置PageHelper分页参数
        PageHelper.startPage(logQueryParam.getPage(), logQueryParam.getPageSize());
        //2. 执行查询
        List<OperateLog> logList = operateLogMapper.list(logQueryParam);
        //3. 封装分页结果(强转为Page对象)
        Page<OperateLog> p = (Page<OperateLog>)logList;
        return new PageResult<OperateLog>(p.getTotal(), p.getResult());
    }
}
