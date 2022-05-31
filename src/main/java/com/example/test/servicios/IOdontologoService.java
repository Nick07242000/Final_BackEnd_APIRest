package com.example.test.servicios;

import com.example.test.exceptions.BadRequestException;
import com.example.test.exceptions.ResourceNotFoundException;
import com.example.test.model.dto.OdontologoDTO;

import java.util.Optional;

public interface IOdontologoService extends CRUDService<OdontologoDTO> {
    OdontologoDTO getByMatricula(Integer matricula) throws BadRequestException,ResourceNotFoundException;
}
