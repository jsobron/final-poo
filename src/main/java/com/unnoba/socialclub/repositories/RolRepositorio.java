package com.unnoba.socialclub.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unnoba.socialclub.entities.Rol;

@Repository
public interface RolRepositorio extends JpaRepository<Rol, Long> {
	
	@Query("SELECT r FROM Rol r WHERE r.nombre = :nombre")
    Rol findByNombre(@Param("nombre") String nombre);
   
}
