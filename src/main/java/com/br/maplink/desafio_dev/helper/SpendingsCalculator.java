package com.br.maplink.desafio_dev.helper;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.br.maplink.desafio_dev.dto.route.Route;
import com.br.maplink.desafio_dev.vos.SpendingsVO;

/**
 * 
 * @author felipe.pelichero
 *
 * 8 de jun de 2017
 */
public class SpendingsCalculator {
	
	private static final Logger logger = LoggerFactory.getLogger(SpendingsCalculator.class);

	private static final Double FUEL_PRICE = 3.29;
	private static final Integer CAR_CONSUME = 8;
	
	public static SpendingsVO calcRouteOutgoings(Route route){
		logger.info(">> Calculando rota <<");
		
		if(route == null){
			throw new IllegalStateException();
		}
		
		SpendingsVO spendings = new SpendingsVO(); 
		
		spendings.setDistance(route.getSummary().getDistance());
		spendings.setDuration(route.getSummary().getDuration());
		BigDecimal fuelCost = calcFuelPriceConsumption(route.getSummary().getDistance());
		spendings.setFuelCost(fuelCost);		
		spendings.setTotalCost(fuelCost.add(new BigDecimal(route.getSummary().getTollFees().stream().mapToDouble(total -> total.getTotalPrice().doubleValue()).sum())));
		
		return spendings;
	}

	private static BigDecimal calcFuelPriceConsumption(Double distance) {
		if(distance == null){
			logger.error("Distancia da rota é Zero.");
			throw new IllegalStateException("Distancia da rota é Zero.");
		}
		
		return new BigDecimal(((distance / 1000) / CAR_CONSUME) * FUEL_PRICE);
	}
	
}
