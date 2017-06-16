package com.br.maplink.desafio_dev.exceptions;

public class MapLinkBusinessException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MapLinkBusinessException() {
		super();
	}

	public MapLinkBusinessException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MapLinkBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public MapLinkBusinessException(String message) {
		super(message);
	}

	public MapLinkBusinessException(Throwable cause) {
		super(cause);
	}	
}
