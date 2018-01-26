package co.com.ceiba.parqueadero.domain;

import org.springframework.stereotype.Service;

import co.com.ceiba.parqueadero.model.Registro;
import co.com.ceiba.parqueadero.model.Vehiculo;

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

	 /**
	  * Función para calcular la tarifa de un vehiculo(Carro-Moto).
	  * @param valorHora, valor de la hora.
	  * @param valorDia, valor del día.
	  * @param vehiculo, vehiculo.
	  * @return calcula el total de la tarifa.
	  */
	public int calcularTarifa(int valorHora, int valorDia, Vehiculo vehiculo, Registro registro) {
		return 0;
	}
}
