package com.nozama.api.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class Idioma implements Serializable {

    @Id @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 25, message = "O idioma não deve exceder 25 caracteres.")
    @NotBlank(message = "O idioma não pode estar vazio.")
    @Column(name = "Idioma", nullable = false)
    private String idioma;

    @Size(min = 2, max = 2, message = "O código do idioma deve ser composto por 2 caracteres.")
    @NotBlank(message = "O código do idioma não pode estar vazio.")
    @Column(name = "Codigo", nullable = false)
    private String codigo;

}
