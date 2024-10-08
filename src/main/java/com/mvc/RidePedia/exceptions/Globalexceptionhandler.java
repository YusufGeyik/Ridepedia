package com.mvc.RidePedia.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class Globalexceptionhandler {
    @ExceptionHandler (CarNotFoundEx.class)
    public ResponseEntity<ErrorObject> handleCarNotFoundEx(CarNotFoundEx ex, WebRequest request)
    {
        ErrorObject errorObject=new ErrorObject();

        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());

        return new ResponseEntity<>(errorObject,HttpStatus.NOT_FOUND);


    }

    @ExceptionHandler(ReviewNotFoundEx.class)
            public ResponseEntity<ErrorObject> handleReviewNotFoundEx(ReviewNotFoundEx ex,WebRequest request)
    {
        ErrorObject errorObject=new ErrorObject();
        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());
        return new ResponseEntity<>(errorObject,HttpStatus.NOT_FOUND);
    }

}
