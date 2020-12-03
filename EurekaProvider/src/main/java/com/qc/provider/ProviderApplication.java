package com.qc.provider;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@Slf4j
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.qc.provider.mapper")
public class ProviderApplication {

    public static void main(String[] args) {
        log.info("EurekaProvider Started...");
        SpringApplication.run(ProviderApplication.class, args);
        log.info("EurekaProvider Success...");
    }

}
