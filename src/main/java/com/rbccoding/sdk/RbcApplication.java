package com.rbccoding.sdk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;

@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.rbccoding.sdk"})
@SpringBootApplication
public class RbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(RbcApplication.class, args);
	}

}
