package com.jykng.enrollment.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jykng.enrollment.model.SystemLog;
import com.jykng.enrollment.repository.SystemLogRepository;

@Service
public class SystemLogService {
    @Autowired
    SystemLogRepository logRepository;

    public void saveLog(SystemLog log){
        log.setTimeStamp(Instant.now()); //set timestamp to now
        logRepository.save(log);
    }
}
