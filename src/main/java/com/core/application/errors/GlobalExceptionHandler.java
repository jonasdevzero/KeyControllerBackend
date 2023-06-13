package com.core.application.errors;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class GlobalExceptionHandler {
    ErrorMessage errorMessage = new ErrorMessage();

    // -------------------ERRO:400---------------------//
    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseStatusException badRequestSuapAuthentication() {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, "É necessário informar o login e a senha do usuário.");
    }
    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseStatusException missingData() {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing Data");
    }
    // -------------------Erro:401----------------------//
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseStatusException unauthorizedSuapAuthentication() {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, "Credenciais inválidas");
    }

    // @ResponseStatus(HttpStatus.UNAUTHORIZED)
    // @ExceptionHandler(SignatureException.class)
    // public ErrorMessage wrongToken() {
    //     errorMessage.setStatus(401);
    //     errorMessage.setDetail("Invalid Token");
    //     return errorMessage;
    // }

    // @ResponseStatus(HttpStatus.UNAUTHORIZED)
    // @ExceptionHandler(ExpiredJwtException.class)
    // public ErrorMessage expiredToken() {
    //     errorMessage.setStatus(401);
    //     errorMessage.setDetail("Expired Token");
    //     return errorMessage;
    // }


    // -------------------Erro:403----------------------//
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ResponseStatusException ForbiddenSuapAuthentication() {
        return new ResponseStatusException(HttpStatus.FORBIDDEN, "Tentativas excessivas de logins. Por favor efetue o login na página inicial do suap.");
    }

    // -------------------ERROR:404--------------------//
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseStatusException NoSuchElementException(NoSuchElementException e) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Not Found!");
    }

    // -------------------ERROR:500--------------------//
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseStatusException HttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Message Not Readable");
    }

}
