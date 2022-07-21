package com.nozama.api.controller;

import com.nozama.api.entity.Idioma;
import com.nozama.api.repository.IdiomaRepository;
import com.nozama.api.service.IdiomaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/idiomas")
public class IdiomaController {

    @Autowired
    private IdiomaService idiomaService;

    @GetMapping
    public ResponseEntity<List<Idioma>> getAll() {
        var idiomas = idiomaService.getAll();
        return ResponseEntity.ok(idiomas);
    }
}
