package com.zup.controller;

import java.util.List;

import com.zup.model.Pessoa;
import com.zup.repository.PessoaRepository;
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
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private Validators validator;
	
	@GetMapping
	@ResponseStatus(HttpStatus.CREATED)
	public List<Pessoa> listPessoa() {
		try {
			return pessoaRepository.findAll();
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Algo não ocorreu bem.", e);
		}
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pessoa addPessoa(@RequestBody Pessoa pessoa) {
		try {
			if(!validator.validaCpf(pessoa.getCpf())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF informado não é válido, favor verificar");
			}
			if(!validator.validaData(pessoa.getDataNascimento())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data de nascimento informada não é válida, favor verificar");
			}
			return pessoaRepository.save(pessoa);			
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Algo não ocorreu bem.", e);
		}
	}
	
    
}
