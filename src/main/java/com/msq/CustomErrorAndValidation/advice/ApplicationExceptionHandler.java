package com.msq.CustomErrorAndValidation.advice;

import com.msq.CustomErrorAndValidation.exception.UserNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})

    public Map<String,String> handleInvalidArgument(MethodArgumentNotValidException ex){
    Map<String ,String> errorMap=new HashMap<>();
    List<FieldError> errors = ex.getBindingResult().getFieldErrors();
    errors.forEach(err->{
        errorMap.put(err.getField(),err.getDefaultMessage());
            }
            );
return errorMap;
}
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserNotFound.class)
    public Map<String ,String> UserNotFound(UserNotFound ex){
    Map<String ,String> errorMap=new HashMap<>();
    String message = ex.getMessage();
    errorMap.put("issue message",message);
    return errorMap;
}


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Map<String ,String> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex){
        Map<String ,String> errorMap=new HashMap<>();
        String message = ex.getMessage();
        errorMap.put("issue message",message);
        return errorMap;
    }

}
