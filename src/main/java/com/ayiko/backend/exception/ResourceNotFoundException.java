//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ayiko.backend.exception;

public class ResourceNotFoundException extends AyikoSystemException {
    public ResourceNotFoundException() {
        this("error.not_found");
    }

    public ResourceNotFoundException(String errorCode) {
        super(errorCode, new Object[0]);
    }

    public ResourceNotFoundException(String errorCode, Object... errorArgs) {
        this(errorCode, (Throwable)null, errorArgs);
    }

    public ResourceNotFoundException(String errorCode, Throwable cause, Object... errorArgs) {
        super(errorCode, cause, errorArgs);
    }
}
