package com.nozama.api.repository;

import com.nozama.api.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Override
    @Query("SELECT l from Livro l WHERE l.ativo = true")
    List<Livro> findAll();

    @Override
    @Query("SELECT l from Livro l WHERE l.id = :id and l.ativo = true")
    Optional<Livro> findById(Long id);
}
