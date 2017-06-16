package com.br.maplink.desafio_dev.dto.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Location {

	@JsonProperty("lat")
	private Double lat;
	
	@JsonProperty("lng")
    private Double lng;
	
	public Location() {
	}
    
    public Location(Double lat, Double lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}



	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}
	
	public String getGeoLocation(){
		return this.lat + "," +this.lng;
	}

	@Override
	public String toString() {
		return "Location [lat=" + lat + ", lng=" + lng + "]";
	}	
}