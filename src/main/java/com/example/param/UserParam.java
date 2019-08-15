package com.example.param;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
public class UserParam {
    private Integer id;

    @NotEmpty(message = "用户名不能为空!")
    private String username;
    @NotEmpty(message = "密码不能为空!")
    @Length(message = "长度不能小于6位")
    private String password;

    private String phone;
}
