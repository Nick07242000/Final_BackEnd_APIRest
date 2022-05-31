package com.example.test.servicios.impl;

import com.example.test.model.Paciente;
import com.example.test.model.Paciente;
import com.example.test.exceptions.BadRequestException;
import com.example.test.exceptions.ResourceNotFoundException;
import com.example.test.model.dto.PacienteDTO;
import com.example.test.repository.IDomicilioRepository;
import com.example.test.repository.IPacienteRepository;
import com.example.test.servicios.IPacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {

    @Autowired
    private IPacienteRepository pacienteRepository;
    @Autowired
    private IDomicilioRepository domicilioRepository;
    @Autowired
    private ObjectMapper mapper;

    @Override
    public List<PacienteDTO> getAll() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        List<PacienteDTO> pacienteDTOS = new ArrayList<>();
        for (Paciente o:
                pacientes) {
            PacienteDTO pdto = mapper.convertValue(o,PacienteDTO.class);
            pacienteDTOS.add(pdto);
        }
        return pacienteDTOS;
    }

    @Override
    public PacienteDTO getById(Long id) throws BadRequestException,ResourceNotFoundException {
        if(id==null)
            throw new BadRequestException("El id no puede ser nulo");
        Optional<Paciente> p = pacienteRepository.findById(id);
        if(p.isEmpty())
            throw new ResourceNotFoundException("No se encontro al paciente con id: " + id);
        return mapper.convertValue(p,PacienteDTO.class);
    }

    @Override
    public PacienteDTO getByDni(Integer dni) throws BadRequestException,ResourceNotFoundException{
        if(dni==null)
            throw new BadRequestException("El dni no puede ser nulo");
        Optional<Paciente> p = pacienteRepository.getByDni(dni);
        if(p.isEmpty())
            throw new ResourceNotFoundException("No se encontro al paciente con dni: " + dni);
        return mapper.convertValue(p,PacienteDTO.class);
    }

    @Override
    public PacienteDTO save(PacienteDTO p) throws BadRequestException {
        if(p == null) {
            throw new BadRequestException("No se recibio un paciente para guardar");
        }
        pacienteRepository.save(mapper.convertValue(p,Paciente.class));
        return p;
    }

    @Override
    public String delete(Long id) throws BadRequestException,ResourceNotFoundException {
        if(id==null)
            throw new BadRequestException("El id no puede ser nulo");
        if(pacienteRepository.findById(id).isEmpty())
            throw new ResourceNotFoundException("No se encontro al paciente con id: " + id);
        pacienteRepository.deleteById(id);
        return "Paciente con id: " + id + " ha sido eliminado";
    }

    @Override
    public String update(PacienteDTO p) throws BadRequestException, ResourceNotFoundException {
        if(p == null)
            throw new BadRequestException("No se recibio un paciente para actualizar");
        if (p.getId() == null)
            throw new BadRequestException("El id del paciente no puede ser null");
        if(pacienteRepository.findById(p.getId()).isEmpty())
            throw new ResourceNotFoundException("No se encontro al paciente con id: " + p.getId());
        pacienteRepository.update(p.getId(), p.getNombre(), p.getApellido(), p.getDni(), p.getEmail(), p.getFechaIngreso());
        domicilioRepository.update(p.getDomicilio().getId(),p.getDomicilio().getCalle(),p.getDomicilio().getNumero(),p.getDomicilio().getLocalidad(),p.getDomicilio().getProvincia());
        return "Paciente con id: " + p.getId() + " ha sido actualizado";
    }
}
