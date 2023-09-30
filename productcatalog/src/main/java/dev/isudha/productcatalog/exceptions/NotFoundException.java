package dev.isudha.productcatalog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// @ResponseStatus allows to send particular response status
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends Exception{

    public NotFoundException(String message){
        super(message);
    }

}
