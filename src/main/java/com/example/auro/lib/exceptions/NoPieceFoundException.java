package com.example.auro.lib.exceptions;

public class NoPieceFoundException extends Exception {

	private static final long serialVersionUID = 1640718718750852649L;
	
	public NoPieceFoundException(final String message) {
		super(message);
	}

}
