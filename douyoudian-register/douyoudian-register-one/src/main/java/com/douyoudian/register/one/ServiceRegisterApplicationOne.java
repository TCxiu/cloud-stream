package com.douyoudian.register.one;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceRegisterApplicationOne {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegisterApplicationOne.class, args);
	}
}
