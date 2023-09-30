package dev.isudha.productcatalog.dtos;

import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ExceptionDto {
    private HttpStatus errorCode;
    private String message;

}
