package co.com.ceiba.parqueadero.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.parqueadero.model.Carro;
import co.com.ceiba.parqueadero.repository.CarroRepository;

@Service
public class CarroService {

	@Autowired
	CarroRepository carroRepository;
	
	/**
	 * Función para obtener todos los carros.
	 * @return lista de carros.
	 */
	public List<Carro> obtenerTodosCarros() {
	    return carroRepository.findAll();
	}
	
	/**
	 * Función para crear un carro.
	 * @param carro, entidad carro.
	 * @return true si el carro fue creado.
	 */	public boolean crearCarro(Carro carro) {
	  boolean creado = false;
		
		if(!existeCarro(carro.getPlaca())) {
			carroRepository.save(carro);
			creado = true;
		}
	    return creado;
	}
	
	/**
	 * Función para saber si existe un carro.
	 */
	public boolean existeCarro(String placa) {
	 
	   return carroRepository.exists(placa);
	}
	
	/**
	 * Función que permite obtener un carro a través de su placa.
	 * @param placa, placa del carro.
	 * @return entidad carro.
	 */
	public Carro consultarCarro(String placa){
		Carro carro = carroRepository.findOne(placa);
		
		if(carro == null){
			return null;
		}
		return carro;
	}
	
	/**
	 * Función para eliminar un carro.
	 * @param placa, placa del carro.
	 * @return true si el carro ha sido eliminado.
	 */
	public boolean eliminarCarro (String placa) {
	    boolean eliminado = false;
		Carro carro = carroRepository.findOne(placa);
	    if(carro != null) {
	    	carroRepository.delete(carro);
	    	eliminado = true;
	    }

	    return eliminado;
	}
}
