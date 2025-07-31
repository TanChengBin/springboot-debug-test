package com.cpolar.springbootdebugtest.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理器
 *
 * 捕获所有未处理的异常，返回统一的JSON响应，便于前端和调试。
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 处理所有异常
     *
     * @param ex 捕获到的异常
     * @return 统一的JSON响应，包含状态码、错误信息、时间等
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleException(Exception ex) {
        Map<String, Object> result = new HashMap<>();
        result.put("状态码", 500);
        result.put("消息", "系统出错，请联系管理员");
        result.put("时间", LocalDateTime.now().format(FORMATTER));
        // 控制台打印异常，便于调试
        ex.printStackTrace();
        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}