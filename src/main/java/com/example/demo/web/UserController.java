package com.example.demo.web;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by BBG on 2018/5/24.
 */
@RestController
@RequestMapping("/api/userController")
public class UserController {
    final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

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
        log.info("getUserById {}",id);
        User user = userRepository.getOne(id);
        return user;
    }

    @DeleteMapping("/deleteUserById/{id}")
    public void deleteUserById(@PathVariable("id") String id){
        userRepository.deleteById(id);
    }

    @GetMapping("/getUserByAge/{age}")
    public List<User> getUserByAge(@PathVariable("age") Integer age){
        return userRepository.findUserByAge(age);
    }


    @GetMapping("/saveTwoUser")
    public void saveTwoUser(){
        userService.insertTwoUser();
    }

    @PostMapping("/saveUser")
    public User saveUser(@Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error(bindingResult.getFieldError().getDefaultMessage());
            return null;
        }
        return userRepository.save(user);
    }
}