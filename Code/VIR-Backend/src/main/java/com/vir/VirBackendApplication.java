package com.vir;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class VirBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirBackendApplication.class, args);
	}
	
	@Value("${spring.application.name}")
	private String name;
	@RequestMapping("/test")
	public String nameTest(){
		return name;
	}
}
