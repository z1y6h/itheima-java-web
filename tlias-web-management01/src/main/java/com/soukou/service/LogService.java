package com.soukou.service;

import com.soukou.pojo.LogQueryParam;
import com.soukou.pojo.OperateLog;
import com.soukou.pojo.PageResult;

public interface LogService {
    PageResult<OperateLog> page(LogQueryParam logQueryParam);
}
