package com.example.citasApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.citasApi.Entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

}
