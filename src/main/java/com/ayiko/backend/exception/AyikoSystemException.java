//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ayiko.backend.exception;

import org.apache.commons.lang3.ObjectUtils;

public abstract class AyikoSystemException extends AyikoException {
    protected final Object[] errorArguments;

    public AyikoSystemException() {
        this("error.system");
    }

    public AyikoSystemException(String errorCode, Object... errorArgs) {
        this(errorCode, (Throwable)null, errorArgs);
    }

    public AyikoSystemException(String errorCode, Throwable cause, Object... errorArgs) {
        super(errorCode, cause);
        this.errorArguments = errorArgs;
    }

    public AyikoSystemException(Throwable cause) {
        this("error.system", cause);
    }

    public AyikoSystemException(String errorCode, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Object... errorArgs) {
        super(errorCode, cause, enableSuppression, writableStackTrace);
        this.errorArguments = errorArgs;
    }

    private String getComponent() {
        return this.getClass().getSimpleName();
    }

    public Object[] getArguments() {
        return (Object[])ObjectUtils.cloneIfPossible(this.errorArguments);
    }

    public String getUserMessage() {
        return super.getMessage();
    }

    /*public String getTraceabilityMessage() {
        return GoSiteExceptionUtils.buildTraceabilityMessage(this.getComponent(), super.getMessage(), this.getCause());
    }*/
}
