package com.nozama.api.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@ToString
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Idioma implements Serializable {

    @Id @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
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
