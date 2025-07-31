package com.jykng.enrollment.interceptor;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.jykng.enrollment.model.SystemLog;
import com.jykng.enrollment.service.SystemLogService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LogInterceptor implements HandlerInterceptor{
    @Autowired
    private SystemLogService logService;

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception ex) throws Exception{
        if (!req.getMethod().equals("OPTIONS")){
            var system_log = new SystemLog();

            //get info
            system_log.setRequestURL(req.getRequestURI()); //url
            system_log.setRequestType(req.getMethod()); //method like GET or POST
            system_log.setRequestIP(req.getRemoteAddr()); //the ip address requesting

            //could get request body, but it needs a special wrapper for getReader() or else "java.lang.IllegalStateException: getInputStream() has already been called for this request"

            system_log.setResponseStatus(res.getStatus()); //status code like 200
            if (ex!= null){
                system_log.setResponseException(ex.getMessage()); //The exception message if any
            }

            logService.saveLog(system_log);
        }

    }
}
