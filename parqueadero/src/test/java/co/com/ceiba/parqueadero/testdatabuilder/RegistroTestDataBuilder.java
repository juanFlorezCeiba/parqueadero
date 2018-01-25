package co.com.ceiba.parqueadero.testdatabuilder;

import java.util.Calendar;

import co.com.ceiba.parqueadero.model.Registro;
import co.com.ceiba.parqueadero.model.Vehiculo;

public class RegistroTestDataBuilder {

	private int id;
	private Calendar fechaEntrada;
	private Vehiculo vehiculo;
	
	
	public RegistroTestDataBuilder(){
		this.id = 99;
		
	}
	public RegistroTestDataBuilder withId(int id){
		this.id = id;
		return this;
	}
	
	public RegistroTestDataBuilder withFechaEntrada(Calendar fecha){
		this.fechaEntrada = fecha;
		return this;
	}
	
	public RegistroTestDataBuilder withVehiculo(Vehiculo vehiculo){
		this.vehiculo = vehiculo;
		return this;
	}
	
	public Registro build(){
		return new Registro(this.id, this.fechaEntrada, null, this.vehiculo);
	}
	
}
