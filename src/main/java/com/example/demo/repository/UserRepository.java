package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by BBG on 2018/5/24.
 */

public interface UserRepository extends JpaRepository<User,String> {

    /**
     * 通过年龄查用户
     */

    public List<User> findUserByAge(Integer age);
}
