package br.com.cursomc.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemPedidoPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "pedido_id", nullable = false,
		foreignKey = @ForeignKey(name = "fk_itempedido__pedido_id",
		foreignKeyDefinition = "foreign key (pedido_id) references pedido(id) on delete cascade"))
	@EqualsAndHashCode.Include
	private Pedido pedido;

	@ManyToOne
	@JoinColumn(name = "produto_id", nullable = false,
		foreignKey = @ForeignKey(name = "fk_itempedido__produto_id",
		foreignKeyDefinition = "foreign key (produto_id) references produto(id) on delete cascade"))
	@EqualsAndHashCode.Include
	private Produto produto;

}
