# Spring Boot Debug Test 项目接口文档

> 本项目提供3个RESTful API接口，用于Spring Boot远程调试测试。包含1个正常接口和2个异常接口，适合在IDEA中进行远程调试练习。所有接口都有"/api"前缀。

## 接口概览

| 接口类型 | 接口路径 | 请求方式 | 功能描述 |
|---------|---------|---------|---------|
| 正常接口 | `/api/normal/info` | GET | 获取应用基本信息 |
| 异常接口 | `/api/bug/null-pointer` | GET | 模拟空指针异常 |
| 异常接口 | `/api/bug/infinite-loop` | GET | 模拟无限循环阻塞 |

---

## 1. 应用信息接口

### 接口详情
- **请求方式**：GET
- **请求路径**：`/api/normal/info`
- **请求参数**：无
- **响应格式**：JSON
- **功能描述**：获取应用程序的基本信息，包括名称、版本、作者等

### 请求示例
```bash
curl -X GET "http://localhost:8080/api/normal/info"
```

### 响应示例
```json
{
  "名称": "springboot-debug-test",
  "版本": "1.0.0",
  "作者": "cpolar",
  "描述": "Spring Boot项目用于模拟IDEA远程调试",
  "当前时间": "2025-07-30 17:05:00"
}
```

### 测试用例

#### 用例1：正常调用测试
**测试目的**：验证接口正常返回应用信息
**测试步骤**：
1. 启动Spring Boot应用
2. 发送GET请求到 `/api/normal/info`
3. 检查响应状态码是否为200
4. 验证返回的JSON包含所有必要字段

**预期结果**：
- 状态码：200 OK
- 响应包含：名称、版本、作者、描述、当前时间
- 时间格式：yyyy-MM-dd HH:mm:ss

#### 用例2：调试断点测试
**测试目的**：在IDEA中设置断点进行调试
**测试步骤**：
1. 在IDEA中打开 `NormalController.java`
2. 在 `getAppInfo()` 方法第35行设置断点
3. 启动远程调试模式
4. 发送请求到 `/api/normal/info`
5. 观察断点触发，检查变量值

**调试要点**：
- 观察 `result` Map的构建过程
- 检查 `LocalDateTime.now()` 的返回值
- 验证时间格式化是否正确

---

## 2. 空指针异常接口

### 接口详情
- **请求方式**：GET
- **请求路径**：`/api/bug/null-pointer`
- **请求参数**：无
- **响应格式**：异常信息
- **功能描述**：故意抛出NullPointerException，用于调试异常处理

### 请求示例
```bash
curl -X GET "http://localhost:8080/api/bug/null-pointer"
```

### 异常响应示例
```json
{
  "状态码": 500,
  "消息": "系统出错，请联系管理员",
  "时间": "2025-07-30 17:05:00"
}
```

### 测试用例

#### 用例1：异常抛出测试
**测试目的**：验证接口正确抛出空指针异常
**测试步骤**：
1. 启动Spring Boot应用
2. 发送GET请求到 `/api/bug/null-pointer`
3. 检查响应状态码是否为500
4. 验证异常信息格式是否符合全局异常处理器的格式

**预期结果**：
- 状态码：500 Internal Server Error
- 响应包含：状态码、消息、时间
- 消息内容："系统出错，请联系管理员"

#### 用例2：调试异常堆栈测试
**测试目的**：在IDEA中调试异常抛出过程
**测试步骤**：
1. 在IDEA中打开 `BugController.java`
2. 在 `nullPointerException()` 方法第32行设置断点
3. 启动远程调试模式
4. 发送请求到 `/api/bug/null-pointer`
5. 观察断点触发，单步执行到异常抛出点

**调试要点**：
- 观察 `nullString` 变量的值为null
- 在调用 `nullString.toString()` 时观察异常抛出
- 检查异常堆栈信息
- 验证全局异常处理器是否正常工作

#### 用例3：全局异常处理测试
**测试目的**：验证GlobalExceptionHandler是否正确处理异常
**测试步骤**：
1. 在 `GlobalExceptionHandler.java` 中设置断点
2. 发送请求到 `/api/bug/null-pointer`
3. 观察异常是否被全局处理器捕获
4. 检查返回的错误信息格式

---

## 3. 无限循环接口

### 接口详情
- **请求方式**：GET
- **请求路径**：`/api/bug/infinite-loop`
- **请求参数**：无
- **响应格式**：无（线程阻塞）
- **功能描述**：进入无限循环，模拟线程阻塞场景

### 请求示例
```bash
curl -X GET "http://localhost:8080/api/bug/infinite-loop"
```

### 测试用例

#### 用例1：线程阻塞测试
**测试目的**：验证接口进入无限循环状态
**测试步骤**：
1. 启动Spring Boot应用
2. 发送GET请求到 `/api/bug/infinite-loop`
3. 观察请求是否一直处于pending状态
4. 检查服务器日志输出"无限循环中..."

**预期结果**：
- 请求一直处于pending状态
- 服务器日志持续输出"无限循环中..."
- 线程被阻塞，无法响应其他请求

#### 用例2：调试无限循环测试
**测试目的**：在IDEA中调试无限循环的执行过程
**测试步骤**：
1. 在IDEA中打开 `BugController.java`
2. 在 `infiniteLoop()` 方法的while循环内设置断点
3. 启动远程调试模式
4. 发送请求到 `/api/bug/infinite-loop`
5. 观察断点重复触发

**调试要点**：
- 观察while(true)循环的执行
- 检查Thread.sleep(1000)的调用
- 验证循环变量和条件
- 学习如何识别和调试无限循环

#### 用例3：线程监控测试
**测试目的**：监控无限循环对系统资源的影响
**测试步骤**：
1. 使用JConsole或VisualVM连接应用
2. 发送请求到 `/api/bug/infinite-loop`
3. 观察线程状态和CPU使用率
4. 手动终止阻塞的线程

**监控要点**：
- 观察线程状态变为RUNNABLE
- 检查CPU使用率是否升高
- 验证Thread.sleep是否有效降低CPU占用
- 学习如何识别和终止问题线程

---

## 调试环境配置

### IDEA远程调试配置
1. **启动参数配置**：
   ```bash
   java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -jar springboot-debug-test-1.0.0.jar
   ```

2. **IDEA配置**：
   - Run → Edit Configurations → + → Remote JVM Debug
   - Host: localhost
   - Port: 5005
   - Debugger mode: Attach to remote JVM

### 断点设置建议
- **正常接口**：在 `NormalController.getAppInfo()` 方法中设置断点，访问 `/api/normal/info`
- **空指针异常**：在 `BugController.nullPointerException()` 方法中设置断点，访问 `/api/bug/null-pointer`
- **无限循环**：在 `BugController.infiniteLoop()` 的while循环中设置断点，访问 `/api/bug/infinite-loop`

### 调试技巧
1. **异常调试**：使用Step Over和Step Into观察异常抛出过程
2. **循环调试**：使用条件断点避免无限触发
3. **线程调试**：使用Thread Dump分析线程状态
4. **变量监控**：使用Watch窗口观察关键变量

---

## 注意事项

1. **无限循环接口**：该接口会导致线程阻塞，测试后需要手动终止应用或重启
2. **异常处理**：确保GlobalExceptionHandler正确配置，避免异常信息泄露
3. **资源监控**：长时间运行无限循环接口可能影响系统性能
4. **调试安全**：在生产环境中不要启用远程调试功能

---

## 版本信息
- **项目版本**：1.0.0
- **Spring Boot版本**：3.x
- **Java版本**：17+
- **更新时间**：2025-07-30