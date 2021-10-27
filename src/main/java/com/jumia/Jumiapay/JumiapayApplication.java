package com.jumia.Jumiapay;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.jumia.*")
public class JumiapayApplication {

	public static void main(String[] args) {
		SpringApplication.run(JumiapayApplication.class, args);
		
		System.out.println("Hello Jumia!");
	}

}
