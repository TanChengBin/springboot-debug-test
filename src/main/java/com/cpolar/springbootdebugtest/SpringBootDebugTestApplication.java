package com.cpolar.springbootdebugtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 主启动类
 * 
 * 该类是Spring Boot应用程序的入口点，用于启动整个应用
 * 主要用于模拟IDEA中的JVM远程调试场景
 * 
 * @author cpolar
 * @version 1.0.0
 * @since 2024-01-01
 */
@SpringBootApplication
public class SpringBootDebugTestApplication {

    /**
     * 应用程序主方法
     * 
     * 该方法启动Spring Boot应用程序，创建Spring容器并启动内嵌的Tomcat服务器
     * 默认情况下，应用将在8080端口启动
     * 
     * @param args 命令行参数，可以用于配置应用启动参数
     */
    public static void main(String[] args) {
        // 启动Spring Boot应用
        SpringApplication.run(SpringBootDebugTestApplication.class, args);
        
        // 打印启动成功信息
        System.out.println("==========================================");
        System.out.println("Spring启动调试测试应用程序已启动！");
        System.out.println("应用程序在端口8080上运行");
        System.out.println("==========================================");
    }
} 