package com.vir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = "com.vir")
@RestController
public class VirBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirBackendApplication.class, args);
	}
}