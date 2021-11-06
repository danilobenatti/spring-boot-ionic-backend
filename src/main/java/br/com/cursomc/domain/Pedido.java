package br.com.cursomc.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@Table(name = "pedido")
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "pedido_id_seq", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	@Column(nullable = false)
	private Date instante;

	@OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
	private Pagamento pagamento;

	public Double getValorTotal() {
		var soma = 0.0;
		for (ItemPedido itemPedido : itens) {
			soma = soma + itemPedido.getSubTotal();
		}
		return soma;
	}

	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false, foreignKey = @ForeignKey(name = "fk_pedido__cliente_id", foreignKeyDefinition = "foreign key (cliente_id) references cliente(id)"))
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "endereco_entrega_id", nullable = false, foreignKey = @ForeignKey(name = "fk_pedido__enderecoentrega_id", foreignKeyDefinition = "foreign key (endereco_entrega_id) references endereco(id) on delete cascade"))
	private Endereco enderecoDeEntrega;

	@Builder.Default
	@OneToMany(mappedBy = "id.pedido")
	private Set<ItemPedido> itens = new HashSet<>();

	@Override
	public String toString() {
		NumberFormat number = NumberFormat
				.getCurrencyInstance(new Locale("pt", "BR"));
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy hh:mm",
				new Locale("pt", "BR"));
		StringBuilder builder = new StringBuilder();
		builder.append("Pedido número: ");
		builder.append(getId());
		builder.append("; Instante: ");
		builder.append(date.format(getInstante()));
		builder.append("; Cliente: ");
		builder.append(getCliente().getNome());
		builder.append("; Situação do Pagamento: ");
		builder.append(getPagamento().getStatus().getDescricao());
		builder.append("\nDetalhes:\n");
		for (ItemPedido itemPedido : getItens()) {
			builder.append(itemPedido.toString());
		}
		builder.append("Valor Total: ");
		builder.append(number.format(getValorTotal()));
		return builder.toString();
	}
}
