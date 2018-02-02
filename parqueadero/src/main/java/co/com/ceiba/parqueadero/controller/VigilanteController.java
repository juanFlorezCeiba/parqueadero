package co.com.ceiba.parqueadero.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
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
		
		return vigilanteService.crearIngresoMoto(moto);
	
	}
	
	@GetMapping("/moto/salida/{placa}")
	public void salidaMoto(@PathVariable(value = "placa") String placa){
		 vigilanteService.salidaMoto(placa);

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
		return vigilanteService.salidaCarro(placa);

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
	
	
	/**
	 * Servicio que permite obtener todos los registros.
	 * @return lista de registros.
	 */
	@GetMapping("/obtener")
	public List<Registro> obtenerRegistros(){
	
		return vigilanteService.obtenerTodosLosRegistrosDeVehiculosParqueados();
	}
	
	/**
	 * Servicio que permite obtener el total de la tarifa de un registro.
	 * @param id, identificador de un registro.
	 * @return el total de la tarifa.
	 */
	@GetMapping("/obtener-total/{id}")
	public Map<String, Integer> obtenerTotal(@PathVariable(value = "id") int id){
		Calendar fechaSalida = Calendar.getInstance();
		Registro registro = vigilanteService.consultarRegistroPorId(id);
		
		String type = registro.getVehiculo().getClass().getSimpleName();
		int tarifa = vigilanteService.calcularTarifa(registro.getFechaEntrada().getTime(), fechaSalida.getTime(), type, registro.getVehiculo().getCilindraje());

		Map<String, Integer> lista = new HashMap<>();
		lista.put("tarifa", tarifa);
		return  lista;
			}

	/**
	 * Servicio que permite pagar y liquidar un registro.
	 * @param id, identificación de un registro.
	 */
	@GetMapping("/pagar/{id}")
	public void pagarTarifa(@PathVariable(value = "id") int id){
		
		Registro registro = vigilanteService.consultarRegistroPorId(id);

		vigilanteService.salidaMoto(registro.getVehiculo().getPlaca());
		}

}


