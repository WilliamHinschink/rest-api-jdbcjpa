package com.rest.api.jdbcjpa.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Classe utilizada para acessar valores do .properties em métodos estáticos
 */
public final class ResourceUtil {

    private static final String LOCALE_BASE_MESSAGES = "locale/messages";

    private ResourceUtil() {
    }

    public static String getMessage(String id) {
        ResourceBundle bundle = ResourceBundle.getBundle(LOCALE_BASE_MESSAGES,
                new Locale.Builder().setLanguage("pt").setRegion("BR").build());
        return bundle.getString(id);
    }
}
