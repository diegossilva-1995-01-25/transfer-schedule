package br.com.diegossilva.app.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Transferencia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "conta_origin_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Conta origem;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "conta_destination_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Conta destino;
	
	@Column(nullable = false)
	private double valorTransferencia;
	
	@Column(nullable = false)
	private double taxa;
	
	@Column(nullable = false)
	private int dataAgendamento;
	
	@Column(nullable = false)
	private int dataTransferencia;

}
