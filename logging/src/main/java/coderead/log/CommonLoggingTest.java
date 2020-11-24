package coderead.log;

import org.apache.commons.logging.Log;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.LogFactoryService;

/**
 * CommonLogging的具体实现怎么看
 * 先看一下Log类,发现 Log只是jcl中自己定义的接口
 * 而这个接口对象是通过工厂方法获取到的.
 * 很显然如果使用log工厂创建一个属于当前环境想要的logFactory具体实例,每一步都是在查找factory,只要有一步查找到factory就直接查找到,后续不会进行 步骤如下
 * a) 静态方法
 * 1. createFactoryStore 用来构建一个空的hashtable(名字叫factories),并通过Class.forName方式创建,有两种方式如下
 *  a) public static final String HASHTABLE_IMPLEMENTATION_PROPERTY =
 *         "org.apache.commons.logging.LogFactory.HashtableImpl";
 *  b) private static final String WEAK_HASHTABLE_CLASSNAME=
 *         "org.apache.commons.logging.impl.WeakHashtable";
 *  2. 初始化当前类的加载器,LogFactory.class.getClassLoader();
 *  // TODO 有个疑惑, Thread.currentThread().getContextClassLoader() 与 LogFactory.class.getClassLoader()有什么区别?
 *
 * b) 查找factory流程
 * 1. 查找当前classLoader,用于在当前线程classLoader中查找实例用的.具体是在查找LogFactory类,getLog返回的参数就是LogFactory
 * 2. (查找1)首先根据配置环境中是否有缓存了factory来查找,一般刚刚启动的应用不会缓存factories,只有在查找出来以后,下次再查找的时候可以直接使用
 * 3. 前面没有中找到,继续通过查找 用户当前应用所处的配置文件 "commons-logging.properties" 以及 jvm环境中"use_tccl"=false,这样的属性来切换 两种上下文
 * 4. 通过当前 org.apache.commons.logging.LogFactory 是否存在系统属性来加载factory
 * 5. (查找2)通过 META-INF/services/org.apache.commons.logging.LogFactory 中查找文件(这里只是简单地遍历文件,而不是利用SPI的方式,估计SPI是其之后出来的新特性)来查找LogFactory
 * 6. (查找3)如果以上都没有找到factory,那么会通过判断 "commons-logging.properties"是否有文件并且查看其文件内是否有org.apache.commons.logging.LogFactory属性来判断
 * 7. (查找4)以上都没有就用默认(default)的"org.apache.commons.logging.impl.LogFactoryImpl"来创建LogFactory实例
 * 8. 最后通过线程级别的classLoader以及查找到的factory来缓存,同时与(查找1)进行合并处理,保证实例的单一
 *
 * 还有个问题,如果用户创建的LOG不是static的那么会不会出现并发问题?
 *
 * @see org.apache.commons.logging.impl.LogFactoryImpl
 */
public class CommonLoggingTest {
    private static Log log = LogFactory.getLog(CommonLoggingTest.class);

    public static void main(String[] args) {
        log.debug("asd");
    }
    /**
     *
     * 剖析 LogFactoryImpl ,并查看初始化方法,以及相应的getLog方法.不过没有getLog方法,因为是JCL LogFactory通过门面方式转发了getInstance(clazz)
     * 方法 Spring LogFactoryService 为
      * @see org.apache.commons.logging.impl.LogFactoryImpl#getInstance(Class)
     * @see LogFactoryService
     */
    public void springjcl(){
        // jcl 默认是走 LogFactoryService体系的
        // LogFactoryService本质是继承与LogFactory（common上的）
//        LogFactoryService;
        // 核心方法,通过adapter适配一个制定的
//        @Override
//        public Log getInstance(String name) {
//            return LogAdapter.createLog(name);
//        }
//        switch (logApi) {
        // 第一步适配 LOG4J
//            case LOG4J:
//                return LogAdapter.Log4jAdapter.createLog(name);
//            case SLF4J_LAL:
        // 第二步适配 Slf4jAdapter本地的
//                return LogAdapter.Slf4jAdapter.createLocationAwareLog(name);
//            case SLF4J:
//                return LogAdapter.Slf4jAdapter.createLog(name);
        // 第三步 适配JUL
//            default:
//                // Defensively use lazy-initializing adapter class here as well since the
//                // java.logging module is not present by default on JDK 9. We are requiring
//                // its presence if neither Log4j nor SLF4J is available; however, in the
//                // case of Log4j or SLF4J, we are trying to prevent early initialization
//                // of the JavaUtilLog adapter - e.g. by a JVM in debug mode - when eagerly
//                // trying to parse the bytecode for all the cases of this switch clause.
//                return LogAdapter.JavaUtilAdapter.createLog(name);
//        }
        // 同时通过适配模式中的类适配
        // 其中 logApi是通过static静态块来决定的,因此通过装载各个

        /**
         * 先后顺序
         * LOG4J_SPI(log4j2.x) -> SLF4J_SPI -> SLF4J_API -> LogApi.JUL
         * 即使通过加载多个日志实现框架,也能中找到匹配的日志框架.最不济的是使用JUL logging
         *
         */
    }

    // 细节 判断一个类是否存在需要用className, false, LogAdapter.class.getClassLoader()来pandan
//    private static boolean isPresent(String className) {
//        try {
//            Class.forName(className, false, LogAdapter.class.getClassLoader());
//            return true;
//        }
//        catch (ClassNotFoundException ex) {
//            return false;
//        }
//    }
    /**
     * slf4j原理
     */
}
