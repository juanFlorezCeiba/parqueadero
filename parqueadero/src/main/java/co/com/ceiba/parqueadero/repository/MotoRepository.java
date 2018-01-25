package co.com.ceiba.parqueadero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.ceiba.parqueadero.model.Moto;

public interface MotoRepository extends JpaRepository<Moto, String> {

}
