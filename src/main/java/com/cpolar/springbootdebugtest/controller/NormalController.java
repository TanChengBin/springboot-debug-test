package com.cpolar.springbootdebugtest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * 正常接口控制器
 * 
 * 该类提供正常的REST API接口，用于测试应用的基本功能
 * 这些接口不会抛出异常，可以正常工作
 * 
 * @author cpolar
 * @version 1.0.0
 * @since 2025-07-30
 */
@RestController
@RequestMapping("/normal")
public class NormalController {

    // 定义统一的时间格式化器
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取应用信息接口
     * 
     * 该接口返回应用的详细信息，包括版本、作者等
     * 
     * @return 包含应用信息的Map对象
     */
    @GetMapping("/info")
    public Map<String, Object> getAppInfo() {
        Map<String, Object> result = new HashMap<>();
        String now = LocalDateTime.now().format(FORMATTER);
        result.put("名称", "springboot-debug-test");
        result.put("版本", "1.0.0");
        result.put("作者", "cpolar");
        result.put("描述", "Spring Boot项目用于模拟IDEA远程调试");
        result.put("当前时间", now);
        System.out.println("应用信息接口被调用，时间：" + now);
        return result;
    }

} 