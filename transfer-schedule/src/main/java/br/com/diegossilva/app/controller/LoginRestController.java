package br.com.diegossilva.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diegossilva.app.entity.Cliente;
import br.com.diegossilva.app.service.ClienteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/transf-sched/api/auth")
@CrossOrigin(origins = "*")
public class LoginRestController {
	
	@Autowired
	private ClienteService service;
	
	@PostMapping("/login")
	public ResponseEntity<?> autenticar(@RequestBody Cliente cliente) {
		
		String[] msgEToken = service.fazerLogin(cliente);
		
		MultiValueMap<String, String> cabecalho = new LinkedMultiValueMap<>();
		cabecalho.add("Authorization", msgEToken[1]);
		HttpHeaders httpHeaders = HttpHeaders.copyOf(cabecalho);
		
		return ResponseEntity.ok().headers(httpHeaders).body(msgEToken[0]);

	}
	
	@PostMapping("/logout")
	public ResponseEntity<?> sair(HttpServletRequest request, HttpServletResponse response) {
		
		String mensagem = service.fazerLogout(request, response);
		
		
		return ResponseEntity.ok().headers(HttpHeaders.EMPTY).body(mensagem);

	}
	
	@PostMapping("/registrar")
    public ResponseEntity<String> registerUser(@RequestBody Cliente cliente){

        String mensagem = service.fazerRegistro(cliente);
        
        if(mensagem.contains("Erro: ")) {
        	return ResponseEntity.badRequest().body(mensagem);
        } else {
        	return ResponseEntity.ok().body(mensagem);
        }
        

    }
	
}
