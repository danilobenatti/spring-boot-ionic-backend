package br.com.cursomc.services;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.cursomc.security.UserDetailsImpl;

public class UserService {

	UserService() {
	}

	public static UserDetailsImpl authenticated() {
		try {
			return (UserDetailsImpl) SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
}
