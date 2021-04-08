package br.com.cursomc.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import br.com.cursomc.domain.enums.StatusPagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@EqualsAndHashCode.Include
	private Integer id;

	private int status;

	@OneToOne
	@JoinColumn(name = "pedido_id", nullable = false,
		foreignKey = @ForeignKey(name = "fk_pagamento_pedido_id",
		foreignKeyDefinition = "foreign key (pedido_id) references pedido(id) on delete cascade"))
	@MapsId
	private Pedido pedido;

	protected Pagamento(Integer id, StatusPagamento status, Pedido pedido) {
		super();
		this.id = id;
		this.status = status.getCodigo();
		this.pedido = pedido;
	}

}
