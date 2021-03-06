package com.nozama.api.dto.response.livro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LivroPutResponse extends LivroResponse {
    private boolean ativo;
}
