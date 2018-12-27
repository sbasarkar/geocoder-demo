package com.geocoder.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.geocoder.demo.model.GeoCoding;
import com.geocoder.demo.model.GeoCoding.GeoCodingResult;
import com.geocoder.demo.model.GeoCoding.Location;
import com.geocoder.demo.model.LatLon;
import com.google.common.util.concurrent.RateLimiter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GoogleGeoCoderImpl {

	@Value("${google.geo.location.url}")
	private String geoUrl;

	@Value("${google.geo.api.key}")
	private String apiKey;
	
	@Value("${google.geo.rate.limit}")
	private double rateLimit = 1;

	@Autowired
	RestTemplate restTemplate;
	
	RateLimiter rateLimiter = RateLimiter.create(rateLimit);

	public LatLon getGeoLocation(String address) {

		Assert.hasText(address, "Address missing");
		
		rateLimiter.acquire(1);
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(geoUrl)
				.queryParam("address", address)
				.queryParam("key", apiKey);
		log.info("Geo API url {}", builder.toUriString());

		GeoCoding geoCoding = restTemplate.getForObject(builder.toUriString(), GeoCoding.class);
		log.info("GeoCoding response :: {} ", geoCoding.toString());
		
		if (geoCoding.getStatus().equals("OK") && !geoCoding.getGeoCodingResults().isEmpty()) {
			GeoCodingResult geoResult = geoCoding.getGeoCodingResults().get(0);
			Location loc = geoResult.getGeometry().getLocation();
			return new LatLon(loc.getLat(), loc.getLng());
		}

		return new LatLon();
	}
}
