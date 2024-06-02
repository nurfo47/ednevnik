package com.tfb.ednevnik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude= {SecurityAutoConfiguration.class})
@ComponentScan(basePackages = {"com.tfb.ednevnik"})
@EntityScan(basePackages = {"com.tfb.ednevnik.model"})
public class EdnevnikApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdnevnikApplication.class, args);
	}

}
