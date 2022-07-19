package com.nozama.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Autor implements Serializable {

    @Id @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @JsonIgnore
    private Set<Livro> livros = new HashSet();

}
