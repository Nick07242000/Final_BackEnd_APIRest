package com.example.test.servicios;

import com.example.test.model.Paciente;
import com.example.test.exceptions.BadRequestException;
import com.example.test.exceptions.ResourceNotFoundException;
import com.example.test.model.dto.PacienteDTO;

import java.util.Optional;

public interface IPacienteService extends CRUDService<PacienteDTO> {
    PacienteDTO getByDni(Integer dni) throws BadRequestException,ResourceNotFoundException;
}
