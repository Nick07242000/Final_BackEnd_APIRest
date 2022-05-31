package com.example.test.repository;

import com.example.test.model.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo, Long> {
    @Query("SELECT o FROM Odontologo o WHERE o.matricula = ?1")
    Optional<Odontologo> getByMatricula(Integer matricula);
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Odontologo o SET o.nombre = :nombre, o.apellido = :apellido, o.matricula = :matricula WHERE o.id = :id")
    void update(@Param("id") Long id,@Param("nombre") String nombre, @Param("apellido") String apellido, @Param("matricula") Integer matricula);
}
