package com.intecap.ctiniguar.Articulos.models.dao;

import com.intecap.ctiniguar.Articulos.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUsuarioDao extends JpaRepository<Usuario,Long> {
        public Usuario findByNombreUsuario(String nombreUsuario);
}
