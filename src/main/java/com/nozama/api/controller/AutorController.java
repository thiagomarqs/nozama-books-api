package com.nozama.api.controller;

import com.nozama.api.dto.request.autor.AutorRequest;
import com.nozama.api.dto.response.autor.AutorResponse;
import com.nozama.api.service.AutorService;
import com.nozama.api.utils.SpringUri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping
    public ResponseEntity<List<AutorResponse>> getAll() {
        var autores = autorService.getAll();
        return ResponseEntity.ok(autores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorResponse> getById(@PathVariable("id") Long id) {
        var autor = autorService.getById(id);
        return ResponseEntity.ok(autor);
    }

    @PostMapping
    public ResponseEntity<AutorResponse> create(@RequestBody AutorRequest request) {
        var autor = autorService.create(request);
        var uri = SpringUri.buildLocationForNewResource(autor.getId());
        return ResponseEntity.created(uri).body(autor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorResponse> update(@PathVariable("id") Long id, @RequestBody AutorRequest request) {
        var autor = autorService.update(id, request);
        return ResponseEntity.ok(autor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        autorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
