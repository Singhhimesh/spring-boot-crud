package com.bagisto.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BagistoApplication {
	public static void main(String[] args) {
		SpringApplication.run(BagistoApplication.class, args);

		System.out.print("http://localhost:3000/users");
	}
}
