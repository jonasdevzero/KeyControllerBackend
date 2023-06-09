package com.core.errors;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@Controller
public class GlobalExceptionHandler {
    ErrorMessage errorMessage = new ErrorMessage();

    // -------------------ERRO:400---------------------//
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ErrorMessage badRequestSuapAuthentication() {
        errorMessage.setStatus(400);
        errorMessage.setDetail("É necessário informar o login e a senha do usuário.");
        return errorMessage;
    }
    // -------------------Erro:401----------------------//
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ErrorMessage unauthorizedSuapAuthentication() {
        errorMessage.setStatus(401);
        errorMessage.setDetail("Credenciais inválidas");
        return errorMessage;
    }
    // -------------------Erro:403----------------------//
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ErrorMessage ForbiddenSuapAuthentication() {
        errorMessage.setStatus(403);
        errorMessage.setDetail("Tentativas excessivas de logins. Por favor efetue o login na página inicial do suap.");
        return errorMessage;
    }

    // -------------------ERROR:404--------------------//
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public ErrorMessage NoSuchElementException(NoSuchElementException e) {
        errorMessage.setStatus(404);
        errorMessage.setDetail("Data Not Found!");
        return errorMessage;
    }

    // -------------------ERROR:500--------------------//
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorMessage HttpMessageNotReadableException(HttpMessageNotReadableException e) {
        errorMessage.setStatus(500);
        errorMessage.setDetail("Message Not Readable");
        return errorMessage;
    }

}
