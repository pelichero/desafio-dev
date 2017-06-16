package com.br.maplink.desafio_dev.services;

import java.util.List;

import com.br.maplink.desafio_dev.dto.route.Route;
import com.br.maplink.desafio_dev.dto.search.GeoData;
import com.br.maplink.desafio_dev.exceptions.MapLinkBusinessException;

/**
 * 
 * @author felipe.pelichero
 *
 * 16 de jun de 2017
 */
public interface RouteServices {
	
	public Route retrieveRoute(List<GeoData> geoData) throws MapLinkBusinessException;
	
}
