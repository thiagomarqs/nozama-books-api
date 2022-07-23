package com.nozama.api.controller;

import com.nozama.api.dto.request.formato.FormatoRequest;
import com.nozama.api.dto.response.formato.FormatoResponse;
import com.nozama.api.service.FormatoService;
import com.nozama.api.utils.SpringUri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formatos")
public class FormatoController {

    @Autowired
    private FormatoService formatoService;

    @GetMapping
    public ResponseEntity<List<FormatoResponse>> getAll() {
        var formatos = formatoService.getAll();
        return ResponseEntity.ok(formatos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormatoResponse> getById(@PathVariable("id") Long id) {
        var formato = formatoService.getById(id);
        return ResponseEntity.ok(formato);
    }

    @PostMapping
    public ResponseEntity<FormatoResponse> create(@RequestBody FormatoRequest request) {
        var formato = formatoService.create(request);
        var uri = SpringUri.buildLocationForNewResource(formato.getId());
        return ResponseEntity.created(uri).body(formato);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormatoResponse> update(@PathVariable("id") Long id, @RequestBody FormatoRequest request) {
        var formato = formatoService.update(id, request);
        return ResponseEntity.ok(formato);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        formatoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
