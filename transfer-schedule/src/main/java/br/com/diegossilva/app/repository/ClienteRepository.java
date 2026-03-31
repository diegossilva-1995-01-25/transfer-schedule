package br.com.diegossilva.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.diegossilva.app.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String>  {

	boolean existsById(String id);
	
}
