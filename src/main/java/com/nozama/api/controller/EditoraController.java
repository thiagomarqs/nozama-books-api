package com.nozama.api.controller;

import com.nozama.api.dto.request.editora.EditoraRequest;
import com.nozama.api.dto.response.editora.EditoraResponse;
import com.nozama.api.service.EditoraService;
import com.nozama.api.utils.SpringUri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/editoras")
public class EditoraController {

    @Autowired
    private EditoraService editoraService;

    @GetMapping
    public ResponseEntity<List<EditoraResponse>> getAll() {
        var editoras = editoraService.getAll();
        return ResponseEntity.ok(editoras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EditoraResponse> getById(@PathVariable("id") Long id) {
        var editora = editoraService.getById(id);
        return ResponseEntity.ok(editora);
    }

    @PostMapping
    public ResponseEntity<EditoraResponse> create(@RequestBody EditoraRequest request) {
        var editora = editoraService.create(request);
        var uri = SpringUri.buildLocationForNewResource(editora.getId());
        return ResponseEntity.created(uri).body(editora);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EditoraResponse> update(@PathVariable("id") Long id, @RequestBody EditoraRequest request) {
        var editora = editoraService.update(id, request);
        return ResponseEntity.ok(editora);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        editoraService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
