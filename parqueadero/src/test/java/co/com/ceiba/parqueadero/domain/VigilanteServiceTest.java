package co.com.ceiba.parqueadero.domain;

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
	
}
