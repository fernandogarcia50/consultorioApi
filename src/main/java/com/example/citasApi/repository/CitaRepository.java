package com.example.citasApi.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.citasApi.Entity.Cita;

public interface CitaRepository extends JpaRepository<Cita, Integer> {
	
	@Query(value = "SELECT COUNT(c) FROM Cita c where c.fechaConsulta = ?1 and c.hora = ?2 and c.idConsultorio = ?3")
	long finByFechaConsulta(@Param("fecha") String fechaConsulta, @Param("hora") Integer hora, @Param("idConsultorio") Integer idConsultorio);
	
	@Query(value = "SELECT COUNT(c) FROM Cita c where c.fechaConsulta = ?1 and c.hora = ?2 and c.idDoctor = ?3")
	long findByDoctorHora(@Param("fecha") String fechaConsulta, @Param("hora") Integer hora, @Param("idDoctor") Integer idDoctor);
	
	@Query(value = "SELECT COUNT(c) FROM Cita c where c.fechaConsulta = ?1 and c.idDoctor = ?2")
	long findByDoctorDia(@Param("fecha") String fechaConsulta, @Param("idDoctor") Integer idDoctor);

	// tiene que ser 0
	@Query(value = "SELECT COUNT(c) FROM Cita c where c.fechaConsulta = ?1 and c.idPaciente = ?2  and c.hora > ?3 and c.hora <= ?4", nativeQuery = false)
	long verificarPaciente(@Param("fecha") String fechaConsulta, @Param("idPaciente") Integer idPaciente, @Param("horaInferior") Integer horaInferior, @Param("horaSuperior") Integer horaSuperior);
	
}
