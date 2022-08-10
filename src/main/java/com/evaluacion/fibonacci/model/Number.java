package com.evaluacion.fibonacci.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Number_Fibo")
@NamedQuery(name = "Number.findById", query = "Select n from Number n where n.id_number = ?1")
public class Number {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_number")
	private int id_number;
	
	@Column(name = "number")
	private int number;
	
	@Column(name = "fibonacci")
	private int fibonacci;
	
	@Column(name = "limit_number")
	private int limit_number;
	
	public int getId_number() {
		return id_number;
	}

	public void setId_number(int id_number) {
		this.id_number = id_number;
	}

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
