package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Transactional
    public void insertTwoUser(){
        User user = new User();
        user.setName("A");
        user.setAge(19);
        user.setPhone("121121212");
        userRepository.save(user);

        User b = new User();
        user.setName("BBBBBBBBBB");
        user.setAge(111111111);
        user.setPhone("999999");
        userRepository.save(b);
    }
}
