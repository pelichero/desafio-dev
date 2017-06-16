package com.br.maplink.desafio_dev.services;

import java.util.List;

import com.br.maplink.desafio_dev.dto.search.GeoData;
import com.br.maplink.desafio_dev.exceptions.MapLinkBusinessException;
import com.br.maplink.desafio_dev.vos.Address;

/**
 * 
 * @author felipe.pelichero
 *
 * 16 de jun de 2017
 */
public interface GeoCodeServices {
	
	public List<GeoData> retrieveGeoData(List<Address> address) throws MapLinkBusinessException;
	
}
