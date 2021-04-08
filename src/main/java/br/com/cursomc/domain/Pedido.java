package br.com.cursomc.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@RequiredArgsConstructor
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@NonNull
	private Integer id;

	@JsonFormat(pattern = "dd/MM/yyyy hh:mm")
	@NonNull
	private Date instante;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
	private Pagamento pagamento;

	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false,
		foreignKey = @ForeignKey(name= "fk_pedido_cliente_id",
		foreignKeyDefinition = "foreign key (cliente_id) references cliente(id) on delete cascade"))
	@NonNull
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "endereco_entrega_id", nullable = false, 
		foreignKey = @ForeignKey(name = "fk_pedido_enderecoentrega_id",
		foreignKeyDefinition = "foreign key (endereco_entrega_id) references endereco(id) on delete cascade"))
	@NonNull
	private Endereco enderecoDeEntrega;

	@OneToMany(mappedBy = "id.pedido")
	private Set<ItemPedido> itens = new HashSet<>();
}
