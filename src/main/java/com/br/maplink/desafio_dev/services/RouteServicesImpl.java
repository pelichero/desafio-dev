package com.br.maplink.desafio_dev.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

import com.br.maplink.desafio_dev.autentication.RequestSigner;
import com.br.maplink.desafio_dev.dto.route.Route;
import com.br.maplink.desafio_dev.dto.route.Routes;
import com.br.maplink.desafio_dev.dto.search.GeoData;
import com.br.maplink.desafio_dev.dto.search.Location;
import com.br.maplink.desafio_dev.exceptions.MapLinkBusinessException;
import com.br.maplink.desafio_dev.exceptions.NoRouteFoundBusinessException;

/**
 * 
 * @author felipe.pelichero
 *
 * 7 de jun de 2017
 */
@Component
public class RouteServicesImpl implements RouteServices{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	protected RestOperations restOperations;

	@Autowired
	private RequestSigner requestSigner;
	
	protected String serviceUrl = "";

	@Autowired
	public RouteServicesImpl(@Value("${routeservice.url}") String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl;
	}
	
	@Override
	public Route retrieveRoute(List<GeoData> geoData) throws MapLinkBusinessException {
		logger.info(">> Invocando servico de rotas <<");
		
		if(CollectionUtils.isEmpty(geoData)){
			logger.error("Não existem geo localizações para traçar rotas");
			throw new IllegalStateException("Não existem geo localizações para traçar rotas");
		}
		
		List<Location> locations = retrieveGeoLocations(geoData);
		if(CollectionUtils.isEmpty(locations)){
			logger.error("Não existem geo localizações para traçar rotas");
			throw new IllegalStateException("Não existem geo localizações para traçar rotas");
		}
		
		
		List<Route> routes;
		try {
			routes = restOperations.getForObject(requestSigner.routeSignWithToken(serviceUrl, locations), Routes.class).getRoutes();
			if(routes == null || routes.isEmpty()){
				logger.error("Não foi possivel encontrar geo dados dos endereços informados " + geoData.toString());
				throw new NoRouteFoundBusinessException("Não foi possivel encontrar geo dados dos endereços informados " + geoData.toString());
			}

			return routes.get(0);
		} catch (RestClientException rE) {
			throw new MapLinkBusinessException("Ocorreu um erro ao invocar o serviço", rE);
		}
	}

	private List<Location> retrieveGeoLocations(List<GeoData> geoDataList) {
		
		List<Location> locations = new ArrayList<>();
		for (GeoData geoData : geoDataList) {
			if(geoData == null){
				throw new IllegalStateException("geoLocalização encontra-se nula");
			}
			
			locations.add(geoData.getLocation());
		}
		
		
		return locations;
	}
	
}