package com.example.test.controller.impl;

import com.example.test.controller.CRUDController;
import com.example.test.model.Turno;
import com.example.test.exceptions.BadRequestException;
import com.example.test.exceptions.ResourceNotFoundException;
import com.example.test.model.dto.TurnoDTO;
import com.example.test.servicios.impl.OdontologoService;
import com.example.test.servicios.impl.TurnoService;
import com.example.test.servicios.impl.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController implements CRUDController<TurnoDTO> {

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @GetMapping
    public ResponseEntity<List<TurnoDTO>> getAll(){
        return ResponseEntity.ok(turnoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> getById(@PathVariable Long id) throws BadRequestException,ResourceNotFoundException {
        TurnoDTO t = turnoService.getById(id);
        return ResponseEntity.ok(t);
    }

    @PostMapping
    public ResponseEntity<TurnoDTO> save(@RequestBody TurnoDTO t) throws BadRequestException,ResourceNotFoundException {
        TurnoDTO turno = turnoService.save(t);
        return ResponseEntity.ok(turno);
    }

//    @PostMapping("/new")
//    public ResponseEntity<Turno> newTurnoAlt(@RequestBody ObjectNode json) {
//        ResponseEntity<Turno> rta;
//        Integer mat = json.get("pacienteDni").asInt();
//        Integer dni = json.get("turnoMat").asInt();
//        if (mat != null && dni != null) {
//            rta = ResponseEntity.ok(turnoService.saveAlt(mat,dni,));
//        } else {
//            rta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//        return rta;
//    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody TurnoDTO t) throws BadRequestException,ResourceNotFoundException {
        String s = turnoService.update(t);
        return ResponseEntity.ok(s);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws BadRequestException, ResourceNotFoundException {
        String s = turnoService.delete(id);
        return ResponseEntity.ok(s);
    }
}
