//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ayiko.backend.exception;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.ObjectUtils;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Status implements Serializable {
    public static final int SUCCESS = 1;
    public static final int FAILED = 0;
    private Integer statusCode;
    private String messageDescription;
    private String errorCode;
    @JsonIgnore
    private Object[] errorArguments;
    private List<Violation> violations;

    public Status() {
    }

    public Status(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Status(Integer statusCode, String messageDescription) {
        this.statusCode = statusCode;
        this.messageDescription = messageDescription;
    }

    public Status(Integer statusCode, String messageDescription, String errorCode) {
        this.statusCode = statusCode;
        this.messageDescription = messageDescription;
        this.errorCode = errorCode;
    }

    public Status(Integer statusCode, String messageDescription, String errorCode, Object[] errorArguments) {
        this.statusCode = statusCode;
        this.messageDescription = messageDescription;
        this.errorCode = errorCode;
        this.errorArguments = errorArguments;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessageDescription() {
        return messageDescription;
    }

    public void setMessageDescription(String messageDescription) {
        this.messageDescription = messageDescription;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String error) {
        errorCode = error;
    }

    public Object[] getErrorArguments() {
        return (Object[]) ObjectUtils.cloneIfPossible(errorArguments);
    }

    public void setErrorArguments(Object[] errorArguments) {
        this.errorArguments = (Object[]) ObjectUtils.cloneIfPossible(errorArguments);
    }

    public List<Violation> getViolations() {
        return violations;
    }

    public void setViolations(List<Violation> violations) {
        this.violations = violations;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Status [statusCode=");
        builder.append(statusCode);
        builder.append(", messageDescription=");
        builder.append(messageDescription);
        builder.append(", errorCode=");
        builder.append(errorCode);
        builder.append(", errorArguments=");
        builder.append(Arrays.toString(errorArguments));
        builder.append(", violations=");
        builder.append(violations);
        builder.append("]");
        return builder.toString();
    }
}
