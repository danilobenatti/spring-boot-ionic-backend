package br.com.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cursomc.domain.Produto;
import br.com.cursomc.dto.ProdutoDTO;
import br.com.cursomc.resources.utils.URL;
import br.com.cursomc.services.ProdutoService;

@RestController
@RequestMapping(path = "/produtos")
public class ProdutoResource {

	@Autowired(required = true)
	private ProdutoService service;

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
		var produto = service.find(id);
		return ResponseEntity.ok().body(produto);
	}

	@GetMapping(path = { "/page" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(name = "nome", defaultValue = "") String nome,
			@RequestParam(name = "categorias", defaultValue = "") String categorias,
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "size", defaultValue = "24") Integer size,
			@RequestParam(name = "direction", defaultValue = "ASC") String direction,
			@RequestParam(name = "orderBy", defaultValue = "nome") String orderBy) {
		String nomeDec = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntToList(categorias);
		var list = service.search(nomeDec, ids, page, size, direction, orderBy);
		var listDto = list.map(ProdutoDTO::new);
		return ResponseEntity.ok().body(listDto);
	}

}
