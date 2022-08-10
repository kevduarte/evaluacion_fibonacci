package com.evaluacion.fibonacci.resource.DTO;


import lombok.Data;

@Data
public class NumberDTO {

	
	private int number;
	private int fibonacci;
	private int limit_number;
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getFibonacci() {
		return fibonacci;
	}
	public void setFibonacci(int fibonacci) {
		this.fibonacci = fibonacci;
	}
	public int getLimit_number() {
		return limit_number;
	}
	public void setLimit_number(int limit_number) {
		this.limit_number = limit_number;
	}
	
}
