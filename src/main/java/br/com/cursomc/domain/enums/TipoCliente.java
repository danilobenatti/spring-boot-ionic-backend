package br.com.cursomc.domain.enums;

public enum TipoCliente {

	PESSOAFISICA(1, "Pessoa Física"), PESSOAJURIDICA(2, "Pessoa Jurídica");

	private int codigo;

	private String descricao;

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	private TipoCliente(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public static TipoCliente toEnum(Integer codigo) {

		if (codigo == null || codigo.toString().isEmpty()) {
			return null;
		}

		for (TipoCliente iterable : TipoCliente.values()) {
			if (codigo.equals(iterable.getCodigo())) {
				return iterable;
			}
		}
		throw new IllegalArgumentException("Código " + codigo + " inválido para tipo de cliente");
	}

}
