package com.nozama.api.service;

import com.nozama.api.dto.request.editora.EditoraRequest;
import com.nozama.api.dto.response.editora.EditoraResponse;
import com.nozama.api.entity.Editora;
import com.nozama.api.repository.EditoraRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EditoraService {

    @Autowired
    private EditoraRepository editoraRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public List<EditoraResponse> getAll() {
        var editoras = editoraRepository.findAll();
        return editoras
                .stream()
                .map(f -> modelMapper.map(f, EditoraResponse.class))
                .toList();
    }

    public EditoraResponse getById(Long id) {
        var editora = editoraRepository.findById(id);
        return modelMapper.map(editora, EditoraResponse.class);
    }

    public EditoraResponse create(EditoraRequest request) {
        var mappedEditora = modelMapper.map(request, Editora.class);
        var editora = editoraRepository.save(mappedEditora);
        return modelMapper.map(editora, EditoraResponse.class);
    }

    public EditoraResponse update(Long id, EditoraRequest request) {
        var editora = editoraRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(request, editora);
        return modelMapper.map(editoraRepository.save(editora), EditoraResponse.class);
    }

    public void delete(Long id) {
        if(!editoraRepository.existsById(id)) throw new NoSuchElementException();
        editoraRepository.deleteById(id);
    }
}
