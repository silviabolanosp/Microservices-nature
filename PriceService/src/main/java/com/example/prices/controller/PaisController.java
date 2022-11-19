package com.example.prices.controller;


import com.example.prices.entity.Pais;
import com.example.prices.entity.request.PaisRequest;
import com.example.prices.entity.response.PaisResponse;
import com.example.prices.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping(path = "/micro/pais")
public class PaisController {

	public PaisController() {

	}

	@RequestMapping(path = "/paises", method = RequestMethod.POST)
	public ResponseEntity<
				PaisResponse> getPais(@RequestBody PaisRequest paisRequest) {

		return new ResponseEntity<>(
				new PaisResponse(paisRequest.getIdPais(), "CR", "America", 1l, 2l), HttpStatus.OK);
	}


	@Autowired
	private PaisRepository repository;

	PaisController(PaisRepository paisRepository) {
		this.repository = paisRepository;
	}

	@GetMapping
	public List<?> findAll() {
		return repository.findAll();
	}

	@GetMapping(path = {"/pais/{id}"})
	public ResponseEntity<Pais> findById(@PathVariable
										 Long id) {
		return repository.findById(id)
				.map(record ->
						ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Pais create(@RequestBody Pais travel) {
		return repository.save(travel);
	}

	@PutMapping(value = "/pais/{id}")
	public ResponseEntity<Pais>
	update(@PathVariable("id") Long id,
		   @RequestBody Pais e) {
		return repository.findById(id)
				.map(record -> {
					record.setName(e.getName());
					record.setContinent(e.getContinent());
					record.setLandArea(e.getLandArea());
					record.setSeaArea(e.getSeaArea());
					Pais updated = repository.save(record);
					return ResponseEntity.ok().body(updated);
				}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = {"/pais/{id}"})
	public ResponseEntity<?> delete(@PathVariable("id")
									Long id) {
		return repository.findById(id)
				.map(record -> {
					repository.deleteById(id);
					return ResponseEntity.ok().build();
				}).orElse(ResponseEntity.notFound().build());
	}


}
