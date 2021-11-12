package br.com.cursomc.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Perfil {

	ADMIN(1, "ROLE_ADMIN"), CLIENT(2, "ROLE_CLIENT");

	private int codigo;

	private String descricao;

	public static Perfil toEnum(Integer codigo) {

		if (codigo == null || codigo.toString().isEmpty()) {
			return null;
		}

		for (Perfil iterable : Perfil.values()) {
			if (codigo.equals(iterable.getCodigo())) {
				return iterable;
			}
		}
		throw new IllegalArgumentException(
				"Código [" + codigo + "] inválido para tipo de cliente");
	}

}
