package br.com.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

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
@Table(name = "produto",
	uniqueConstraints = @UniqueConstraint(name= "uk_produto__nome", columnNames = "nome"))
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "produto_id_seq", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;

	@NotBlank(message = "{Produto.nome.NotBlank}")
	@Column(length = 150, nullable = false)
	private String nome;

	@PositiveOrZero(message = "{Produto.preco.PositiveOrZero}")
	@Column(nullable = false, columnDefinition = "double precision default 0")
	private Double preco;

	public Produto(Integer id, String nome, Double preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}

	@JsonIgnore
	public List<Pedido> getPedidos() {
		List<Pedido> lista = new ArrayList<>();
		for (ItemPedido itemPedido : itens) {
			lista.add(itemPedido.getPedido());
		}
		return lista;
	}

	@Builder.Default
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "produto_categoria",
		uniqueConstraints = @UniqueConstraint(name = "uk_produtoid_categoriaid",
			columnNames = {"produto_id", "categoria_id"}),
		joinColumns = @JoinColumn(name = "produto_id", nullable = false,
			foreignKey = @ForeignKey(name = "fk_produto__produto_id",
				foreignKeyDefinition = "foreign key (produto_id) references produto(id) on delete restrict")),
		inverseJoinColumns = @JoinColumn(name = "categoria_id", nullable = false,
			foreignKey = @ForeignKey(name = "fk_produto__categoria_id",
				foreignKeyDefinition = "foreign key (categoria_id) references categoria(id) on delete restrict")))
	private List<Categoria> categorias = new ArrayList<>();

	@Builder.Default
	@JsonIgnore
	@OneToMany(mappedBy = "id.produto")
	private transient Set<ItemPedido> itens = new HashSet<>();

}
