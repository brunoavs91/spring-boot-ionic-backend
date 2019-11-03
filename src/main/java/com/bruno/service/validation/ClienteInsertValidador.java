package com.bruno.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.bruno.domain.enums.TipoCliente;
import com.bruno.dto.ClienteNewDTO;
import com.bruno.resources.exception.FieldMessage;
import com.bruno.service.validation.utils.BR;

public class ClienteInsertValidador implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		
		if(objDto.getTipoCliente().equals(TipoCliente.PESSOAFISICA) && BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("CpfOuCnpj", "CPF inválido"));
		}
		if(objDto.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA) && BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("CpfOuCnpj", "CNPJ inválido"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldMessage())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
	
