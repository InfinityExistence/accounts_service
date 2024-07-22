package com.meow.accounts_service.exception;

import com.meow.accounts_service.entity.response.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthenticationGlobalExceptionHandler {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());


    @ExceptionHandler
    public ResponseEntity<ResponseData<Object>> handleException(UnauthorizedException exception) {
        ResponseData<Object> authenticationIncorrectData
                = ResponseData.unauthorized(exception.getMessage());
        return new ResponseEntity<>(authenticationIncorrectData, HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler
    public ResponseEntity<ResponseData<Object>> handleException(BadCredentialsException exception) {
        ResponseData<Object> responseErrorData
                = ResponseData.unauthorized(exception.getMessage());
        return new ResponseEntity<>(responseErrorData, HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler
    public ResponseEntity<ResponseData<Object>> handleException(AuthenticationException exception) {
        ResponseData<Object> responseErrorData
                = ResponseData.unauthorized(exception.getMessage());
        return new ResponseEntity<>(responseErrorData, HttpStatus.UNAUTHORIZED);

    }


    @ExceptionHandler
    public ResponseEntity<ResponseData<Object>> handleException(OutdatedException exception) {
        ResponseData<Object> responseErrorData
                = ResponseData.forbidden(exception.getMessage());
        return new ResponseEntity<>(responseErrorData, HttpStatus.FORBIDDEN);

    }

    @ExceptionHandler
    public ResponseEntity<ResponseData<Object>> handleException(Exception exception) {
        LOGGER.error(exception.getMessage());
        ResponseData<Object> responseErrorData
                = ResponseData.internalError("Internal server error.");
        return new ResponseEntity<>(responseErrorData, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
