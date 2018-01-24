package co.com.ceiba.parqueadero.controller;

import org.springframework.http.ResponseEntity;

import co.com.ceiba.parqueadero.model.Parqueadero;
import co.com.ceiba.parqueadero.repository.ParqueaderoRepository;

public class ParqueaderoController {

	ParqueaderoRepository parqueaderoRepository;
	
	public Parqueadero getParqueaderoById(Integer id){
		Parqueadero parqueadero = parqueaderoRepository.findOne(id);
		
		if(parqueadero == null) {
			return null;
		}
		return parqueadero;
	}
	
}
