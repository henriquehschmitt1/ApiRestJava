package com.zup.controller;

import java.util.List;
import com.zup.model.Vacina;
import com.zup.repository.VacinaRepository;
import com.zup.validators.Validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/vacinas")
public class VacinaController {
	
	
	@Autowired
	private VacinaRepository vacinaRepository;
	private Validators validator = new Validators();
	
	@GetMapping
	@ResponseStatus(HttpStatus.CREATED)
	public List<Vacina> listVacina() {
		try {
			return vacinaRepository.findAll();						
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Algo não ocorreu bem.", e);
		}
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Vacina addVacina(@RequestBody Vacina vacina) {
		try {
			if(!validator.validaData(vacina.getData())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data de nascimento informada não é válida, favor verificar");
			}
			return vacinaRepository.save(vacina);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Algo não ocorreu bem.", e);
		}
	}
}
