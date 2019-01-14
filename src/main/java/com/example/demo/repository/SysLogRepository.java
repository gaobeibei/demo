package com.example.demo.repository;

import com.example.demo.entity.SysLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysLogRepository extends JpaRepository<SysLog, String> {
}
