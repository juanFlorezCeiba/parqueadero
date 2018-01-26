package co.com.ceiba.parqueadero.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.parqueadero.domain.VigilanteService;
import co.com.ceiba.parqueadero.model.Constants;
import co.com.ceiba.parqueadero.repository.ConstantsRepository;

@RestController
@RequestMapping("vigilante")
public class VigilanteController {

	
	@Autowired
	private VigilanteService vigilanteService;
	@Autowired
	private ConstantsRepository constantsRepository;
	
	@GetMapping("/")
	public String index(){
		return "Pagina Vigilante";
	}
	
	@PostMapping("/crear")
	public Map<String, String> crearIngreso(){
		return null;
		
		
	}
	
	// Get a Single Note
	@GetMapping("/obtener/{id}")
	public String getNoteById(@PathVariable(value = "id") String id) {
	    Constants note = constantsRepository.findOne("valor_hora_moto");
	    if(note == null) {
	        return "nada";
	    }
	    return note.getValor();
	}
}
