package com.geocoder.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LatLon {
	
	private double latitude = 0.0;
	
	private double longitude = 0.0;
}
