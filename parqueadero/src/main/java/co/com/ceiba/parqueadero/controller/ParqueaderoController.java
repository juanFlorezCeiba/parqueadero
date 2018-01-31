package co.com.ceiba.parqueadero.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.parqueadero.domain.ParqueaderoService;
import co.com.ceiba.parqueadero.model.Parqueadero;

@RestController
@RequestMapping("/parqueadero")
public class ParqueaderoController {

	@Autowired
	private ParqueaderoService parqueaderoService;
	
	
	/**
	 * Servicio REST que permite obtener un parqueadero.
	 * @param id, identificación del parqueadero.
	 * @return, response entity del parqueadero.
	 */
	@GetMapping("/obtener/{id}")
	public ResponseEntity<Parqueadero> getParqueaderoById(@PathVariable(value = "id") Integer id){

		Parqueadero parqueadero = parqueaderoService.getParqueaderoById(id);
		
		if(parqueadero == null){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(parqueadero);
		
	}
	
}
