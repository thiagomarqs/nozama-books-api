package com.nozama.api.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class Editora implements Serializable {

    @Id @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255, message = "O nome da editora não deve exceder 255 caracteres.")
    @NotBlank(message = "O nome da editora não pode estar vazio.")
    @Column(name = "Nome", nullable = false)
    private String nome;

    @URL(message = "Por favor, informe uma URL válida.")
    @NotBlank(message = "O site da editora não pode estar vazio.")
    @Column(name = "Site", nullable = false)
    private String site;

}
