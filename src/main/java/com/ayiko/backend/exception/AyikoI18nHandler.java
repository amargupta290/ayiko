//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ayiko.backend.exception;

import java.util.Locale;
import org.springframework.context.MessageSource;

public final class AyikoI18nHandler {
    private static final MessageSource messageSource = (MessageSource) AyikoApplicationContext.getBean("messageSource");

    private AyikoI18nHandler() {
    }

    public static String getTranslatedMessage(String code, Locale locale) {
        try {
            return messageSource.getMessage(code, (Object[])null, locale);
        } catch (Exception var3) {
            return code;
        }
    }

    public static String getTranslatedMessage(String code, Locale locale, Object[] args) {
        try {
            return messageSource.getMessage(code, args, locale);
        } catch (Exception var4) {
            return code;
        }
    }

    public static String getTranslatedMessage(String code, String defaultErrorMessage, Locale locale) {
        try {
            return messageSource.getMessage(code, (Object[])null, defaultErrorMessage, locale);
        } catch (Exception var4) {
            return code;
        }
    }
}
