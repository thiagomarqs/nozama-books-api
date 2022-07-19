package com.nozama.api.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Required;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class ExceptionResponse {
    private final String message;
    private final Integer httpStatus;
    private Instant moment = Instant.now();
    private final String path;
    private List<ValidationError> errors = new ArrayList<>();

    public void addValidationError(String field, String message) {
        this.errors.add(new ValidationError(field, message));
    }


}
