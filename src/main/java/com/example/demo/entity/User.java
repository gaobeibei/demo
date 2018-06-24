package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;

/**
 * Created by BBG on 2018/5/24.
 */
@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private String name;

    @Min(value = 18,message = "未成年人禁止操作")
    private Integer age;

    private String phone;
}
