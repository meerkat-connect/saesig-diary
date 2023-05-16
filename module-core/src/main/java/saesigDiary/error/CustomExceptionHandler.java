package saesigDiary.error;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> runtimeExceptionHandler(Exception e) {
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse = ErrorResponse.of(errorCode.getCode(), e.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;
        ErrorResponse errorResponse = ErrorResponse.of(errorCode.getCode(), e.getMessage(), e.getBindingResult());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> bindExceptionHandler(BindException e) {
        ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;
        ErrorResponse errorResponse = ErrorResponse.of(errorCode.getCode(), e.getMessage(), e.getBindingResult());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(TypeMismatchException.class)
    public ResponseEntity<ErrorResponse> TypeMismatchExceptionHandler(TypeMismatchException e) {
        ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;
        ErrorResponse errorResponse = ErrorResponse.of(errorCode.getCode(), e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }
}
