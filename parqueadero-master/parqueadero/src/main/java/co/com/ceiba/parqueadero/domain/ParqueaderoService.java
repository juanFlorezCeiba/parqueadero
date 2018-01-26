package co.com.ceiba.parqueadero.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.parqueadero.model.Parqueadero;
import co.com.ceiba.parqueadero.repository.ParqueaderoRepository;

@Service
public class ParqueaderoService {

	@Autowired
	ParqueaderoRepository parqueaderoRepository;
	
	/**
	 * Función para obtener un parqueadero.
	 * @param id, identificación del parqueadero.
	 * @return la entidad parqueadero.
	 */
	public Parqueadero getParqueaderoById(int id){
		
		Parqueadero parqueadero = parqueaderoRepository.findOne(id);
		
		return parqueadero;
	}
}
