package com.nozama.api.dto.response.categoria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoriaPutResponse extends BaseCategoriaResponse {
    private boolean ativo;
}
