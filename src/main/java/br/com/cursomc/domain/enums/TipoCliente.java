package br.com.cursomc.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoCliente {

	PESSOAFISICA(1, "Pessoa Física"), PESSOAJURIDICA(2, "Pessoa Jurídica");

	private int codigo;

	private String descricao;

	public static TipoCliente toEnum(Integer codigo) {

		if (codigo == null || codigo.toString().isEmpty()) {
			return null;
		}

		for (TipoCliente iterable : TipoCliente.values()) {
			if (codigo.equals(iterable.getCodigo())) {
				return iterable;
			}
		}
		throw new IllegalArgumentException(
				"Código [" + codigo + "] inválido para tipo de cliente");
	}

}
