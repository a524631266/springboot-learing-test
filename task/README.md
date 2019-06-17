# springboot（2.1.5）与任务方法的相关内容
## 1、 异步任务
也就是在单个用户进行请求的时候对任务进行异步处理
```commandline
对于初学者来说，多个用户对一个controller进行请求会堵塞吗？
这个需要理解web中的 顾虑器，拦截器以及转发器的应用才能解答，这里按揭不表
```
1. 需要开启@EnableAsync
2. 在同步方法上添加注解 @Async
    见项目 aysnc
    可以不用等待就能返回结果
## 2、 简单定时任务
定时任务，使用@Scheduled 任务
1. @EnableScheduling 开启定时任务
2. 在方法上使用 @Scheduled
3. 学习表达式
+  **0 * * * * MON-FRI**  # 星期一到星期五 每次的0秒执行一次

+  **0,1,2,3,4 * * * * MON-FRI** # 星期一到星期五 每次的0,1,2,3,4秒都执行一次
    
    等效于 **0-4 * * * * MON-FRI**
```commandline
    @Scheduled(cron = "0,1,2,3,4 * * * * MON-FRI")
    public void hello() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "hello。。。。");
        }
    }
    =========》  只执行了两次，一次是0秒的时候等待3秒之后执行的一次，第二次为第3 秒的时候执行一次
    =========》 并不会在0 1 2 3 4 秒均执行一次
    2019-06-17 15:46:03hello。。。。
    2019-06-17 15:46:07hello。。。。
```
   ========= 如何解决上面的问题？ ==============
   <br/>使用异步任务解决
```commandline
    @Async
    @Scheduled(cron = "0,1,2,3,4 * * * * MON-FRI")
    public void hello() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "hello。。。。");
        }
    }
     =========》 并会在0 1 2 3 4 秒均执行一次
     2019-06-17 15:49:03hello。。。。
     2019-06-17 15:49:04hello。。。。
     2019-06-17 15:49:05hello。。。。
     2019-06-17 15:49:06hello。。。。
     2019-06-17 15:49:07hello。。。。
```
  
+ / 间隔步长
    **0/4 * * * * MON-FRI**   在星期一☞星期五 每隔4秒执行一次
+ ? 当日 （第四个参数）、 星期（第六个参数） 冲突的时候使用？来避免冲突，类似于*
    
      * * * ? * MON 在第四个参数中不能用 * ，因为不是每天都是星期一 ，所以使得日失效
      * * * 1 * ？ 在表明每月1号执行任务的时候，第六个参数也要使用?
      
+ SUN-SAT 周日到周六  在 springboot的内置定时任务中 0与7代表星期日， 1,2,3,4 分别代表相应的星期
与 **Quartz[1-7 中 1代表周日]** 不一样，需要区别待遇，也就是说springboot内置的更人性化国际化


练习题
【 0 0/5 14,18 * * ？】 每天的 14 与 18 点 ，每隔5分钟执行一次
【 0 15 10 ？ * 1-6】 每个月的周一至周六的10点 15分执行一次
【 0 0 2 ？ * 6L】每个月的最后一个星期六2点执行一次
【 0 0 2 LW * ?】每个月的最后一个工作日2点执行一次
【 0 0 2-4 ？ * 1#1】每个月的第一个周一 2点到4点的整点执行一次  （4#2 代表第二个星期四）
------


## 3、 邮件任务

a) 引入依赖
[官网地址](ihttps://docs.spring.io/spring-boot/docs/2.1.5.RELEASE/reference/htmlsingle/)

```pom.xml
 <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```
b) 配置邮箱的账户和密码
自动配置属性 application.properties
```application.properties
spring.mail.username=524631266@qq.com
# 密码需要自己在第三方登录QQ邮箱，可能存在邮件泄露风险，甚至危害Apple ID安全，建议使用QQ邮箱手机版登录。
# 继续获取授权码登录第三方客户端邮箱 。 生成授权码
spring.mail.password=xxxx
spring.mail.host=smtp.qq.com
spring.mail.properties.mail.smtp.ssl.enable=true
```
c) 测试发送邮箱
```java
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootTestMail {
    @Autowired
    JavaMailSender javaMailSender;
    @Test
    public void contextLoads() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("通知");  // 标题
        message.setText("今晚7:30开会"); // 内容
        message.setTo("62390zll@163.com"); // 接收方
        message.setFrom("524631266@qq.com"); // 来自于哪里，必须要与发送方一致
        javaMailSender.send(message);
    }
}
```
[spring 邮箱官网](https://docs.spring.io/spring/docs/5.1.7.RELEASE/spring-framework-reference/integration.html#mail-usage)
