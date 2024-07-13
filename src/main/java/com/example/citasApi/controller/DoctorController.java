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
import com.example.citasApi.Entity.Doctor;
import com.example.citasApi.repository.DoctorRepository;

@RestController
@RequestMapping("doctor/")
public class DoctorController {
		
	@Autowired
	private DoctorRepository doctorRepository;
	
	@GetMapping("/doctor/{id}")
	public ResponseEntity <Doctor> getDoctorById(@PathVariable int id){
		try {
				Doctor doctor = doctorRepository.findById(id).orElse(null);
				if(doctor != null) {
					return ResponseEntity.ok(doctor);
				}
				else {
					return ResponseEntity.notFound().build();
				}
		
		} catch (Exception e) {
				return ResponseEntity.internalServerError().build();
		}
		
	}
	
	@GetMapping("/doctor/todos")
	public ResponseEntity <List<Doctor>> getAllDoctores(){
		try {
			List<Doctor> todosDoctores = doctorRepository.findAll();
			return ResponseEntity.ok(todosDoctores);
		} catch (Exception e) {
				return ResponseEntity.internalServerError().build();
		}
		
	}
	
	@PostMapping("/doctor/agregar")
	public ResponseEntity<Doctor> agregarDoctor(@RequestBody Doctor doctor){
		try {
			if(doctor.getApellidoMaterno().isEmpty() || doctor.getApellidoPaterno().isEmpty() ||
						doctor.getNombre().isEmpty() || doctor.getEspecialidad().isEmpty()) {
				return ResponseEntity.badRequest().build();
			}
			Doctor doctorSave = doctorRepository.save(doctor);
			return ResponseEntity.ok(doctorSave);
		} catch (Exception e) {
				return ResponseEntity.internalServerError().build();
		}
		
	}
	

}
