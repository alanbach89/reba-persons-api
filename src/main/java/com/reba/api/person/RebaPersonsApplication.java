package com.reba.api.person;

import com.reba.api.person.enums.DocumentType;
import com.reba.api.person.enums.RelationType;
import com.reba.api.person.help.CountryList;
import com.reba.api.person.model.Person;
import com.reba.api.person.model.Relation;
import com.reba.api.person.repository.PersonRepository;
import com.reba.api.person.repository.RelationRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.List;

@SpringBootApplication
public class RebaPersonsApplication {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private RelationRepository relationRepository;

	public static void main(String[] args) {
		CountryList.getCountryNames();
		SpringApplication.run(RebaPersonsApplication.class, args);
	}

	@Bean
	InitializingBean sendDatabase() {
		return () -> {

			if(personRepository.count() > 0) {
				return;
			}

			List<String> countryNames = CountryList.getCountryNames();

			DocumentType dni = DocumentType.DNI;
			Person p1 = personRepository.save(new Person("Jose", "Manolo", 31, dni, "34345345", countryNames.get(1), "324554423", "asdasd@gmail.com"));
			Person p2 = personRepository.save(new Person("Agustin", "Perez", 31, dni, "43534545", countryNames.get(2), "67867867867", "qwert@gmail.com"));
			Person p3 = personRepository.save(new Person("Sofia", "Sasa", 31, dni, "53454535", countryNames.get(0), "123436789", "tyurti@gmail.com"));
			Person p4 = personRepository.save(new Person("Alan", "Lolo", 31, dni, "78978997", countryNames.get(1), "324554464423", "ghjkl@gmail.com"));
			Person p5 = personRepository.save(new Person("Gustavo", "Garcia", 31, dni, "67856753", countryNames.get(2), "9845634535345", "xcvnn@gmail.com"));

			relationRepository.save(new Relation(p2, p1.getId(), RelationType.fromString("PADRE")));
			relationRepository.save(new Relation(p3, p1.getId(), RelationType.fromString("HERMANO")));
			relationRepository.save(new Relation(p5, p4.getId(), RelationType.fromString("TIO")));
		};
	}
}
