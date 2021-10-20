package br.com.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.cursomc.domain.Cliente;
import br.com.cursomc.services.validation.ClienteUpdate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@ClienteUpdate
public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotBlank(message = "{Cliente.nome.NotBlank}")
	@Size(min = 5, max = 50, message = "{Cliente.nome.Size}")
	private String nome;

	@NotBlank(message = "{Cliente.email.NotBlank}")
	@Email(message = "{Cliente.email.Email}")
	private String email;

	public ClienteDTO(Cliente cliente) {
		id = cliente.getId();
		nome = cliente.getNome();
		email = cliente.getEmail();
	}

}
