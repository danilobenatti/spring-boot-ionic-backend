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
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date instante;

	@JsonManagedReference
	@OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
	private Pagamento pagamento;

	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false,
		foreignKey = @ForeignKey(name= "fk_pedido_cliente_id",
		foreignKeyDefinition = "foreign key (cliente_id) references cliente(id)"))
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "endereco_entrega_id", nullable = false, 
		foreignKey = @ForeignKey(name = "fk_pedido__enderecoentrega_id",
		foreignKeyDefinition = "foreign key (endereco_entrega_id) references endereco(id) on delete cascade"))
	private Endereco enderecoDeEntrega;

	@Builder.Default
	@OneToMany(mappedBy = "id.pedido")
	private Set<ItemPedido> itens = new HashSet<>();

}
