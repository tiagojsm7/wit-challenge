package com.wit.challenge.entities;

import java.io.Serializable;
import java.math.BigDecimal;

public class OperationRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Operations operation;
	private BigDecimal firstOperator;
	private BigDecimal secondOperator;

	public OperationRequest(Operations operation, BigDecimal firstOperator, BigDecimal secondOperator) {
		super();
		this.operation = operation;
		this.firstOperator = firstOperator;
		this.secondOperator = secondOperator;
	}

	public Operations getOperation() {
		return operation;
	}

	public void setOperation(Operations operation) {
		this.operation = operation;
	}

	public BigDecimal getFirstOperator() {
		return firstOperator;
	}

	public void setFirstOperator(BigDecimal firstOperator) {
		this.firstOperator = firstOperator;
	}

	public BigDecimal getSecondOperator() {
		return secondOperator;
	}

	public void setSecondOperator(BigDecimal secondOperator) {
		this.secondOperator = secondOperator;
	}

	@Override
	public String toString() {
		return "OperationRequest [operation=" + operation + ", firstOperator=" + firstOperator + ", secondOperator="
				+ secondOperator + "]";
	}

}