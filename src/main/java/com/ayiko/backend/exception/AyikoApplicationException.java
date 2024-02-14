//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ayiko.backend.exception;

import java.util.Locale;
import org.apache.commons.lang3.ObjectUtils;

public abstract class AyikoApplicationException extends AyikoException {
    private final Object[] errorArguments;

    public AyikoApplicationException() {
        this("error.application");
    }

    public AyikoApplicationException(String errorCode, Object... errorArgs) {
        this(errorCode, (Throwable)null, errorArgs);
    }

    public AyikoApplicationException(String errorCode, Throwable cause, Object... errorArgs) {
        super(errorCode, cause);
        this.errorArguments = errorArgs;
    }

    public AyikoApplicationException(Throwable cause) {
        this("error.application", cause);
    }

    public AyikoApplicationException(String errorCode, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Object... errorArgs) {
        super(errorCode, cause, enableSuppression, writableStackTrace);
        this.errorArguments = errorArgs;
    }

    public abstract String getLocalizedMessage(String errorCode, Locale locale, Object... args);

    private String getComponent() {
        return this.getClass().getSimpleName();
    }

    public Object[] getArguments() {
        return (Object[]) ObjectUtils.cloneIfPossible(this.errorArguments);
    }

    public String getUserMessage() {
        return super.getMessage();
    }
/*
    public String getTraceabilityMessage() {
        return GoSiteExceptionUtils.buildTraceabilityMessage(this.getComponent(), super.getMessage(), this.getCause());
    }*/
}
