package br.com.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cursomc.domain.Cidade;
import br.com.cursomc.services.CidadeService;

@RestController
@RequestMapping(path = "/cidades")
public class CidadeResource {

	@Autowired
	private CidadeService service;

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cidade> find(@PathVariable Integer id) {
		var categoria = service.buscarUmaCidade(id);
		return ResponseEntity.ok().body(categoria);
	}

}
