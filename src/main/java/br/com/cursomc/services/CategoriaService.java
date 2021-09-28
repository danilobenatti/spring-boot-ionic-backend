package br.com.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.cursomc.domain.Categoria;
import br.com.cursomc.dto.CategoriaDTO;
import br.com.cursomc.repositories.CategoriaRepository;
import br.com.cursomc.services.exceptions.DataIntegrityException;
import br.com.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public Categoria find(Integer id) {
		Optional<Categoria> optional = repository.findById(id);
		return optional.orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado! ID: "
						+ id + ", tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		obj.setNome(obj.getNome().strip());
		return repository.save(obj);
	}

	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repository.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Não é possível excluir uma categoria que possui produtos");
		}
	}

	public List<Categoria> findAll() {
		return repository.findAll();
	}

	public Page<Categoria> findPage(Integer page, Integer size,
			String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, size,
				Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}

}
