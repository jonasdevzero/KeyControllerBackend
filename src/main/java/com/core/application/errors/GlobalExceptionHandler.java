package com.core.application.errors;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class GlobalExceptionHandler {
    ErrorMessage errorMessage = new ErrorMessage();
    
//-------------------ERROR:404--------------------//
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public ErrorMessage NoSuchElementException(NoSuchElementException e){
        errorMessage.setStatus(404);
        errorMessage.setMessage("Data Not Found!");
        return errorMessage;
    }
//-------------------ERROR:500--------------------//
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorMessage HttpMessageNotReadableException(HttpMessageNotReadableException e){
        errorMessage.setStatus(500);    
        errorMessage.setMessage("Message Not Readable");
        return errorMessage;
    }
    
}
