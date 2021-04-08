package br.com.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cursomc.domain.Categoria;
import br.com.cursomc.services.CategoriaService;

@RestController
@RequestMapping(path = "/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		Categoria categoria = service.buscar(id);
		return ResponseEntity.ok().body(categoria);
	}

}
