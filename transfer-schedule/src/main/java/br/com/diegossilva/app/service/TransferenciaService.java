package br.com.diegossilva.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import br.com.diegossilva.app.entity.Conta;
import br.com.diegossilva.app.entity.Transferencia;
import br.com.diegossilva.app.repository.ContaRepository;
import br.com.diegossilva.app.repository.TransferenciaRepository;
import br.com.diegossilva.app.utils.CalculoTaxa;

@Service
@Lazy
public class TransferenciaService {
	
	@Autowired
	private TransferenciaRepository repo;
	
	@Autowired
	private ContaRepository contaRepo;
	
	@Autowired
	private CalculoTaxa ct;
	
	public Transferencia agendarTransferencia(Transferencia t) throws Exception {
		
		Conta contaOrigem = contaRepo.getReferenceById(t.getOrigem().getId());
		t.setOrigem(contaOrigem);
		
		Conta contaDestino = contaRepo.getReferenceById(t.getDestino().getId());
		t.setDestino(contaDestino);
		
		t.setTaxa(ct.calcularTaxa(String.valueOf(t.getDataAgendamento()), String.valueOf(t.getDataTransferencia())));
		
		if(t.getTaxa() != -1) {
			return repo.save(t);
		} else {
			throw new Exception("Taxa inválida, mais de 50 dias não permitido");
		}
		
	}
	
	public List<Transferencia> transferenciasPorOrigem(Integer idOrigem) {
		return repo.findAllByOrigemId(idOrigem);
	}
	
	public List<Transferencia> transferenciasPorDestino(Integer idDestino) {
		return repo.findAllByDestinoId(idDestino);
	}
	
	public Transferencia pegarTransferenciaPorId(Integer id) {
		return repo.getReferenceById(id);
	}
	
	public Transferencia alterarTransferencia(Integer id, Transferencia t) throws Exception {
		
		t.setId(id);
		
		Conta contaOrigem = contaRepo.getReferenceById(t.getOrigem().getId());
		t.setOrigem(contaOrigem);
		
		Conta contaDestino = contaRepo.getReferenceById(t.getDestino().getId());
		t.setDestino(contaDestino);
		
		t.setTaxa(ct.calcularTaxa(String.valueOf(t.getDataAgendamento()), String.valueOf(t.getDataTransferencia())));
		
		if(t.getTaxa() != -1) {
			return repo.save(t);
		} else {
			throw new Exception("Taxa inválida, mais de 50 dias não permitido");
		}
	}
	
	public void excluirTransferencia(Integer id) {
		repo.deleteById(id);
	}

}
