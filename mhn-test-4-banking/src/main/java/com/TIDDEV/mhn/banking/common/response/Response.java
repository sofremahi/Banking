package com.TIDDEV.mhn.banking.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private String message ;
    private HttpStatus status ;
    private T data ;

    public Response(T data , HttpStatus status) {
        this.status = status;
        this.data = data;
    }
    public Response(HttpStatus status, T data) {
        this.status = status;
        this.data = data;
    }

    public Response(HttpStatus status) {
        this.status = status;
    }

    public Response(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public Response(String message, T data, HttpStatus status) {
        this.message = message;
        this.status = status;
        this.data = data;
    }
}
