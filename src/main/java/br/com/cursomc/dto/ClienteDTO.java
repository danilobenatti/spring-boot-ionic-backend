package br.com.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.cursomc.domain.Cliente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotBlank(message = "Nome do cliente é obrigatório, não pode ser nulo nem vazio.")
	@Size(min = 5, max = 50, message = "Nome do cliente deve ter mínimo de 5 e máximo de 80 caracteres.")
	private String nome;

	@NotBlank(message = "Email é obrigatório, não pode ser nulo nem vazio.")
	@Email(message = "Email inválido.")
	private String email;

	public ClienteDTO(Cliente cliente) {
		id = cliente.getId();
		nome = cliente.getNome();
		email = cliente.getEmail();
	}

}
