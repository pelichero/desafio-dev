package com.br.maplink.desafio_dev.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.br.maplink.desafio_dev.dto.route.Route;
import com.br.maplink.desafio_dev.dto.search.GeoData;
import com.br.maplink.desafio_dev.helper.SpendingsCalculator;
import com.br.maplink.desafio_dev.services.GeoCodeServices;
import com.br.maplink.desafio_dev.services.RouteServices;
import com.br.maplink.desafio_dev.vos.Address;
import com.br.maplink.desafio_dev.vos.SpendingsVO;

/**
 * 
 * @author felipe.pelichero
 *
 * 8 de jun de 2017
 */
@Controller
public class RouteDetailController {

	private static final String ERROR_CODE = "500";

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private GeoCodeServices geoCodeServices;
	
	private RouteServices routeServices;
	
	@Autowired
	public RouteDetailController(GeoCodeServices geoCodeServices, RouteServices routeServices) {
		super();
		this.geoCodeServices = geoCodeServices;
		this.routeServices = routeServices;
	}	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String calculatePricingTest(Model model){
		logger.info(">> Recebendo requisicao / <<");		
		
		Address addressBegin = new Address.AddressBuilder().cidade("Santo Andre").estado("Sao Paulo").nomeRua("Rua das Hortencias").numero(1102).build();		
		Address addressEnd = new Address.AddressBuilder().cidade("Florianopolis").estado("Santa Catarina").nomeRua("Av. Rio Branco").numero(37).build();
			
		
		SpendingsVO spendings;
		try {
			spendings = buildSpendings(model, addressBegin, addressEnd);
			model.addAttribute("geoDatas", spendings.getGeoDatas());		
			model.addAttribute("spendings", spendings);
			return "pricingRoute"; 
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "error";
		}
    }

	
	@RequestMapping(value = "/pricingCheck", method = RequestMethod.GET)
	public @ResponseBody SpendingsVO calculatePricing(Model model,
			@RequestParam("addressBeginCity")  String addressBeginCity,	
			@RequestParam("addressBeginState")  String addressBeginState,
			@RequestParam("addressBeginStreet")  String addressBeginStreet,
			@RequestParam("addressBeginNumber")  Integer addressBeginNumber,
			@RequestParam("addressBeginAvenue")  String addressBeginAvenue,
			
			@RequestParam("addressEndCity")   String addressEndCity,
			@RequestParam("addressEndState")  String addressEndState,
			@RequestParam("addressEndStreet")  String addressEndStreet,
			@RequestParam("addressEndNumber")  Integer addressEndNumber,
			@RequestParam("addressEndAvenue")  String addressEndAvenue) {
		
		try {
			logger.info(">> Recebendo requisicao /pricingCheck <<");
			
			return buildSpendings(model,
					  buildAddress(addressBeginCity, addressBeginState, addressBeginStreet, addressBeginNumber , addressBeginAvenue)
					, buildAddress(addressEndCity, addressEndState, addressEndStreet, addressEndNumber , addressEndAvenue));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new SpendingsVO(ERROR_CODE, e.getMessage());
		}		
	}

	private Address buildAddress(String addressCity, String addressState, String addressStreet,Integer addressNumber, String addressAvenue) {
		logger.debug("addressCity="+addressCity+", addressState="+addressState+", addressStreet= "+addressStreet+", addressNumber= "+addressNumber+", addressAvenue= "+addressAvenue);
		
		return new Address.AddressBuilder().cidade(addressCity).estado(addressState).nomeRua(addressStreet).numero(addressNumber).build();
	}
	
	private SpendingsVO buildSpendings(Model model, Address addressBegin, Address addressEnd) throws Exception {
		List<GeoData> geoDatas = geoCodeServices.retrieveGeoData(addressBegin, addressEnd);		
		Route route = routeServices.retrieveRoute(geoDatas.toArray(new GeoData[geoDatas.size()]));
		
		SpendingsVO spendings = SpendingsCalculator.calcRouteOutgoings(route);
		spendings.setGeoDatas(geoDatas);
		
		return spendings;
	}
}
