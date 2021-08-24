package com.qc.provider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.qc.provider.mapper")
public class ProviderApplication {

    public static void main(String[] args) {
        log.info("<---ProviderApplication is running--->");
        SpringApplication.run(ProviderApplication.class, args);
        log.info("<---ProviderApplication is Success--->");
    }

}
