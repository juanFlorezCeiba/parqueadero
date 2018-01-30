package co.com.ceiba.parqueadero.domain;

import java.util.Calendar;
import java.util.Date;import java.util.Optional;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.com.ceiba.parqueadero.ParqueaderoApplication;
import co.com.ceiba.parqueadero.model.Carro;
import co.com.ceiba.parqueadero.model.Moto;
import co.com.ceiba.parqueadero.model.Parqueadero;
import co.com.ceiba.parqueadero.model.Registro;
import co.com.ceiba.parqueadero.repository.ConstantesRepository;
import co.com.ceiba.parqueadero.repository.ParqueaderoRepository;
import co.com.ceiba.parqueadero.testdatabuilder.CarroTestDataBuilder;
import co.com.ceiba.parqueadero.testdatabuilder.MotoTestDataBuilder;


public class VigilanteServiceTest {
	
	@Autowired
	private VigilanteService vigilanteService;
	@Autowired
	private Carro carro;
	@Autowired
	 ParqueaderoRepository parqueaderoRepository;
	@Autowired
	Parqueadero parq;
	
	static Mockito mock;
	
	
	@Before
	public void setUp(){
		vigilanteService = new VigilanteService();
	}
	
	/**
	 * Prueba unitaria de la creación de un registro para una moto.
	 */
	@Test
	public void crearIngresoMotoTest(){
//		//Arrange
//		Moto moto = new MotoTestDataBuilder().withPlaca("qqazw32").withCilindraje(650).build();
//		
//		//Act 
//		String message = vigilanteService.crearIngresoMoto(moto);
//		
//		//Assert
//		Assert.assertEquals(message, "HAS BEEN SAVED");
		
		//Arrange 
		
		//Act
		parq = parqueaderoRepository.findOne(1);
		
		Assert.assertEquals(20, parq.getEspaciosCarros());
	}
	
	
	/**
	 * Prueba unitaria de la creación de un registro para un carro.
	 */
	@Test
	public void crearIngresoCarroTest(){
		//Arrange
		Carro carro = new CarroTestDataBuilder().withPlaca("qqazw32").withCilindraje(1400).build();
		
		//Act 
		String message = vigilanteService.crearIngresoCarro(carro);
		
		//Assert
		Assert.assertEquals(message, "HAS BEEN SAVED");
	}
	
	/**
	 * Prueba unitaria del metodo que permite darle salida a un carro.
	 */
	@Test
	public void salidaCarroTest(){
		
		//Arrange
		String placa = "qwerty201";
		
		//Act
		String response = vigilanteService.salidaCarro(placa);
		
		//Assert
		Assert.assertEquals("VEHICLE_HAS_LEFT", response);
	}
	
	/**
	 * Prueba unitaria del metodo que permite darle salida a un carro.
	 */
	@Test
	public void salidaMotoTest(){
		
		//Arrange
		String placa = "qwerty201";
		
		//Act
		String response = vigilanteService.salidaMoto(placa);
		
		//Assert
		Assert.assertEquals("VEHICLE_HAS_LEFT", response);
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
	    int tarifa = vigilanteService.calcularTarifa(fechaInicial, fechaFinal, "Moto", 650);
	    
	    //Assert
	    Assert.assertEquals(11000, tarifa);
	    
	}
	
	/**
	 * Prueba unitaria para calcular el total de una tarifa a través de un metodo recursivo.
	 */
	@Test
	public void totalTarifaTest(){
		
		//Arrange
		double totalDias0 = 0.03;
		double totalDias =  0.2;
		double totalDias2 = 0.4;
		double totalDias3 = 1.125;
		int total = 0;
		
		//Act
		int totalTarifa0 = vigilanteService.totalTarifa(1000, 8000,totalDias0, total);
		int totalTarifa = vigilanteService.totalTarifa(1000, 8000,totalDias, total);
		int totalTarifa2 = vigilanteService.totalTarifa(1000, 8000,totalDias2, total);
		int totalTarifa3 = vigilanteService.totalTarifa(1000, 8000,totalDias3, total);

		//Assert
		Assert.assertEquals(totalTarifa0, 1000);
		Assert.assertEquals(totalTarifa, 4000);
		Assert.assertEquals(totalTarifa2, 8000);
		Assert.assertEquals(totalTarifa3, 11000);


	}
	
	/*
	 * Prueba unitaria de la función que permite verificar 
	 * si un vehiculo puede ingresar.
	 */
	@Test
	public void puedeIngresarTest(){
		
		//Arrange
		String placa = "new31c";
		
		//Act
		boolean puedeIngresar = vigilanteService.puedeIngresar(placa);
		
		//Assert
		Assert.assertEquals(true, puedeIngresar);
		
	}
	
	/*
	 * Prueba unitaria de la función que permite verificar 
	 * si un vehiculo con la primera letra 'A' en la placa puede ingresar.
	 */
	@Test
	public void puedeIngresarPorLetraATest(){
		
		//Arrange
		String placa = "acd30c";
		
		//Act
		boolean puedeIngresar = vigilanteService.puedeIngresar(placa);
		
		//Assert
		Assert.assertEquals(true, puedeIngresar);
		
	}
	
	/**
	 * Prueba unitaria para saber si un carro esta parqueado.
	 */
	@Test
	public void carroEstaParqueadoTest(){
		//Arrange
		Moto carro = new MotoTestDataBuilder().withPlaca("sebasq122").withCilindraje(650).build();
		
		
		//Act
		boolean estaGuardado = vigilanteService.vehiculoEstaParqueado(carro);
		
		//Assert
		Assert.assertEquals(false, estaGuardado);
		
	}
	
	/**
	 * Prueba unitaria para consultar la información de un registro.
	 */
	@Test
	public void consultarRegistroMotoTest(){
		
		//Arrange
		String placa = "qwerty120";
		Moto carro = new MotoTestDataBuilder().withPlaca(placa).withCilindraje(1000).build();
		
		//Act
		Registro registro = vigilanteService.consultarRegistro(carro);
		
		//Assert
		Assert.assertNotNull(registro);
		
	}
}
