//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ayiko.backend.exception;

import org.springframework.validation.BindingResult;

public class ValidationException extends Exception {
    private static final long serialVersionUID = 2991985905462762652L;
    private final BindingResult bindingResult;

    public ValidationException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return this.bindingResult;
    }
}
