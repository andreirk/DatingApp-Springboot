package com.kabdi.springjwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class SpringjwtApplication {


	public static void main(String[] args) {
		SpringApplication.run(SpringjwtApplication.class, args);
	}
}
