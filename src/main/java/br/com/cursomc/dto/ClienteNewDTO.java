package br.com.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.cursomc.services.validation.ClienteInsert;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@ClienteInsert
public class ClienteNewDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "{Cliente.nome.NotBlank}")
	@Size(min = 5, max = 50, message = "{Cliente.nome.Size}")
	private String nome;

	@NotBlank(message = "{Cliente.email.NotBlank}")
	@Email(message = "{Cliente.email.Email}")
	private String email;

	@NotBlank(message = "{Cliente.cpfOuCnpj.NotBlank}")
	private String cpfOuCnpj;

	private Integer tipo;

	@NotBlank(message = "{Cliente.logradouro.NotBlank}")
	private String logradouro;

	@NotBlank(message = "{Cliente.numero.NotBlank}")
	private String numero;

	private String complemento;

	private String bairro;

	@NotBlank(message = "{Cliente.cep.NotBlank}")
	private String cep;

	@NotBlank(message = "{Cliente.telefone.NotBlank}")
	private String telefone1;

	private String telefone2;

	private String telefone3;

	private Integer cidadeId;

}
