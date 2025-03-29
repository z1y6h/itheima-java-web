package com.soukou.utils;

public class CurrentHolder {

    private static final ThreadLocal<Integer> CURRENT_LOCAL= new ThreadLocal<>();

    // 存储当前登录用户id
    public static void setCurrentId(Integer employeeId) {
        CURRENT_LOCAL.set(employeeId);
    }

    // 获取当前登录用户id
    public static Integer getCurrentId() {
        return CURRENT_LOCAL.get();
    }

    // 释放内存，删除当前登录用户id
    public static void remove() {
        CURRENT_LOCAL.remove();
    }
}
