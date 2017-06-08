package com.br.maplink.desafio_dev.services;

import java.net.MalformedURLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

import com.br.maplink.desafio_dev.autentication.RequestSigner;
import com.br.maplink.desafio_dev.dto.route.Route;
import com.br.maplink.desafio_dev.dto.route.Routes;
import com.br.maplink.desafio_dev.dto.search.GeoData;
import com.br.maplink.desafio_dev.exceptions.MapLinkBusinessException;

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
	public Route retrieveRoute(GeoData... geoData) throws MapLinkBusinessException {
		logger.info(">> Invocando servico de rotas <<");
		
		List<Route> routes;
		try {
			routes = restOperations.getForObject(requestSigner.routeSignWithToken(serviceUrl, geoData), Routes.class).getRoutes();
			if(routes == null || routes.isEmpty()){
				logger.error("Não foi possivel encontrar geo dados dos endereços informados " + geoData.toString());
				throw new MapLinkBusinessException("Não foi possivel encontrar geo dados dos endereços informados " + geoData.toString());
			}

			return routes.get(0);
		} catch (RestClientException | MalformedURLException e) {
			e.printStackTrace();
		}
		
		return null;
	}	
}