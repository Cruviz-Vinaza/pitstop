package com.formula1.pitstop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //Es una combinación de tres anotaciones en una sola
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
public class PitstopApplication {

	public static void main(String[] args) {
		SpringApplication.run(PitstopApplication.class, args);
	}

}
