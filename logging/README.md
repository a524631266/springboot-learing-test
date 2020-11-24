1. logger日志

jul -> log4j2
```xml
<!--   log4j2 (log4j-core)包,用来适配 包,也就是会自动包含slf4j包  优先级比  log4j 包高   -->
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-slf4j-impl</artifactId>
    <version>2.13.3</version>
</dependency>
<!--   log4j 包-->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-log4j12</artifactId>
    <version>1.7.30</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>5.3.1</version>
</dependency>
````
jcl -> slf4j -> log4j2(1 version)
### 
```xml

<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-log4j12</artifactId>
    <version>1.7.30</version>
</dependency>

<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>5.3.1</version>
</dependency>
```
jcl -> slf4j -> log4j(1 version)


### 桥接
桥接的核心目标是 抽离实现和抽象的区别.也就是说抽象的部分可以独立变化,
实现的部分也可以独立编号,适配器是一对一适配.

在日志体系中，是通过 jcl/jul/log4j/来转接到slf4j接口上。

具体实现是通过LogFactory类实现的。在各个桥接包中定义一个相同名称的LogFactory
LOG4J 通过 LocationAwareLogger

SLF4JBridgeHandler
JUL 通过 jul-to-slf4j 

桥接的作用是对实现的桥接。比如通过应用的接口来实现桥接。

### 循环依赖