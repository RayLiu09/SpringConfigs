package com.example.demo.controllers;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entities.UserEntiity;
import com.example.demo.services.UserService;
import com.example.demo.utils.SecurityUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tags(value = {
        @Tag(name = "用户管理", description = "用户管理相关接口")
})
@RequestMapping("/api/users")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * 分页查询所有用户
     */
    @RequestMapping("/list")
    @Operation(summary = "分页查询所有用户")
    public List<UserEntiity> list(
            @RequestParam(value = "page", defaultValue = "1")
            @Parameter(name = "page", description = "页码", required = true)
                    Integer page,
            @RequestParam(value = "size", defaultValue = "10")
            @Parameter(name = "size", description = "每页数量", required = true)
                    Integer size) {
        return userService.list(new Page<>(page, size));
    }

    /**
     * 获取用户详情
     *
     * @param id
     */
    @RequestMapping("/detail/{id}")
    @Operation(summary = "获取用户详情")
    public UserEntiity detail(@PathVariable("id") @Parameter(name = "id", description = "用户ID", required = true) Long id) {
        return userService.getById(id);
    }

    /**
     * 创建用户
     *
     */
    @RequestMapping("/create")
    @Operation(summary = "创建用户")
    public boolean create(@RequestBody @Parameter(name = "user", description = "用户信息", required = true) UserEntiity user) {
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        return userService.save(user);
    }
}
