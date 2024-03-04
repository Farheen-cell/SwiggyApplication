package com.masai.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashSet;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerException.class)
   public ResponseEntity<MyErrorDetails> CustomerException(CustomerException ad, WebRequest wrq){

       MyErrorDetails err = new MyErrorDetails();
       err.setLocaldateTime(LocalDateTime.now());
       err.setMessage(ad.getMessage());
       err.setDescription(wrq.getDescription(false));

       return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_GATEWAY);
   }

   @ExceptionHandler(OrderException.class)
    public ResponseEntity<MyErrorDetails> OrderException(OrderException ad, WebRequest wrq){

        MyErrorDetails err = new MyErrorDetails();
        err.setLocaldateTime(LocalDateTime.now());
        err.setMessage(ad.getMessage());
        err.setDescription(wrq.getDescription(false));

        return  new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_GATEWAY);
   }

 @ExceptionHandler(RestaurantException.class)
    public ResponseEntity<MyErrorDetails> RestaurantException(RestaurantException ad, WebRequest wrq){
        MyErrorDetails err = new MyErrorDetails();

        err.setLocaldateTime(LocalDateTime.now());
        err.setMessage(ad.getMessage());
        err.setDescription(wrq.getDescription(false));

        return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_GATEWAY);
 }

    @ExceptionHandler(DeliveryException.class)
    public ResponseEntity<MyErrorDetails> DeliveryException(DeliveryException ad, WebRequest wrq){
        MyErrorDetails err = new MyErrorDetails();

        err.setLocaldateTime(LocalDateTime.now());
        err.setMessage(ad.getMessage());
        err.setDescription(wrq.getDescription(false));

        return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_GATEWAY);
    }
}
