package com.br.maplink.desafio_dev.dto.route;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author felipe.pelichero
 *
 * 8 de jun de 2017
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Routes {

	@JsonProperty("routes")
	private List<Route> routes;

	public List<Route> getRoutes() {
		return routes;
	}

	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}

	@Override
	public String toString() {
		return "Routes [routes=" + routes + "]";
	}
}
