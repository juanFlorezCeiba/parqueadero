package co.com.ceiba.parqueadero.model;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_vehiculo")
@Table(name = "vehiculos")
public class Vehiculo {

	@Id
	@Column(name = "placa")
	private String placa;
	
	@NotBlank
	private int cilindraje;

	
	
	
	public Vehiculo(String placa, int cilindraje) {
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
