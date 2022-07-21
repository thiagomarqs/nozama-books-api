package com.nozama.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
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
        scope = Autor.class
)
public class Autor implements Serializable {

    @Id @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Size(max = 255, message = "O nome do autor não deve exceder 255 caracteres.")
    @NotBlank(message = "O nome do autor não pode estar vazio.")
    @Column(name = "Nome", nullable = false)
    private String nome;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
        name = "LivroAutor",
        joinColumns = {@JoinColumn(name = "AutorId", referencedColumnName = "Id")},
        inverseJoinColumns = {@JoinColumn(name = "LivroId", referencedColumnName = "Id")}
    )
    private Set<Livro> livros = new HashSet<>();

    public void deleteLivro(Livro livro) {
        if(hasLivro(livro)) {
            this.livros.remove(livro);
            livro.getAutores().remove(this);
        }
    }

    public boolean hasLivro(Livro livro) {
        return livros.contains(livro);
    }

}
