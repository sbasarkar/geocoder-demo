package com.geocoder.demo.model;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address {

	@NotEmpty(message = "addr1 cannot be blank")
	private String addr1;

	private String addr2;

	@NotEmpty(message = "city cannot be blank")
	private String city;

	@NotEmpty(message = "state cannot be blank")
	private String state;

	private String zip;

	private String country;

}
