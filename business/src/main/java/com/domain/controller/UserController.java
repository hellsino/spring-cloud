package com.domain.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.domain.entity.User;
import com.domain.mapper.UserMapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author hellsino
 * @since 2026-06-10 13:33:41.654
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    /**
     * MybatisPlusConfig 分页配置类
     * page.setMaxLimit(Long.parseLong("50"));
     * 设置每页80条,这里最多显示每页50条
     */
    @ApiOperation("page")
    @GetMapping("/page")
    public Page<User> page(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        //查询第pageNum页,每页pageSize条数据
        //Page<User> page = new Page<>(1, 80);
        Page<User> page = new Page<>();
        page.setCurrent(1);
        page.setSize(80);
        //将分页参数page作为Mybatis或Mybatis Plus的第一个参数传入持久层函数，即可完成分页查询
        return userMapper.selectPage(page, null);
    }

    @ApiOperation("pageParam")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "每页显示多少条", required = true, dataType = "Integer", paramType = "query")
    })
    @GetMapping("/pageParam")
    public Page<User> pageParam(@RequestParam(value = "current", required = false, defaultValue = "1") Integer current, @RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("status", "0");

        Page<User> page = new Page<>();
        page.setCurrent(current);
        page.setSize(size);
        return userMapper.selectPage(page, wrapper);
    }

    @ApiOperation("userRandomUpdate")
    @PostMapping("/userRandomUpdate")
    @Transactional
    public int userRandomUpdate() {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getStatus, "0").last("limit 1");
        User user = userMapper.selectOne(lambdaQueryWrapper);
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(User::getId, user.getId()).set(User::getStatus, "0");
        return userMapper.update(null, lambdaUpdateWrapper);
    }

    @ApiOperation("selectDistinct")
    @GetMapping("/selectDistinct")
    public void selectDistinct() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("distinct id,username,password");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @ApiOperation("list")
    @GetMapping("/list")
    public ResponseEntity list() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("*");
        List<User> list = userMapper.selectList(queryWrapper);
        return ResponseEntity.ok(list);
    }

    @ApiOperation("feign")
    @GetMapping("/feign")
    public ResponseEntity<List<User>> feign() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("*");
        List<User> list = userMapper.selectList(queryWrapper);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
