package com.br.maplink.desafio_dev.exceptions;

public class MoreThanOneGeoLocationBusinessException extends MapLinkBusinessException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MoreThanOneGeoLocationBusinessException() {
		super();
	}

	public MoreThanOneGeoLocationBusinessException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MoreThanOneGeoLocationBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public MoreThanOneGeoLocationBusinessException(String message) {
		super(message);
	}

	public MoreThanOneGeoLocationBusinessException(Throwable cause) {
		super(cause);
	}	
}
