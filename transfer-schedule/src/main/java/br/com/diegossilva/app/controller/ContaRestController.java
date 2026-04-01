package br.com.diegossilva.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diegossilva.app.entity.Cliente;
import br.com.diegossilva.app.entity.Conta;
import br.com.diegossilva.app.service.ContaService;

@RestController
@RequestMapping("/transf-sched/conta")
public class ContaRestController {
	
	@Autowired
	private ContaService service;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<String> cadastrarConta(@RequestBody Conta conta) {
		
		conta = service.cadastrarConta(conta);
		
		return ResponseEntity.ok().body("Conta cadastrada: " + conta.getId());
		
	}
	
	@PostMapping("/alterar/{id}")
	public ResponseEntity<String> alterarConta(@PathVariable Integer id, @RequestBody Conta conta) {
		
		conta = service.alterarConta(id, conta);
		
		return ResponseEntity.ok().body("Conta alterada: " + conta.getId());
		
	}
	
	@PostMapping("/excluir/{id}")
    public ResponseEntity<String> excluirConta(@PathVariable Integer id) {
        service.excluirConta(id);
        return ResponseEntity.ok().body("Conta excluída com sucesso: " + id);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<Conta> resgateBeneficiarioPorId(@PathVariable Integer id){
        return ResponseEntity.ok(service.resgateContaPorId(id));
    }
    
    @GetMapping("/todos")
    public ResponseEntity<List<Conta>> resgateTodosBeneficiarios(){
        return ResponseEntity.ok(service.resgateTodosContas());
    }
    
    @GetMapping("/todos/{id}")
    public ResponseEntity<List<Conta>> resgateTodosBeneficiariosPorCliente(Cliente c){
        return ResponseEntity.ok(service.resgateTodosContasPorCliente(c));
    }

}
