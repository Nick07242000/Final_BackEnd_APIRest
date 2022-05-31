package com.example.test.servicios.impl;

import com.example.test.model.Odontologo;
import com.example.test.exceptions.BadRequestException;
import com.example.test.exceptions.ResourceNotFoundException;
import com.example.test.model.dto.OdontologoDTO;
import com.example.test.repository.IOdontologoRepository;
import com.example.test.servicios.IOdontologoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {

    @Autowired
    private IOdontologoRepository odontologoRepository;
    @Autowired
    private ObjectMapper mapper;

    @Override
    public List<OdontologoDTO> getAll() {
        List<Odontologo> odontologos = odontologoRepository.findAll();
        List<OdontologoDTO> odontologoDTOS = new ArrayList<>();
        for (Odontologo o:
             odontologos) {
            OdontologoDTO odto = mapper.convertValue(o,OdontologoDTO.class);
            odontologoDTOS.add(odto);
        }
        return odontologoDTOS;
    }

    @Override
    public OdontologoDTO getById(Long id) throws BadRequestException,ResourceNotFoundException {
        if(id==null)
            throw new BadRequestException("El id no puede ser nulo");
        Optional<Odontologo> o = odontologoRepository.findById(id);
        if(o.isEmpty())
            throw new ResourceNotFoundException("No se encontro al odontologo con id: " + id);
        return mapper.convertValue(o,OdontologoDTO.class);
    }

    @Override
    public OdontologoDTO getByMatricula(Integer matricula) throws BadRequestException,ResourceNotFoundException {
        if(matricula==null)
            throw new BadRequestException("La matricula no puede ser nula");
        Optional<Odontologo> o = odontologoRepository.getByMatricula(matricula);
        if(o.isEmpty())
            throw new ResourceNotFoundException("No se encontro al odontologo con matricula: " + matricula);
        return mapper.convertValue(o,OdontologoDTO.class);
    }

    @Override
    public OdontologoDTO save(OdontologoDTO o) throws BadRequestException {
        if(o == null) {
            throw new BadRequestException("No se recibio un odontologo para guardar");
        }
        odontologoRepository.save(mapper.convertValue(o,Odontologo.class));
        return o;
    }

    @Override
    public String delete(Long id) throws BadRequestException,ResourceNotFoundException{
        if(id==null)
            throw new BadRequestException("El id no puede ser nulo");
        if(odontologoRepository.findById(id).isEmpty())
            throw new ResourceNotFoundException("No se encontro al odontologo con id: " + id);
        odontologoRepository.deleteById(id);
        return "Odontologo con id: " + id + " ha sido eliminado";
    }

    @Override
    public String update(OdontologoDTO o) throws BadRequestException, ResourceNotFoundException {
        if(o == null)
            throw new BadRequestException("No se recibio un odontologo para actualizar");
        if (o.getId() == null)
            throw new BadRequestException("El id del odontólogo no puede ser null");
        if(odontologoRepository.findById(o.getId()).isEmpty())
            throw new ResourceNotFoundException("No se encontro al odontologo con id: " + o.getId());
        odontologoRepository.update(o.getId(), o.getNombre(), o.getApellido(), o.getMatricula());
        return "Odontologo con id: " + o.getId() + " ha sido actualizado";
    }
}
