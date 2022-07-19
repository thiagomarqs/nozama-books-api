package com.nozama.api.dto.response.livro;

import com.nozama.api.dto.base.BaseLivroDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseLivroResponse extends BaseLivroDto {

    private Long id;
    private LocalDateTime dataHoraRegistro;

}
