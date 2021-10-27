package br.com.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.cursomc.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PagamentoComBoleto boleto,
			Date instanteDoPedido) {
		var dataFuturaDePagto = Calendar.getInstance();
		dataFuturaDePagto.setTime(instanteDoPedido);
		dataFuturaDePagto.add(Calendar.DAY_OF_MONTH, 7);
		boleto.setDataVencimento(dataFuturaDePagto.getTime());
	}
}
