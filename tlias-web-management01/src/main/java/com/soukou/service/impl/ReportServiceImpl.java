package com.soukou.service.impl;

import com.soukou.mapper.EmpMapper;
import com.soukou.mapper.StudentMapper;
import com.soukou.pojo.ClazzOption;
import com.soukou.pojo.JobOption;
import com.soukou.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public JobOption getEmpJobData() {
        List<Map<String,Object>> list = empMapper.countEmpJobData();  // 每个map: {"pos":"java","total":10}
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("total")).toList();
        return new JobOption(jobList, dataList);  // 返回一个对象
    }

    @Override
    public List<Map> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public ClazzOption getClazzData() {
        List<Map<String,Object>> list = studentMapper.countClazzData();  // 每个map: {"pos":"java","total":10}
        List<Object> clazzList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("total")).toList();
        return new ClazzOption(clazzList, dataList);  // 返回一个对象
    }

    @Override
    public List<Map> getDegreeData() {
        return studentMapper.countDegreeData();
    }

}
