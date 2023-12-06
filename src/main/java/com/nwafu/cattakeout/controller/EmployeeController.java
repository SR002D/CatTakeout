package com.nwafu.cattakeout.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nwafu.cattakeout.common.Result;
import com.nwafu.cattakeout.pojo.Employee;
import com.nwafu.cattakeout.service.IEmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    IEmployeeService employeeService;

    /**
     * 登录
     */
    @PostMapping("/login")
    public Result login(HttpServletRequest request,@RequestBody Employee employee){
        //将页面提交的密码password进行md5加密处理
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);

        if(emp==null){
            return Result.error("账号错误！");
        }

        // 校验密码
        if(!emp.getPassword().equals(password)){
            return Result.error("密码错误！");
        }

        //员工账号禁用
        if(emp.getStatus() == 0){
            return Result.error("账号已禁用！");
        }

        //登录成功，将员工id存入Session并返回登录成功结果
        request.getSession().setAttribute("employee",emp.getId());
        return Result.success(emp);
    }

    /**
     * 退出登录
     */
    @PostMapping("/logout")
    public Result logout(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        return Result.success("退出成功！");
    }

    /**
     * 分页查找
     */
    @GetMapping("/page")
    public Result page(int page,int pageSize,String name){
        log.info("page = {},pageSize = {},name = {}" ,page,pageSize,name);

        //构造分页构造器
        Page pageInfo = new Page(page,pageSize);

        //构造条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name),Employee::getName,name);
        //添加排序条件
        queryWrapper.orderByDesc(Employee::getUpdateTime);
        //执行查询
        employeeService.page(pageInfo,queryWrapper);
        return Result.success(pageInfo);
    }

    /**
     * 根据id查找员工
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id){
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getId,id);
        Employee emp = employeeService.getOne(queryWrapper);
        log.info("查询员工：id：{}，员工：{}",id,emp);
        return Result.success(emp);
    }

    /**
     * 修改员工信息
     */
    @PutMapping()
    public Result updateEmp(@RequestBody Employee employee){
        employeeService.updateById(employee);
        log.info("修改员工信息：{}",employee);
        return Result.success();
    }

    /**
     * 新增员工
     */
    @PostMapping()
    public Result add(@RequestBody Employee employee){
        // 默认密码为123456，使用md5加密
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        log.info("新增员工：{}",employee);
        employeeService.save(employee);
        return Result.success();
    }
}
