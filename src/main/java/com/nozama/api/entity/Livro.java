package com.nozama.api.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@ToString
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Livro  {

    @Id @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Size(max = 255, message = "O título do livro não deve exceder 255 caracteres.")
    @NotBlank(message = "O título do livro não pode estar vazio.")
    @Column(name = "Titulo", nullable = false)
    private String titulo;

    @Size(max = 2000, message = "A descrição não deve exceder 2000 caracteres.")
    @NotBlank(message = "A descrição do livro não pode estar vazia.")
    @Column(name = "Descricao", nullable = false)
    private String descricao;

    @URL(message = "Por favor, informe uma URL válida.")
    @Column(name = "UrlImagem", nullable = false)
    @NotBlank(message = "A URL da imagem do não pode estar vazia.")
    private String urlImagem;

    @DecimalMin(value = "0.01", message = "O preço não deve ser menor do que 0,01")
    @NotNull(message = "O preço do livro deve ser especificado.")
    @Column(name = "Preco", nullable = false)
    private Double preco;

    @ManyToMany(mappedBy = "livros")
    @NotEmpty(message = "O livro deve estar associado a pelo menos uma categoria.")
    @JsonManagedReference
    private Set<Categoria> categorias = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "FormatoId")
    @NotNull(message = "O formato do livro deve ser especificado.")
    private Formato formato;

    @NotNull(message = "O número de páginas do livro deve ser especificado.")
    @Positive(message = "O número de páginas do livro deve ser maior do que zero.")
    @Column(name = "Paginas", nullable = false)
    private Integer paginas;

    @ManyToOne
    @NotNull(message = "O idioma do livro deve ser especificado.")
    @JoinColumn(name = "IdiomaId")
    private Idioma idioma;

    @ManyToOne
    @NotNull(message = "A editora do livro deve ser especificada.")
    @JoinColumn(name = "EditoraId")
    private Editora editora;

    @ManyToMany(mappedBy = "livros")
    @NotEmpty(message = "O livro deve estar associado a pelo menos um autor.")
    @JsonManagedReference
    private Set<Autor> autores = new HashSet<>();

    @Column(name = "DataPublicacao", nullable = false)
    private LocalDate dataPublicacao;

    @Size(min = 13, max = 13, message = "O ISBN do livro deve ser composto por 13 caracteres.")
    @NotBlank(message = "O ISBN do livro não pode estar vazio.")
    @Column(name = "ISBN", nullable = false)
    private String isbn;

    @DecimalMin(value = "0.01", message = "A altura não deve ser menor do que 0,01")
    @NotNull(message = "A altura do livro deve ser especificada.")
    @Column(name = "DimensaoAltura", nullable = false)
    private Double dimensaoAltura;

    @DecimalMin(value = "0.01", message = "A largura não deve ser menor do que 0,01")
    @NotNull(message = "A largura do livro deve ser especificada.")
    @Column(name = "DimensaoLargura", nullable = false)
    private Double dimensaoLargura;
    
    @DecimalMin(value = "0.01", message = "A profundidade não deve ser menor do que 0,01")
    @NotNull(message = "A profundidade do livro deve ser especificada.")
    @Column(name = "DimensaoProfundidade", nullable = false)
    private Double dimensaoProfundidade;

    @NotNull(message = "A quantidade disponível do livro deve ser especificada.")
    @Positive(message = "A quantidade disponível do livro deve ser maior do que zero.")
    @Column(name = "QuantidadeDisponivel", nullable = false)
    private Integer quantidadeDisponivel;

    @NotNull
    @Column(name = "DataHoraRegistro", nullable = false)
    private LocalDateTime dataHoraRegistro;

    @NotNull
    @Column(name = "Ativo", nullable = false)
    private Boolean ativo;

    public void deleteCategoria(Categoria categoria) {
        if(isInCategoria(categoria)) {
            this.categorias.remove(categoria);
            categoria.deleteLivro(this);
        }
    }

    public void deleteAutor(Autor autor) {
        if(hasAutor(autor)) {
            autores.remove(autor);
            autor.deleteLivro(this);
        }
    }

    public void addCategoria(Categoria categoria) {
        if(!isInCategoria(categoria)) {
            this.categorias.add(categoria);
        }
    }

    public boolean isInCategoria(Categoria categoria) {
        return this.categorias.contains(categoria);
    }

    public boolean hasAutor(Autor autor) {
        return autores.contains(autor);
    }
}
