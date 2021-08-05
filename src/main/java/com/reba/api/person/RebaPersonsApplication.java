package com.reba.api.person;

import com.reba.api.person.help.CountryList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class RebaPersonsApplication {

	@RequestMapping("/")
	public String home() {
		return "Hello Reba World";
	}

	public static void main(String[] args) {
		CountryList.getCountryNames();
		SpringApplication.run(RebaPersonsApplication.class, args);
	}
}
