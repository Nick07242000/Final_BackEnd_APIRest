package com.example.test.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private Integer dni;
    private LocalDateTime fechaIngreso;
    private DomicilioDTO domicilio;

    public PacienteDTO() {}

    public PacienteDTO(Long id, String nombre, String apellido, String email, Integer dni, LocalDateTime fechaIngreso, DomicilioDTO domicilio) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
    }

    public PacienteDTO(String nombre, String apellido, String email, Integer dni, LocalDateTime fechaIngreso, DomicilioDTO domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
    }
}
