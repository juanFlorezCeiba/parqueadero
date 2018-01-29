package co.com.ceiba.parqueadero.domain;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.parqueadero.model.Carro;
import co.com.ceiba.parqueadero.model.Constants;
import co.com.ceiba.parqueadero.model.Moto;
import co.com.ceiba.parqueadero.model.Parqueadero;
import co.com.ceiba.parqueadero.model.Registro;
import co.com.ceiba.parqueadero.model.Vehiculo;
import co.com.ceiba.parqueadero.repository.CarroRepository;
import co.com.ceiba.parqueadero.repository.ConstantsRepository;
import co.com.ceiba.parqueadero.repository.MotoRepository;
import co.com.ceiba.parqueadero.repository.ParqueaderoRepository;
import co.com.ceiba.parqueadero.repository.RegistroRepository;

@Service
public class VigilanteService {

	@Autowired
	private ConstantsRepository constantsRepository;
	@Autowired
	private ParqueaderoRepository parqueaderoRepository;
	@Autowired
	MotoRepository motoRepository;
	@Autowired
	private CarroRepository carroRepository;
	@Autowired
	private RegistroRepository registroRepository;
	
	public VigilanteService(){
	}



	 /**
	  * Funci�n para calcular la tarifa del tiempo que estuvo un vehiculo en el parquedero.
	  * @param fechaInicial
	  * @param fechaFinal
	  * @param type
	  * @return
	  */
	public int calcularTarifa(Date fechaInicial, Date fechaFinal, String type, int cilindraje) {

		float diff = (fechaFinal.getTime()-fechaInicial.getTime())/60000;			
		float totalHoras = diff/60;
		float porcentajeDias = totalHoras/24;
		
		//TODO - Hacer metodo con recursividad y con conexi�n a BD.
		int total = 0;
		int valorHora = 0;
		int valorDia = 0;
		
		if(type == "moto"){

			
//			Constants constantValorHoraMoto = constantsRepository.findOne("valor_hora_moto");
//			System.out.println(constantValorHoraMoto.getId());
//			Constants constantValorDiaMoto = constantsRepository.findOne("valor_dia_moto");
//			System.out.println("SI");
//
//			valorHora = Integer.parseInt(constantValorHoraMoto.getValor());
//			valorDia = Integer.parseInt(constantValorDiaMoto.getValor());

			valorHora = 500;
			valorDia = 4000;
			total += totalTarifa(valorHora, valorDia, porcentajeDias, total);
			if(cilindraje > 500){
				total += 2000;
			}
			
		} else {
//			Constants constantValorHoraCarro = constantsRepository.findOne("valor_hora_carro");
//			Constants constantValorDiaCarro = constantsRepository.findOne("valor_dia_carro");
//			valorHora = Integer.parseInt(constantValorHoraCarro.getValor());
//			valorDia = Integer.parseInt(constantValorDiaCarro.getValor());
			
			valorHora = 1000;
			valorDia = 8000;
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
	 * Función que permite crear un ingreso al parqueadero a una moto.
	 * @param moto, moto la cual ingresará al parqueadero.
	 * @return cadena, la cual indica si ingreso o no al parqueadero.
	 */
	public String crearIngresoMoto(Moto moto) {
		
		Parqueadero parqueadero = parqueaderoRepository.findOne(1);
		
		if(!puedeIngresar(moto.getPlaca())){
			return "IS_NOT_SUNDAY_MONDAY";
		}
	
			int espaciosMotos = parqueadero.getEspaciosMotos();
			
			if(espaciosMotos == 0){
				return "THERE_IS_NOT_SPACE";
			}
			motoRepository.save(moto);
			
			Registro registro = new Registro();
			registro.setVehiculo(moto);
			
			registroRepository.save(registro);
			
			return "HAS BEEN SAVED";

	}



	/**
	 * Función que permite crear un ingreso al parqueadero a un carro.
	 * @param carro, carro el cual ingresará al parqueadero.
	 * @return cadena, la cual indica si ingreso o no al parqueadero.
	 */
	public String crearIngresoCarro(Carro carro) {

		Parqueadero parqueadero = parqueaderoRepository.findOne(1);
		
		if(!puedeIngresar(carro.getPlaca())){
			return "IS_NOT_SUNDAY_MONDAY";
		}
	
			int espaciosCarros = parqueadero.getEspaciosCarros();
			
			if(espaciosCarros == 0){
				return "THERE_IS_NOT_SPACE";
			}
			carroRepository.save(carro);
			
			Registro registro = new Registro();
			registro.setVehiculo(carro);
			
			registroRepository.save(registro);
			
			return "HAS BEEN SAVED";
	}



	public boolean vehiculoEstaGuardado(Vehiculo vehiculo) {
		
		Registro registro = registroRepository.findByVehiculo(vehiculo);
		
		if(registro == null){
			System.out.println("NO EXISTE");
			return false;
		}
		System.out.println("EXISTE");
		return true;
	}
	
	
}
