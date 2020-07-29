package com.microservices.skeleton.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

/**
 * @author German Vazquez Renteria
 * @id GermanVR
 * @url https://github.com/GermanVR
 */
@Getter
@Component
public class AppConfig {

	@Value(value = "${application.id}")
	private String applicationId;
	@Value(value = "${application.type}")
	private String applicationLayer;

	public final String getId() {
		return String.format("%s-%s-", this.getApplicationId(), this.getApplicationLayer());
	}

}
