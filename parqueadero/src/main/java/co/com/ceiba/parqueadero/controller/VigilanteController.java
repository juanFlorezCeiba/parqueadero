package co.com.ceiba.parqueadero.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.parqueadero.domain.CarroService;
import co.com.ceiba.parqueadero.domain.MotoService;
import co.com.ceiba.parqueadero.domain.VigilanteService;
import co.com.ceiba.parqueadero.model.Carro;
import co.com.ceiba.parqueadero.model.Constantes;
import co.com.ceiba.parqueadero.model.Moto;
import co.com.ceiba.parqueadero.model.Registro;
import co.com.ceiba.parqueadero.repository.ConstantesRepository;
import co.com.ceiba.parqueadero.repository.MotoRepository;
import co.com.ceiba.parqueadero.repository.VehiculoRepository;

@RestController
@RequestMapping("vigilante")
public class VigilanteController {

	
	@Autowired
	private VigilanteService vigilanteService;
	@Autowired
	private CarroService carroService;
	@Autowired
	private MotoService motoService;
	@Autowired
	private ConstantesRepository constantsRepository;
	@Autowired
	private MotoRepository motoRepository;
	
	/**
	 * Servicio de bienvenida.
	 * @return, Mensaje.
	 */
	@GetMapping("/")
	public String index(){
		
		return "Pagina Vigilante";
	}
	
	// Get a Single Note
	@GetMapping("/obtener/{id}")
	public String getNoteById(@PathVariable(value = "id") String id) {
	    Constantes note = constantsRepository.findOne("valor_hora_moto");
	    if(note == null) {
	        return "nada";
	    }
	    return note.getValor();
	}
	
	/**
	 * Servicio para crear registro cuando ingresa una moto.
	 * @param moto, entidad moto.
	 * @return response, indicando si se guardo la moto.
	 */
	@PostMapping("/moto/crear-registro")
	public String crearRegistroMoto(@Valid @RequestBody Moto moto){
		
		String response = vigilanteService.crearIngresoMoto(moto);
		
		return response;
		
	}
	
	@GetMapping("/moto/salida/{placa}")
	public int salidaMoto(@PathVariable(value = "placa") String placa){
		int total = vigilanteService.salidaMoto(placa);
		
		return total;
	}
	
	/**
	 * Servicio para crear registro cuando ingresa un carro.
	 * @param carro, entidad carro.
	 * @return response, indicando si se guardo el carro.
	 */
	@PostMapping("/carro/crear-registro")
	public void crearRegistroCarro(@Valid @RequestBody Carro carro){
		
		try{
			vigilanteService.crearIngresoCarro(carro);
		}
		catch(RuntimeException e){
			 e.getMessage();
		}

	}
	
	@GetMapping("/carro/salida/{placa}")
	public int salidaCarro(@PathVariable(value = "placa") String placa){
		int total = vigilanteService.salidaCarro(placa);
		
		return total;
	}
	
	/**
	 * Servicio que permite consultar el registro de un carro en el parqueadero.
	 * @param placa, placa del carro.
	 * @return entidad registro.
	 */
	@GetMapping("/carro/consultar/{placa}")
	public ResponseEntity<Registro> consultarCarro(@PathVariable(value = "placa") String placa){
		Carro carro = carroService.consultarCarro(placa);
		
		if(carro == null){
			return ResponseEntity.notFound().build();
		}
		
		Registro registro = vigilanteService.consultarRegistro(carro);
		
		return (registro == null)?ResponseEntity.notFound().build():ResponseEntity.ok().body(registro);
		
	}
	
	/**
	 * Servicio  que permite consultar el registro de una moto en el parqueadero.
	 * @param placa, placa de la moto.
	 * @return entidad registro.
	 */
	@GetMapping("/moto/consultar/{placa}")
	public ResponseEntity<Registro> consultarMoto(@PathVariable(value = "placa") String placa){
		Moto moto = motoService.consultarMoto(placa);
		
		if(moto == null){
			return ResponseEntity.notFound().build();
		}
		
		Registro registro = vigilanteService.consultarRegistro(moto);
		
		return (registro == null)?ResponseEntity.notFound().build():ResponseEntity.ok().body(registro);
		
	}
}
