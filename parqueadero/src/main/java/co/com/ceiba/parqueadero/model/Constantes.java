package co.com.ceiba.parqueadero.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "constants")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(allowGetters = true)

public class Constantes implements Serializable{

	@Id
	private String id;
	
	@NotBlank
	private String valor;
	
	
	public Constantes() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
	
	
	
}
