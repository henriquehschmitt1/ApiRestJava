package com.zup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zup.model.Vacina;

@Repository
public interface VacinaRepository extends JpaRepository<Vacina, Long>{

}
