package com.br.maplink.desafio_dev.vos;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import com.br.maplink.desafio_dev.dto.search.GeoData;

public class SpendingsVO {
	
	
	private Integer duration;	
	private Double distance;
	private BigDecimal fuelCost;
	private BigDecimal totalCost;
	private List<GeoData> geoDatas;
	private String statusCode = "200";
	private String message;
	
	private DecimalFormat df;
	
	public SpendingsVO() {
		 df = new DecimalFormat("#.##");
	}

	public SpendingsVO(String statusCode, String message) {
		super();
		this.statusCode = statusCode;
		this.message = message;
	}

	public Integer getDuration() {
		if(duration != null){
			return duration / 3600;
		}
		
		return duration;
	}
	
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
	public String getDistance() {
		if(distance != null){			
			return df.format(distance / 1000);
		}
		
		return df.format(distance);
	}
	
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	
	public String getFuelCost() {
		return df.format(fuelCost.doubleValue());
	}
	
	public void setFuelCost(BigDecimal fuelCost) {		
		this.fuelCost = fuelCost;
	}
	
	public String getTotalCost() {
		return df.format(totalCost.doubleValue());
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

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "SpendingsVO [duration=" + duration + ", distance=" + distance + ", fuelCost=" + fuelCost
				+ ", totalCost=" + totalCost + ", geoDatas=" + geoDatas + ", statusCode=" + statusCode + ", df=" + df
				+ "]";
	}
}
