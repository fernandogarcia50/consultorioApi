package com.example.citasApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.citasApi.Entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer>{

}
