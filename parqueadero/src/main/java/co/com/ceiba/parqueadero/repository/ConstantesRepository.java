package co.com.ceiba.parqueadero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.ceiba.parqueadero.model.Constantes;

@Repository
public interface ConstantesRepository extends JpaRepository<Constantes, String> {

}
