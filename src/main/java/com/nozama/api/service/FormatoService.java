package com.nozama.api.service;

import com.nozama.api.dto.request.formato.FormatoRequest;
import com.nozama.api.dto.response.formato.FormatoResponse;
import com.nozama.api.entity.Formato;
import com.nozama.api.repository.FormatoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FormatoService {

    @Autowired
    private FormatoRepository formatoRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public List<FormatoResponse> getAll() {
        var formatos = formatoRepository.findAll();
        return formatos
                .stream()
                .map(f -> modelMapper.map(f, FormatoResponse.class))
                .toList();
    }

    public FormatoResponse getById(Long id) {
        var formato = formatoRepository.findById(id);
        return modelMapper.map(formato, FormatoResponse.class);
    }

    public FormatoResponse create(FormatoRequest request) {
        var mappedFormato = modelMapper.map(request, Formato.class);
        var formato = formatoRepository.save(mappedFormato);
        return modelMapper.map(formato, FormatoResponse.class);
    }

    public FormatoResponse update(Long id, FormatoRequest request) {
        var formato = formatoRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(request, formato);
        return modelMapper.map(formatoRepository.save(formato), FormatoResponse.class);
    }

    public void delete(Long id) {
        if(!formatoRepository.existsById(id)) throw new NoSuchElementException();
        formatoRepository.deleteById(id);
    }
}
