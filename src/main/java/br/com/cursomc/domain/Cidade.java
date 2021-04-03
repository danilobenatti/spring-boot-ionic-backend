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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Cidade implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;
	
	@Column(length = 30)
	private String nome;
	
	@ManyToOne(targetEntity = Estado.class, optional = false)
	@JoinColumn(name = "estado_id", nullable = false, 
		foreignKey = @ForeignKey(name = "fk_cidade_estado_id", 
		foreignKeyDefinition = "foreign key (estado_id) references estado(id) on delete cascade"))
	private Estado estado;

}
