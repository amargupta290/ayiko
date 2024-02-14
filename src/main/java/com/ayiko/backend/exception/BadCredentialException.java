//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ayiko.backend.exception;

public class BadCredentialException extends AyikoSystemException {
    public BadCredentialException() {
        this("error.not_found");
    }

    public BadCredentialException(String errorCode) {
        super(errorCode, new Object[0]);
    }

    public BadCredentialException(String errorCode, Object... errorArgs) {
        this(errorCode, (Throwable)null, errorArgs);
    }

    public BadCredentialException(String errorCode, Throwable cause, Object... errorArgs) {
        super(errorCode, cause, errorArgs);
    }
}
