package com.nozama.api.service;

import com.nozama.api.dto.request.categoria.CategoriaPostRequest;
import com.nozama.api.dto.request.categoria.CategoriaPutRequest;
import com.nozama.api.dto.request.livro.LivroPostRequest;
import com.nozama.api.dto.request.livro.LivroPutRequest;
import com.nozama.api.dto.response.categoria.CategoriaGetResponse;
import com.nozama.api.dto.response.categoria.CategoriaPostResponse;
import com.nozama.api.dto.response.categoria.CategoriaPutResponse;
import com.nozama.api.entity.Categoria;
import com.nozama.api.entity.Livro;
import com.nozama.api.repository.CategoriaRepository;
import com.nozama.api.repository.LivroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoriaService {

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private LivroRepository livroRepository;

    public CategoriaGetResponse getById(Long id) {
        var categoria = categoriaRepository.findById(id).orElseThrow();
        return modelMapper.map(categoria, CategoriaGetResponse.class);
    }

    public List<CategoriaGetResponse> getAll() {
        var categorias = categoriaRepository.findAll();
        return categorias
                .stream()
                .map(c -> modelMapper.map(c, CategoriaGetResponse.class))
                .toList();
    }

    public CategoriaPostResponse create(CategoriaPostRequest request) {
        var mappedCategoria = modelMapper.map(request, Categoria.class);
        mappedCategoria.setAtivo(true);
        return modelMapper.map(categoriaRepository.save(mappedCategoria), CategoriaPostResponse.class);
    }

    public CategoriaPutResponse update(Long id, CategoriaPutRequest request) throws NoSuchElementException {
        if(!categoriaRepository.existsById(id)) {
            throw new NoSuchElementException(String.format("Categoria de id %d não existe.", id));
        }

        var categoria = categoriaRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(request, categoria);
        return modelMapper.map(categoriaRepository.save(categoria), CategoriaPutResponse.class);
    }

    public void delete(Long id) throws NoSuchElementException {
        if(!categoriaRepository.existsById(id)) {
            throw new NoSuchElementException(String.format("Categoria de id %d não existe.", id));
        }

        var categoria = categoriaRepository.getReferenceById(id);
        categoria.getLivros().forEach(l -> l.deleteCategoria(categoria));
        categoriaRepository.delete(categoria);
    }

}
