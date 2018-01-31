package co.com.ceiba.parqueadero.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.com.ceiba.parqueadero.model.Moto;
import co.com.ceiba.parqueadero.repository.MotoRepository;

@RestController
@RequestMapping("vigilante/moto")
public class MotoController {

	@Autowired
	MotoRepository motoRepository;
	
	@GetMapping("/")
	public String index(){
		return "EXITO!!";
	}
	
	@PostMapping("/create")
	public Map<String, String> createMoto(@Valid @RequestBody Moto moto){

		HashMap<String, String> map = new HashMap<>();
		
		map.put("Status", "Repeat");
		
		if(!motoRepository.exists(moto.getPlaca())){
			motoRepository.save(moto);
			 map.put("Status", "OK");
		} 
			
		 return map;
	}
}
