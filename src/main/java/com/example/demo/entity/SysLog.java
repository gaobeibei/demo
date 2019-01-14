package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "sys_log")
public class SysLog extends BaseEntity {
    private String ip;

    private String method;

    private String url;

    private String args;

    private Date time;
}
