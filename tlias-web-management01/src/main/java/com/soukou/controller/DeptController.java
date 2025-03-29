package com.soukou.controller;

import com.soukou.anno.LogOperation;
import com.soukou.pojo.Dept;
import com.soukou.pojo.Result;
import com.soukou.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    //@RequestMapping(value = "/depts",method = RequestMethod.GET) // 指定请求方式
    // 查询所有部门
    @GetMapping("/depts") // 简化写法(推荐)
    public Result list(){  // 当访问/depts时，自动执行此方法
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    };

    /**
     * 根据id删除部门 - delete http://localhost:8080/depts?id=1
     */
    @LogOperation
    @DeleteMapping("/depts")
    public Result delete(Integer id){
        System.out.println("根据id删除部门, id=" + id);
        deptService.deleteById(id);
        return Result.success();
    }

    /**
     * 新增部门 - POST http://localhost:8080/depts   请求参数：{"name":"研发部"}
     */
    @LogOperation
    @PostMapping("/depts")
    public Result save(@RequestBody Dept dept){
        System.out.println("新增部门, dept=" + dept);
        deptService.save(dept);
        return Result.success();
    }

    /**
     * 根据ID查询 - GET http://localhost:8080/depts/1
     */
    @GetMapping("/depts/{id}")
    public Result getById(@PathVariable Integer id){
        System.out.println("根据ID查询, id=" + id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    /**
     * 修改部门 - PUT http://localhost:8080/depts  请求参数：{"id":1,"name":"研发部"}
     */
    @LogOperation
    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept){
        System.out.println("修改部门, dept=" + dept);
        deptService.update(dept);
        return Result.success();
    }
}
