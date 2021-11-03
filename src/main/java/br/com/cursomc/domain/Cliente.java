package br.com.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.cursomc.domain.enums.TipoCliente;
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
@Table(name = "cliente",
	uniqueConstraints = @UniqueConstraint(name = "uk_cliente__email", columnNames = "email"))
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "cliente_id_seq", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;

	@NotBlank(message = "{Cliente.nome.NotBlank}")
	@Size(min = 5, max = 50, message = "{Cliente.nome.Size}")
	@Column(length = 50, nullable = false)
	private String nome;

	@NotBlank(message = "{Cliente.email.NotBlank}")
	@Email(message = "{Cliente.email.Email}")
	@Column(nullable = false)
	private String email;

	@NotBlank(message = "{Cliente.cpfOuCnpj.NotBlank}")
	@Column(length = 20, nullable = false)
	private String cpfOuCnpj;

//	@NotNull(message = "{Cliente.tipo.NotNull}")
	@Column(nullable = false)
	private Integer tipo;

	public TipoCliente getTipo() {
		return TipoCliente.toEnum(tipo);
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCodigo();
	}

	@Builder.Default
	@Fetch(value = FetchMode.JOIN)
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Endereco> enderecos = new ArrayList<>();

	@Builder.Default
	@Fetch(value = FetchMode.SELECT)
	@ElementCollection
	@CollectionTable(name = "telefone",
		uniqueConstraints = @UniqueConstraint(name = "uk_telefone__numero", columnNames = {"numero"}),
		foreignKey = @ForeignKey(name = "fk_telefone__cliente_id", 
		foreignKeyDefinition = "foreign key (cliente_id) references cliente(id) on delete cascade"))
	@Column(name = "numero", length = 20, nullable = false)
	private Set<String> telefones = new HashSet<>();

	public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipo) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = tipo == null ? null : tipo.getCodigo();
	}

	@JsonIgnore
	@Builder.Default
	@OneToMany(mappedBy = "cliente")
	private transient List<Pedido> pedidos = new ArrayList<>();

}