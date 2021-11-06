package br.com.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.cursomc.domain.Categoria;
import br.com.cursomc.domain.Pedido;
import br.com.cursomc.domain.Produto;
import br.com.cursomc.repositories.CategoriaRepository;
import br.com.cursomc.repositories.ProdutoRepository;
import br.com.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Produto find(Integer id) {
		Optional<Produto> optional = produtoRepository.findById(id);
		return optional.orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado! ID: "
						+ id + ", tipo: " + Pedido.class.getName()));
	}

	public Page<Produto> search(String nome, List<Integer> ids, Integer page,
			Integer size, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, size,
				Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return produtoRepository
				.findDistinctByNomeContainingIgnoreCaseAndCategoriasIn(nome,
						categorias, pageRequest);
	}

}
