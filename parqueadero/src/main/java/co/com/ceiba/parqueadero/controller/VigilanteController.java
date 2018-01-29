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

import co.com.ceiba.parqueadero.domain.VigilanteService;
import co.com.ceiba.parqueadero.model.Carro;
import co.com.ceiba.parqueadero.model.Constantes;
import co.com.ceiba.parqueadero.model.Moto;
import co.com.ceiba.parqueadero.repository.ConstantesRepository;
import co.com.ceiba.parqueadero.repository.MotoRepository;

@RestController
@RequestMapping("vigilante")
public class VigilanteController {

	
	@Autowired
	private VigilanteService vigilanteService;
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
	public String salidaMoto(@PathVariable(value = "placa") String placa){
		String response = vigilanteService.salidaMoto(placa);
		
		return response;
	}
	
	/**
	 * Servicio para crear registro cuando ingresa un carro.
	 * @param carro, entidad carro.
	 * @return response, indicando si se guardo el carro.
	 */
	@PostMapping("/carro/crear-registro")
	public String crearRegistroCarro(@Valid @RequestBody Carro carro){
		
		String response = vigilanteService.crearIngresoCarro(carro);
		
		return response;
	}
	
	@GetMapping("/carro/salida/{placa}")
	public String salidaCarro(@PathVariable(value = "placa") String placa){
		String response = vigilanteService.salidaCarro(placa);
		
		return response;
	}
}
