package coderead.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Component
public class Boot02Application {
    // slfj4j
    Logger logger = LoggerFactory.getLogger(Boot02Application.class);
    @PostConstruct
    public void init() {
        logger.info("这是一个普通消息");
        logger.error("这是一个异常消息");
        logger.debug("这是一个调式消息");
    }

    public static void main(String[] args) {
        SpringApplication.run(Boot02Application.class, args);
    }

}
