package com.qc.cicada;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Slf4j
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@EnableFeignClients
public class CicadaApplication {

    public static void main(String[] args) {
        log.info("<---CicadaApplication is running--->");
        SpringApplication.run(CicadaApplication.class, args);
        log.info("<---CicadaApplication is Success--->");
    }

}
