package com.saesig.error;

import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiRequestResult> commonExceptionHandler(Exception ex) {
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse = ErrorResponse.of(errorCode.getMessage(), errorCode.getCode());
        ex.printStackTrace();

        return ResponseEntity.status(errorCode.getStatus()).body(ApiRequestResult.of(errorResponse));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiRequestResult> runtimeExceptionHandler(RuntimeException ex) {
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse = ErrorResponse.of(errorCode.getMessage(), errorCode.getCode());
        ex.printStackTrace();

        return ResponseEntity.status(errorCode.getStatus()).body(ApiRequestResult.of(errorResponse));
    }

    @ExceptionHandler(NicknameDuplicateException.class)
    public ResponseEntity<ApiRequestResult> nicknameDuplicateExceptionHandler(NicknameDuplicateException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        ErrorResponse errorResponse = ErrorResponse.of(ex.getMessage(), errorCode.getCode());

        return ResponseEntity.status(errorCode.getStatus()).body(ApiRequestResult.of(errorResponse));
    }

    @ExceptionHandler(VerificationCodeMismatchException.class)
    public ResponseEntity<ApiRequestResult> verificationCodeMismatchExceptionHandler(VerificationCodeMismatchException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        ErrorResponse errorResponse = ErrorResponse.of(ex.getMessage(), errorCode.getCode());

        return ResponseEntity.status(errorCode.getStatus()).body(ApiRequestResult.of(errorResponse));
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex
            , HttpHeaders headers
            , HttpStatus status
            , WebRequest request) {
        ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;
        ErrorResponse errorResponse = ErrorResponse.of(errorCode.getMessage(), errorCode.getCode(), ex.getBindingResult());

        return ResponseEntity.status(errorCode.getStatus()).body(ApiRequestResult.of(errorResponse));
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(
            TypeMismatchException ex
            , HttpHeaders headers
            , HttpStatus status
            , WebRequest request) {
        ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;
        ErrorResponse errorResponse = ErrorResponse.of(errorCode.getMessage(), errorCode.getCode());

        return ResponseEntity.status(errorCode.getStatus()).body(ApiRequestResult.of(errorResponse));
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;
        ErrorResponse errorResponse = ErrorResponse.of(errorCode.getMessage(), errorCode.getCode(), ex.getBindingResult());

        return ResponseEntity.status(errorCode.getStatus()).body(ApiRequestResult.of(errorResponse));
    }
}
