package cn.my3gods.demotest.advice;

import cn.my3gods.demotest.dto.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("error: {}", e.getMessage(), e);
        e.printStackTrace();
        StringBuilder sb = new StringBuilder();
        e.getBindingResult().getFieldErrors().forEach(fieldError -> sb.append(fieldError.getDefaultMessage()).append(";"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CommonResponse.fail(sb.toString()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse> exceptionHandler(Exception e) {
        log.error("error: {}", e.getMessage(), e);
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CommonResponse.fail(e.getMessage()));
    }
}
