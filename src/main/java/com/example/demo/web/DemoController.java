package com.example.demo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by BBG on 2018/5/24.
 */
@RestController
@RequestMapping("/api/demoController")
public class DemoController {

    final Logger log = LoggerFactory.getLogger(DemoController.class);

    @Value("${user.name}")
    private String name;


    public String say(){
        log.info(name);
        return name;
    }
}
