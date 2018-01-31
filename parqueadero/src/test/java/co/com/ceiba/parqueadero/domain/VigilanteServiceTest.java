package co.com.ceiba.parqueadero.domain;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.parqueadero.model.Carro;
import co.com.ceiba.parqueadero.model.Moto;
import co.com.ceiba.parqueadero.model.Parqueadero;
import co.com.ceiba.parqueadero.model.Registro;
import co.com.ceiba.parqueadero.repository.CarroRepository;
import co.com.ceiba.parqueadero.repository.MotoRepository;
import co.com.ceiba.parqueadero.repository.ParqueaderoRepository;
import co.com.ceiba.parqueadero.repository.RegistroRepository;
import co.com.ceiba.parqueadero.testdatabuilder.CarroTestDataBuilder;
import co.com.ceiba.parqueadero.testdatabuilder.MotoTestDataBuilder;
import co.com.ceiba.parqueadero.testdatabuilder.RegistroTestDataBuilder;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class VigilanteServiceTest {
	
	@InjectMocks
	@Autowired
	private VigilanteService vigilanteService;
	
	@Mock
	ParqueaderoRepository parqueaderoRepository;
	
	@Mock
	RegistroRepository registroRepository;
	
	@Mock
	MotoRepository motoRepository;
	
	@Mock
	CarroRepository carroRepository;

	/**
	 * Prueba unitaria para validar si el vehiculo esta parqueado.
	 */
	@Test
	public void crearIngresoCarroTest(){
		//Arrange
		Carro carro = new CarroTestDataBuilder().withPlaca("qqazw32").withCilindraje(1400).build();
		
		Parqueadero parqueadero = new Parqueadero();
		parqueadero.setEspaciosCarros(20);
		Mockito.when(parqueaderoRepository.findOne(1)).thenReturn(parqueadero);
		
		//Act 
		
		try {
			vigilanteService.crearIngresoCarro(carro);
		} catch(RuntimeException e) {
			Assert.assertEquals(e.getMessage(), "El vehiculo está parqueado");
		}
		
	}
	/**
	 * Prueba unitaria para validar si el vehiculo esta parqueado.
	 */
	@Test
	public void validarSiVehiculoEstaParqueadoTest(){
		//Arrange
		Calendar calendar = Calendar.getInstance();
		calendar.set(2018, 01, 28, 20, 30);
		Carro carro = new CarroTestDataBuilder().withPlaca("qqazw32").withCilindraje(1400).build();
		Registro registro = new RegistroTestDataBuilder().withFechaEntrada(calendar).withVehiculo(carro).build();
		List<Registro> listaRegistros = new ArrayList<>();
		listaRegistros.add(registro);
		
		Parqueadero parqueadero = new Parqueadero();
		parqueadero.setEspaciosCarros(20);
		
		Mockito.when(registroRepository.findByVehiculoOrderByFechaEntradaDesc(carro)).thenReturn(listaRegistros);
		Mockito.when(parqueaderoRepository.findOne(1)).thenReturn(parqueadero);
		
		//Act 
		
		try {
			vigilanteService.crearIngresoCarro(carro);
			fail();
		} catch(RuntimeException e) {
			Assert.assertEquals(e.getMessage(), "El vehiculo está parqueado");
		}
		
	}
	
	@Test
	public void validarQueNoPuedeIngresarConELParqueaderoLleno(){
		//Arrange
		Carro carro = new CarroTestDataBuilder().withPlaca("qqazw32").withCilindraje(1400).build();
		
		Parqueadero parqueadero = new Parqueadero();
		parqueadero.setEspaciosCarros(0);
		Mockito.when(parqueaderoRepository.findOne(1)).thenReturn(parqueadero);
		
		//Act 
		
		try {
			vigilanteService.crearIngresoCarro(carro);
			fail();
		} catch (RuntimeException e) {
			//Assert
			Assert.assertEquals(e.getMessage(), "El parqueadero està lleno");
		}
		 
		
		
	}
	
	/**
	 * Prueba unitaria del metodo que permite darle salida a un carro.
	 */
	@Test
	public void salidaCarroTest(){
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(2018, 01, 28, 20, 30);
		String placa = "qwerty201";
		Carro carro = new CarroTestDataBuilder().withPlaca(placa).withCilindraje(1000).build();
		Registro registro = new RegistroTestDataBuilder().withFechaEntrada(calendar).withVehiculo(carro).build();
		List<Registro> listaRegistros = new ArrayList<>();
		listaRegistros.add(registro);
		
		Mockito.when(registroRepository.findByVehiculoOrderByFechaEntradaDesc(carro)).thenReturn(listaRegistros);
		Mockito.when(carroRepository.findOne(placa)).thenReturn(carro);
		
		
		//Act
		int total = vigilanteService.salidaCarro(placa);
		
		//Assert
				Assert.assertEquals(-1, total);
	}
	
	/**
	 * Prueba unitaria del metodo que permite darle salida a un carro.
	 */
	@Test
	public void salidaMotoTest(){
		
		//Arrange
		Calendar calendar = Calendar.getInstance();
		calendar.set(2018, 01, 28, 20, 30);
		String placa = "qwerty201";
		Moto moto = new MotoTestDataBuilder().withPlaca(placa).withCilindraje(259).build();
		Registro registro = new RegistroTestDataBuilder().withFechaEntrada(calendar).withVehiculo(moto).build();
		List<Registro> listaRegistros = new ArrayList<>();
		listaRegistros.add(registro);
		
		Mockito.when(registroRepository.findByVehiculoOrderByFechaEntradaDesc(moto)).thenReturn(listaRegistros);
		Mockito.when(motoRepository.findOne(placa)).thenReturn(moto);
		
		//Act
		int total = vigilanteService.salidaMoto(placa);
		
		//Assert
		Assert.assertEquals(-1, total);
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
	    calendarFinal.set(2018, 0,29,10,40);
	    Date fechaInicial = calendarInicial.getTime();
	    Date fechaFinal = calendarFinal.getTime();
	    
	    //Act
	    int tarifa = vigilanteService.calcularTarifa(fechaInicial, fechaFinal, "Moto", 650);
	    
	    //Assert
	    Assert.assertEquals(8500, tarifa);
	    
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
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		int day2 = 4;
		
		//Act
		boolean puedeIngresar = vigilanteService.puedeIngresar(placa, day);
		boolean puedeIngresar2 = vigilanteService.puedeIngresar(placa, day2);

		//Assert
		Assert.assertEquals(true, puedeIngresar);
		Assert.assertEquals(true, puedeIngresar2);

	}
	
	/*
	 * Prueba unitaria de la función que permite verificar 
	 * si un vehiculo con la primera letra 'A' en la placa puede ingresar.
	 */
	@Test
	public void puedeIngresarPorLetraATest(){
		
		//Arrange
		String placa = "acd30c";
		int day = 1;
		int day2 = 4;
		//Act
		boolean puedeIngresar = vigilanteService.puedeIngresar(placa, day);
		boolean puedeIngresar2 = vigilanteService.puedeIngresar(placa, day2);

		//Assert
		Assert.assertEquals(true, puedeIngresar);
		Assert.assertEquals(false, puedeIngresar2);

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
		Calendar calendar = Calendar.getInstance();
		calendar.set(2018, 01, 28, 20, 30);
		String placa = "qwerty201";
		Moto moto = new MotoTestDataBuilder().withPlaca(placa).withCilindraje(259).build();
		Registro registro = new RegistroTestDataBuilder().withFechaEntrada(calendar).withVehiculo(moto).build();
		List<Registro> listaRegistros = new ArrayList<>();
		listaRegistros.add(registro);
		
		Mockito.when(registroRepository.findByVehiculoOrderByFechaEntradaDesc(moto)).thenReturn(listaRegistros);

		//Act
		Registro registroResult = vigilanteService.consultarRegistro(moto);
		
		//Assert
		Assert.assertEquals(registro.getId(), registroResult.getId());
		
	}
	
	@Test
	public void obtenerRegistroTodosLosVehiculosParqueadosTest(){
		
		//Arrange
		Calendar calendar = Calendar.getInstance();
		calendar.set(2018, 01, 28, 20, 30);
		String placa = "qwerty201";
		Moto moto = new MotoTestDataBuilder().withPlaca(placa).withCilindraje(259).build();
		Registro registro = new RegistroTestDataBuilder().withFechaEntrada(calendar).withVehiculo(moto).build();
		List<Registro> listaRegistros = new ArrayList<>();
		listaRegistros.add(registro);
		Mockito.when(registroRepository.obtenerRegistrosDeLosVehiculosParqueados()).thenReturn(listaRegistros);
		
		//Act
		List<Registro> listaVehiculosParqueados = vigilanteService.obtenerTodosLosRegistrosDeVehiculosParqueados();
		
		//Assert
		Assert.assertNotEquals(0, listaVehiculosParqueados.size());
		
	}
	
}
