package br.com.diegossilva.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.diegossilva.app.entity.Cliente;
import br.com.diegossilva.app.entity.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {
	
	List<Conta> findAllByClienteCpf(Cliente c);

}
