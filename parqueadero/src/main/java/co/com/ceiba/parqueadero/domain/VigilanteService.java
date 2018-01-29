package co.com.ceiba.parqueadero.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.parqueadero.model.Carro;
import co.com.ceiba.parqueadero.model.Constantes;
import co.com.ceiba.parqueadero.model.Moto;
import co.com.ceiba.parqueadero.model.Parqueadero;
import co.com.ceiba.parqueadero.model.Registro;
import co.com.ceiba.parqueadero.model.Vehiculo;
import co.com.ceiba.parqueadero.repository.CarroRepository;
import co.com.ceiba.parqueadero.repository.ConstantesRepository;
import co.com.ceiba.parqueadero.repository.MotoRepository;
import co.com.ceiba.parqueadero.repository.ParqueaderoRepository;
import co.com.ceiba.parqueadero.repository.RegistroRepository;

@Service
public class VigilanteService {

	@Autowired
	 ConstantesRepository constantsRepository;
	@Autowired
	 ParqueaderoRepository parqueaderoRepository;
	@Autowired
	MotoRepository motoRepository;
	@Autowired
	private CarroRepository carroRepository;
	@Autowired
	private RegistroRepository registroRepository;
	
	
	
	public VigilanteService(){
		
	}



	/**
	 * Función que permite crear un ingreso al parqueadero a una moto.
	 * @param moto, moto la cual ingresará al parqueadero.
	 * @return cadena, la cual indica si ingreso o no al parqueadero.
	 */
	public String crearIngresoMoto(Moto moto) {
		
		Parqueadero parqueadero = parqueaderoRepository.findOne(1);
		
		//Se valida si la moto esta parqueado en este momento.
		if(vehiculoEstaParqueado(moto)){
			return "VEHICLE_PARKED_NOW";
		}
		//Se valida si la moto puede acceder al parqueadero.
		if(!puedeIngresar(moto.getPlaca())){
			return "IS_NOT_SUNDAY_MONDAY";
		}
			
		int espaciosMotos = parqueadero.getEspaciosMotos();
			
		//Se verifica si hay espacio en el parqueadero.
		if(espaciosMotos == 0){
			return "THERE_IS_NOT_SPACE";
		}
		motoRepository.save(moto);
		
		Registro registro = new Registro();
		registro.setVehiculo(moto);
		
		registroRepository.save(registro);
		
		return "BIKE_HAS_BEEN_SAVED";

	}



	/**
	 * Función que permite crear un ingreso al parqueadero a un carro.
	 * @param carro, carro el cual ingresará al parqueadero.
	 * @return cadena, la cual indica si ingreso o no al parqueadero.
	 */
	public String crearIngresoCarro(Carro carro) {

		Parqueadero parqueadero = parqueaderoRepository.findOne(1);
		
		//Se valida si el carro esta parqueado en este momento.
		if(vehiculoEstaParqueado(carro)){
			return "VEHICLE_PARKED_NOW";
		} 
		
		//Se valida si el carro puede acceder al parqueadero.
		if(!puedeIngresar(carro.getPlaca())){
			return "IS_NOT_SUNDAY_MONDAY";
		}
	
			int espaciosCarros = parqueadero.getEspaciosCarros();
			
			//Se verifica si hay espacio en el parqueadero.
			if(espaciosCarros == 0){
				return "THERE_IS_NOT_SPACE";
			}
			
			carroRepository.save(carro);
			
			Registro registro = new Registro();
			registro.setVehiculo(carro);
			
			registroRepository.save(registro);
			
			return "CAR_HAS_BEEN_SAVED";
	}



