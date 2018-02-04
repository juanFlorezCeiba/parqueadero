package co.com.ceiba.parqueadero.testdatabuilder;

import co.com.ceiba.parqueadero.model.Parqueadero;

public class ParqueaderoTestDataBuilder {

	
	private int id;
	private int espacioCarros;
	private int espacioMotos;
	
	public ParqueaderoTestDataBuilder(){
		this.id = 1;
		this.espacioCarros = 20;
		this.espacioMotos = 10;
	}
	
	
	public ParqueaderoTestDataBuilder withId(int id){
		this.id = id;
		return this;
	}
	
	public ParqueaderoTestDataBuilder withEspacioCarros(int espacioCarros){
		this.espacioCarros = espacioCarros;
		return this;
	}
	
	public ParqueaderoTestDataBuilder withEspacioMotos(int espacioMotos){
		this.espacioMotos = espacioMotos;
		return this;
	}
	
	public Parqueadero build(){
		return new Parqueadero(this.id, this.espacioCarros, this.espacioMotos);
	}
}
