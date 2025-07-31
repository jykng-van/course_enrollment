package com.jykng.enrollment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jykng.enrollment.model.SystemLog;

public interface SystemLogRepository extends JpaRepository<SystemLog, Long> {

}
