package com.example.test.servicios.impl;

import com.example.test.model.Odontologo;
import com.example.test.model.Turno;
import com.example.test.exceptions.BadRequestException;
import com.example.test.exceptions.ResourceNotFoundException;
import com.example.test.model.dto.OdontologoDTO;
import com.example.test.model.dto.TurnoDTO;
import com.example.test.repository.IOdontologoRepository;
import com.example.test.repository.IPacienteRepository;
import com.example.test.repository.ITurnoRepository;
import com.example.test.servicios.CRUDService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements CRUDService<TurnoDTO> {

    @Autowired
    private ITurnoRepository turnoRepository;
    @Autowired
    private IPacienteRepository pacienteRepository;
    @Autowired
    private IOdontologoRepository odontologoRepository;
    @Autowired
    private ObjectMapper mapper;

    @Override
    public List<TurnoDTO> getAll() {
        List<Turno> turnos = turnoRepository.findAll();
        List<TurnoDTO> turnosDTOS = new ArrayList<>();
        for (Turno t:
                turnos) {
            TurnoDTO tdto = mapper.convertValue(t,TurnoDTO.class);
            turnosDTOS.add(tdto);
        }
        return turnosDTOS;
    }

    @Override
    public TurnoDTO getById(Long id) throws BadRequestException,ResourceNotFoundException{
        if(id==null)
            throw new BadRequestException("El id no puede ser nulo");
        Optional<Turno> t = turnoRepository.findById(id);
        if(t.isEmpty())
            throw new ResourceNotFoundException("No se encontro al turno con id: " + id);
        return mapper.convertValue(t,TurnoDTO.class);
    }

    @Override
    public TurnoDTO save(TurnoDTO t) throws BadRequestException,ResourceNotFoundException {
        if(t == null)
            throw new BadRequestException("No se recibio un turno para guardar");
        if(pacienteRepository.findById(t.getPaciente().getId()).isEmpty())
            throw new ResourceNotFoundException("Paciente no existe en la base de datos");
        if(odontologoRepository.findById(t.getOdontologo().getId()).isEmpty())
            throw new ResourceNotFoundException("Odontologo no existe en la base de datos");
        turnoRepository.save(mapper.convertValue(t,Turno.class));
        return t;
    }

//    public Turno saveAlt(Integer matricula, Integer dni, LocalDate fecha) {
//        OdontologoService os = new OdontologoService();
//        PacienteService ps = new PacienteService();
//        Optional<Odontologo> o = os.getByMatricula(matricula);
//        Optional<Paciente> p = ps.getByDni(dni);
//        if(o.isPresent() && p.isPresent()) {
//            Turno t = new Turno(p.get(),o.get(),fecha);
//            return save(t);
//        }
//        return new Turno();
//    }

    @Override
    public String delete(Long id) throws BadRequestException, ResourceNotFoundException {
        if(id==null)
            throw new BadRequestException("El id no puede ser nulo");
        if(turnoRepository.findById(id).isEmpty())
            throw new ResourceNotFoundException("No se encontro al turno con id: " + id);
        turnoRepository.deleteById(id);
        return "Odontologo con id: " + id + " ha sido eliminado";
    }

    @Override
    public String update(TurnoDTO t) throws BadRequestException, ResourceNotFoundException {
        if(t == null)
            throw new BadRequestException("No se recibio un turno para actualizar");
        if (t.getId() == null)
            throw new BadRequestException("El id del odontólogo no puede ser null");
        if(turnoRepository.findById(t.getId()).isEmpty())
            throw new ResourceNotFoundException("No se encontro al turno con id: " + t.getId());
        turnoRepository.update(t.getId(),t.getFecha());
        return "Odontologo con id: " + t.getId() + " ha sido actualizado";
    }
}
