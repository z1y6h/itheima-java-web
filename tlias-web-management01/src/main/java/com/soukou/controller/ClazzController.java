package com.soukou.controller;


import com.soukou.anno.LogOperation;
import com.soukou.pojo.*;
import com.soukou.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;



    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam) {
        log.info("查询请求参数： {}",clazzQueryParam );
        PageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 添加班级
     */
    @LogOperation
    @PostMapping
    public Result save(@RequestBody Clazz clazz){  // 前端返回的是一个对象时，使用@RequestBody
        log.info("请求参数clazz: {}", clazz);
        clazzService.save(clazz);
        return Result.success();
    }

    /**
     * 查询回显
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){  // 从url中获取id
        log.info("根据id查询班级的详细信息");
        Clazz clazz  = clazzService.getInfo(id);
        return Result.success(clazz);
    }

    /**
     * 更新员工信息
     */
    @LogOperation
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("修改班级信息, {}", clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    /**
     * 删除班级信息
     */
    @LogOperation
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        System.out.println("根据id删除班级, id=" + id);
        clazzService.delete(id);
        return Result.success();
    }

    // 查询所有部门
    @GetMapping("/list")
    public Result list(){
        List<Clazz> clazzList = clazzService.findAll();
        return Result.success(clazzList);
    }

}
