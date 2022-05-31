package com.example.test.controller.impl;

import com.example.test.controller.CRUDController;
import com.example.test.model.Odontologo;
import com.example.test.exceptions.BadRequestException;
import com.example.test.exceptions.ResourceNotFoundException;
import com.example.test.model.dto.OdontologoDTO;
import com.example.test.servicios.impl.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController implements CRUDController<OdontologoDTO> {

    @Autowired
    private OdontologoService odontologoService;

    @GetMapping
    public ResponseEntity<List<OdontologoDTO>> getAll(){
        return ResponseEntity.ok(odontologoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDTO> getById(@PathVariable Long id) throws BadRequestException,ResourceNotFoundException {
        OdontologoDTO o = odontologoService.getById(id);
        return ResponseEntity.ok(o);
    }

    @GetMapping("/m/{matricula}")
    public ResponseEntity<OdontologoDTO> getByMat(@PathVariable Integer matricula) throws BadRequestException,ResourceNotFoundException {
        OdontologoDTO o = odontologoService.getByMatricula(matricula);
        return ResponseEntity.ok(o);
    }

    @PostMapping
    public ResponseEntity<OdontologoDTO> save(@RequestBody OdontologoDTO o) throws BadRequestException {
        OdontologoDTO odontologo = odontologoService.save(o);
        return ResponseEntity.ok(odontologo);
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody OdontologoDTO o) throws BadRequestException,ResourceNotFoundException {
        String s = odontologoService.update(o);
        return ResponseEntity.ok(s);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws BadRequestException,ResourceNotFoundException {
        String s = odontologoService.delete(id);
        return ResponseEntity.ok(s);
    }
}
