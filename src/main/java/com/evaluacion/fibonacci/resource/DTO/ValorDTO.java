package com.evaluacion.fibonacci.resource.DTO;

import lombok.Data;

@Data
public class ValorDTO {
	
	private int number;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
