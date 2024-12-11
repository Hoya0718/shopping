/*
package com.nayo.shop;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class MyExceptionHandler {

    //@ControllerAdvice 모든 Controller 파일의 에러를 캐치
    @ExceptionHandler(Exception.class)//모든 API의 에러를 캐치
    public ResponseEntity<String> handler1() {
        return ResponseEntity.status(400).body("에러남");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)//에러마다 다르게 처리 가능
    public ResponseEntity<String> handler2() {
        return ResponseEntity.status(400).body("에러남");
    }
}
*/