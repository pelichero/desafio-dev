package com.br.maplink.desafio_dev.services;

import java.util.List;

import com.br.maplink.desafio_dev.dto.search.GeoData;
import com.br.maplink.desafio_dev.exceptions.MapLinkBusinessException;
import com.br.maplink.desafio_dev.vos.Address;

public interface GeoCodeServices {
	
	public List<GeoData> retrieveGeoData(Address... address) throws MapLinkBusinessException;
	
}
