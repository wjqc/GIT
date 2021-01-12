package com.qc.cicada;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@EnableFeignClients
public class CicadaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CicadaApplication.class, args);
    }

    @Bean
    public Logger.Level getLog(){
        return Logger.Level.FULL;
    }
}
