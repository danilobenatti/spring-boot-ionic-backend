package br.com.cursomc.resources;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cursomc.security.JwtUtils;
import br.com.cursomc.security.UserDetailsImpl;
import br.com.cursomc.services.UserService;

@RestController
@RequestMapping(path = "/auth")
public class AuthResource {

	@Autowired
	private JwtUtils jwtUtils;

	@PostMapping(path = "/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserDetailsImpl userDetails = UserService.authenticated();
		var token = jwtUtils.generateJwtToken(userDetails.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}

}
