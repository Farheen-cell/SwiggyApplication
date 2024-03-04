package com.masai.exception;

import org.aspectj.weaver.ast.Or;

public class OrderException extends Exception {

    public OrderException(){}

    public OrderException(String message){
        super(message);
    }
}