	 /**
	  * Función para calcular la tarifa del tiempo que estuvo un vehiculo en el parquedero.
	  * @param fechaInicial, fecha en la que ingreso el vehiculo.
	  * @param fechaFinal, fecha en la que sale el vehiculo.
	  * @param type, tipo de vehiculo.
	  * @return
	  */
	public int calcularTarifa(Date fechaInicial, Date fechaFinal, String type, int cilindraje) {

		Constantes constantTypeVehicle = constantsRepository.findOne("vehiculo_tipo");

		float diff = (fechaFinal.getTime()-fechaInicial.getTime())/60000;			
		float totalHoras = diff/60;
		float porcentajeDias = totalHoras/24;
		
		//TODO - Hacer metodo con recursividad y con conexi�n a BD.
		int total = 0;
		int valorHora = 0;
		int valorDia = 0;
		
		if(type == constantTypeVehicle.getValor()){

			Constantes constantValorHoraMoto = constantsRepository.findOne("valor_hora_moto");
			Constantes constantValorDiaMoto = constantsRepository.findOne("valor_dia_moto");
			

			valorHora = Integer.parseInt(constantValorHoraMoto.getValor());
			valorDia = Integer.parseInt(constantValorDiaMoto.getValor());

//			valorHora = 500;
//			valorDia = 4000;
			total += totalTarifa(valorHora, valorDia, porcentajeDias, total);
			if(cilindraje > 500){
				total += 2000;
			}
			
		} else {
			
			Constantes constantValorHoraCarro = constantsRepository.findOne("valor_hora_carro");
			Constantes constantValorDiaCarro = constantsRepository.findOne("valor_dia_carro");
			
			valorHora = Integer.parseInt(constantValorHoraCarro.getValor());
			valorDia = Integer.parseInt(constantValorDiaCarro.getValor());
			
//			valorHora = 1000;
//			valorDia = 8000;
			total += totalTarifa(valorHora, valorDia, porcentajeDias, total);

		}
		return total;
	}

	
	/**
	 * Metodo recursivo que permite saber cuanto es la tarifa que debe pagar un vehiculo
	 * por el tiempo que estuvo en el parqueadero.
	 * @param valorHora, valor hora parqueo.
	 * @param valorDia, valor dia parqueo.
	 * @param porcentajeDias, porcentaje de dias que estuvo el vehiculo parqueado.
	 * @param total, total de la tarifa.
	 * @return, retorna el total de la tarifa que estuvo parqueado el vehiculo.
	 */
	public int totalTarifa(int valorHora, int valorDia, double porcentajeDias, int total) {
		
		//Si el vehiculo estuvo entre 0 y 9 horas.
		if(porcentajeDias > 0 && porcentajeDias < 0.375){
			if(porcentajeDias <= 0.04166){
				return valorHora;
			}
			int numHoras = (int) (24 * porcentajeDias);
			total += (int) (numHoras * valorHora);
			
			return total;
			
		} 
		
		//Si el vehiculo estuvo entre 9 y 24 horas.
		else if(porcentajeDias >= 0.375 && porcentajeDias <= 1.0){
			
			total += valorDia;
			
			return totalTarifa(valorHora, valorDia, 0, total);
		}
		
		//Si el vehiculo estuvo más de 24 horas.
		else if(porcentajeDias > 1.0){
			
			porcentajeDias = porcentajeDias - 1;
			total += valorDia;
			
			return totalTarifa(valorHora, valorDia, porcentajeDias, total);
		}
		return total;
		
	}

	/**
	 * Función que permite saber si un vehiculo puede ingresar al parqueadero.
	 * @param placa, placa del vehiculo.
	 * @return retorna true si puede ingresar, false en caso contrario.
	 */
	public boolean puedeIngresar(String placa) {
		
		if(placa.charAt(0) == 'a' || placa.charAt(0)  == 'A') {
			Calendar calendar = Calendar.getInstance();
			int day = calendar.get(Calendar.DAY_OF_WEEK);
			
			if(day == Calendar.SUNDAY || day == Calendar.MONDAY){
				return true;
			} else {
				return false;
			}
		}
		return true;
	}



	/**
	 * Función que permite saber si un vehiculo esta actualmente en el parqueadero
	 * @param vehiculo, entidad vehiculo.
	 * @return true si el vehiculo se encuentra actualmente en el parqueadero, false de lo contrario.
	 */
	public boolean vehiculoEstaParqueado(Vehiculo vehiculo) {
		
		List<Registro> registros = registroRepository.findByVehiculoOrderByFechaEntradaDesc(vehiculo);
		Registro registro = registros.get(0);		
		if(registro == null){
			
			return false;
		}
		if(registro.getFechaSalida() == null){
			return true;
		}
	
		return false;
	}



	/**
	 * Metodo para dar salida a un carro y calcular la tarifa a pagar
	 * por el tiempo que estuvo en el parqueadero.
	 * @param placa, placa del vehiculo.
	 * @return response, la cual contendrá la respuesta al proceso.
	 */
	public String salidaCarro(String placa) {

		Calendar fechaSalida = Calendar.getInstance();
		
		Carro carro = carroRepository.findOne(placa);
		List<Registro> registros = registroRepository.findByVehiculoOrderByFechaEntradaDesc(carro);
		Registro registro = registros.get(0);	
		registro.setFechaSalida(fechaSalida);
		
		registroRepository.save(registro);
		
		//TODO-Agregar la parte de calcular tarifa.
		return "VEHICLE_HAS_LEFT";
	}


	/**
	 * Metodo para dar salida al vehiculo y calcular la tarifa a pagar
	 * por el tiempo que estuvo en el parqueadero.
	 * @param placa, placa del vehiculo.
	 * @return response, la cual contendrá la respuesta al proceso.
	 */
	public String salidaMoto(String placa) {

		Calendar fechaSalida = Calendar.getInstance();
		
		Moto moto= motoRepository.findOne(placa);
		List<Registro> registros = registroRepository.findByVehiculoOrderByFechaEntradaDesc(moto);
		
		Registro registro = registros.get(0);
		registro.setFechaSalida(fechaSalida);
		
		registroRepository.save(registro);
		//TODO-Agregar la parte de calcular tarifa.

		return "VEHICLE_HAS_LEFT";
	}
	
	
}
