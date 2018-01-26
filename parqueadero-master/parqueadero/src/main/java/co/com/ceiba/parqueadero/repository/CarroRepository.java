package co.com.ceiba.parqueadero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.ceiba.parqueadero.model.Carro;

@Repository
public interface CarroRepository extends JpaRepository<Carro, String> {

}
