package com.nozama.api.service;

import com.nozama.api.dto.base.IdiomaDto;
import com.nozama.api.dto.request.idioma.IdiomaRequest;
import com.nozama.api.dto.response.idioma.IdiomaResponse;
import com.nozama.api.entity.Idioma;
import com.nozama.api.repository.IdiomaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class IdiomaService {

    @Autowired
    private IdiomaRepository idiomaRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public List<IdiomaResponse> getAll() {
        var idiomas = idiomaRepository.findAll();
        return idiomas
                .stream()
                .map(i -> modelMapper.map(i, IdiomaResponse.class))
                .toList();
    }

    public IdiomaResponse getById(Long id) {
        var idioma = idiomaRepository.findById(id).orElseThrow();
        return modelMapper.map(idioma, IdiomaResponse.class);
    }

    public IdiomaResponse create(IdiomaRequest request) {
        var mappedIdioma = modelMapper.map(request, Idioma.class);
        return modelMapper.map(idiomaRepository.save(mappedIdioma), IdiomaResponse.class);
    }

    public IdiomaResponse update(Long id, IdiomaRequest request) {
        var idioma = idiomaRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(request, idioma);
        return modelMapper.map(idiomaRepository.save(idioma), IdiomaResponse.class);
    }

    public void delete(Long id) {
        if(!idiomaRepository.existsById(id)) throw new NoSuchElementException();
        idiomaRepository.deleteById(id);
    }
}
