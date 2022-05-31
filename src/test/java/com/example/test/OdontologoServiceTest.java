package com.example.test;

import com.example.test.exceptions.BadRequestException;
import com.example.test.exceptions.ResourceNotFoundException;
import com.example.test.model.dto.OdontologoDTO;
import com.example.test.servicios.impl.OdontologoService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;
    private OdontologoDTO odontologoDTO;

    @Test
    @Order(1)
    void crearOdontologoTest() throws BadRequestException, ResourceNotFoundException {
        OdontologoDTO odontologoDTO = new OdontologoDTO();
        odontologoDTO.setMatricula(132465);
        odontologoDTO.setApellido("Tasda");
        odontologoDTO.setNombre("asddas");
        odontologoService.save(odontologoDTO);
        Assert.assertNotNull(odontologoService.getById(1L));
    }
    @Test
    @Order(2)
    public void traerTodosTest() {
        List<OdontologoDTO> odontologos = odontologoService.getAll();
        Assert.assertFalse(odontologos.isEmpty());
    }

}
