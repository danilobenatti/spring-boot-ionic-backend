package br.com.cursomc.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;

	private String logradouro;

	private String numero;

	private String complemento;

	private String bairro;

	private String cep;

	@ManyToOne(targetEntity = Cliente.class, optional = false)
	@JoinColumn(name = "cliente_id", nullable = false,
		foreignKey = @ForeignKey(name = "fk_endereco_cliente_id", 
		foreignKeyDefinition = "foreign key (cliente_id) references cliente(id) on delete cascade"))
	private Cliente cliente;

	@ManyToOne(targetEntity = Cidade.class, optional = false)
	@JoinColumn(name = "cidade_id", nullable = false, 
		foreignKey = @ForeignKey(name = "fk_endereco_cidade_id",
		foreignKeyDefinition = "foreign key (cidade_id) references cidade(id) on delete cascade"))
	private Cidade cidade;

}
