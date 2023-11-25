package com.intecap.ctiniguar.Articulos.models.dao;

import com.intecap.ctiniguar.Articulos.models.Fabricante;
import com.intecap.ctiniguar.Articulos.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRolesDao extends JpaRepository<Roles,Long> {
    Optional<Roles> findByNombre(String nombre);
}
