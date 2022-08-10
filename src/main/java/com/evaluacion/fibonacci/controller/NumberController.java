/**
 * 
 */
package com.evaluacion.fibonacci.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evaluacion.fibonacci.model.Number;
import com.evaluacion.fibonacci.resource.DTO.NumberDTO;
import com.evaluacion.fibonacci.resource.DTO.ValorDTO;
import com.evaluacion.fibonacci.service.NumberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Angel
 *
 */
@RestController
@RequestMapping("/fibonacci/number")
@Api(tags = "fibonacci")
public class NumberController {
	
private final NumberService numberService;
	
	public NumberController(NumberService numberService) {
		this.numberService = numberService;
	}
	
	@PostMapping
	@ApiOperation(value = "CALCULAR EL FIBONACCI DE UN NÚMERO", notes = "Servicio para crear el fibonacci pasando como parámetro el número del cual se quiere obtener su fibonacci")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Número y fibonacci creados"),
			@ApiResponse(code = 400, message = "Solicitud Invalida") })
	public String createNumber(@RequestBody ValorDTO valorDTO){
		
		
		Number number = new Number();
		File archivo=new File("DATOS_FIBONACCI.txt");
		BufferedWriter bw = null;
	    FileWriter fw = null;
        int num=CalculaFibonacci(valorDTO.getNumber());
		
		
		String valores="EL FIBONACCI DE"+" "+valorDTO.getNumber()+" "+"ES:"+" "+num+"\n";
		
		number.setNumber(valorDTO.getNumber());
		number.setFibonacci(num);
		number.setLimit_number(num);
		
		//SE CREA LA ENTIDAD EN LA BASE DE DATOS
		this.numberService.create(number);
	
	    try
	        {   
	        
	         // Si el archivo no existe, se crea!
	        if (!archivo.exists()) {
	            archivo.createNewFile();
	        }
	       
	        fw = new FileWriter(archivo.getAbsoluteFile(), true);
	        bw = new BufferedWriter(fw);
	        bw.write(valores);
	        
	        } 
	        catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                       
	                if (bw != null)
	                    bw.close();
	                if (fw != null)
	                    fw.close();
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
		
		return "EL FIBONACCI DE"+" "+valorDTO.getNumber()+" "+"ES:"+" "+num;
	}
	
	@PutMapping("/{id_number}")
	@ApiOperation(value = "ACTUALIZAR REGISTROS DE CÁLCULOS DE FIBONACCI GUARDADOS", notes = "Servicio para actualizar número y su fibonacci de algún registro pasado mediante su id de creación")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Número actualizado"),
			@ApiResponse(code = 404, message = "Numero no encontrado") })
	public ResponseEntity<Number> updateNumber(@PathVariable("id_number") int id_number, @RequestBody NumberDTO numberDTO){
		
		Number number = this.numberService.findById(id_number);
		
		if (number == null) {
			return new ResponseEntity<Number>(HttpStatus.NOT_FOUND);
		} else {
			number.setNumber(numberDTO.getNumber());
			number.setFibonacci(numberDTO.getFibonacci());
			number.setLimit_number(numberDTO.getLimit_number());
		}
		
		return new ResponseEntity<Number>(this.numberService.update(number), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id_number}")
	@ApiOperation(value = "ELIMINAR CÁLCULO DE FIBONACCI DE UN NÚMERO", notes = "Servicio para eliminar el registro mediante su id")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Número actualizado"),
			@ApiResponse(code = 404, message = "Numero no encontrado") })
	public void deleteUser(@PathVariable("id_number") int id_number) {
		Number number = this.numberService.findById(id_number);
		
		if (number != null) {
			this.numberService.delete(number);
		}
	}
	
	@GetMapping
	@ApiOperation(value = "LISTAR TODOS LOS CÁLCULOS DE FIBONACCI", notes = "Servicio para listar números, su fibonacci y su id registrados")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Números listados"),
			@ApiResponse(code = 404, message = "Números no encontrados") })
	public ResponseEntity<List<Number>> findAll(){
		return ResponseEntity.ok(this.numberService.findAll());
	}
	
	
	/*MÉTODO RECURSIVO PARA CÁLCULAR EL FIBONACCI DE UN NÚMERO*/
	public static int CalculaFibonacci(int n) {
		 
        if (n == 0) {
            return 0;
               
        } else if (n == 1) {
            return 1;
        } else {
            
            return CalculaFibonacci(n - 1) + CalculaFibonacci(n - 2);
        }
 
}

}
