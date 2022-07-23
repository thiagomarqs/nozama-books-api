package com.nozama.api.controller;

import com.nozama.api.dto.request.idioma.IdiomaRequest;
import com.nozama.api.dto.response.idioma.IdiomaResponse;
import com.nozama.api.entity.Idioma;
import com.nozama.api.repository.IdiomaRepository;
import com.nozama.api.service.IdiomaService;
import com.nozama.api.utils.SpringUri;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/idiomas")
public class IdiomaController {

    @Autowired
    private IdiomaService idiomaService;

    @GetMapping
    public ResponseEntity<List<IdiomaResponse>> getAll() {
        var idiomas = idiomaService.getAll();
        return ResponseEntity.ok(idiomas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IdiomaResponse> getById(@PathVariable("id") Long id) {
        var idioma = idiomaService.getById(id);
        return ResponseEntity.ok(idioma);
    }

    @PostMapping
    public ResponseEntity<IdiomaResponse> create(@RequestBody IdiomaRequest request) {
        var idioma = idiomaService.create(request);
        var uri = SpringUri.buildLocationForNewResource(idioma.getId());
        return ResponseEntity.created(uri).body(idioma);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IdiomaResponse> update(@PathVariable("id") Long id, @RequestBody IdiomaRequest request) {
        var idioma = idiomaService.update(id, request);
        return ResponseEntity.ok(idioma);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        idiomaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
