package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;

/**
 * Created by BBG on 2018/5/24.
 */
@Data
@Entity
@Table(name = "user")
public class User extends BaseEntity{

    private String name;

    @Min(value = 18,message = "未成年人禁止操作")
    private Integer age;

    private String phone;
}
