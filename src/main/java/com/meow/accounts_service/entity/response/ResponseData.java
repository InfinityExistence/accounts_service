package com.meow.accounts_service.entity.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface ResponseData<T> {
    int getStatus();
    String getMessage();
    T getData();


    static <S> ResponseData<S> ok(S Data) {
        return new ResponseOkData<>(Data);
    }
    static ResponseData<Object> ok() {
        return new ResponseOkData<>(null);
    }

    static ResponseData<Object> unauthorized(String message) {
        return new ResponseErrorData<>(401, message);
    }
    static ResponseData<Object> forbidden(String message) {
        return new ResponseErrorData<>(403, message);
    }
    static ResponseData<Object> internalError(String message) {
        return new ResponseErrorData<>(500, message);
    }

}
