package br.com.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.cursomc.domain.Categoria;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoriaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotBlank(message = "Nome da categoria é obrigatório, não pode ser nulo nem vazio.")
	@Size(min = 5, max = 80, message = "Nome da categoria deve ter mínimo de 5 e máximo de 80 caracteres.")
	private String nome;

	public CategoriaDTO(Categoria categoria) {
		super();
		this.id = categoria.getId();
		this.nome = categoria.getNome();
	}

}
