package br.com.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cursomc.domain.Cidade;
import br.com.cursomc.repositories.CidadeRepository;
import br.com.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repository;

	public Cidade findOneCidade(Integer id) {
		Optional<Cidade> optional = repository.findById(id);
		return optional.orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado! ID: "
						+ id + ", tipo: " + Cidade.class.getName()));
	}

}
