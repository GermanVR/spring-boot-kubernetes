package com.microservices.skeleton.responses;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author German Vazquez Renteria
 * @id GermanVR
 * @url https://github.com/GermanVR
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public abstract class Response<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer status;
	private transient T payload;
	private List<String> messages;

}
