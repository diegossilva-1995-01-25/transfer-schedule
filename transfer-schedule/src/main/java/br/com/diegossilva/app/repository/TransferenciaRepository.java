package br.com.diegossilva.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.diegossilva.app.entity.Transferencia;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Integer> {
	
	List<Transferencia> findAllByOrigemId(Integer id);
	
	List<Transferencia> findAllByDestinoId(Integer id);

}
