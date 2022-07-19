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
public class Formato implements Serializable {

    @Id @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 100, message = "O formato do livro não deve exceder 100 caracteres.")
    @NotBlank(message = "O formato do livro não pode estar vazio.")
    @Column(name = "Formato", nullable = false)
    private String formato;

}
