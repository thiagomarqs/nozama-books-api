package com.nozama.api.service;

import com.nozama.api.dto.request.livro.LivroPostRequest;
import com.nozama.api.dto.request.livro.LivroPutRequest;
import com.nozama.api.dto.response.livro.LivroGetResponse;
import com.nozama.api.dto.response.livro.LivroPostResponse;
import com.nozama.api.dto.response.livro.LivroPutResponse;
import com.nozama.api.entity.Categoria;
import com.nozama.api.entity.Livro;
import com.nozama.api.repository.AutorRepository;
import com.nozama.api.repository.CategoriaRepository;
import com.nozama.api.repository.LivroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class LivroService {

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private AutorRepository autorRepository;

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

    public LivroPostResponse create(LivroPostRequest request) {
        var mappedLivro = modelMapper.map(request, Livro.class);
        mappedLivro.setDataHoraRegistro(LocalDateTime.now());
        mappedLivro.setAtivo(true);
        return modelMapper.map(livroRepository.save(mappedLivro), LivroPostResponse.class);
    }

    public LivroPutResponse update(Long id, LivroPutRequest request) throws NoSuchElementException {
        var livro = livroRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(request, livro);
        return modelMapper.map(livroRepository.save(livro), LivroPutResponse.class);
    }

    public void delete(Long id) throws NoSuchElementException {
        if(!livroRepository.existsById(id)) throw new NoSuchElementException();

        var livro = livroRepository.getReferenceById(id);

        livro.getCategorias().forEach(c -> livro.deleteCategoria(c));
        livro.getAutores().forEach(a -> livro.deleteAutor(a));

        livroRepository.delete(livro);
    }


}
