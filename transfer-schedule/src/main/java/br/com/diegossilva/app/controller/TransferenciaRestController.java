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
	
	@GetMapping("/todos-origem/{id}")
	public ResponseEntity<List<Transferencia>> capturarTodasTransferenciasPorOrigem(@PathVariable Integer idOrigem) {
		return ResponseEntity.ok(service.transferenciasPorOrigem(idOrigem));
	}
	
	@GetMapping("/todos-destino/{id}")
	public ResponseEntity<List<Transferencia>> capturarTodasTransferenciasPorDestino(@PathVariable Integer idDestino) {
		return ResponseEntity.ok(service.transferenciasPorDestino(idDestino));
	}
	
	@PostMapping("/{id}/agendar")
	public ResponseEntity<Transferencia> agendarTransferencia(@RequestBody Transferencia transferencia) {
		return ResponseEntity.ok(service.agendarTransferencia(transferencia));
	}
	
	/*
	@PostMapping("/{id}/alterar")
	public ResponseEntity<Transferencia> alterarTransferencia(@RequestBody Transferencia transferencia) {
		return ResponseEntity.ok(service.agendarTransferencia(transferencia));
	}
	
	@PostMapping("/{id}/agendar")
	public ResponseEntity<Transferencia> cancelarTransferencia(@RequestBody Transferencia transferencia) {
		return ResponseEntity.ok(service.agendarTransferencia(transferencia));
	}
	*/

}
