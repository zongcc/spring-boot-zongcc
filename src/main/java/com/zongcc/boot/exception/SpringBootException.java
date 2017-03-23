package com.zongcc.boot.exception;

/**
 * Created by chunchengzong on 2017-03-20.
 */
public class SpringBootException extends RuntimeException{
    public SpringBootException() {
    }

    public SpringBootException(String message) {
        super(message);
    }

    public SpringBootException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpringBootException(Throwable cause) {
        super(cause);
    }

    public SpringBootException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
