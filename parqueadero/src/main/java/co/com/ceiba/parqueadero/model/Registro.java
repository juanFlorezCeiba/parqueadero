package co.com.ceiba.parqueadero.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "registros")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
allowGetters = true)
public class Registro  implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "fecha_entrada", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
	private Calendar fechaEntrada;
	
	@Column(name = "fecha_salida", nullable = true)
	private Calendar fechaSalida;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="vehiculo_placa")
	private Vehiculo vehiculo;
	
	
	public Registro() {
		
	}
	
	public Registro(int id, Calendar fechaEntrada, Vehiculo vehiculo) {
		super();
		this.id = id;
		this.fechaEntrada = fechaEntrada;
		this.vehiculo = vehiculo;
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


	public Vehiculo getVehiculo() {
		return vehiculo;
	}


	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	
	
}
