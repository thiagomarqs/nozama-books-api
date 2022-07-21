package com.nozama.api.service;

import com.nozama.api.entity.Idioma;
import com.nozama.api.repository.IdiomaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdiomaService {

    @Autowired
    private IdiomaRepository idiomaRepository;

    public List<Idioma> getAll() {
        return idiomaRepository.findAll();
    }
}
