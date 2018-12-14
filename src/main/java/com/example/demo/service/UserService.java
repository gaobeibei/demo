package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.enums.ResultEnum;
import com.example.demo.model.BadRequestException;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Transactional
    public void insertTwoUser() {
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

    public void getAge(String id) throws Exception {
        User user = userRepository.findById(id).get();
        Integer age = user.getAge();
        if (age < 10) {
            //返回"你还在上小学吧"
            throw new BadRequestException(ResultEnum.PRIMARY_SCHOOL);
        } else if (age >= 10 && age <= 16) {
            //返回"你可能在上初中"
            throw new BadRequestException(ResultEnum.MIDDLE_SCHOOL);
        } else {
            throw new BadRequestException(ResultEnum.UNKONW_ERROR);
        }
    }

    /**
     *
     */
    public User findOne(String id) {
        return userRepository.findById(id).get();
    }
}
