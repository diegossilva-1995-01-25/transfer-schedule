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

import br.com.diegossilva.app.entity.Transferencia;
import br.com.diegossilva.app.service.TransferenciaService;

@RestController
@RequestMapping("/transf-sched/transf")
public class TransferenciaRestController {
	
	@Autowired
	private TransferenciaService service;
	
	@GetMapping("/todos-origem/{idOrigem}")
	public ResponseEntity<List<Transferencia>> capturarTodasTransferenciasPorOrigem(@PathVariable("idOrigem") Integer idOrigem) {
		return ResponseEntity.ok(service.transferenciasPorOrigem(idOrigem));
	}
	
	@GetMapping("/todos-destino/{idDestino}")
	public ResponseEntity<List<Transferencia>> capturarTodasTransferenciasPorDestino(@PathVariable("idDestino") Integer idDestino) {
		return ResponseEntity.ok(service.transferenciasPorDestino(idDestino));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Transferencia> pegarTransferenciaPorId(@PathVariable Integer id) {
		return ResponseEntity.ok(service.pegarTransferenciaPorId(id));
	}
	
	@PostMapping("/agendar")
	public ResponseEntity<Transferencia> agendarTransferencia(@RequestBody Transferencia transferencia) {
		return ResponseEntity.ok(service.agendarTransferencia(transferencia));
	}
	
	@PostMapping("/alterar/{id}")
	public ResponseEntity<Transferencia> alterarTransferencia(@PathVariable Integer id, @RequestBody Transferencia transferencia) {
		return ResponseEntity.ok(service.alterarTransferencia(id, transferencia));
	}
	
	@PostMapping("/excluir/{id}")
	public ResponseEntity<String> cancelarTransferencia(@PathVariable Integer id) {
		service.excluirTransferencia(id);
		return ResponseEntity.ok().body("Transferência cancelada com sucesso: " + id);
	}

}
