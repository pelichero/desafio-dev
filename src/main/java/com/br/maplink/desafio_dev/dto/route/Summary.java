package com.br.maplink.desafio_dev.dto.route;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Summary {
	
	@JsonProperty("duration")
	private Integer duration;
	
	@JsonProperty("distance")
	private Double distance;
	
	@JsonProperty("tollFees")
	private List<TollFees> tollFees;

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public List<TollFees> getTollFees() {
		return tollFees;
	}

	public void setTollFees(List<TollFees> tollFees) {
		this.tollFees = tollFees;
	}

	@Override
	public String toString() {
		return "Summary [duration=" + duration + ", distance=" + distance + ", tollFees=" + tollFees + "]";
	}	
}
