package com.kaganmercan.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlingException {

    //yoktur değer olduğunda sporing yakalayacak
    @ExceptionHandler({KaganMercanException.class})
    public String handlingNotFoundException(){
        return "Value is not exist.";
    }


    //null değer olduğunda sporing yakalayacak
    @ExceptionHandler({NullPointerException.class})
    public String handlingNullPointerException(){
        return "Null value entered.";
    }
}
