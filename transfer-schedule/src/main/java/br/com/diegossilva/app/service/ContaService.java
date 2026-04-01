package br.com.diegossilva.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diegossilva.app.entity.Cliente;
import br.com.diegossilva.app.entity.Conta;
import br.com.diegossilva.app.repository.ClienteRepository;
import br.com.diegossilva.app.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
    private ContaRepository repo;
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	public Conta resgateContaPorId(Integer id) {
		return repo.findById(id).get();
	}
	
	public List<Conta> resgateTodosContas() {
		return repo.findAll();
	}
	
	public List<Conta> resgateTodosContasPorCliente(Cliente c) {
		return repo.findAllByClienteCpf(c);
	}
	
	public Conta cadastrarConta(Conta conta) {
		
		Cliente c = clienteRepo.getReferenceById(conta.getCliente().getCpf());
		conta.setCliente(c);
		
		return repo.save(conta);
	}
	
	public Conta alterarConta(Integer id, Conta conta) {
		conta.setId(id);
		
		Cliente c = clienteRepo.getReferenceById(conta.getCliente().getCpf());
		conta.setCliente(c);
		
        return repo.save(conta);
	}
	
	public void excluirConta(Integer id) {
		repo.deleteById(id);
	}
	
}
