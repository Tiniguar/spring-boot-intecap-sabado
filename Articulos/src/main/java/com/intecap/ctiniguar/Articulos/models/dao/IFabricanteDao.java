package com.intecap.ctiniguar.Articulos.models.dao;

import com.intecap.ctiniguar.Articulos.models.Fabricante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IFabricanteDao extends JpaRepository<Fabricante,Long> {

    Optional<Fabricante> findByNombre(String nombre);
}
