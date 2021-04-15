package com.zup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zup.model.Pessoa;

//responsavel por interagir como BD
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
