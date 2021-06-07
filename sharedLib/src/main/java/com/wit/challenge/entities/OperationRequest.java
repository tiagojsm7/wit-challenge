package com.wit.challenge.entities;

import java.io.Serializable;
import java.math.BigDecimal;

public class OperationRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String op;
	private BigDecimal a;
	private BigDecimal b;

	public OperationRequest(String op, BigDecimal a, BigDecimal b) {
		super();
		this.op = op;
		this.a = a;
		this.b = b;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public BigDecimal getA() {
		return a;
	}

	public void setA(BigDecimal a) {
		this.a = a;
	}

	public BigDecimal getB() {
		return b;
	}

	public void setB(BigDecimal b) {
		this.b = b;
	}

	@Override
	public String toString() {
		return "OperationRequest [op=" + op + ", a=" + a + ", b=" + b + "]";
	}


}