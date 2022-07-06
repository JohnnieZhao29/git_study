package com.zjydemo.mallstore.service.ex;

/**
 * @author zjy
 * @version 1.0
 */

public class AddressNumbersOutOfException extends ServiceException{
    public AddressNumbersOutOfException() {
        super();
    }

    public AddressNumbersOutOfException(String message) {
        super(message);
    }

    public AddressNumbersOutOfException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressNumbersOutOfException(Throwable cause) {
        super(cause);
    }

    protected AddressNumbersOutOfException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

