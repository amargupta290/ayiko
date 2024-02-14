//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ayiko.backend.exception;

import java.util.Locale;

public class CommonApplicationException extends AyikoApplicationException {
    public CommonApplicationException() {
        this("error.application");
    }

    public CommonApplicationException(String errorCode) {
        super(errorCode, new Object[0]);
    }

    public CommonApplicationException(String errorCode, Object... errorArgs) {
        this(errorCode, (Throwable)null, errorArgs);
    }

    public CommonApplicationException(String errorCode, Throwable cause, Object... errorArgs) {
        super(errorCode, cause, errorArgs);
    }

    @Override
    public String getLocalizedMessage(String errorCode, Locale locale, Object... args) {
        return getLocalizedMessage();
    }
}
