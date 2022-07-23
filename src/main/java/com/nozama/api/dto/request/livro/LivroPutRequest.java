package com.nozama.api.dto.request.livro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LivroPutRequest extends LivroRequest {

    private Boolean ativo;

}
