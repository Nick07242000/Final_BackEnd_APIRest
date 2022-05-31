package com.example.test.repository;

import com.example.test.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Turno t SET t.fecha = :fecha WHERE t.id = :id")
    void update(@Param("id") Long id,@Param("fecha") LocalDateTime fecha);
}
