package br.com.cursomc.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import br.com.cursomc.domain.Produto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProdutoDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotBlank(message = "{Produto.nome.NotBlank}")
	@Column(length = 150, nullable = false)
	private String nome;

	@PositiveOrZero(message = "{Produto.preco.PositiveOrZero}")
	@Column(nullable = false)
	private Double preco;

	public ProdutoDTO(Produto obj) {
		id = obj.getId();
		nome = obj.getNome();
		preco = obj.getPreco();
	}
}
