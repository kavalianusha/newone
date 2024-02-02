package com.example.efilingbazaar.exception;

import lombok.Data;

@Data
public class MainServiceException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	 private String message;
		public MainServiceException(String message) {
			this.message = message;
	    }
}
