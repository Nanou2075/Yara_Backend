package com.nanou.yaraBank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class YaraBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(YaraBankApplication.class, args);
	}

}
