package com.nozama.api.dto.request.livro;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LivroPutRequest extends BaseLivroRequest {

    private Boolean ativo;

}
