package br.com.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cursomc.domain.Pedido;
import br.com.cursomc.services.PedidoService;

@RestController
@RequestMapping(path = "/pedidos")
public class PedidoResource {

	@Autowired(required = true)
	private PedidoService service;

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pedido> find(@PathVariable Integer id) {
		var pedido = service.finOnePedido(id);
		return ResponseEntity.ok().body(pedido);
	}

}
