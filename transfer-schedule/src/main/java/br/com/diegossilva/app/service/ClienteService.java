package br.com.diegossilva.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import br.com.diegossilva.app.entity.Cliente;
import br.com.diegossilva.app.repository.ClienteRepository;
import br.com.diegossilva.app.security.JWTHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
@Lazy
public class ClienteService {
	
	@Autowired
    private AuthenticationManager gerenciadorAutenticacao;
	
	private ClienteRepository repo;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SecurityContextLogoutHandler logoutHandler;
	
	public ClienteService(ClienteRepository repo) {
		this.repo = repo;
	}
	
	public String[] fazerLogin(Cliente cliente) {
		
		String[] respostas = new String[2];
		
		Authentication autenticacao = gerenciadorAutenticacao.authenticate(
				new UsernamePasswordAuthenticationToken(cliente.getCpf(), cliente.getSenha()));
		
		String token = JWTHelper.generateToken(cliente.getCpf());
		SecurityContextHolder.getContext().setAuthentication(autenticacao);
		
		cliente = repo.findById(cliente.getCpf()).get();
		
		respostas[0] = "Seja bem-vindo, " + cliente.getNome();
		respostas[1] = token;
		
		return respostas;
		
	}
	
	public String fazerRegistro(Cliente cliente) {
		
		if(repo.existsById(cliente.getCpf())){
            return "Erro: E-mail já existe na base de dados.";
        }
		
		cliente.setSenha(passwordEncoder.encode(cliente.getSenha()));

        repo.save(cliente);
		
		return "Você foi registrado com sucesso no sistema, " + cliente.getNome();
		
	}
	
	public String fazerLogout(HttpServletRequest request, HttpServletResponse response) {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication != null) {
	        logoutHandler.logout(request, response, authentication);
	    }
	    SecurityContextHolder.clearContext();
	    return "Sessão encerrada.";
	}

}
