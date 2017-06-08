package com.br.maplink.desafio_dev.dto.route;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author felipe.pelichero
 *
 * 8 de jun de 2017
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Route {
	
	@JsonProperty("routeIdentification")
	private String routeIdentification;
	
	@JsonProperty("summary")
	private Summary summary;

	public String getRouteIdentification() {
		return routeIdentification;
	}

	public void setRouteIdentification(String routeIdentification) {
		this.routeIdentification = routeIdentification;
	}

	public Summary getSummary() {
		return summary;
	}

	public void setSummary(Summary summary) {
		this.summary = summary;
	}

	@Override
	public String toString() {
		return "Route [routeIdentification=" + routeIdentification + ", summary=" + summary + "]";
	}	
}