package com.nozama.api.dto.response.categoria;

import com.nozama.api.dto.base.CategoriaDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaResponse extends CategoriaDto {
    private Long id;
}
