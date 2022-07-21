package com.nozama.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@ToString
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Categoria.class
)
public class Categoria implements Serializable {

    @Id @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Size(max = 100, message = "O nome da categoria não deve exceder 100 caracteres.")
    @NotBlank(message = "O nome da categoria não pode estar vazio.")
    @Column(name = "Categoria", nullable = false)
    private String categoria;

    @Size(max = 255, message = "A descrição da categoria não deve exceder 255 caracteres.")
    @NotBlank(message = "A descrição da categoria não pode estar vazia.")
    @Column(name = "Descricao", nullable = false)
    private String descricao;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "LivroCategoria",
            joinColumns = {@JoinColumn(name = "CategoriaId", referencedColumnName = "Id")},
            inverseJoinColumns = {@JoinColumn(name = "LivroId", referencedColumnName = "Id")}
    )
    private Set<Livro> livros = new HashSet<>();

    @NotNull(message = "Não deve ser nulo")
    @Column(name = "Ativo", nullable = false)
    private Boolean ativo;

    public void deleteLivro(Livro livro) {
        if(hasLivro(livro)) {
            this.livros.remove(livro);
            livro.getCategorias().remove(this);
        }
    }

    public void addLivro(Livro livro) {
        if(!this.hasLivro(livro)) {
            this.livros.add(livro);
            livro.addCategoria(this);
        }
    }

    public boolean hasLivro(Livro livro) {
        return this.livros.contains(livro);
    }
}
