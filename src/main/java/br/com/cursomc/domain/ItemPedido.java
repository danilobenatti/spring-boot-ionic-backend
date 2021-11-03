package br.com.cursomc.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "item_pedido")
public class ItemPedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	@EqualsAndHashCode.Include
	private ItemPedidoPK id = new ItemPedidoPK();

	@PositiveOrZero(message = "{ItemPedido.desconto.PositiveOrZero}")
	@Column(nullable = false, columnDefinition = "double DEFAULT 0")
	private Double desconto;

	@PositiveOrZero(message = "{ItemPedido.quantidade.PositiveOrZero}")
	@Min(value = 1, message = "{ItemPedido.quantidade.Min}")
	@Column(nullable = false)
	private Integer quantidade;

	@PositiveOrZero(message = "{ItemPedido.preco.PositiveOrZero}")
	@Column(nullable = false)
	private Double preco;

	public ItemPedido(Pedido pedido, Produto produto, Double desconto,
			Integer quantidade, Double preco) {
		super();
		id.setPedido(pedido);
		id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	@JsonIgnore
	public Pedido getPedido() {
		return id.getPedido();
	}

	public void setPedido(Pedido pedido) {
		id.setPedido(pedido);
	}

	public Produto getProduto() {
		return id.getProduto();
	}

	public void setProduto(Produto produto) {
		id.setProduto(produto);
	}

	public Double getSubTotal() {
		return (preco - desconto) * quantidade;
	}

}
