package br.com.cursomc.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cursomc.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	@Query(value = " SELECT * FROM cliente cli "
			+ " INNER JOIN endereco end ON end.cliente_id = cli.id "
			+ " INNER JOIN cidade cid ON end.cidade_id = cid.id "
			+ " INNER JOIN estado est ON cid.estado_id = est.id "
			+ " INNER JOIN telefone tel ON tel.cliente_id = cli.id "
			+ " WHERE cli.id = ?1 ", nativeQuery = true)
	Optional<Cliente> findByIdCustom(Integer id);

	@Transactional(readOnly = true)
	Optional<Cliente> findByEmail(String email);
}
