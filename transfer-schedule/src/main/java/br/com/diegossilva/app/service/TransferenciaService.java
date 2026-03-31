package br.com.diegossilva.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import br.com.diegossilva.app.entity.Transferencia;
import br.com.diegossilva.app.repository.TransferenciaRepository;

@Service
@Lazy
public class TransferenciaService {
	
	@Autowired
	private TransferenciaRepository repo;
	
	public Transferencia agendarTransferencia(Transferencia t) {
		return repo.save(t);
	}
	
	public List<Transferencia> transferenciasPorOrigem(Integer idOrigem) {
		return repo.findAllByOrigemId(idOrigem);
	}
	
	public List<Transferencia> transferenciasPorDestino(Integer idDestino) {
		return repo.findAllByDestinoId(idDestino);
	}

}
