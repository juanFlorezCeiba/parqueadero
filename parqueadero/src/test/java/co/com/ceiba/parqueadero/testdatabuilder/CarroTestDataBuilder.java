package co.com.ceiba.parqueadero.testdatabuilder;

import co.com.ceiba.parqueadero.model.Carro;

public class CarroTestDataBuilder {

	private String placa;
	private int cilindraje;
	
	public CarroTestDataBuilder(){
		this.placa = "qwerty10";
		this.cilindraje = 100;
	}
	
	public CarroTestDataBuilder withPlaca(String placa){
		this.placa = placa;
		return this;
	}
	
	public CarroTestDataBuilder withCilindraje(int cilindraje){
		this.cilindraje = cilindraje;
		return this;
	}
	
	public Carro build(){
		return new Carro(this.placa, this.cilindraje);
	}
	
}
