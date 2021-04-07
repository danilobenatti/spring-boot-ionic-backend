package br.com.cursomc.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusPagamento {

	PENDENTE(1, "Pendente"), QUITADO(2, "Quitado"), CANCELADO(3, "Cancelado");

	private int codigo;

	private String descricao;

	public static StatusPagamento toEnum(Integer codigo) {

		if (codigo == null || codigo.toString().isEmpty()) {
			return null;
		}

		for (StatusPagamento iterable : StatusPagamento.values()) {
			if (codigo.equals(iterable.getCodigo())) {
				return iterable;
			}
		}
		throw new IllegalArgumentException("Código " + codigo + " inválido para tipo de pagamento");
	}

}
