package com.microservices.skeleton.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.skeleton.beans.PersonBean;
import com.microservices.skeleton.clients.IExampleClient;
import com.microservices.skeleton.exceptions.AgeMinorException;
import com.microservices.skeleton.services.PersonService;

/**
 * @author German Vazquez Renteria
 * @id GermanVR
 * @url https://github.com/GermanVR
 */
@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private IExampleClient exampleClient;

	@Override
	public PersonBean getPerson(PersonBean person) {
		if (person.getAge() == null || person.getAge() <= 0 || person.getLastName() == null || person.getName() == null) {
			throw new AgeMinorException("Invalid Person Object");
		}
		if (person.getAge() < 18) {
			throw new AgeMinorException("The person is a minor");
		}
		
		person.setData(exampleClient.getData());
		return person;
	}
}