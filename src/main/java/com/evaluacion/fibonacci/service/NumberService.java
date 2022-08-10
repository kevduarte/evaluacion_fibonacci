/**
 * 
 */
package com.evaluacion.fibonacci.service;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evaluacion.fibonacci.model.Number;
import com.evaluacion.fibonacci.repository.NumberRepository;

/**
 * @author Angel
 *
 */
@Service
@Transactional(readOnly = true)
public class NumberService {
	
	public final NumberRepository numberRepository; 
	
	public NumberService(NumberRepository numberRepository) {
		this.numberRepository = numberRepository;
	}
	
	@Transactional
	public Number create(Number number) {
		return this.numberRepository.save(number);
	}
	
	@Transactional
	public Number update(Number number) {
		return this.numberRepository.save(number);
	}
	
	@Transactional
	public void delete(Number number) {
		this.numberRepository.delete(number);
	}
	
	public Number findById(int id_number) {
		return this.numberRepository.findById(id_number);
	}
	
	public List<Number> findAll(){
		return this.numberRepository.findAll();
	}

}
