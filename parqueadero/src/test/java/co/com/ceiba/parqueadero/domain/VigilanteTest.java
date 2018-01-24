package co.com.ceiba.parqueadero.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class VigilanteTest {

	private Vigilante vigilante;
	
	@Before
	public void setup(){
		vigilante = new Vigilante();
	}
	
	@Test
	public void test() {

		//Act
		boolean result = vigilante.isTrue();
		//Assert
		Assert.assertEquals(true, result);
	}

}
