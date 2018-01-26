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

	public int calcularTarifa(Date fechaInicial, Date fechaFinal, String type) {

		float diff = (fechaFinal.getTime()-fechaInicial.getTime())/60000;			
		float totalHoras = diff/60;
		float numDias = totalHoras/24;
		int total = 0;
		int valorHora = 0;
		int valorDia = 0;
		
		if(type == "moto"){

			Constants constantValorHoraMoto = constantsRepository.findOne("valor_hora_moto");
			System.out.println(constantValorHoraMoto.getId());
			Constants constantValorDiaMoto = constantsRepository.findOne("valor_dia_moto");
			System.out.println("SI");

			valorHora = Integer.parseInt(constantValorHoraMoto.getValor());
			valorDia = Integer.parseInt(constantValorDiaMoto.getValor());

		} else {
			Constants constantValorHoraCarro = constantsRepository.findOne("valor_hora_carro");
			Constants constantValorDiaCarro = constantsRepository.findOne("valor_dia_carro");
			valorHora = Integer.parseInt(constantValorHoraCarro.getValor());
			valorDia = Integer.parseInt(constantValorDiaCarro.getValor());
		}
		
		if(numDias > 1.0){
			System.out.println(numDias);

		}
		return total;
	}
}
