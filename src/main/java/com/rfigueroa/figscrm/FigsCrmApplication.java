package com.rfigueroa.figscrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.DependsOn;

@SpringBootApplication
@DependsOn("databaseInitializer")
public class FigsCrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(FigsCrmApplication.class, args);
	}

}
