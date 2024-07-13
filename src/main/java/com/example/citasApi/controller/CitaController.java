package com.example.citasApi.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.citasApi.Entity.Cita;
import com.example.citasApi.Entity.Consultorio;
import com.example.citasApi.repository.CitaRepository;

@RestController
@RequestMapping("cita/")
public class CitaController {
	
	@Autowired
	private CitaRepository citaRepository;
	
	
	@GetMapping("/citas/{id}")
	public ResponseEntity <Cita> getById(@PathVariable int id){
		try {
				Cita cita = citaRepository.findById(id).orElse(null);
				if(cita != null) {
					return ResponseEntity.ok(cita);
				}
				else {
					return ResponseEntity.notFound().build();
				}
		
		} catch (Exception e) {
				return ResponseEntity.internalServerError().build();
		}
		
	}
	
	@GetMapping("/citas/todos")
	public ResponseEntity <List<Cita>> getAllCitas(){
		try {
			List<Cita> todasCitas = citaRepository.findAll();
			return ResponseEntity.ok(todasCitas);
		} catch (Exception e) {
				return ResponseEntity.internalServerError().build();
		}
		
	}
	
	@PostMapping("/cita/agregar")
	public ResponseEntity<Cita> agregarCita(@RequestBody Cita cita){
		try {
			
			SimpleDateFormat formato = new SimpleDateFormat("dd/mm/yyyy");
			
		
			long cantidadCitasHoraFecha = citaRepository.finByFechaConsulta(cita.getFechaConsulta(), cita.getHora(), cita.getIdConsultorio());
			long doctorOCupado = citaRepository.findByDoctorHora(cita.getFechaConsulta(), cita.getHora(), cita.getIdDoctor());
			long limiteDoctor = citaRepository.findByDoctorDia(cita.getFechaConsulta(), cita.getIdDoctor());
			long esPosibleAgendar = citaRepository.verificarPaciente(cita.getFechaConsulta(), cita.getIdPaciente(), cita.getHora()-2, cita.getHora()+3);
			
			if(cantidadCitasHoraFecha > 0) {
				return new ResponseEntity("No es posible agendar a la misma hora en la misma fecha en el mismo consultorio",HttpStatus.BAD_REQUEST);
			}
			
			if(doctorOCupado > 0) {
				return new ResponseEntity("No es posible agendar la cita con el Doctor a esta hora. Ya tiene agendada una cita",HttpStatus.BAD_REQUEST);
			}
			
			if(limiteDoctor > 8) {
				return new ResponseEntity("El doctor ya ha trabajado 8 horas",HttpStatus.BAD_REQUEST);
			}
			
			if(esPosibleAgendar > 0) {
				return new ResponseEntity("El paciente ya tiene agenda una cita en un tiempo de dos horas",HttpStatus.BAD_REQUEST);
			}
			
			Cita citaSave = citaRepository.save(cita);
			return ResponseEntity.ok(citaSave);
			
		} catch (Exception e) {
				return ResponseEntity.internalServerError().eTag(e.getMessage()).build();
		}
		
	}
	

}
