package com.br.maplink.desafio_dev.dto.route;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author felipe.pelichero
 *
 * 8 de jun de 2017
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class PricesAtHourRange {

	@JsonProperty("startHour")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm:ss")
    private Date startHour;
    
	@JsonProperty("endHour")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm:ss")
	private Date endHour;
	
	@JsonProperty("price")
    private BigDecimal price;
	
    public Date getStartHour() {
		return startHour;
	}
	
    public void setStartHour(Date startHour) {
		this.startHour = startHour;
	}
	
	public Date getEndHour() {
		return endHour;
	}
	
	public void setEndHour(Date endHour) {
		this.endHour = endHour;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "PricesAtHourRange [startHour=" + startHour + ", endHour=" + endHour + ", price=" + price + "]";
	}
}
