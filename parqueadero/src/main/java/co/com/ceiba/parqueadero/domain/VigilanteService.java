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

	
	/*
	 * Metodo recursivo que permite saber cuanto es la tarifa que debe pagar un vehiculo
	 * por el tiempo que estuvo en el parqueadero.
	 */
	public int totalTarifa(int valorHora, int valorDia, double porcentajeHoras, int total) {
		
		//Si el vehiculo estuvo entre 0 y 9 horas.
		if(porcentajeHoras > 0 && porcentajeHoras < 0.375){
			if(porcentajeHoras <= 0.04166){
				return valorHora;
			}
			int numHoras = (int) (24 * porcentajeHoras);
			total += (int) (numHoras * valorHora);
			
			return total;
			
		} 
		
		//Si el vehiculo estuvo entre 9 y 24 horas.
		else if(porcentajeHoras >= 0.375 && porcentajeHoras <= 1.0){
			
			total += valorDia;
			
			return totalTarifa(valorHora, valorDia, 0, total);
		}
		
		//Si el vehiculo estuvo más de 24 horas.
		else if(porcentajeHoras > 1.0){
			
			porcentajeHoras = porcentajeHoras - 1;
			total += valorDia;
			
			return totalTarifa(valorHora, valorDia, porcentajeHoras, total);
		}
		return total;
		
	}
	
	
}
