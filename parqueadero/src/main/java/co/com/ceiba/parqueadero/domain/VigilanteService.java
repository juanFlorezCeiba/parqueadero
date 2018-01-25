package co.com.ceiba.parqueadero.domain;

import org.springframework.stereotype.Service;

import co.com.ceiba.parqueadero.model.Registro;

@Service
public class VigilanteService {

	
	public VigilanteService(){
	}

	 /**
	  * Servicio que permite crear un registro.
	  * @param registro, entidad registro.
	  * @return verdadero si se creo el registro, falso si no.
	  */
	 public boolean crearIngreso(Registro registro) {
		
		return false;
	}
}
