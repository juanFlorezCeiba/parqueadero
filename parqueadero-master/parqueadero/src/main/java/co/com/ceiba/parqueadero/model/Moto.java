package co.com.ceiba.parqueadero.model;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@SuppressWarnings("serial")
@Entity
@EntityListeners(AuditingEntityListener.class)
@DiscriminatorValue("moto")
public class Moto extends Vehiculo implements Serializable{

	public Moto(String placa, int cilindraje) {
		super(placa, cilindraje);
	}

public Moto() {
	// TODO Auto-generated constructor stub
}

}
