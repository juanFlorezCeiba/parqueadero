package co.com.ceiba.parqueadero.model;

import javax.persistence.*;


@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("carro")
public class Carro extends Vehiculo{

	public Carro(String placa, int cilindraje){
		super(placa, cilindraje);
	}
}
