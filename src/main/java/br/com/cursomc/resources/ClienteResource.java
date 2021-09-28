package br.com.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cursomc.domain.Cliente;
import br.com.cursomc.dto.ClienteDTO;
import br.com.cursomc.services.ClienteService;

@RestController
@RequestMapping(path = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		var cliente = service.find(id);
		return ResponseEntity.ok().body(cliente);
	}

	@PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto,
			@PathVariable Integer id) {
		var obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(path = {"", "/"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ClienteDTO>> findAll() {
		var list = service.findAll();
		var objDto = list.stream()
				.map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(objDto);
	}

	@GetMapping(path = {"/page"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "size", defaultValue = "24") Integer size,
			@RequestParam(name = "direction", defaultValue = "ASC") String direction,
			@RequestParam(name = "orderBy", defaultValue = "nome") String orderBy) {
		var list = service.findPage(page, size, direction, orderBy);
		var listDto = list.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}

}
