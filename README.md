# 西宁野生动物园论坛设计

技术栈：

- SpringBoot
- MybatisPlus
- SpringSecurity
- EasyExcel
- Swagger2
- Redis
- Echarts
- Vue
- ElementUI

## 项目结构

- [Xining-main](https://github.com/gjkt2001/Xining-main) ：项目的Java后端
- [Xining-admin-vue](https://github.com/gjkt2001/Xining-admin-vue)：项目的Vue前端-后台（管理）
- [Xining-forum-vue](https://github.com/gjkt2001/Xining-forum-vue)：项目的Vue前端-前台（展示）

## 启动

### 后端

IDEA演示：

1. 在数据库工具（如Navicat Premium）中运行sql文件或者直接复制到命令列界面中运行。

   ![image-20240105155858164](https://cdn.jsdelivr.net/gh/xiaoyangzi2001/Imgs/img/image-20240105155858164.png)

2. 分别配置后台、前台的application.yml配置文件

![image-20240105160107276](https://cdn.jsdelivr.net/gh/xiaoyangzi2001/Imgs/img/image-20240105160107276.png)

3. 启动后台和前台的SpringBootApplication程序

![image-20240105160358154](https://cdn.jsdelivr.net/gh/xiaoyangzi2001/Imgs/img/image-20240105160358154.png)

注意前台因为使用了Redis缓存浏览量，所以需要自行下载redis并打开。

![image-20240105160528718](https://cdn.jsdelivr.net/gh/xiaoyangzi2001/Imgs/img/image-20240105160528718.png)

4. 启动后正常显示端口号，或没报错表示启动成功

![image-20240105160658432](https://cdn.jsdelivr.net/gh/xiaoyangzi2001/Imgs/img/image-20240105160658432.png)

前台swagger接口文档：[前台接口文档](http://localhost:8989/swagger-ui.html)

![image-20240110170929516](https://cdn.jsdelivr.net/gh/xiaoyangzi2001/Imgs/img/image-20240110170929516.png)

后台swagger接口文档：[后台接口文档](http://localhost:7777/swagger-ui.html)

![image-20240110171003384](https://cdn.jsdelivr.net/gh/xiaoyangzi2001/Imgs/img/image-20240110171003384.png)

### 前端

VSCode演示：

1. 确保正常安装**NodeJs**

2. 没有就去下载

   [Index of /dist/ (nodejs.org)](https://nodejs.org/dist/)
   [下载 | Node.js 中文网 (nodejs.cn)](https://nodejs.cn/download/)

   启动项目报异常错误可以尝试用cnpm。

   - npm：官方提供包管理工具，指向国外服务器。
   - cnpm：淘宝镜像，指向国内服务器。

3. 在前端项目路径下安装依赖(二个都要安)

![image-20240105161430558](https://cdn.jsdelivr.net/gh/xiaoyangzi2001/Imgs/img/image-20240105161430558.png)

```shell
npm install
```

4. 启动Vue项目

![image-20240105161546079](https://cdn.jsdelivr.net/gh/xiaoyangzi2001/Imgs/img/image-20240105161546079.png)

![image-20240105161627443](https://cdn.jsdelivr.net/gh/xiaoyangzi2001/Imgs/img/image-20240105161627443.png)

```shell
npm run dev
```

### 启动完成

![image-20240105162014943](https://cdn.jsdelivr.net/gh/xiaoyangzi2001/Imgs/img/image-20240105162014943.png)

![image-20240105162037692](https://cdn.jsdelivr.net/gh/xiaoyangzi2001/Imgs/img/image-20240105162037692.png)

## 检测代码

![img](https://cdn.jsdelivr.net/gh/xiaoyangzi2001/Imgs/img/image-20240104111325510.jpeg)

## 有意思

1. **Spring Security + JWT + RBAC 模型**：
    - **Spring Security**：Spring Security是一个强大的身份验证和授权框架，用于处理用户身份验证、授权和保护应用程序的安全性。在论坛系统中，Spring Security可以用于实现用户的身份验证和授权，确保只有经过身份验证的用户才能访问受保护的资源。
    - **JWT（JSON Web Token）**：JWT是一种用于安全传输信息的开放标准。在这种情况下，JWT用于生成和验证用户的身份信息，以便用户在登录后获得访问令牌，该令牌包含了用户的信息和权限，以供后续请求验证使用。
    - **RBAC 模型**：基于角色的访问控制（RBAC）模型用于管理用户的角色和权限。通过RBAC，不同用户可以分配不同的角色，每个角色具有特定的权限。使用五表联查等方式，你可以实现将用户、角色、权限等信息存储在数据库中，并进行高效的权限控制。
2. **子评论的树形结构**：
    - 为了实现子评论的功能，通常会使用递归或其他方式来构建树形结构，其中每个评论可以包含子评论。这允许用户回复其他用户的评论，并构建评论的嵌套结构，以便在UI中正确呈现。
    - 使用Java 8中的Stream流是一种有效的方法，可以用来处理树形结构数据，使其更容易进行查询和呈现。你可以使用递归或迭代方式来构建这种树形结构，以及对树的操作。
3. **文章浏览量的 Redis 缓存**：
    - 在高流量的网站上，频繁更新数据库中的浏览次数可能导致性能问题。为了解决这个问题，你可以将文章的浏览量存储在Redis等缓存中。
    - 定时任务可以定期将Redis中的浏览次数更新到MySQL数据库，以确保数据的持久性，并减少频繁数据库更新的开销。这有助于提高服务器和数据库的性能，同时保持数据的一致性。
4. **AOP 增强代码**：
    - 利用AOP（面向切面编程），你可以在代码中标记注解，然后实现通用的横切关注点，如日志记录、性能监控、异常处理等。
	- 通过AOP，你可以实现在标记的方法执行前或执行后添加额外的操作，这有助于提高代码的可维护性和可读性。在这种情况下，你可以使用AOP来记录日志信息，以便更容易调试和监控应用程序。



