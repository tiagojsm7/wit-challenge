package com.wit.challenge.entities;

import java.io.Serializable;
import java.math.BigDecimal;

public class OperationRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Operations op;
	private BigDecimal a;
	private BigDecimal b;
	private String uuid;

	public OperationRequest(Operations op, BigDecimal a, BigDecimal b, String uuid) {
		super();
		this.op = op;
		this.a = a;
		this.b = b;
		this.uuid = uuid;
	}

	public Operations getOp() {
		return op;
	}

	public void setOp(Operations op) {
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

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public String toString() {
		return "OperationRequest [op=" + op + ", a=" + a + ", b=" + b + ", uuid=" + uuid + "]";
	}
	
	


}