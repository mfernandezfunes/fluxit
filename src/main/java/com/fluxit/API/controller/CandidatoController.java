package com.fluxit.API.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fluxit.API.exception.ResourceNotFoundException;
import com.fluxit.API.model.Candidato;
import com.fluxit.API.repository.CandidatoRepository;

@RestController
@RequestMapping({ "api" })
public class CandidatoController {

	private CandidatoRepository repositorio;

	CandidatoController(CandidatoRepository candidatoRepository) {
		this.repositorio = candidatoRepository;
	}

	@GetMapping(path = { "/candidatos" })//{id}
	public @ResponseBody List<Candidato> findAll() {
		return repositorio.findAll();
	}
	
	@GetMapping(path = { "/candidatos/{id}" })
	public ResponseEntity<Candidato> getCandidateById(
	@PathVariable (value = "id") Long candidateId) throws ResourceNotFoundException {			
		Candidato candidato = repositorio.findById(candidateId)
		.orElseThrow(() -> new ResourceNotFoundException("Candidato no encontrado con :: " + candidateId));
		return ResponseEntity.ok().body(candidato);
	}
	
	// Find by dni
	
	@GetMapping(path = { "/candidatos/search/dni/{dni}" })
	public ResponseEntity<Candidato> findByDni(
	@PathVariable (value = "dni") Long candidateDni) throws ResourceNotFoundException {			
		Candidato candidato = repositorio.findById(candidateDni)
		.orElseThrow(() -> new ResourceNotFoundException("Candidato no encontrado con dni: " + candidateDni));
		return ResponseEntity.ok().body(candidato);
	}

	@PostMapping
	public Candidato create(@RequestBody Candidato candidato) {
		System.out.println(candidato);
		return repositorio.save(candidato);
	}

	@PutMapping(value = "/candidatos/{id}")
	public ResponseEntity<Candidato> update(@PathVariable("id") long id, @RequestBody Candidato candidato) {
		return repositorio.findById(id).map(record -> {
			record.setFirstName(candidato.getFirstName());
			record.setLastName(candidato.getLastName());
			record.setDni(candidato.getDni());
			record.setPhone(candidato.getPhone());
			record.setEmail(candidato.getEmail());
			Candidato updated = repositorio.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = { "/candidatos/{id}" })
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		return repositorio.findById(id).map(record -> {
			repositorio.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
