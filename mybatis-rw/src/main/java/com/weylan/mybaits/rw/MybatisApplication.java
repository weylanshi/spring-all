package com.weylan.mybaits.rw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MybatisApplication {


  public static void main(String[] args) {
    SpringApplication.run(MybatisApplication.class, args);
  }

}
