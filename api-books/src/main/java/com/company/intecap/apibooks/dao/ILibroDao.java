package com.company.intecap.apibooks.dao;

import com.company.intecap.apibooks.model.EntityLibro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILibroDao extends JpaRepository<EntityLibro,Long> {
}
