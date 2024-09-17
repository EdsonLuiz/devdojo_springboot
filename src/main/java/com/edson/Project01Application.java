package com.edson;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Project01Application {

	public static void main(String[] args) {
		var applicationContext = SpringApplication.run(Project01Application.class, args);
		System.out.println("==== START BEAN CONTEXT ====");
		Arrays.stream(applicationContext.getBeanDefinitionNames())
			.forEach(System.out::println);
			System.out.println("==== END BEAN CONTEXT ====");
	}

}
