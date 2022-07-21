package com.nozama.api.dto.request.categoria;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaPutRequest extends BaseCategoriaRequest{

    private Boolean ativo;
}
