package com.fse.projmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.fse.projmanagement")
public class ProjManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjManagementServiceApplication.class, args);
	}

}
