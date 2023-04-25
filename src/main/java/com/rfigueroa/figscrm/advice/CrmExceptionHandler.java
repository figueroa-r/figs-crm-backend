package com.rfigueroa.figscrm.advice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.rfigueroa.figscrm.exception.EntityNotFoundException;
import com.rfigueroa.figscrm.response.CrmErrorResponse;
import com.rfigueroa.figscrm.response.CrmValidationErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
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


    @ExceptionHandler({javax.persistence.EntityNotFoundException.class})
    public ResponseEntity<CrmErrorResponse> handleEntityNotFoundException(
            javax.persistence.EntityNotFoundException entityNotFoundException) {

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

    // entity does not exist for parameter id
    @ExceptionHandler({java.util.NoSuchElementException.class})
    public ResponseEntity<CrmErrorResponse> handleMissingElementViolation(NoSuchElementException exception) {

        // create CrmErrorResponse
        CrmErrorResponse error = new CrmErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "One or more of the provided Entity id's does not exist",
                new Date()
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // invalid method parameter
    @ExceptionHandler({org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class})
    public ResponseEntity<CrmErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException exception) {

        // custom message for type
        String message = exception.getName() + " should be of type " + exception.getRequiredType().getName();

        // create CrmErrorResponse
        CrmErrorResponse error = new CrmErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                message,
                new Date()
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // missing request parameter
    @ExceptionHandler({org.springframework.web.bind.MissingServletRequestParameterException.class})
    public ResponseEntity<CrmErrorResponse> handleMissingParameter(MissingServletRequestParameterException exception) {

        // custom message
        String message = exception.getMessage();

        // create CrmErrorResponse
        CrmErrorResponse error = new CrmErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                message,
                new Date()
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // JSON parsing error
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<CrmErrorResponse> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException exception) {

        // default message
        String message = "Invalid request payload";

        if(exception.getCause() instanceof InvalidFormatException) {
            InvalidFormatException invalidEx = (InvalidFormatException) exception.getCause();
            String fieldName = invalidEx.getPath().get(0).getFieldName();
            String fieldType = invalidEx.getTargetType().getSimpleName();
            message = String.format("%s must be a valid %s", fieldName, fieldType);
        }

        // create CrmErrorResponse
        CrmErrorResponse error = new CrmErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                message,
                new Date()
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // @Valid method parameter validation
    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public ResponseEntity<CrmValidationErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {

        List<String> errors = exception
                .getFieldErrors().stream()
                .map(error -> String.format("%s - %s", error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());

        // create Validation Error response
        CrmValidationErrorResponse error = new CrmValidationErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                errors,
                new Date()
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // attempting to delete a record that doesn't exist
    @ExceptionHandler({org.springframework.dao.EmptyResultDataAccessException.class})
    public ResponseEntity<CrmErrorResponse> handleEmptyResultDataAccessException(EmptyResultDataAccessException exception) {

        // message
        String message = exception.getMessage();



        // create validation error response
        CrmErrorResponse error = new CrmErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                message,
                new Date()
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
