package br.com.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;

	private String nome;

	private Double preco;
	
	@JsonBackReference
	@ManyToMany
	@JoinTable(name = "produto_categoria", 
		uniqueConstraints = @UniqueConstraint(name = "uk_produtoid_categoriaid", columnNames = {"produto_id","categoria_id"}),
			joinColumns = @JoinColumn(name = "produto_id", nullable = false,
				foreignKey = @ForeignKey(name = "fk_produto_categoria_produtoid", 
				foreignKeyDefinition = "FOREIGN KEY (produto_id) REFERENCES produto(id) ON DELETE CASCADE")),
			inverseJoinColumns = @JoinColumn(name = "categoria_id", nullable = false,
				foreignKey = @ForeignKey(name = "fk_produto_categoria_categoriaid", 
				foreignKeyDefinition = "FOREIGN KEY (categoria_id) REFERENCES categoria(id) ON DELETE CASCADE")))
	private List<Categoria> categorias = new ArrayList<>();

	public Produto(Integer id, String nome, Double preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}

}
