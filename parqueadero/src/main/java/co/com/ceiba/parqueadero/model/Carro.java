package co.com.ceiba.parqueadero.model;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@SuppressWarnings("serial")
@Entity
@EntityListeners(AuditingEntityListener.class)
@DiscriminatorValue("carro")
public class Carro extends Vehiculo implements Serializable{


	public Carro() {
	}
	
	public Carro(String placa, int cilindraje) {
		super(placa, cilindraje);
	}

	
	
}
