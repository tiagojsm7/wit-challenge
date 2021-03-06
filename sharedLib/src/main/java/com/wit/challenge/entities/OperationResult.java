package com.wit.challenge.entities;

import java.io.Serializable;
import java.math.BigDecimal;

public class OperationResult implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal result;
	private String message;

	public OperationResult(BigDecimal result, String message) {
		super();
		this.result = result;
		this.message = message;
	}

	public BigDecimal getResult() {
		return result;
	}

	public void setResult(BigDecimal result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "OperationResult [result=" + result + ", message=" + message + "]";
	}


}