package com.company.intecap.apibooks.dao;

import com.company.intecap.apibooks.model.EntityCategoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoriaDao extends JpaRepository<EntityCategoria,Long> {
}
