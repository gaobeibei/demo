package com.example.demo.web;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by BBG on 2018/5/24.
 */
@RestController
@RequestMapping("/api/userController")
public class UserController {
    final Logger log = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    UserRepository userRepository;

    @GetMapping("/getAllUser")
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    @PostMapping("/saveOneUser")
    private void saveOneUser(@RequestParam(name = "name",required = true) String name,
                             @RequestParam("age") Integer age,
                             @RequestParam("phone") String phone){
        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setPhone(phone);
        userRepository.save(user);
    }

    @GetMapping("/getUserById/{id}")
    private User getUserById(@PathVariable("id") String id){
        User user = userRepository.getOne(id);
        return user;
    }
}
