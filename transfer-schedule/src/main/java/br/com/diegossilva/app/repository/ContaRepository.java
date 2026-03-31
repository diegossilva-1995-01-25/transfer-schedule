package br.com.diegossilva.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.diegossilva.app.entity.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {

}
