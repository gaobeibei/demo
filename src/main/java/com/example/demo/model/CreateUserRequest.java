package com.example.demo.model;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class CreateUserRequest {
    private String name;

    @Min(value = 18,message = "未成年人禁止操作")
    private Integer age;

    private String phone;
}
