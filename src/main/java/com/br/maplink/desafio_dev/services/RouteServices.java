package com.br.maplink.desafio_dev.services;

import com.br.maplink.desafio_dev.dto.route.Route;
import com.br.maplink.desafio_dev.dto.search.GeoData;
import com.br.maplink.desafio_dev.exceptions.MapLinkBusinessException;

public interface RouteServices {
	
	public Route retrieveRoute(GeoData... geoData) throws MapLinkBusinessException;
	
}
