package co.com.ceiba.parqueadero.domain;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.*;

import co.com.ceiba.parqueadero.model.Moto;
import co.com.ceiba.parqueadero.model.Registro;
import co.com.ceiba.parqueadero.testdatabuilder.MotoTestDataBuilder;
import co.com.ceiba.parqueadero.testdatabuilder.RegistroTestDataBuilder;

public class VigilanteServiceTest {

	
	private VigilanteService vigilanteService;
	
	@Before
	public void setUp(){
		vigilanteService = new VigilanteService();

	}
	
	/**
	 * Prueba unitaria de la creación de un registro.
	 */
	@Test
	public void crearIngresoTest(){
		//Arrange
		Moto moto = new MotoTestDataBuilder().withPlaca("qqazw32").build();
		Registro registro = new RegistroTestDataBuilder().withVehiculo(moto).build();
		
		//Act 
		boolean seCreo = vigilanteService.crearIngreso(registro);
		
		//Assert
		Assert.assertEquals(seCreo, false);
	}
	
	/**
	 * Prueba unitaria para calcular tarifa.
	 */
	@Test
	public void calcularTarifaTest(){
		//Arrange 
	    Calendar calendarInicial = Calendar.getInstance();
	    calendarInicial.set(2018, 0, 28, 05, 40);
	    Calendar calendarFinal = Calendar.getInstance();
	    Date fechaInicial = calendarInicial.getTime();
	    Date fechaFinal = calendarFinal.getTime();
	    
	    //Act
	    int tarifa = vigilanteService.calcularTarifa(fechaInicial, fechaFinal, "carro", 650);
	    
	    //Assert
	    Assert.assertEquals(11000, tarifa);
	    
	}
	
	/**
	 * Prueba unitaria para calcular el total de una tarifa a través de un metodo recursivo.
	 */
	@Test
	public void totalTarifaTest(){
		
		//Arrange
		double totalDias =  1.125;
		int total = 0;
		
		//Act
		int totalTarifa = vigilanteService.totalTarifa(1000, 8000,totalDias, total);
		System.out.println("PRUEBA: " + totalTarifa);
		//Assert
		Assert.assertEquals(totalTarifa, 11000);
	}
}
