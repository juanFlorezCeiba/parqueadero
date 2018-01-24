package co.com.ceiba.parqueadero.model;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "parqueadero")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(allowGetters = true)
public class Parqueadero {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotBlank
	@Column(name = "cant_carros")
	private int cantCarros;
	
	@NotBlank
	@Column(name = "cant_motos")
	private int cantMotos;
	
	@NotBlank
	@Column(name = "espacios_carros")
	private int espaciosCarros;
	
	@NotBlank
	@Column(name = "espacios_motos")
	private int espaciosMotos;

	
	
	//Getters and Setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantCarros() {
		return cantCarros;
	}

	public void setCantCarros(int cantCarros) {
		this.cantCarros = cantCarros;
	}

	public int getCantMotos() {
		return cantMotos;
	}

	public void setCantMotos(int cantMotos) {
		this.cantMotos = cantMotos;
	}

	public int getEspaciosCarros() {
		return espaciosCarros;
	}

	public void setEspaciosCarros(int espaciosCarros) {
		this.espaciosCarros = espaciosCarros;
	}

	public int getEspaciosMotos() {
		return espaciosMotos;
	}

	public void setEspaciosMotos(int espaciosMotos) {
		this.espaciosMotos = espaciosMotos;
	}
	
}
