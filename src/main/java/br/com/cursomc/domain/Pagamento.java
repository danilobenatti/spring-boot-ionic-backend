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

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.cursomc.domain.enums.StatusPagamento;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public abstract class Pagamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@EqualsAndHashCode.Include
	private Integer id;

	private Integer status;

	public StatusPagamento getStatus() {
		return StatusPagamento.toEnum(status);
	}

	public void setStatus(StatusPagamento status) {
		this.status = status.getCodigo();
	}

	@JsonBackReference
	@OneToOne
	@JoinColumn(name = "pedido_id", nullable = false,
		foreignKey = @ForeignKey(name = "fk_pagamento_pedido_id",
		foreignKeyDefinition = "foreign key (pedido_id) references pedido(id) on delete cascade"))
	@MapsId
	private Pedido pedido;

	public Pagamento(Integer id, StatusPagamento status, Pedido pedido) {
		super();
		this.id = id;
		this.status = status.getCodigo();
		this.pedido = pedido;
	}

}
