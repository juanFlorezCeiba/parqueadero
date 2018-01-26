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
		//Assert 
	    Calendar calendarInicial = Calendar.getInstance();
	    calendarInicial.set(2018, 0, 25, 10, 00);
	    Calendar calendarFinal = Calendar.getInstance();
		
	    Date fechaInicial = calendarInicial.getTime();
	    Date fechaFinal = calendarFinal.getTime();
	    
	    //Act
	    int tarifa = vigilanteService.calcularTarifa(fechaInicial, fechaFinal, "moto");
	    
	    //Assert
	    Assert.assertEquals(1, tarifa);
	    
		
	}
}
