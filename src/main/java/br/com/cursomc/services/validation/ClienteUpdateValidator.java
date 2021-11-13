package br.com.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.cursomc.domain.Cliente;
import br.com.cursomc.dto.ClienteDTO;
import br.com.cursomc.repositories.ClienteRepository;
import br.com.cursomc.resources.exceptions.FieldMessage;

public class ClienteUpdateValidator
		implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void initialize(ClienteUpdate constraintAnnotation) {
		// document why this method is empty
	}

	@Override
	public boolean isValid(ClienteDTO objDto,
			ConstraintValidatorContext context) {

		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));

		List<FieldMessage> list = new ArrayList<>();

		Optional<Cliente> cliente = clienteRepository
				.findByEmail(objDto.getEmail());
		if (cliente.isPresent() && !Objects
				.equals(cliente.map(Cliente::getId).isPresent(), uriId)) {
			list.add(new FieldMessage("email", "E-mail já existente."));
		}

		for (FieldMessage message : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message.getMessage())
					.addPropertyNode(message.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}
