package br.com.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cursomc.domain.Categoria;
import br.com.cursomc.repositories.CategoriaRepository;
import br.com.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public Categoria findOneCategoria(Integer id) {
		Optional<Categoria> optional = repository.findById(id);
		return optional.orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado! ID: "
						+ id + ", tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Categoria update(Categoria obj) {
		findOneCategoria(obj.getId());
		return repository.save(obj);
	}

}
