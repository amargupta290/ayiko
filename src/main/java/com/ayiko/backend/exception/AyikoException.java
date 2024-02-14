//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ayiko.backend.exception;

public class AyikoException extends RuntimeException {
    public AyikoException(String message) {
        super(message);
    }

    public AyikoException(String errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public AyikoException(String errorCode, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(errorCode, cause, enableSuppression, writableStackTrace);
    }
}
