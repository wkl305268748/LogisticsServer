package com.kenny.service.logistics;

import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@MapperScan({"com.kenny.service.logistics.mapper","com.baomidou.mybatisplus.samples.quickstart.mapper"})
@SpringBootApplication
 //启动入口
public class Application {

    private static Logger logger = Logger.getLogger(Application.class);

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Application.class, args);
        logger.info("============= SpringBoot Start Success =============");
    }
}
