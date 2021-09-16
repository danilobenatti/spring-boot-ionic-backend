package br.com.cursomc.domain;

import javax.persistence.Entity;

import br.com.cursomc.domain.enums.StatusPagamento;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class PagamentoComCartao extends Pagamento {

	private static final long serialVersionUID = 1L;

	private Integer numeroDeParcelas;

	public PagamentoComCartao(Integer id, StatusPagamento status, Pedido pedido,
			Integer numeroDeParcelas) {
		super(id, status, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}

}
