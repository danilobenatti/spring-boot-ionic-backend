package br.com.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.cursomc.repositories.ClienteRepository;
import br.com.cursomc.security.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {
		var cliente = clienteRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException(
						"Usuário com email " + email + " não encontrado."));
		return new UserDetailsImpl(cliente.getId(), cliente.getEmail(),
				cliente.getSenha(), cliente.getPerfis());
	}

}
