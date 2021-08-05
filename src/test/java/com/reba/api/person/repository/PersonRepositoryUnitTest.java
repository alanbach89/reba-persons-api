package com.reba.api.person.repository;

import com.reba.api.person.controller.PersonController;
import com.reba.api.person.enums.DocumentType;
import com.reba.api.person.exception.AdultPersonException;
import com.reba.api.person.exception.AtLeastOneContactDataException;
import com.reba.api.person.help.CountryList;
import com.reba.api.person.model.Person;
import com.reba.api.person.service.PersonService;
import com.reba.api.person.service.PersonServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
class PersonRepositoryUnitTest {



}