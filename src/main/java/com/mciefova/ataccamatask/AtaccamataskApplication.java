package com.mciefova.ataccamatask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class AtaccamataskApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtaccamataskApplication.class, args);
	}

}
