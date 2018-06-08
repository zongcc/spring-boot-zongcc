package com.zongcc.boot;

import com.zongcc.boot.service.HelloWorldService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.jms.Queue;

/**
 * Created by chunchengzong on 2017-01-06.
 */
//@Configuration
//@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)//禁用自动配置类
//@ComponentScan
@SpringBootApplication //注解等价于以默认属性使用	@Configuration，	@EnableAutoConfiguration和@ComponentScan
@ServletComponentScan //项目中如果需要使用java原生的servlet和filter，可以在类中使用注解实现，主要是配置Druid监控时需要用到
@EnableJms
@EnableCaching
@EnableScheduling
@EnableTransactionManagement
public class Application extends SpringBootServletInitializer implements CommandLineRunner{

    @Autowired
    private HelloWorldService helloWorldService;

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("sample.queue");
    }

    //实现CommandLineRunner，启动调用
    @Override
    public void run(String... strings) throws Exception {
        System.out.println("Application=====start=====");
        System.out.println("Application=====run=====result=====> "+helloWorldService.getHelloMessage());
        System.out.println("Application=====end=====");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
       SpringApplication.run(Application.class,args);
       /* SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF); //禁用banner
        app.run(args);*/
    }

}
