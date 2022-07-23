package com.nozama.api.dto.response.livro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LivroPutResponse extends BaseLivroResponse {
    private boolean ativo;
}
