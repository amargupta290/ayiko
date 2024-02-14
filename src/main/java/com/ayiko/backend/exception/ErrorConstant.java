//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ayiko.backend.exception;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

public final class ErrorConstant {
    public static final String SYSTEM_ERROR = "error.system";
    public static final String RESOURCE_NOT_FOUND_ERROR = "error.not_found";
    public static final String VALIDATION_ERROR = "error.validation";
    public static final String UNEXPECTED_ERROR = "error.unexpected";
    public static final String APPLICATION_ERROR = "error.application";
    public static final String AUTHORIZATION_ERROR = "error.unauthorized";
    public static final String ALREADY_EXIST_ERROR = "error.already_exist";

    private ErrorConstant() {
    }

    public static Locale getLocale(HttpServletRequest request) {
        String localeHeader = request.getHeader("Accept-Language");
        return StringUtils.isEmpty(localeHeader) ? Locale.US : new Locale(localeHeader);
    }

    public static Status getErrorStatus(String errorCode, String errorMessage, Object[] errorArguments) {
        Status errorResponse = new Status();
        errorResponse.setErrorCode(errorCode);
        errorResponse.setStatusCode(0);
        errorResponse.setMessageDescription(errorMessage);
        errorResponse.setErrorArguments(errorArguments);
        return errorResponse;
    }
}
