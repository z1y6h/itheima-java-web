package com.soukou.controller;

import com.soukou.pojo.ClazzOption;
import com.soukou.pojo.JobOption;
import com.soukou.pojo.Result;
import com.soukou.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 统计各个职位的员工人数
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("统计各个职位的员工人数");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    /**
     * 统计员工性别信息
     */
    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("统计员工性别信息");
        List<Map> genderList = reportService.getEmpGenderData();
        return Result.success(genderList);
    }

    /**
     * 统计每个班级的人数
     */
    @GetMapping("/studentCountData")
    public Result getClazzData(){
        log.info("统计每个班级的人数");
        ClazzOption clazzOption = reportService.getClazzData();
        return Result.success(clazzOption);

    }

    /**
     * 统计学生学历信息
     */
    @GetMapping("/studentDegreeData")
    public Result getDegreeData(){
        log.info("统计学生学历信息");
        List<Map> degreeList = reportService.getDegreeData();
        return Result.success(degreeList);
    }


}
