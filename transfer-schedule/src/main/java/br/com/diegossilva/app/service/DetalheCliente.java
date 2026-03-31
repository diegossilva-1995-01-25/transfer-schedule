package br.com.diegossilva.app.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.diegossilva.app.entity.Cliente;
import br.com.diegossilva.app.repository.ClienteRepository;

@Service
public class DetalheCliente implements UserDetailsService {

	private final ClienteRepository repo;

	public DetalheCliente(ClienteRepository repo) {
		this.repo = repo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String cpf) {
		Cliente c = repo.findById(cpf)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Cliente não encontrado: "+ cpf));
		
		return org.springframework.security.core.userdetails.User.builder()
			.username(c.getCpf())
			.password(c.getSenha())
			.build();
	}
	
}
