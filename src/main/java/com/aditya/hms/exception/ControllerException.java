package com.aditya.hms.exception;

public class ControllerException extends RuntimeException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String message;
	
	public ControllerException() {
		
	}
	public ControllerException(String errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	

}
