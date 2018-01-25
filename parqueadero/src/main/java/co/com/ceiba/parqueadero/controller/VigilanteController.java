package co.com.ceiba.parqueadero.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.parqueadero.domain.VigilanteService;

@RestController
@RequestMapping("vigilante")
public class VigilanteController {

	
	@Autowired
	private VigilanteService vigilanteService;
	
	@GetMapping("/")
	public String index(){
		return "Pagina Vigilante";
	}
	
	@PostMapping("/crear")
	public Map<String, String> crearIngreso(){
		return null;
		
		
	}
}
