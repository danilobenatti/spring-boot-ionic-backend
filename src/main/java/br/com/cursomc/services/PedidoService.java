package br.com.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cursomc.domain.Pedido;
import br.com.cursomc.repositories.PedidoRepository;
import br.com.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	public Pedido finOnePedido(Integer id) {
		Optional<Pedido> optional = repository.findById(id);
		return optional.orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado! ID: "
						+ id + ", tipo: " + Pedido.class.getName()));
	}

}
