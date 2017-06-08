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
public class Prices {

	@JsonProperty("pricesAtHourRange")
	private List<PricesAtHourRange> pricesAtHourRange;

	public List<PricesAtHourRange> getPricesAtHourRange() {
		return pricesAtHourRange;
	}

	public void setPricesAtHourRange(List<PricesAtHourRange> pricesAtHourRange) {
		this.pricesAtHourRange = pricesAtHourRange;
	}
	
	public BigDecimal getTotalPrices(){
		BigDecimal totalPrice = BigDecimal.ZERO;
		if(this.pricesAtHourRange != null){
			for (PricesAtHourRange pricesAtHourRange : pricesAtHourRange) {
				totalPrice = totalPrice.add(pricesAtHourRange.getPrice());
			}
		}
		
		return totalPrice;
	}

	@Override
	public String toString() {
		return "Prices [pricesAtHourRange=" + pricesAtHourRange + "]";
	}
}
