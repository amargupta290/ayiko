//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ayiko.backend.exception;

import java.io.Serializable;

public final class Violation implements Serializable {
    private final String field;
    private final String errorCode;
    private final Object rejectedValue;
    private final String message;

    public Violation(final String field, final String errorCode, final Object rejectedValue, final String message) {
        this.field = field;
        this.errorCode = errorCode;
        this.rejectedValue = rejectedValue;
        this.message = message;
    }

    public String getField() {
        return this.field;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public Object getRejectedValue() {
        return this.rejectedValue;
    }

    public String getMessage() {
        return this.message;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Violation [field=");
        builder.append(this.field);
        builder.append(", errorCode=");
        builder.append(this.errorCode);
        builder.append(", rejectedValue=");
        builder.append(this.rejectedValue);
        builder.append(", message=");
        builder.append(this.message);
        builder.append("]");
        return builder.toString();
    }
}
