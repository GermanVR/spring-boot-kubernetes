package com.microservices.skeleton.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.skeleton.beans.PersonBean;
import com.microservices.skeleton.services.PersonService;
import com.microservices.skeleton.utilities.impl.ResponseUtil;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * @author German Vazquez Renteria
 * @id GermanVR
 * @url https://github.com/GermanVR
 */
@RestController
//@RequestMapping("/")
@Slf4j
@RefreshScope
public class SkeletonController {

	@Autowired
	private PersonService personaService;

	@Autowired
	private ResponseUtil responseUtil;

	@Value(value = "${timeout}")
	private long timeout;

	@PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<ResponseEntity<PersonBean>> hello(@RequestBody PersonBean person) {
		ResponseEntity<PersonBean> responseEntity = responseUtil.successResponseEntity(personaService.getPerson(person), "Success when consulting the information");
		return Mono.just(responseEntity);
	}

	@GetMapping(path = "/data", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<ResponseEntity<String>> status() {
		log.info("TimeOut is {}", timeout);
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			log.error("Problema con {}", e.getMessage());

		}
		return Mono.just(responseUtil.successResponseEntity("Person Validated!", "Success"));

	}
}