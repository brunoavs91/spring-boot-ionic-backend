package com.bruno.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.bruno.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto,Date instantePedido) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(instantePedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());
		
	}
}
