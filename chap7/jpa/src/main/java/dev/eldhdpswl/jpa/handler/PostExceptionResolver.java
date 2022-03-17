package dev.eldhdpswl.jpa.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.eldhdpswl.jpa.exception.BaseException;
import dev.eldhdpswl.jpa.exception.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class PostExceptionResolver extends AbstractHandlerExceptionResolver {

    /*
    * ExceptionResolver를 구현하게되면 어떠한 특정한 예외가 왔을때 처리할수 있는 Resolver가 있는지 서려보면서 처리한다.
    *
    * ModelAndView를 반환하는 것은 spring web 기준이기 떄문이다.
    * */
    @Override
    protected ModelAndView doResolveException(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex) {
        logger.debug(ex.getClass());
//        if (ex instanceof BaseException) {
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            try{
//                response.getOutputStream().print(
//                        new ObjectMapper().writeValueAsString(
//                                new ErrorResponseDto("in resolver, message: "+ ex.getMessage())
//                        )
//                );
//                response.setHeader("content-Type", MediaType.APPLICATION_JSON_VALUE);
//                return new ModelAndView();
//            }catch (IOException e){
//                logger.warn("Handling exception caused exception: {}", e);
//            }
//
//        }

        return null;
    }
}
