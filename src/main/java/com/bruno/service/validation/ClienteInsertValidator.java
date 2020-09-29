package com.bruno.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.bruno.domain.Cliente;
import com.bruno.domain.enums.TipoCliente;
import com.bruno.dto.ClienteNewDTO;
import com.bruno.repository.ClienteRepository;
import com.bruno.resources.exception.FieldMessage;
import com.bruno.service.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository clienteRepostory;
	
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		Integer tipoCliente = Integer.parseInt(objDto.getTipoCliente() != null? objDto.getTipoCliente() : null);
		if (tipoCliente != null && tipoCliente == (TipoCliente.PESSOAFISICA.getCod()) 
				&& BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("CpfOuCnpj", "CPF inválido"));
		}
		if (objDto.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA) && BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("CpfOuCnpj", "CNPJ inválido"));
		}
		
		Cliente clie= clienteRepostory.findByEmail(objDto.getEmail());
		
		if(clie != null) {
			list.add(new FieldMessage("Email","Email já existente"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldMessage())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
	
