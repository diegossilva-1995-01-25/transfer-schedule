package br.com.diegossilva.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diegossilva.app.entity.Conta;
import br.com.diegossilva.app.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
    private ContaRepository repo;
	
	public Conta resgateContaPorId(Integer id) {
		return repo.findById(id).get();
	}
	
	public List<Conta> resgateTodosContas() {
		return repo.findAll();
	}
	
	public Conta cadastrarConta(Conta conta) {
		return repo.save(conta);
	}
	
	public Conta alterarConta(Integer id, Conta conta) {
		conta.setId(id);
        return repo.save(conta);
	}
	
	public void excluirConta(Integer id) {
		repo.deleteById(id);
	}
	
}
