/**
 * 
 */
package com.evaluacion.fibonacci.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.evaluacion.fibonacci.model.Number;

/**
 * @author Angel
 *
 */
public interface NumberRepository extends JpaRepository<Number, String>{

	public Number findById(int id_number);

}
