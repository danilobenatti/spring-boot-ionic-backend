package br.com.cursomc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonTypeName;

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
@JsonTypeName(value = "pagamentoComCartao")
@Entity
@Table(name = "pagamento_com_cartao")
public class PagamentoComCartao extends Pagamento {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "{PagamentoComCartao.numeroDeParcelas.NotNull}")
	@Min(value = 1, message = "{PagamentoComCartao.numeroDeParcelas.Min}")
	@Column(nullable = false)
	private Integer numeroDeParcelas;

	public PagamentoComCartao(Integer id, StatusPagamento status, Pedido pedido,
			Integer numeroDeParcelas) {
		super(id, status, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}

}
