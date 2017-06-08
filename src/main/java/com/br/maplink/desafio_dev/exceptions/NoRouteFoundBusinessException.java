package com.br.maplink.desafio_dev.exceptions;

public class NoRouteFoundBusinessException extends MapLinkBusinessException{

	public NoRouteFoundBusinessException() {
		super();
	}

	public NoRouteFoundBusinessException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoRouteFoundBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoRouteFoundBusinessException(String message) {
		super(message);
	}

	public NoRouteFoundBusinessException(Throwable cause) {
		super(cause);
	}	
}
