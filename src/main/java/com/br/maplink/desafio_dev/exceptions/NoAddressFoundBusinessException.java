package com.br.maplink.desafio_dev.exceptions;

public class NoAddressFoundBusinessException extends MapLinkBusinessException{

	public NoAddressFoundBusinessException() {
		super();
	}

	public NoAddressFoundBusinessException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoAddressFoundBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoAddressFoundBusinessException(String message) {
		super(message);
	}

	public NoAddressFoundBusinessException(Throwable cause) {
		super(cause);
	}	
}
