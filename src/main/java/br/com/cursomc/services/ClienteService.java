package br.com.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.cursomc.domain.Cliente;
import br.com.cursomc.dto.ClienteDTO;
import br.com.cursomc.repositories.ClienteRepository;
import br.com.cursomc.services.exceptions.DataIntegrityException;
import br.com.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public Cliente find(Integer id) {
		Optional<Cliente> optional = repository.findById(id);
		return optional.orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado! ID: "
						+ id + ", tipo: " + Cliente.class.getName()));
	}

	public Cliente update(Cliente obj) {
		var newObj = find(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Não é possível excluir porque há entidades relacionadas.");
		}
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer size, String direction,
			String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, size,
				Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(),
				null, null);
	}

}
