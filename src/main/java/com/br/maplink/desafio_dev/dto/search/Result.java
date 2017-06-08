package com.br.maplink.desafio_dev.dto.search;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author felipe.pelichero
 *
 * 7 de jun de 2017
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Result {
	
	@JsonProperty("results")
	private List<GeoData> results;

	public List<GeoData> getResults() {
		return results;
	}

	public void setResults(List<GeoData> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "Result [results=" + results + "]";
	}
}
