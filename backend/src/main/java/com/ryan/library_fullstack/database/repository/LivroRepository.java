package com.ryan.library_fullstack.database.repository;

import com.ryan.library_fullstack.database.model.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<LivroEntity, Long> {
    List<LivroEntity> findByAutorId(Long autorId);

    boolean existsByAutorId(Long autorId);
}