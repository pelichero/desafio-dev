package com.br.maplink.desafio_dev.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RestConfig {

	/*
	@Bean
	public RestTemplate createTemplate(final ClientHttpRequestFactory clientHttpRequestFactory){
		return new RestTemplate(clientHttpRequestFactory);
	}
	*/
	
	@Bean
	public ClientHttpRequestFactory createClientHttpRequestFactory(@Value("${connect.timeout}") final int connectTimeout, @Value("${read.timeout}") final int readTimeout){
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		clientHttpRequestFactory.setReadTimeout(readTimeout);
		clientHttpRequestFactory.setConnectionRequestTimeout(connectTimeout);
		
		return clientHttpRequestFactory;
	}
	
	@Bean
	public RestTemplate restTemplate(final ClientHttpRequestFactory clientHttpRequestFactory) {
	    RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
	    List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
	    for (HttpMessageConverter<?> converter : converters) {
	        if (converter instanceof MappingJackson2HttpMessageConverter) {
	            MappingJackson2HttpMessageConverter jsonConverter = (MappingJackson2HttpMessageConverter) converter;
	            jsonConverter.setObjectMapper(new ObjectMapper());
	            jsonConverter.setSupportedMediaTypes(Arrays.asList(new MediaType("application", "json", MappingJackson2HttpMessageConverter.DEFAULT_CHARSET), new MediaType("text", "javascript", MappingJackson2HttpMessageConverter.DEFAULT_CHARSET)));
	        }
	    }
	    return restTemplate;
	}
}