package ua.com.sergeiokon.utils;

import javax.servlet.http.HttpServletRequest;

public class ControllerUtils {
    private static final String ONLY_DIGITS = "[0-9]+";

    public static boolean idIsNumber(HttpServletRequest request) {
        final String id = request.getParameter("id");
        return id != null && (id.length() > 0) && id.matches(ONLY_DIGITS);
    }
}
