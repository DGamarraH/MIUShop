package com.ea.miushop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

// @Sql({"/employees_schema.sql"})
@EnableScheduling
@SpringBootApplication(scanBasePackages = {"com.ea.*"})
public class MiushopCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiushopCartApplication.class, args);
	}

}
