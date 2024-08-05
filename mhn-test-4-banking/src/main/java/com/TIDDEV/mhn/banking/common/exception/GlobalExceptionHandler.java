package com.TIDDEV.mhn.banking.common.exception;

import com.TIDDEV.mhn.banking.common.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundExceptionCustom.class)
    public ResponseEntity<Response> notFound(NotFoundExceptionCustom e){
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response , HttpStatus.FOUND);
    }
    @ExceptionHandler(NotEnoughMoneyException.class)
    public ResponseEntity<Response> notEnoughMoney(NotEnoughMoneyException e){
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response , HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NotValidInputException.class)
    public ResponseEntity<Response> notValidInput(NotValidInputException e){
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response , HttpStatus.BAD_REQUEST);
    }
}
