package edu.utdallas.pages;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Pages11Application {

	public static void main(String[] args) {
		System.out.println("HERE");
		System.out.println(System.getenv("S3_KEY"));
		SpringApplication.run(Pages11Application.class, args);
	}

}
