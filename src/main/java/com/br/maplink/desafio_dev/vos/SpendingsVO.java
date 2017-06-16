package com.br.maplink.desafio_dev.vos;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.br.maplink.desafio_dev.dto.search.GeoData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SpendingsVO {
	
	
	private Integer duration;	
	private Double distance;
	private BigDecimal fuelCost;
	private BigDecimal totalCost;
	private List<GeoData> geoDatas;
	private Integer statusCode = 200;
	private String message;
	
	private DecimalFormat df;
	
	public SpendingsVO() {
		 df = new DecimalFormat("#.##");
	}

	public SpendingsVO(Integer statusCode, String message) {
		super();
		this.statusCode = statusCode;
		this.message = message;		
	}

	public Integer getDuration() {
		if(duration != null && duration > 0){
			return duration / 3600;
		}
		
		return duration;
	}
	
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
	public String getDistance() {
	
		if(distance != null && distance > 0){			
			return df.format(distance / 1000);
		}
	
		return distance + "";
	}
	
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	
	public String getFuelCost() {
		if(fuelCost != null){
			return df.format(fuelCost.doubleValue());
		}
		
		return fuelCost+"";
	}
	
	public void setFuelCost(BigDecimal fuelCost) {		
		this.fuelCost = fuelCost;
	}
	
	public String getTotalCost() {
		if(totalCost != null){
			return df.format(totalCost.doubleValue());
		}
		
		return totalCost+"";
	}
	
	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}
	
	public List<GeoData> getGeoDatas() {
		return geoDatas;
	}

	public void setGeoDatas(List<GeoData> geoDatas) {
		this.geoDatas = geoDatas;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public SpendingsVO nullObject(){
		this.duration = 0;	
		this.distance = 0d;
		this.fuelCost = BigDecimal.ZERO;
		this.totalCost = BigDecimal.ZERO;
		this.geoDatas = new ArrayList<>();
		
		return this;
	}

	@Override
	public String toString() {
		return "SpendingsVO [duration=" + duration + ", distance=" + distance + ", fuelCost=" + fuelCost
				+ ", totalCost=" + totalCost + ", geoDatas=" + geoDatas + ", statusCode=" + statusCode + ", df=" + df
				+ "]";
	}
}
