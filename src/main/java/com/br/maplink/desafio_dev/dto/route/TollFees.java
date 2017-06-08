package com.br.maplink.desafio_dev.dto.route;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author felipe.pelichero
 *
 * 8 de jun de 2017
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class TollFees {
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("prices")
	private List<Prices> prices;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Prices> getPrices() {
		return prices;
	}

	public void setPrices(List<Prices> prices) {
		this.prices = prices;
	}
	
	public BigDecimal getTotalPrice(){
		BigDecimal totalPrices = BigDecimal.ZERO;			
		if(prices != null){
			for (Prices prices : prices) {
				totalPrices = totalPrices.add(prices.getTotalPrices());
			}
		}
		
		return totalPrices;
	}

	@Override
	public String toString() {
		return "ToolFees [name=" + name + ", prices=" + prices + "]";
	}	
}
