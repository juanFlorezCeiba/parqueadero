package co.com.ceiba.parqueadero.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.parqueadero.model.Moto;
import co.com.ceiba.parqueadero.repository.MotoRepository;

@Service
public class MotoService {

	@Autowired
	MotoRepository motoRepository;
	

	/**
	 * Función para obtener todas las motos.
	 * @return lista de motos.
	 */
	public List<Moto> obtenerTodosMotos() {
	    return motoRepository.findAll();
	}
	
	
	/**
	 * Función para crear una moto.
	 * @param moto, entidad moto.
	 * @return true si la moto fue creada.
	 */
	public boolean crearMoto(Moto moto) {
	  boolean creado = false;
		
		if(!existeMoto(moto.getPlaca())) {
			motoRepository.save(moto);
			creado = true;
		}
	    return creado;
	}
	
	/**
	 * Función para saber si existe una moto.
	 */
	public boolean existeMoto(String placa) {
	 
	   return motoRepository.exists(placa);
	  
	}
	
	/**
	 * Función que permite obtener una moto a través de su placa.
	 * @param placa, placa de la moto.
	 * @return entidad moto.
	 */
	public Moto consultarMoto(String placa){
		Moto moto = motoRepository.findOne(placa);
		
		if(moto == null){
			return null;
		}
		return moto;
	}
	
	/**
	 * Función para eliminar un moto.
	 * @param placa, placa del moto.
	 * @return true si el moto ha sido eliminado.
	 */
	public boolean eliminarMoto (String placa) {
	    boolean eliminado = false;
		Moto moto = motoRepository.findOne(placa);
	    if(moto != null) {
	    	motoRepository.delete(moto);
	    	eliminado = true;
	    }

	    return eliminado;
	}
}
