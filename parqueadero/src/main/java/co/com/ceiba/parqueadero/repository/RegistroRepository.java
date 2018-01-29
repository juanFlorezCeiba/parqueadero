package co.com.ceiba.parqueadero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.ceiba.parqueadero.model.Registro;
import co.com.ceiba.parqueadero.model.Vehiculo;

public interface RegistroRepository extends JpaRepository<Registro, Integer> {

	Registro findByVehiculo(Vehiculo vehiculo);
}
