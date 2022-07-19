package com.nozama.api.service;

import com.nozama.api.dto.request.livro.LivroPostRequest;
import com.nozama.api.dto.request.livro.LivroPutRequest;
import com.nozama.api.dto.response.livro.LivroGetResponse;
import com.nozama.api.entity.Livro;
import com.nozama.api.repository.LivroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LivroService {

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private LivroRepository livroRepository;

    public LivroGetResponse getById(Long id) throws NoSuchElementException{
        var livro = livroRepository.findById(id).orElseThrow();
        return modelMapper.map(livro, LivroGetResponse.class);
    }

    public List<LivroGetResponse> getAll() {
        return livroRepository.findAll()
                .stream()
                .map(livro -> modelMapper.map(livro, LivroGetResponse.class))
                .toList();
    }

    public Livro create(LivroPostRequest request) {
        var mappedLivro = modelMapper.map(request, Livro.class);
        mappedLivro.setDataHoraRegistro(LocalDateTime.now());
        mappedLivro.setAtivo(true);
        return livroRepository.save(mappedLivro);
    }

    public Livro update(Long id, LivroPutRequest request) throws NoSuchElementException {
        if(!livroRepository.existsById(id)) {
            throw new NoSuchElementException(String.format("Livro de id %d não existe.", id));
        }

        var livro = livroRepository.getReferenceById(id);
        BeanUtils.copyProperties(request, livro);
        return livroRepository.save(livro);
    }

    public void delete(Long id) throws NoSuchElementException {
        if(!livroRepository.existsById(id)) {
            throw new NoSuchElementException(String.format("Livro de id %d não existe.", id));
        }
        livroRepository.deleteById(id);
    }


}
