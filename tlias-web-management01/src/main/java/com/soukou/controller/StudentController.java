package com.soukou.controller;

import com.soukou.anno.LogOperation;
import com.soukou.pojo.*;
import com.soukou.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public Result page(StudentQueryParam studentQueryParam) {
        log.info("查询请求参数： {}",studentQueryParam );
        PageResult<Student> pageResult = studentService.page(studentQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 添加学生
     */
    @LogOperation
    @PostMapping
    public Result save(@RequestBody Student student){  // 前端返回的是一个对象时，使用@RequestBody
        log.info("请求参数student: {}", student);
        studentService.save(student);
        return Result.success();
    }

    /**
     * 查询回显
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){  // 从url中获取id
        log.info("根据id查询学生的详细信息");
        Student student  = studentService.getInfo(id);
        return Result.success(student);
    }

    /**
     * 更新学生信息
     */
    @LogOperation
    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("修改学生信息, {}", student);
        studentService.update(student);
        return Result.success();
    }

    /**
     * 批量删除学生
     */
    @LogOperation
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除学生: ids={} ", ids);
        studentService.deleteByIds(ids);
        return Result.success();
    }

    /**
     * 违纪处理
     */
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id, @PathVariable Integer score){
        log.info("违纪处理, id={}, score={}", id, score);
        studentService.violation(id, score);
        return Result.success();
    }



}
