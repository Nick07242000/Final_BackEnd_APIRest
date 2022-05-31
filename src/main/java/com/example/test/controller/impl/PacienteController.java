package com.example.test.controller.impl;

import com.example.test.controller.CRUDController;
import com.example.test.model.Paciente;
import com.example.test.exceptions.BadRequestException;
import com.example.test.exceptions.ResourceNotFoundException;
import com.example.test.model.dto.PacienteDTO;
import com.example.test.servicios.impl.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController implements CRUDController<PacienteDTO> {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<PacienteDTO>> getAll(){
        return ResponseEntity.ok(pacienteService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> getById(@PathVariable Long id) throws BadRequestException, ResourceNotFoundException {
        PacienteDTO p = pacienteService.getById(id);
        return ResponseEntity.ok(p);
    }

    @GetMapping("/d/{dni}")
    public ResponseEntity<PacienteDTO> getByDni(@PathVariable Integer dni) throws BadRequestException,ResourceNotFoundException{
        PacienteDTO p = pacienteService.getByDni(dni);
        return ResponseEntity.ok(p);
    }

    @PostMapping
    public ResponseEntity<PacienteDTO> save(@RequestBody PacienteDTO p) throws BadRequestException {
        PacienteDTO paciente = pacienteService.save(p);
        return ResponseEntity.ok(paciente);
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody PacienteDTO p) throws BadRequestException,ResourceNotFoundException {
        String s = pacienteService.update(p);
        return ResponseEntity.ok(s);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws BadRequestException,ResourceNotFoundException {
        String s = pacienteService.delete(id);
        return ResponseEntity.ok(s);
    }
}
