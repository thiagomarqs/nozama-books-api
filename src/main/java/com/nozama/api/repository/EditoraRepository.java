package com.nozama.api.repository;

import com.nozama.api.entity.Categoria;
import com.nozama.api.entity.Editora;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditoraRepository extends JpaRepository<Editora, Long> {
}
