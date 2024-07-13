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

import com.example.citasApi.Entity.Paciente;
import com.example.citasApi.repository.PacienteRepository;

@RestController
@RequestMapping("paciente/")
public class PacienteController {
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	
	@GetMapping("/paciente/{id}")
	public ResponseEntity <Paciente> getPacienteById(@PathVariable int id){
		try {
				Paciente paciente = pacienteRepository.findById(id).orElse(null);
				if(paciente != null) {
					return ResponseEntity.ok(paciente);
				}
				else {
					return ResponseEntity.notFound().build();
				}
		
		} catch (Exception e) {
				return ResponseEntity.internalServerError().build();
		}
		
	}
	
	@GetMapping("/paciente/todos")
	public ResponseEntity <List<Paciente>> getAllPacientes(){
		try {
			List<Paciente> todosPacientes = pacienteRepository.findAll();
			return ResponseEntity.ok(todosPacientes);
		} catch (Exception e) {
				return ResponseEntity.internalServerError().build();
		}
		
	}
	
	@PostMapping("/paciente/agregar")
	public ResponseEntity<Paciente> agregarPaciente(@RequestBody Paciente paciente){
		try {
			if(paciente.getApellidoMaterno().isEmpty() || paciente.getApellidoPaterno().isEmpty() || paciente.getNombre().isEmpty()) {
				return ResponseEntity.badRequest().build();
			}
			Paciente pacienteSave = pacienteRepository.save(paciente);
			return ResponseEntity.ok(pacienteSave);
		} catch (Exception e) {
				return ResponseEntity.internalServerError().build();
		}
		
	}

}
