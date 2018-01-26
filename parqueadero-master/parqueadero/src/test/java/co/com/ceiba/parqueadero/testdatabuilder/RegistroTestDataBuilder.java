package co.com.ceiba.parqueadero.testdatabuilder;

import java.util.Calendar;

import co.com.ceiba.parqueadero.model.Registro;
import co.com.ceiba.parqueadero.model.Vehiculo;

public class RegistroTestDataBuilder {

	private int id;
	private Calendar fechaEntrada;
	private Calendar fechaSalida;
	private Vehiculo vehiculo;
	
	
	public RegistroTestDataBuilder(){
		this.id = 99;
		
	}
	public RegistroTestDataBuilder withId(int id){
		this.id = id;
		return this;
	}
	
	public RegistroTestDataBuilder withFechaEntrada(Calendar fechaEntrada){
		this.fechaEntrada = fechaEntrada;
		return this;
	}
	
	public RegistroTestDataBuilder withVehiculo(Vehiculo vehiculo){
		this.vehiculo = vehiculo;
		return this;
	}
	
	public RegistroTestDataBuilder withFechaSalida(Calendar fechaSalida){
		this.fechaSalida = fechaSalida;
		return this;
	}
	
	public Registro build(){
		return new Registro(this.id, this.fechaEntrada, this.fechaSalida, this.vehiculo);
	}
	
}
