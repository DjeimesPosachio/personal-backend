package com.personal.utils;

public class StringUtils {

    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }
}
