package com.nozama.api.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.ConstraintViolation;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class ExceptionResponse {
    private final String message;
    private final Integer httpStatus;
    private Instant moment = Instant.now();
    private final String path;
    private List<ValidationError> errors = new ArrayList<>();

    public ExceptionResponse(String message, Integer httpStatus, String path, Set<ConstraintViolation<?>> violations) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.path = path;
        errors.addAll(mapConstraintViolationsToValidationErrors(violations));
    }

    private List<ValidationError> mapConstraintViolationsToValidationErrors(Set<ConstraintViolation<?>> violations) {
        return violations.stream().map(this::mapConstraintViolationToValidationError).toList();
    }

    private ValidationError mapConstraintViolationToValidationError(ConstraintViolation<?> violation) {
        var field = violation.getPropertyPath().toString();
        var violationMessage = violation.getMessage();
        return new ValidationError(field, violationMessage);
    }

    public void addValidationError(String field, String message) {
        this.errors.add(new ValidationError(field, message));
    }

}
