package co.com.ceiba.parqueadero.testdatabuilder;

import co.com.ceiba.parqueadero.model.Moto;

public class MotoTestDataBuilder {

	private String placa;
	private int cilindraje;
	
	public MotoTestDataBuilder(){
		this.placa = "qwerty10";
		this.cilindraje = 100;
	}
	
	public MotoTestDataBuilder withPlaca(String placa){
		this.placa = placa;
		return this;
	}
	
	public MotoTestDataBuilder withCilindraje(int cilindraje){
		this.cilindraje = cilindraje;
		return this;
	}
	
	public Moto build(){
		return new Moto(this.placa, this.cilindraje);
	}
	
}
