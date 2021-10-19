package br.com.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.cursomc.domain.enums.TipoCliente;
import br.com.cursomc.dto.ClienteNewDTO;
import br.com.cursomc.resources.exceptions.FieldMessage;
import br.com.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator
		implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Override
	public void initialize(ClienteInsert constraintAnnotation) {
		// document why this method is empty
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto,
			ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (objDto.getTipo() == null) {
			list.add(new FieldMessage("Tipo", "Tipo não pode ser nulo."));
		}

		if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCodigo())
				&& !BR.isValidCpf(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj","CPF inválido."));
		}

		if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCodigo())
				&& !BR.isValidCnpj(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj","CNPJ inválido."));
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
