package com.intecap.ctiniguar.Articulos.models.dao;

import com.intecap.ctiniguar.Articulos.models.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IArticuloDao extends JpaRepository<Articulo, Long> {
    Optional<Articulo> findByNombre(String nombre);
}
