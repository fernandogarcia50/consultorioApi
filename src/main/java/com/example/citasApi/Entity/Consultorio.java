package com.example.citasApi.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="consultorio")
public class Consultorio {
	
	@Id
	@Column(name="numeroConsultorio")
	 private Integer numeroConsultorio;
	
	@Column(name="piso")
	 private Integer piso;

	public Integer getNumeroConsultorio() {
		return numeroConsultorio;
	}

	public void setNumeroConsultorio(Integer numeroConsultorio) {
		this.numeroConsultorio = numeroConsultorio;
	}

	public Integer getPiso() {
		return piso;
	}

	public void setPiso(Integer piso) {
		this.piso = piso;
	}

	
	
	

}
