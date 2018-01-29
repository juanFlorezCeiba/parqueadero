package co.com.ceiba.parqueadero.domain;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.parqueadero.model.Constants;
import co.com.ceiba.parqueadero.model.Registro;
import co.com.ceiba.parqueadero.repository.ConstantsRepository;

@Service
public class VigilanteService {

	@Autowired
	private ConstantsRepository constantsRepository;
	
	public VigilanteService(){
	}

	 /**
	  * Servicio que permite crear un registro.
	  * @param registro, entidad registro.
	  * @return verdadero si se creo el registro, falso si no.
	  */
	 public boolean crearIngreso(Registro registro) {
		
		return false;
	}

	 /**
	  * Función para calcular la tarifa del tiempo que estuvo un vehiculo en el parquedero.
	  * @param fechaInicial
	  * @param fechaFinal
	  * @param type
	  * @return
	  */
	public int calcularTarifa(Date fechaInicial, Date fechaFinal, String type, int cilindraje) {

		float diff = (fechaFinal.getTime()-fechaInicial.getTime())/60000;			
		float totalHoras = diff/60;
		float totalDias = totalHoras/24;
		System.out.println("El total de dias es ");
		
		//TODO - Hacer metodo con recursividad y con conexión a BD.
		int total = 0;
		int valorHora = 0;
		int valorDia = 0;
		int numDias = 0;
		float numHoras = 0;
		
		if(type == "moto"){

			if(cilindraje > 500){
				total += 2000;
			}
//			Constants constantValorHoraMoto = constantsRepository.findOne("valor_hora_moto");
//			System.out.println(constantValorHoraMoto.getId());
//			Constants constantValorDiaMoto = constantsRepository.findOne("valor_dia_moto");
//			System.out.println("SI");
//
//			valorHora = Integer.parseInt(constantValorHoraMoto.getValor());
//			valorDia = Integer.parseInt(constantValorDiaMoto.getValor());

			valorHora = 500;
			valorDia = 4000;
			
			
		} else {
//			Constants constantValorHoraCarro = constantsRepository.findOne("valor_hora_carro");
//			Constants constantValorDiaCarro = constantsRepository.findOne("valor_dia_carro");
//			valorHora = Integer.parseInt(constantValorHoraCarro.getValor());
//			valorDia = Integer.parseInt(constantValorDiaCarro.getValor());
		}
		numDias = (int) Math.abs(totalDias);
		numHoras =  (int)(24*(totalDias-numDias)) + 1;
		
		
		if(totalDias >= 0.375 && totalDias < 1.0){

			total += (int) ((numDias * valorDia) + (numHoras * valorHora));
			
		} else if (totalDias < 0.375){
			total += (int) (numHoras * valorHora);
		} else{
			
		}
		
		System.out.println("El total es: " + total);
		return total;
	}
}
