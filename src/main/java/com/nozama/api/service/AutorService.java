package com.nozama.api.service;

import com.nozama.api.dto.request.autor.AutorRequest;
import com.nozama.api.dto.response.autor.AutorResponse;
import com.nozama.api.entity.Autor;
import com.nozama.api.repository.AutorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public List<AutorResponse> getAll() {
        var autors = autorRepository.findAll();
        return autors
                .stream()
                .map(i -> modelMapper.map(i, AutorResponse.class))
                .toList();
    }

    public AutorResponse getById(Long id) {
        var autor = autorRepository.findById(id).orElseThrow();
        return modelMapper.map(autor, AutorResponse.class);
    }

    public AutorResponse create(AutorRequest request) {
        var mappedAutor = modelMapper.map(request, Autor.class);
        return modelMapper.map(autorRepository.save(mappedAutor), AutorResponse.class);
    }

    public AutorResponse update(Long id, AutorRequest request) {
        var autor = autorRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(request, autor);
        return modelMapper.map(autorRepository.save(autor), AutorResponse.class);
    }

    public void delete(Long id) {
        if(!autorRepository.existsById(id)) throw new NoSuchElementException();
        autorRepository.deleteById(id);
    }
}
