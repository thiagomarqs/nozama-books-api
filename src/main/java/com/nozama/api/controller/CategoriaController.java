package com.nozama.api.controller;

import com.nozama.api.dto.request.categoria.CategoriaPostRequest;
import com.nozama.api.dto.request.categoria.CategoriaPutRequest;
import com.nozama.api.dto.response.categoria.CategoriaGetResponse;
import com.nozama.api.dto.response.categoria.CategoriaPostResponse;
import com.nozama.api.dto.response.categoria.CategoriaPutResponse;
import com.nozama.api.entity.Categoria;
import com.nozama.api.service.CategoriaService;
import com.nozama.api.utils.SpringUri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaGetResponse> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(categoriaService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaGetResponse>> getAll() {
        var categorias = categoriaService.getAll();
        return ResponseEntity.ok(categorias);
    }

    @PostMapping
    public ResponseEntity<CategoriaPostResponse> create(@RequestBody CategoriaPostRequest request) {
        var createdCategoria = categoriaService.create(request);
        URI location = SpringUri.buildLocationForNewResource(createdCategoria.getId());
        return ResponseEntity.created(location).body(createdCategoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaPutResponse> update(@PathVariable("id") Long id, @RequestBody CategoriaPutRequest request) {
        return ResponseEntity.ok(categoriaService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
