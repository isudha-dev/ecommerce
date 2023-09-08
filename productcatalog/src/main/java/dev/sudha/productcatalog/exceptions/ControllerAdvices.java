package dev.sudha.productcatalog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import dev.sudha.productcatalog.dtos.ExceptionDto;

@ControllerAdvice
// kind of interceptor
// every controller calls this class after its work is done
public class ControllerAdvices {
    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException notFoundException){
        return new ResponseEntity<>(new ExceptionDto(HttpStatus.NOT_FOUND, notFoundException.getMessage()), HttpStatus.NOT_FOUND);
    }

}
