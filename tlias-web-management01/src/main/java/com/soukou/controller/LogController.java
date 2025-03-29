package com.soukou.controller;

import com.soukou.pojo.*;
import com.soukou.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/log/page")
public class LogController {

    @Autowired
    private LogService logService;
    @GetMapping
    public Result page(LogQueryParam logQueryParam) {
        log.info("查询请求参数： {}",logQueryParam );
        PageResult<OperateLog> pageResult = logService.page(logQueryParam);
        return Result.success(pageResult);
    }
}
