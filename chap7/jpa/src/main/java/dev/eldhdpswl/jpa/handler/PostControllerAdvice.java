package dev.eldhdpswl.jpa.handler;

import dev.eldhdpswl.jpa.exception.BaseException;
import dev.eldhdpswl.jpa.exception.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

//@ControllerAdvice
@RestControllerAdvice
public class PostControllerAdvice {

    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    
    // @ContollerAdvice일떄
//    public @ResponseBody ErrorResponseDto handleException(BaseException exception){
//        return new ErrorResponseDto(exception.getMessage());
//    }

    // @RestControllerAdvice 일떄
    public ErrorResponseDto handleException(BaseException exception){
        return new ErrorResponseDto(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponseDto handleValidException(
            MethodArgumentNotValidException exception
    ){
        return  new ErrorResponseDto(exception.getMessage());
    }
    
}

