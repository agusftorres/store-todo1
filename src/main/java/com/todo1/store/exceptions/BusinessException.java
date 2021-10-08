package com.todo1.store.exceptions;

import com.todo1.store.ErrorCode;

public class BusinessException extends GenericException {

    public BusinessException(ErrorCode errorCode){
        super(errorCode);
    }
}
