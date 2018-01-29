package co.com.ceiba.parqueadero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.ceiba.parqueadero.model.Registro;
import co.com.ceiba.parqueadero.model.Vehiculo;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Integer> {

	List<Registro> findByVehiculoOrderByFechaEntradaDesc(Vehiculo vehiculo);
}
