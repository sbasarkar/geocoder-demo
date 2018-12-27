package com.geocoder.demo.controller;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.geocoder.demo.model.Address;
import com.geocoder.demo.model.LatLon;
import com.geocoder.demo.service.GoogleGeoCoderImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class GeoCoderController {

	@Autowired
	private GoogleGeoCoderImpl geoCoder;
	
	@PostMapping(path="/geo-code",consumes = "application/json", produces = "application/json" )
	public ResponseEntity<LatLon> getGeoCodingForLoc(@Valid @RequestBody Address address) {
		
		log.info("Get geocoding location for address {}", address);
		return ResponseEntity.status(HttpStatus.OK).body(geoCoder.getGeoLocation(getFormattedAddress(address)));
	}
	
	
	static String getFormattedAddress(Address address) {
		String formattedAddress = "";

		if(StringUtils.isNotEmpty(address.getAddr1())) formattedAddress += address.getAddr1();
		if(StringUtils.isNotEmpty(address.getAddr2())) formattedAddress += " " + address.getAddr2();
		if(StringUtils.isNotEmpty(address.getCity())) formattedAddress += " " + address.getCity();
		if(StringUtils.isNotEmpty(address.getState())) formattedAddress += " " + address.getState();
		if(StringUtils.isNotEmpty(address.getZip())) formattedAddress += " " + address.getZip();
		if(StringUtils.isNotEmpty(address.getCountry())) formattedAddress += " " + address.getCountry();

		return formattedAddress.trim();
	}
}
