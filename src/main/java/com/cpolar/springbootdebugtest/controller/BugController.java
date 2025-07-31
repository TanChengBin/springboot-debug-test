package com.cpolar.springbootdebugtest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 包含Bug的接口控制器
 * 
 * 该类提供包含各种bug的REST API接口，用于模拟调试场景
 * 这些接口会故意抛出不同类型的异常，便于测试调试功能
 * 
 * @author cpolar
 * @version 1.0.0
 * @since 2025-07-30
 */
@RestController
@RequestMapping("/bug")
public class BugController {

    // 定义统一的时间格式化器
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 空指针异常接口
     * 
     * 该接口会故意抛出NullPointerException
     * 用于测试空指针异常的调试场景
     * 
     * @return 永远不会执行到这里，因为会抛出异常
     */
    @GetMapping("/null-pointer")
    public String nullPointerException() {
        String nullString = null;
        String now = LocalDateTime.now().format(FORMATTER);
        System.out.println("空指针异常接口被调用，准备抛出异常，时间：" + now);
        return nullString.toString();
    }

    /**
     * 无限循环接口
     * 
     * 该接口会进入无限循环，用于测试线程阻塞场景
     * 注意：这个接口会导致线程阻塞，需要手动停止
     * 
     * @return 永远不会返回，因为会进入无限循环
     */
    @GetMapping("/infinite-loop")
    public String infiniteLoop() {
        // 故意创建无限循环
        // 用于模拟线程阻塞场景
        String now = LocalDateTime.now().format(FORMATTER);
        System.out.println("无限循环接口被调用，进入无限循环，时间：" + now);
        while (true) {
            // 这个循环永远不会结束
            // 用于测试调试器如何处理无限循环
            System.out.println("无限循环中...");
            try {
                // 添加一个小的延迟，避免CPU占用过高
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // 忽略中断异常
            }
        }
    }
} 