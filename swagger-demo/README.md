
## swagger使用步骤

- 引入swagger依赖 `springfox-swagger2` 和 `springfox-swagger-ui`
- 添加@EnableAsync
- 添加SwaggerConfig配置，注入Docket实例
- 浏览器访问 http://ip:port/swagger-ui.html;





## swagger替换为knife4j


### 方法一
- 去调swagger之前引入的依赖，如：`springfox-swagger2` 和 `springfox-swagger-ui`
- 引入依赖：`knife4j-spring-boot-starter`
- 添加@EnableSwagger2WebMvc（@EnableSwagger2这个注解好像也行，区别可以以后再研究）
- 浏览器访问 http://ip:port/doc.html;

注意：这样之前项目的swagger-ui.html就打不开了



## 接口文档权限
- 可以通过Docket的enable控制接口文档是否展示
- knife4j可以通过特殊参数，达到生产环境屏蔽，或者解耦文档权限



## 网关集成Knife4j
[官方文档](https://doc.xiaominfo.com/docs/action/springcloud-gateway)



## 集成接口文档服务
knife4j作者提供的api文档聚合服务