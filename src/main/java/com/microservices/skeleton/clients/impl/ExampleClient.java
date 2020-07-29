package com.microservices.skeleton.clients.impl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.microservices.skeleton.clients.IExampleClient;
import com.microservices.skeleton.responses.ResponseBean;

@Repository
public class ExampleClient implements IExampleClient {

	@Value(value = "${application.client.url}")
	private String clientUrl;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public String getData() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>("body", headers);
		ResponseEntity<ResponseBean<String>> responseEntity = restTemplate.exchange(clientUrl + "data", HttpMethod.GET, entity, new ParameterizedTypeReference<ResponseBean<String>>() {
		});

		return responseEntity.getBody().getPayload();
	}
}