package com.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author hellsino
 * @since 2026-06-10 13:33:41.654
 */
//@Getter
//@Setter
@Validated
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("user")
@ApiModel(value = "User对象", description = "用户表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户名（登录）")
    @TableField("username")
    private String username;

    @ApiModelProperty("昵称")
    @TableField("nickname")
    private String nickname;

    @ApiModelProperty("密码")
    @TableField("password")
    private String password;

    @ApiModelProperty("手机号")
    @TableField("phone")
    private String phone;

    @ApiModelProperty("邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty("头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty("账号状态（0正常 1停用）")
    @TableField("status")
    private String status;

    @ApiModelProperty("删除标志（0代表存在 1代表删除）")
    @TableField("del_flag")
    private String delFlag;

    @ApiModelProperty("最后登录IP")
    @TableField("login_ip")
    private String loginIp;

    @ApiModelProperty("最后登录时间")
    @TableField("login_date")
    private LocalDateTime loginDate;

    @ApiModelProperty("姓名")
    @TableField("name")
    private String name;

    @ApiModelProperty("性别(字典)")
    @TableField("gender")
    private String gender;

    @ApiModelProperty("年龄")
    @TableField("age")
    private String age;

    @ApiModelProperty("生日")
    @TableField("birthday")
    private String birthday;

    @ApiModelProperty("身高")
    @TableField("height")
    private String height;

    @ApiModelProperty("体重")
    @TableField("weight")
    private String weight;

    @ApiModelProperty("婚姻状态（字典）")
    @TableField("marriage")
    private String marriage;

    @ApiModelProperty("家乡")
    @TableField("home_city")
    private String homeCity;

    @ApiModelProperty("工作居住地")
    @TableField("live_city")
    private String liveCity;

    @ApiModelProperty("自我介绍")
    @TableField("introduce")
    private String introduce;

    @ApiModelProperty("公司")
    @TableField("company")
    private String company;

    @ApiModelProperty("职业（字典）")
    @TableField("job")
    private String job;

    @ApiModelProperty("毕业院校")
    @TableField("school")
    private String school;

    @ApiModelProperty("最高学历（字典）")
    @TableField("education")
    private String education;

    @ApiModelProperty("年薪（字典）")
    @TableField("salary")
    private String salary;

    @ApiModelProperty("有房")
    @TableField("house")
    private String house;

    @ApiModelProperty("有车")
    @TableField("car")
    private String car;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "创建日期:yyyy-MM-dd HH:mm:ss.SSS")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间:yyyy-MM-dd HH:mm:ss.SSS")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    @TableField("update_time")
    private LocalDateTime updateTime;

}
