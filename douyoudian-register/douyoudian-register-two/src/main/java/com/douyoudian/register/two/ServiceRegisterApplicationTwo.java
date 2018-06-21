package com.douyoudian.register.two;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceRegisterApplicationTwo {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegisterApplicationTwo.class, args);
	}
}
