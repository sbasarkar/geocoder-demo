package com.geocoder.demo.model;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoCoding {
	String status;

	@JsonProperty(value = "results")
	List<GeoCodingResult> geoCodingResults;

	@Data
	public static class GeoCodingResult {
		@JsonProperty(value = "formatted_address")
		String formattedAddress;

		Geometry geometry;
	}
	
	@Data
	public static class Geometry {
		Location location;
	}
	
	@Data
	public static class Location {
		double lat;
		double lng;
	}
	
}