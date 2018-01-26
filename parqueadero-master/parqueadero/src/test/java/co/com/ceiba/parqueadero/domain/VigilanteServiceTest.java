package co.com.ceiba.parqueadero.domain;

import java.util.Calendar;

import org.junit.*;
import co.com.ceiba.parqueadero.model.Vehiculo;

import co.com.ceiba.parqueadero.model.Carro;
import co.com.ceiba.parqueadero.model.Moto;
import co.com.ceiba.parqueadero.model.Registro;
import co.com.ceiba.parqueadero.testdatabuilder.MotoTestDataBuilder;
import co.com.ceiba.parqueadero.testdatabuilder.RegistroTestDataBuilder;

public class VigilanteServiceTest {

	private VigilanteService vigilanteService;
	private Moto moto;
	private Registro registro;
	private Calendar fechaEntrada;
	
	
	@Before
	public void setUp(){
		fechaEntrada = Calendar.getInstance();
		fechaEntrada.set(2018, 01, 24, 20, 30);
		vigilanteService = new VigilanteService();
		moto = new MotoTestDataBuilder().withPlaca("qqazw32").build();
		registro = new RegistroTestDataBuilder().withVehiculo(moto).withFechaEntrada(fechaEntrada).build();
		
	}
	
	/**
	 * Prueba unitaria de la creación de un registro.
	 */
	@Test
	public void crearIngresoTest(){

		//Act 
		boolean seCreo = vigilanteService.crearIngreso(registro);
		
		//Assert
		Assert.assertEquals(seCreo, false);
	}
	
	/**
	 * Prueba unitaria para calcular el total de la tarifa a pagar.
	 */
	@Test
	public void calcularTarifaTest(){
	
		int valorHora = 500;
		int valorDia = 400;
		Vehiculo vehiculo = this.moto;
		//Act
		int tarifa = vigilanteService.calcularTarifa( valorHora, valorDia, vehiculo, registro);
		
		//Assert
		Assert.assertEquals(expected, actual);
		
	}
	
	@After
	public void remove(){
		
		vigilanteService = null;
		moto = null;
		registro = null;
	}
	
}
