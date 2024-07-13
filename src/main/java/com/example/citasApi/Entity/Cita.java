package com.example.citasApi.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="cita")
public class Cita {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	 private int id;
	
	@Column(name="idDoctor")
	 private Integer idDoctor;
	
	@Column(name="idPaciente")
	 private Integer idPaciente;
	
	@Column(name="idConsultorio")
	 private Integer idConsultorio;
	
	@Column(name="fechaConsulta")
    private String fechaConsulta;
	
	@Column(name="hora")
	 private Integer hora;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Integer getIdDoctor() {
		return idDoctor;
	}

	public void setIdDoctor(Integer idDoctor) {
		this.idDoctor = idDoctor;
	}

	public Integer getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
	}

	public Integer getIdConsultorio() {
		return idConsultorio;
	}

	public void setIdConsultorio(Integer idConsultorio) {
		this.idConsultorio = idConsultorio;
	}

	public Integer getHora() {
		return hora;
	}

	public void setHora(Integer hora) {
		this.hora = hora;
	}

	public String getFechaConsulta() {
		return fechaConsulta;
	}

	public void setFechaConsulta(String fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}

	
	
}
