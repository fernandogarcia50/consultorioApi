package com.example.citasApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.citasApi.Entity.Consultorio;
import com.example.citasApi.repository.ConsultorioRepository;

@RestController
@RequestMapping("consultorio/")
public class ConsultorioController {
	
	@Autowired
	private ConsultorioRepository consultorioRepository;
	
	@GetMapping("/consultorio/{id}")
	public ResponseEntity <Consultorio> getByNumber(@PathVariable int id){
		try {
				Consultorio consultorio = consultorioRepository.findById(id).orElse(null);
				if(consultorio != null) {
					return ResponseEntity.ok(consultorio);
				}
				else {
					return ResponseEntity.notFound().build();
				}
		
		} catch (Exception e) {
				return ResponseEntity.internalServerError().build();
		}
		
	}
	
	@GetMapping("/consultorio/todos")
	public ResponseEntity <List<Consultorio>> getAllConsultorios(){
		try {
			List<Consultorio> todosConsultorios = consultorioRepository.findAll();
			return ResponseEntity.ok(todosConsultorios);
		} catch (Exception e) {
				return ResponseEntity.internalServerError().build();
		}
		
	}
	
	
	
	@PostMapping("/consultorio/agregar")
	public ResponseEntity<Consultorio> agregarConsultorio(@RequestBody Consultorio consultorio){
		try {
			if(consultorio.getNumeroConsultorio() != null || consultorio.getPiso() != null) {
				return ResponseEntity.badRequest().build();
			}
			Consultorio consultorioSave = consultorioRepository.save(consultorio);
			return ResponseEntity.ok(consultorioSave);
		} catch (Exception e) {
				return ResponseEntity.internalServerError().build();
		}
		
	}
	
	
}
