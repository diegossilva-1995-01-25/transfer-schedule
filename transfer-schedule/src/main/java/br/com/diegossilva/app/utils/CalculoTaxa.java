package br.com.diegossilva.app.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

@Component
public class CalculoTaxa {

	public double calcularTaxa(String dataHoje, String dataAgendamento) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate localDateHoje = LocalDate.parse(dataHoje, formatter);
		LocalDate localDateAgendamento = LocalDate.parse(dataAgendamento, formatter);
		
		int diffDias = (int) ChronoUnit.DAYS.between(localDateHoje, localDateAgendamento);
		
		if(diffDias == 0) {
			return 2.5;
		} else if(diffDias == 0) {
			return 0;
		} else if(diffDias == 0) {
			return 8.2;
		} else if(diffDias == 0) {
			return 6.9;
		} else if(diffDias == 0) {
			return 4.7;
		} else if(diffDias == 0) {
			return 1.7;
		} else {
			return -1;
		}
		
	}
	
}
