package dev.eldhdpswl.jpa;

import dev.eldhdpswl.jpa.exception.PostNotInBoardException;
import dev.eldhdpswl.jpa.exception.PostNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("except")
public class ExceptTestController {
    @GetMapping("{id}")
    public void throwException(@PathVariable int id){
        switch (id){
            case 1:
                throw new PostNotExistException();
            case 2:
                throw new PostNotInBoardException();
            default:
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


    /*
    *  이 컨트롤러 내부에서 발생하는 BaseException에 대해서 처리하게된다.
    *  결과적으로 ExceptionHandler는 자신이 선언된 controller 내부에서만 작동을 하는 예외처리 방식이다.
    * */
//    @ExceptionHandler(BaseException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorResponseDto handleBaseException(BaseException exception){
//        return new ErrorResponseDto(exception.getMessage());
//
//    }

}
