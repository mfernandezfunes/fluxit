package com.fluxit.API.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fluxit.API.model.Candidato;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {


	/*
	@Query ( "SELECT firstname, lastname, dni FROM Candidato c WHERE c.lastname= %?1 AND c.firstname = %?2" )
	List<Candidato> findByLastnameAndFirstname(String lastname, String firstname);
	*/
	@Query ( "SELECT firstName, lastName, dni FROM Candidato c WHERE c.dni = '?1'" )
	Candidato findByDni(String dni);

}