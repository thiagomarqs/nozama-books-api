package com.nozama.api.repository;

import com.nozama.api.entity.Categoria;
import com.nozama.api.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Override
    @Query("SELECT c FROM Categoria c WHERE c.ativo = true")
    List<Categoria> findAll();

    @Override
    @Query("SELECT c FROM Categoria c WHERE c.id = :id and c.ativo = true")
    Optional<Categoria> findById(Long id);
}
