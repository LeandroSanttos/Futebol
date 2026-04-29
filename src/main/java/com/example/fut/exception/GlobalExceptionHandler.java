package com.example.fut.exception;

import com.example.fut.dto.ErrorResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<Object> buildResponse(String message, HttpStatus status) {
        ErrorResponseDTO body = new ErrorResponseDTO(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message
        );

        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(JogadorInvalidoException.class)
    public ResponseEntity<Object> handleJogagorInvalidoException(JogadorInvalidoException exception) {
        return buildResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ClubeInvalidoException.class)
    public ResponseEntity<Object> handleClubeInvalidoException(ClubeInvalidoException exception) {
        return buildResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FederacaoNacionalInvalidaException.class)
    public ResponseEntity<Object> handleFederacaoInvalidaException(FederacaoNacionalInvalidaException exception) {
        return buildResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TransferenciaException.class)
    public ResponseEntity<Object> handleTransferenciaException(TransferenciaException exception) {
        return buildResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException exception) {
        return buildResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException exception) {
        return buildResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleEnumError(MethodArgumentTypeMismatchException exception) {
        return buildResponse("Valor inválido para o parâmetro: " + exception.getName(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Object> handleIllegalStateException(IllegalStateException exception) {
        return buildResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleGenericException(RuntimeException exception) {
        return buildResponse("Erro interno do servidor: " + exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
