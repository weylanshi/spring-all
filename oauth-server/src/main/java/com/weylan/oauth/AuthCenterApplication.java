package com.weylan.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableFeignClients
//@EnableHystrix
public class AuthCenterApplication {

  public static void main(String[] args) {
    SpringApplication.run(AuthCenterApplication.class);
  }

}
