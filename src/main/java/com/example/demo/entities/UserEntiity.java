package com.example.demo.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "user")
@Table(name = "T_USER")
public class UserEntiity extends BaseEntity {
    // 标明ID，主键策略
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @TableField(value = "name")
    @Schema(description = "用户名", example = "admin")
    private String username;

    @TableField(value = "password")
    @Schema(description = "密码", example = "pass@word")
    private String password;

    @TableField(value = "status")
    @Schema(description = "状态", example = "1")
    private int status;
}
