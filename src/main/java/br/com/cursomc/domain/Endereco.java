package br.com.cursomc.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "endereco_id_seq", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;

	@NotBlank(message = "{Endereco.logradouro.NotBlank}")
	@Column(nullable = false)
	private String logradouro;

	@NotBlank(message = "{Endereco.numero.NotBlank}")
	@Column(length = 15, nullable = false)
	private String numero;

	@Column(length = 15)
	private String complemento;

	@Column(length = 25)
	private String bairro;

	@NotBlank(message = "{Endereco.cep.NotBlank}")
	@Column(length = 10, nullable = false)
	private String cep;

	@JsonIgnore
	@ManyToOne(targetEntity = Cliente.class, optional = false)
	@JoinColumn(name = "cliente_id", nullable = false,
		foreignKey = @ForeignKey(name = "fk_endereco__cliente_id",
		foreignKeyDefinition = "foreign key (cliente_id) references cliente(id) on delete cascade"))
	private Cliente cliente;

	@ManyToOne(targetEntity = Cidade.class, optional = false)
	@JoinColumn(name = "cidade_id", nullable = false,
		foreignKey = @ForeignKey(name = "fk_endereco__cidade_id",
		foreignKeyDefinition = "foreign key (cidade_id) references cidade(id) on delete restrict"))
	private Cidade cidade;

}
