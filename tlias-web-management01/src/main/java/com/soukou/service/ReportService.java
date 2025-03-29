package com.soukou.service;

import com.soukou.pojo.ClazzOption;
import com.soukou.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /**
     * 统计各个职位的员工人数
     * @return
     */
    JobOption getEmpJobData();

    /**
     * 统计员工性别信息
     */
    List<Map> getEmpGenderData();

    ClazzOption getClazzData();

    List<Map> getDegreeData();
}
