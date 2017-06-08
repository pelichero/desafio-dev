package com.br.maplink.desafio_dev.services;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

import com.br.maplink.desafio_dev.autentication.RequestSigner;
import com.br.maplink.desafio_dev.dto.search.GeoData;
import com.br.maplink.desafio_dev.dto.search.Result;
import com.br.maplink.desafio_dev.exceptions.MapLinkBusinessException;
import com.br.maplink.desafio_dev.exceptions.NoAddressFoundBusinessException;
import com.br.maplink.desafio_dev.exceptions.NoRouteFoundBusinessException;
import com.br.maplink.desafio_dev.vos.Address;

/**
 * 
 * @author felipe.pelichero
 *
 * 7 de jun de 2017
 */
@Component
public class GeoCodeServicesImpl implements GeoCodeServices{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	protected RestOperations restOperations;

	@Autowired
	private RequestSigner requestSigner;
	
	protected String serviceUrl = "";

	@Autowired
	public GeoCodeServicesImpl(@Value("${searchservice.url}") String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl;
	}
	
	@Override
	public List<GeoData> retrieveGeoData(Address... address) throws MapLinkBusinessException {
		logger.info(">> Invocando servico de geoLocalizacao <<");
		
		
		if(address == null){
			logger.error("Não existem enderecos para traçar rotas");
			throw new NoAddressFoundBusinessException("Não existem enderecos para traçar rotas"); 
		}
		
		List<GeoData> geoData = new ArrayList<>();
		
		
		try {
			for (Address addressObj : address) {
				Result result;
					result = restOperations.getForObject(requestSigner.addressSignWithToken(serviceUrl, addressObj), Result.class);
				if(result == null){
					logger.error("Nenhuma rota encontrada para os enderecos "+address);
					throw new NoRouteFoundBusinessException("Nenhuma rota encontrada para os enderecos "+address); 
				}
				
				geoData.add(result.getResults().get(0));
			}
		} catch (RestClientException | MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return geoData;
	}	
}