package com.nozama.api.dto.base;

import com.nozama.api.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseLivroDto implements Serializable {

    private String titulo;
    private String descricao;
    private String urlImagem;
    private Double preco;
    private Set<BaseCategoriaDto> categorias = new HashSet<>();
    private Formato formato;
    private Integer paginas;
    private Idioma idioma;
    private Editora editora;
    private Set<Autor> autores = new HashSet<>();
    private LocalDate dataPublicacao;
    private String isbn;
    private Double dimensaoAltura;
    private Double dimensaoLargura;
    private Double dimensaoProfundidade;
    private Integer quantidadeDisponivel;

}
