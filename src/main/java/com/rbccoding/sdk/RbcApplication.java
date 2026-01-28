package com.rbccoding.sdk;

import com.rbccoding.sdk.service.StockDataService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;

@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.rbccoding.sdk"})
@SpringBootApplication
public class RbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(RbcApplication.class, args);
	}

    @Bean
    CommandLineRunner init(StockDataService service) {
        return args -> {
            File file = new File("src/main/resources/data/dow_jones_index.csv");
            if (file.exists()) {
                service.bulkInsert(file, "1");
            }
        };
    }


}
