package com.example.test.repository;

import com.example.test.model.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface IDomicilioRepository extends JpaRepository<Domicilio, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Domicilio d SET d.calle = :calle, d.numero = :numero, d.localidad = :localidad, d.provincia = :provincia WHERE d.id = :id")
    void update(@Param("id") Long id,@Param("calle") String calle,@Param("numero") Integer numero,@Param("localidad") String localidad,@Param("provincia") String provincia);
}
