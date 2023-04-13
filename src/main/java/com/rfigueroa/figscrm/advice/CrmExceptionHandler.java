package com.rfigueroa.figscrm.advice;

import com.rfigueroa.figscrm.exception.EntityNotFoundException;
import com.rfigueroa.figscrm.response.CrmErrorResponse;
import com.rfigueroa.figscrm.response.CrmValidationErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class CrmExceptionHandler {

    // add exception handling code
    @ExceptionHandler
    public ResponseEntity<CrmErrorResponse> handleException(EntityNotFoundException entityNotFoundException) {

        // create a CrmErrorResponse

        CrmErrorResponse error = new CrmErrorResponse(
                HttpStatus.NOT_FOUND.value(), // status
                entityNotFoundException.getMessage(), // message
                new Date() // date instance @ creation
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }

    // Validation exception handling
    @ExceptionHandler({javax.validation.ConstraintViolationException.class})
    public ResponseEntity<CrmValidationErrorResponse> handleValidationException(ConstraintViolationException exception) {

        // helper variable for errors
        List<String> errors = exception
                .getConstraintViolations().stream()
                .map(violation -> String.format("%s - %s", violation.getPropertyPath(), violation.getMessage()))
                .collect(Collectors.toList());

        // create CrmValidationErrorResponse
        CrmValidationErrorResponse error = new CrmValidationErrorResponse(
                HttpStatus.BAD_REQUEST.value(), // status
                errors,
                new Date()
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({org.springframework.dao.DataIntegrityViolationException.class})
    public ResponseEntity<CrmErrorResponse> handleConstraintViolation(DataIntegrityViolationException constraintViolation) {

        // create CrmErrorResponse
        CrmErrorResponse error = new CrmErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                constraintViolation.getMostSpecificCause().getMessage(),
                new Date()
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
