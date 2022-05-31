package com.example.test.servicios.impl;

import com.example.test.model.Domicilio;
import com.example.test.exceptions.BadRequestException;
import com.example.test.exceptions.ResourceNotFoundException;
import com.example.test.model.Odontologo;
import com.example.test.model.dto.DomicilioDTO;
import com.example.test.repository.IDomicilioRepository;
import com.example.test.servicios.CRUDService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DomicilioService implements CRUDService<DomicilioDTO> {

    @Autowired
    private IDomicilioRepository domicilioRepository;
    @Autowired
    private ObjectMapper mapper;

    @Override
    public List<DomicilioDTO> getAll() {
        List<Domicilio> domicilios = domicilioRepository.findAll();
        List<DomicilioDTO> domicilioDTOS = new ArrayList<>();
        for (Domicilio d:
             domicilios) {
            DomicilioDTO ddto = mapper.convertValue(d,DomicilioDTO.class);
            domicilioDTOS.add(ddto);
        }
        return domicilioDTOS;
    }

    @Override
    public DomicilioDTO getById(Long id) throws BadRequestException,ResourceNotFoundException {
        if(id==null)
            throw new BadRequestException("El id no puede ser nulo");
        Optional<Domicilio> d = domicilioRepository.findById(id);
        if(d.isEmpty())
            throw new ResourceNotFoundException("No se encontro al domicilio con id: " + id);
        return mapper.convertValue(d,DomicilioDTO.class);
    }

    @Override
    public DomicilioDTO save(DomicilioDTO d) throws BadRequestException {
        if(d == null) {
            throw new BadRequestException("No se recibio un domicilio para guardar");
        }
        domicilioRepository.save(mapper.convertValue(d,Domicilio.class));
        return d;
    }

    @Override
    public String delete(Long id) throws BadRequestException,ResourceNotFoundException {
        if(id==null)
            throw new BadRequestException("El id no puede ser nulo");
        if(domicilioRepository.findById(id).isEmpty())
            throw new ResourceNotFoundException("No se encontro al domicilio con id: " + id);
        domicilioRepository.deleteById(id);
        return "Odontologo con id: " + id + " ha sido eliminado";
    }

    @Override
    public String update(DomicilioDTO d) throws BadRequestException, ResourceNotFoundException {
        if(d == null)
            throw new BadRequestException("No se recibio un domicilio para actualizar");
        if (d.getId() == null)
            throw new BadRequestException("El id del odontólogo no puede ser null");
        if(domicilioRepository.findById(d.getId()).isEmpty())
            throw new ResourceNotFoundException("No se encontro al domicilio con id: " + d.getId());
        domicilioRepository.update(d.getId(),d.getCalle(),d.getNumero(),d.getLocalidad(),d.getProvincia());
        return "Odontologo con id: " + d.getId() + " ha sido actualizado";
    }
}
