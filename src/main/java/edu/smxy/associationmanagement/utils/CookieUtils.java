package edu.smxy.associationmanagement.utils;

import javax.servlet.http.*;

public class CookieUtils
{
    public static String getCookie(final HttpServletRequest request, final String cookieName) {
        final Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (final Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
    
    public static void writeCookie(final HttpServletResponse response, final String cookieName, final String value) {
        final Cookie cookie = new Cookie(cookieName, value);
        cookie.setPath("/");
        cookie.setMaxAge(3600);
        response.addCookie(cookie);
    }
}
