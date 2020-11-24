package coderead.log;
/**
 * @Copyright 源码阅读网 http://coderead.cn
 */

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
///**
// * @author 鲁班大叔
// * @date 2020
// */
//public class LogTest {
//
//    @Test
//    public void JclTest() {
//        Log log = LogFactory.getLog(LogTest.class);
//        log.info("普通 消息");
//        log.error("异常消息");
//    }
//
//    @Test
//    public void slf4jTest() {
//        Logger log = LoggerFactory.getLogger(LogTest.class);
//        log.info("普通 消息");
//        log.error("异常消息");
//    }
//
//    @Test
//    public void julTest() {
//    }
//
//    @Test
//    public void springTest() {
//        ClassPathXmlApplicationContext
//                context = new ClassPathXmlApplicationContext("spring.xml");
//        context.start();
//        //spring-> jcl-->jcl-over-slf4j(桥接器)-->slf4j-->log4j-slf4j-impl(适配器)->log4j2
//    }
//}
