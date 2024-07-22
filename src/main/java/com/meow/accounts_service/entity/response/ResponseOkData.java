package com.meow.accounts_service.entity.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponseOkData<T> implements ResponseData<T>{
    T data;

    @Override
    public int getStatus() {
        return 200;
    }

    @Override
    public String getMessage() {
        return "OK";
    }

}
