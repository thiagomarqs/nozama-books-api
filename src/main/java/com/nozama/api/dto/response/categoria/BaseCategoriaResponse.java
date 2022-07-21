package com.nozama.api.dto.response.categoria;

import com.nozama.api.dto.base.BaseCategoriaDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseCategoriaResponse extends BaseCategoriaDto {
    private Long id;
}
