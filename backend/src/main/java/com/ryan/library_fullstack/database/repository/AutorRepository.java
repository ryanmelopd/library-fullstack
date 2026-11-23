package com.ryan.library_fullstack.database.repository;

import com.ryan.library_fullstack.database.model.AutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<AutorEntity, Long> {
}