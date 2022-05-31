package com.example.test.repository;

import com.example.test.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Long> {
    @Query("SELECT p FROM Paciente p WHERE p.dni = ?1")
    Optional<Paciente> getByDni(Integer dni);
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Paciente p SET p.nombre = :nombre, p.apellido = :apellido, p.dni = :dni, p.email = :email, p.fechaIngreso = :fecha WHERE p.id = :id")
    void update(@Param("id") Long id, @Param("nombre") String nombre, @Param("apellido") String apellido, @Param("dni") Integer dni, @Param("email") String email, @Param("fecha") LocalDateTime fechaIngreso);
}
