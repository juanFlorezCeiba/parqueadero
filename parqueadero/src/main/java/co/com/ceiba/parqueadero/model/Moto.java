package co.com.ceiba.parqueadero.model;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("moto")
public class Moto extends Vehiculo{

	public Moto(String placa, int cilindraje){
		super(placa, cilindraje);
	}
}
