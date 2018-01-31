package co.com.ceiba.parqueadero.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_vehiculo")
@Table(name = "vehiculos")
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
allowGetters = true)
public class Vehiculo implements Serializable{

	@Id
	@Column(name = "placa")
	private String placa;
	
	@NotNull
	private int cilindraje;

	public Vehiculo(){
		
	}

	public Vehiculo(String placa, int cilindraje) {
		super();
		this.placa = placa;
		this.cilindraje = cilindraje;
	}



	//Getters and Setters
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public int getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}
	
	
}
