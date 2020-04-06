package com.si20103262.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class CustomerAccountTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerAccountTrackerApplication.class, args);
	}

}
