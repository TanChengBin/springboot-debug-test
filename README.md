# Spring Boot Debug Test Project

## 项目简介

这是一个用于模拟 JVM 远程调试的 Spring Boot 项目，特别适合结合 Cpolar 内网穿透工具进行远程调试测试。项目包含正常的接口和故意设计的异常接口，便于测试各种调试场景。通过本项目，开发者可以学习和实践如何在无法直接访问的内网环境中进行远程调试。

## 主要特性

- 🚀 基于 Spring Boot 2.7.18 和 Java 17
- 🔍 提供完整的远程调试配置示例
- 🌐 支持与 Cpolar 内网穿透工具集成
- 🧪 包含多种测试场景（正常接口、异常接口、阻塞接口）
- 📝 详细的中文文档和注释

## 应用场景

- 在家远程调试部署在公司内网的测试环境
- 学习 JVM 远程调试技术
- 快速搭建调试测试环境
- 内网穿透技术实践

## 项目结构

```
springboot-debug-test/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── cpolar/
│       │           └── springbootdebugtest/
│       │               ├── SpringBootDebugTestApplication.java  # 主启动类
│       │               ├── controller/
│       │               │   ├── NormalController.java            # 正常接口控制器
│       │               │   └── BugController.java               # 包含bug的接口控制器
│       │               └── handler/
│       │                   └── GlobalExceptionHandler.java      # 全局异常处理器
│       └── resources/
│           └── application.yml                                  # 配置文件
├── pom.xml                                                      # Maven配置文件
└── README.md                                                    # 项目说明文档
```

## 技术栈

- **Spring Boot**: 2.7.18
- **Java**: 17
- **Maven**: 项目构建工具
- **依赖**: 仅包含 spring-boot-starter-web

## 接口说明

### 正常接口 (`/api/normal`)

1. **应用信息**: `GET /api/normal/info`
   - 返回应用的详细信息
   - 包括版本、作者等信息
   - 用于验证应用正常运行

### 包含Bug的接口 (`/api/bug`)

1. **空指针异常**: `GET /api/bug/null-pointer`
   - 故意抛出 NullPointerException
   - 用于测试空指针异常调试
   - 可以观察异常堆栈信息

2. **无限循环**: `GET /api/bug/infinite-loop`
   - 进入无限循环
   - 用于测试线程阻塞场景
   - 可以学习如何处理线程阻塞问题

## 快速开始

### 系统要求

- JDK 17 或更高版本
- Maven 3.6 或更高版本
- IntelliJ IDEA（推荐用于远程调试）

### 编译项目

```bash
mvn clean compile
```

### 运行项目

```bash
mvn spring-boot:run
```

### 打包项目

```bash
mvn clean package
```

打包后的 jar 文件位于 `target/springboot-debug-test-1.0.0.jar`

### 运行 jar 包

```bash
java -jar target/springboot-debug-test-1.0.0.jar
```

## 远程调试配置

### 启动时开启远程调试

```bash
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005 -jar target/springboot-debug-test-1.0.0.jar
```

参数说明：
- `transport=dt_socket`: 使用 socket 传输
- `server=y`: 作为调试服务器启动
- `suspend=n`: 启动时不暂停等待调试器连接
- `address=*:5005`: 监听所有网络接口的 5005 端口

### IDEA 远程调试配置

1. 在 IDEA 中创建 "Remote JVM Debug" 配置
2. 设置 Host: localhost（本地调试）或 Cpolar 提供的公网地址（远程调试）
3. 设置 Port: 5005（本地调试）或 Cpolar 映射的公网端口（远程调试）
4. 启动调试会话

## 结合 Cpolar 进行内网穿透调试

本项目可以与 Cpolar 内网穿透工具结合使用，实现在公网环境下调试内网服务。具体步骤可参考项目中的 `使用 Cpolar + Remote JVM Debug 进行内网服务器调试.md` 文档。

主要步骤包括：
1. 在内网服务器安装 Cpolar
2. 配置 Cpolar 映射 HTTP 端口（8080）和调试端口（5005）
3. 使用 Cpolar 提供的公网地址进行远程调试

## 测试建议

1. 首先测试正常接口，确保应用正常运行
2. 然后逐个测试包含 bug 的接口，观察异常信息
3. 使用 IDEA 远程调试功能连接应用进行调试
4. 在调试过程中设置断点，观察变量值和调用栈
5. 尝试修复代码中的问题，验证调试效果

## 注意事项

- 项目仅包含 spring-boot-starter-web 依赖，保持简洁
- 所有接口都有详细的中文注释，便于理解
- 包含 bug 的接口会故意抛出异常，这是正常行为，用于测试调试功能
- 无限循环接口会导致线程阻塞，需要手动停止或设置超时处理
- 远程调试端口（5005）在生产环境中应当关闭，以防安全风险

## 贡献指南

欢迎提交 Issue 和 Pull Request 来完善本项目。在提交 PR 前，请确保：
1. 代码风格符合项目规范
2. 新功能或修复有对应的测试用例
3. 所有测试通过
4. 更新相关文档

## 许可证

本项目采用 MIT 许可证，详情请参阅 LICENSE 文件。

## 作者

- **作者**: cpolar
- **版本**: 1.0.0
- **用途**: 模拟 JVM 远程调试场景，特别适合内网穿透调试测试