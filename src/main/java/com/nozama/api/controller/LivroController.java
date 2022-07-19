package com.nozama.api.controller;

import com.nozama.api.dto.request.livro.LivroPostRequest;
import com.nozama.api.dto.request.livro.LivroPutRequest;
import com.nozama.api.dto.response.livro.LivroGetResponse;
import com.nozama.api.entity.Livro;
import com.nozama.api.service.LivroService;
import com.nozama.api.utils.SpringUri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping("/{id}")
    public ResponseEntity<LivroGetResponse> getById(@PathVariable("id") Long id) {
        var livro = livroService.getById(id);
        return ResponseEntity.ok(livro);
    }

    @GetMapping
    public ResponseEntity<List<LivroGetResponse>> getAll() {
        var livros = livroService.getAll();
        return ResponseEntity.ok(livros);
    }

    @PostMapping
    public ResponseEntity<Livro> create(@RequestBody LivroPostRequest request) {
        var createdLivro = livroService.create(request);
        URI location = SpringUri.buildLocationForNewResource(createdLivro.getId());
        return ResponseEntity.created(location).body(createdLivro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> update(@PathVariable("id") Long id, @RequestBody LivroPutRequest request) {
        return ResponseEntity.ok(livroService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        livroService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
