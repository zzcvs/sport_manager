package com.project.demo.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({DuplicateKeyException.class})
    public Map<String, Object> handleDuplicationKey(DuplicateKeyException e) {
        log.warn("数据库唯一约束拦截：{}", e.getMessage());
        Map<String, Object> error = new HashMap<>();
        error.put("code", 30000);
        error.put("message", "数据重复冲突：该时间段已存在相同安排（数据库唯一约束拦截）");
        Map<String, Object> body = new HashMap<>();
        body.put("error", error);
        return body;
    }

    @ExceptionHandler(BusinessException.class)
    public Map<String, Object> handleBusiness(BusinessException e) {
        log.warn("业务拦截规则:{}", e.getMessage());
        Map<String, Object> error = new HashMap<>();
        error.put("code", e.getCode());
        error.put("message", e.getMessage());
        Map<String, Object> body = new HashMap<>();
        body.put("error", error);
        return body;
    }
}
