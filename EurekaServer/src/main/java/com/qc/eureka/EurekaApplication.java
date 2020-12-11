package com.qc.eureka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@Slf4j
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
public class EurekaApplication {

    public static void main(String[] args) {
        log.info("EurekaServer Started...");
        SpringApplication.run(EurekaApplication.class, args);
        log.info("EurekaServer Success...");
    }

}
