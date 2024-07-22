package com.meow.accounts_service.entity.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseErrorData<T> implements ResponseData<T> {
    int status;
    private String message;

    @Override
    public T getData() {
        return null;
    }
}
