package com.example.demo.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(generator = "system_uuid")
    @GenericGenerator(name="system_uuid", strategy = "uuid")
    private String id;
}
