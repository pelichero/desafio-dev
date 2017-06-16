package com.br.maplink.desafio_dev;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.br.maplink.desafio_dev.dto.route.Route;
import com.br.maplink.desafio_dev.dto.search.GeoData;
import com.br.maplink.desafio_dev.exceptions.MapLinkBusinessException;
import com.br.maplink.desafio_dev.services.GeoCodeServices;
import com.br.maplink.desafio_dev.services.RouteServices;
import com.br.maplink.desafio_dev.vos.Address;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ServicesTests {

	private static final double BEGIN_LAT = -23.68602;
	private static final double BEGIN_LNG = -46.51538;
	
	private static final double END_LAT = -27.59254;
	private static final double END_LNG = -48.55757;

	@Autowired
	private GeoCodeServices geoServices;
	
	@Autowired
	private RouteServices routeServices;

	private Address addressBegin;
	private Address addressEnd;
	
	@Before
	public void init(){
		addressBegin = new Address.AddressBuilder()
				.cidade("Santo Andre")
				.estado("Sao Paulo")
				.nomeRua("Rua das Hortencias")
				.numero(1102)
				.build();	
		
		addressEnd = new Address.AddressBuilder()
				.cidade("Florianopolis")
				.estado("Santa Catarina")
				.nomeRua("Av. Rio Branco")
				.numero(37)
				.build();
	}
	
	
	@Test
	public void testSucessoGeoServices() {
		Throwable t = null;
		final Integer MUST_GEO_DATA_RETRIEVED = 2;
		Integer actualGeoDataRetrieved = null;
		try {
			List<GeoData> retrieveGeoData = geoServices.retrieveGeoData(Arrays.asList(addressBegin, addressEnd));
			System.out.println(retrieveGeoData);
			
			actualGeoDataRetrieved = retrieveGeoData.size();
		} catch (MapLinkBusinessException e) {
			e.printStackTrace();
			t = e;
		}
		
		Assert.assertEquals(MUST_GEO_DATA_RETRIEVED, actualGeoDataRetrieved);
		Assert.assertNull(t);
	}

	@Test
	public void testSucessoRouteServices() {
		Throwable t = null;
		
		try {
			Route retrieveRoute = routeServices.retrieveRoute(Arrays.asList(new GeoData(BEGIN_LAT,BEGIN_LNG), new GeoData(END_LAT,END_LNG)));
			System.out.println(retrieveRoute);
		} catch (MapLinkBusinessException e) {
			e.printStackTrace();
			t = e;
		}			
		
		Assert.assertNull(t);
	}
	
	@Test
	public void testFalhaGeoServicesNullAddresses() {
		Throwable t = null;
		IllegalArgumentException iEx = null;
		try {
			geoServices.retrieveGeoData(Arrays.asList(null, null));
		} catch (MapLinkBusinessException e) {
			t = e;
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			iEx = e;
		}		
		
		Assert.assertNull(t);
		Assert.assertNotNull(iEx);
	}
	
	@Test
	public void testFalhaRouteServices() {
		Throwable t = null;
		IllegalStateException iEx = null;
		try {
			routeServices.retrieveRoute(Arrays.asList(null, null));
		} catch (MapLinkBusinessException e) {
			e.printStackTrace();
			t = e;
		} catch (IllegalStateException e) {
			iEx = e;
		}		
		
		Assert.assertNull(t);
		Assert.assertNotNull(iEx);
	}
}