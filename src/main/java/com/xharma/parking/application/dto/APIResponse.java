package com.xharma.parking.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;


@Data
@ToString
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class APIResponse<T> {

    private Integer statusCode;
    private String message;
    private T result;


    public APIResponse(Integer statusCode, String message, T result) {
        super();
        this.statusCode = statusCode;
        this.message = message;
        this.result = result;
    }

    public APIResponse(Integer statusCode, String message) {
        super();
        this.statusCode = statusCode;
        this.message = message;
    }

    public APIResponse(HttpStatus httpStatus, T result) {
        super();
        this.statusCode = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
        this.result = result;
    }

    public APIResponse(HttpStatus httpStatus) {
        super();
        this.statusCode = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
    }
}
