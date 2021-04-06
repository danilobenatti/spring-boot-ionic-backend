package br.com.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Estado implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;

	@Column(length = 20)
	private String nome;

	@Column(length = 2)
	private String sigla;

	@JsonBackReference
	@OneToMany(mappedBy = "estado")
	private List<Cidade> cidades = new ArrayList<>();

	public Estado(Integer id, String nome, String sigla) {
		super();
		this.id = id;
		this.nome = nome;
		this.sigla = sigla;
	}

}
