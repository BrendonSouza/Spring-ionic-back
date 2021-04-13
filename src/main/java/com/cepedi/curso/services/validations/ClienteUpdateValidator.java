package com.cepedi.curso.services.validations;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cepedi.curso.domain.Cliente;
import com.cepedi.curso.dto.ClienteDTO;
import com.cepedi.curso.repositories.ClienteRepository;
import com.cepedi.curso.resources.exceptions.FieldMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;


public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private HttpServletRequest request;	

	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		@SuppressWarnings("unchecked")
		Map<String,String> map= (Map<String,String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriID=	Integer.parseInt(map.get("id"));

		List<FieldMessage> list = new ArrayList<>();

		Cliente aux= clienteRepository.findByEmail(objDto.getEmail());
		if(aux!=null && !aux.getId().equals(uriID)){
			list.add(new FieldMessage("email", "Email já cadastrado"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}