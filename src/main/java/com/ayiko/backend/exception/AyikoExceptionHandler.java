//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ayiko.backend.exception;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AyikoExceptionHandler extends ResponseEntityExceptionHandler {
    protected static final String DEFAULT_NOT_FOUND_ERROR_MESSAGE = "Requested resource not found.";
    protected static final String DEFAULT_ERROR_MESSAGE = "An unexpected error occurred while processing your request.";
    private final Logger logger = Logger.getLogger(AyikoExceptionHandler.class.getName());

    public AyikoExceptionHandler() {
    }

    @ExceptionHandler({AyikoApplicationException.class})
    public ResponseEntity<SimpleResponse> handleAyikoApplicationException(HttpServletRequest request, AyikoApplicationException exception) {
        logger.log(Level.SEVERE, "AyikoApplicationException : " + exception.getMessage(), exception);
        Object[] errorArguments = exception.getArguments();
        String errorMessage = null;
        if (errorArguments != null && errorArguments.length > 0) {
            errorMessage = AyikoI18nHandler.getTranslatedMessage(exception.getMessage(), ErrorConstant.getLocale(request), errorArguments);
        } else {
            errorMessage = AyikoI18nHandler.getTranslatedMessage(exception.getMessage(), ErrorConstant.getLocale(request));
        }

        Status errorStatus = ErrorConstant.getErrorStatus(exception.getMessage(), errorMessage, errorArguments);
        int statusCode = ObjectUtils.isEmpty(exception.getArguments()) ? HttpStatus.INTERNAL_SERVER_ERROR.value() : (int) exception.getArguments()[0];
        errorStatus.setStatusCode(statusCode);
        return new ResponseEntity(errorStatus, null, statusCode);
    }

    @ExceptionHandler({BadCredentialException.class})
    public ResponseEntity<SimpleResponse> handleBadCredentialsException(HttpServletRequest request, BadCredentialException exception) {
        Object[] errorArguments = exception.getArguments();
        String defaultErrorMessage = AyikoI18nHandler.getTranslatedMessage("error.unauthorized", "Invalid username or password.", ErrorConstant.getLocale(request));
        String errorMessage = null;
        if (errorArguments != null && errorArguments.length > 0) {
            errorMessage = AyikoI18nHandler.getTranslatedMessage(exception.getMessage(), ErrorConstant.getLocale(request), errorArguments);
        } else {
            errorMessage = AyikoI18nHandler.getTranslatedMessage(exception.getMessage(), defaultErrorMessage, ErrorConstant.getLocale(request));
        }

        Status errorStatus = ErrorConstant.getErrorStatus(exception.getMessage(), errorMessage, errorArguments);
        errorStatus.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity(errorStatus, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<SimpleResponse> handleResourceNotFoundException(HttpServletRequest request, ResourceNotFoundException exception) {
        Object[] errorArguments = exception.getArguments();
        String defaultErrorMessage = AyikoI18nHandler.getTranslatedMessage("error.not_found", "Requested resource not found.", ErrorConstant.getLocale(request));
        String errorMessage = null;
        if (errorArguments != null && errorArguments.length > 0) {
            errorMessage = AyikoI18nHandler.getTranslatedMessage(exception.getMessage(), ErrorConstant.getLocale(request), errorArguments);
        } else {
            errorMessage = AyikoI18nHandler.getTranslatedMessage(exception.getMessage(), defaultErrorMessage, ErrorConstant.getLocale(request));
        }

        Status errorStatus = ErrorConstant.getErrorStatus(exception.getMessage(), errorMessage, errorArguments);
        errorStatus.setStatusCode(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity(errorStatus, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({AyikoSystemException.class})
    public ResponseEntity<SimpleResponse> handleAyikoSystemException(HttpServletRequest request, AyikoSystemException exception) {
        logger.log(Level.SEVERE, "Ayiko System Exception : " + exception.getMessage(), exception);
        String errorMessage = AyikoI18nHandler.getTranslatedMessage("error.system", "An unexpected error occurred while processing your request.", ErrorConstant.getLocale(request));
        Status errorStatus = ErrorConstant.getErrorStatus("error.system", errorMessage, (Object[]) null);
        errorStatus.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity(errorStatus, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({AyikoException.class})
    public ResponseEntity<SimpleResponse> handleAyikoException(HttpServletRequest request, AyikoException exception) {
        logger.log(Level.SEVERE, "Ayiko Exception : " + exception.getMessage(), exception);
        String errorMessage = AyikoI18nHandler.getTranslatedMessage("error.unexpected", "An unexpected error occurred while processing your request.", ErrorConstant.getLocale(request));
        Status errorStatus = ErrorConstant.getErrorStatus("error.unexpected", errorMessage, (Object[]) null);
        errorStatus.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity(errorStatus, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<SimpleResponse> resolveValidationException(HttpServletRequest request, ValidationException exception) {
        logger.log(Level.SEVERE, "Validation Exception: " + exception.getMessage(), exception);
        SimpleResponse errorResponse = new SimpleResponse();
        prepareValidationErrorResponse(exception.getBindingResult(), errorResponse, ErrorConstant.getLocale(request));
        return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
    }

    protected void prepareValidationErrorResponse(BindingResult bindingResult, SimpleResponse response, Locale locale) {
        List<Violation> violations = new ArrayList();
        Status status = new Status();
        status.setStatusCode(0);
        bindingResult.getFieldErrors().forEach((error) -> {
            Object[] args = ArrayUtils.toArray(new Object[]{error.getRejectedValue(), error.getField()});
            String errorMessage = getErrorMessage(error.getCodes(), error.getDefaultMessage(), args, locale);
            violations.add(new Violation(error.getField(), error.getCode(), error.getRejectedValue(), errorMessage));
        });
        status.setErrorCode("error.validation");
        status.setViolations(violations);
        status.setMessageDescription(AyikoI18nHandler.getTranslatedMessage("error.validation", locale, (Object[]) null));
        response.setStatus(status);
    }

    private String getErrorMessage(String[] errorCodes, String defaultMessage, Object[] args, Locale locale) {
        return defaultMessage;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.log(Level.SEVERE, "Validation Exception: " + ex.getMessage(), ex);
        SimpleResponse errorResponse = new SimpleResponse();
        String localeHeader = request.getHeader("Accept-Language");
        Locale locale = !StringUtils.hasText(localeHeader) ? Locale.US : new Locale(localeHeader);
        prepareCustomValidationErrorResponse(ex.getBindingResult(), errorResponse, locale);
        Status responseStatus = errorResponse.getStatus();
        responseStatus.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity(responseStatus, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<String> handleConstraintViolationException(HttpServletRequest request, ConstraintViolationException exception) {
        logger.log(Level.SEVERE, "ConstraintViolation Exception : " + exception.getMessage(), exception);
        SimpleResponse errorResponse = new SimpleResponse();
        String localeHeader = request.getHeader("Accept-Language");
        Locale locale = !StringUtils.hasText(localeHeader) ? Locale.US : new Locale(localeHeader);
        prepareConstraintViolationErrorResponse(exception.getConstraintViolations(), errorResponse, locale);
        Status responseStatus = errorResponse.getStatus();
        responseStatus.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity(responseStatus, HttpStatus.BAD_REQUEST);
    }

    protected void prepareCustomValidationErrorResponse(BindingResult bindingResult, SimpleResponse response, Locale locale) {
        List<Violation> violations = new ArrayList();
        Status status = new Status();
        status.setStatusCode(0);
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<ObjectError> errors = bindingResult.getAllErrors();
        if (fieldErrors.isEmpty()) {
            errors.forEach((error) -> {
                Object[] args = error.getArguments();
                String errorMessage = getErrorMessage(error.getCodes(), error.getDefaultMessage(), args, locale);
                if (!ObjectUtils.isEmpty(errorMessage)) {
                    violations.add(new Violation(error.getObjectName(), error.getCode(), error.getDefaultMessage(), errorMessage));
                }
            });
        } else {
            fieldErrors.forEach((error) -> {
                Object[] args = Optional.of(new Object[]{error.getRejectedValue(), error.getField()})
                        .orElse(error.getArguments());
                String errorMessage = getErrorMessage(error.getCodes(), error.getDefaultMessage(), args, locale);
                if (!ObjectUtils.isEmpty(error.getField())) {
                    violations.add(new Violation(error.getField(), error.getCode(), error.getRejectedValue(), errorMessage));
                } else {
                    violations.add(new Violation(error.getObjectName(), error.getCode(), error.getDefaultMessage(), errorMessage));
                }
            });
        }
        status.setErrorCode("error.validation");
        status.setViolations(violations);
        status.setMessageDescription(AyikoI18nHandler.getTranslatedMessage("error.validation", locale, (Object[]) null));
        response.setStatus(status);
    }

    private void prepareConstraintViolationErrorResponse(Set<ConstraintViolation<?>> constraintViolations, SimpleResponse errorResponse, Locale locale) {
        List<Violation> violations = new ArrayList();
        Status status = new Status();
        status.setStatusCode(0);
        constraintViolations.forEach((error) -> {
            Object[] args = new Object[]{error.getInvalidValue(), error.getMessage()};
            String errorMessage = getErrorMessage(new String[]{error.getMessageTemplate()}, error.getMessage(), args, locale);
            String[] field = new String[1];
            error.getPropertyPath().forEach(path -> {
                field[0] = path.getName();
            });
            violations.add(new Violation(field[0], error.getMessageTemplate(), error.getInvalidValue(), errorMessage));
        });
        status.setErrorCode("error.validation");
        status.setViolations(violations);
        status.setMessageDescription(AyikoI18nHandler.getTranslatedMessage("error.validation", locale, (Object[]) null));
        errorResponse.setStatus(status);
    }

    @ExceptionHandler(javax.validation.ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<String> handleValidationException(HttpServletRequest request, javax.validation.ValidationException exception) {
        logger.log(Level.SEVERE, "ValidationException Exception : " + exception.getMessage(), exception);
        String errorMessage = AyikoI18nHandler.getTranslatedMessage(exception.getMessage(), ErrorConstant.getLocale(request));
        Status errorStatus = ErrorConstant.getErrorStatus(exception.getMessage(), errorMessage, null);
        errorStatus.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity(errorStatus, null, HttpStatus.BAD_REQUEST);
    }
}
