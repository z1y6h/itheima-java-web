package com.soukou.aop;

import com.soukou.anno.LogOperation;
import com.soukou.mapper.OperateLogMapper;
import com.soukou.pojo.OperateLog;
import com.soukou.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class OperationLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    // 环绕通知
    @Around("@annotation(log)")
    public Object around(ProceedingJoinPoint joinPoint, LogOperation log) throws Throwable {
        // 记录开始时间
        long startTime = System.currentTimeMillis();
        // 执行方法
        Object result = joinPoint.proceed();
        // 当前时间
        long endTime = System.currentTimeMillis();
        // 耗时
        long costTime = endTime - startTime;

        // 构建日志对象
        OperateLog operateLog = new OperateLog();
        operateLog.setOperateEmpId(getCurrentUserId()); // 需要实现 getCurrentUserId 方法
        operateLog.setOperateTime(LocalDateTime.now());  // 获取当前时间
        operateLog.setClassName(joinPoint.getTarget().getClass().getName());  // 获取类名
        operateLog.setMethodName(joinPoint.getSignature().getName());  // 获取方法名
        operateLog.setMethodParams(Arrays.toString(joinPoint.getArgs()));  // 获取参数
        operateLog.setReturnValue(result.toString());  // 返回值
        operateLog.setCostTime(costTime);  // 耗时


        // 插入日志
        operateLogMapper.insert(operateLog);
        return result;
    }

    // 示例方法，获取当前用户ID
    private int getCurrentUserId() {
        // 这里应该根据实际情况从认证信息中获取当前登录用户的ID
        // 从线程中获取当前登录用户的ID
        return CurrentHolder.getCurrentId(); // 示例返回值
    }
}

