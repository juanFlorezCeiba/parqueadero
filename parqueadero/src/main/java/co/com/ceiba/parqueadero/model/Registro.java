package co.com.ceiba.parqueadero.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "registros")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
allowGetters = true)
public class Registro  implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "fecha_entrada", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fechaEntrada;
	
	@Column(name = "fecha_salida", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fechaSalida;

	
	
	public Registro(int id, Calendar fechaEntrada, Calendar fechaSalida) {
		super();
		this.id = id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
	}


	//Getters and Setters
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public Calendar getFechaEntrada() {
		return fechaEntrada;
	}


	public void setFechaEntrada(Calendar fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}


	public Calendar getFechaSalida() {
		return fechaSalida;
	}


	public void setFechaSalida(Calendar fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	
	
}
