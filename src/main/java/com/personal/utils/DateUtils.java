package com.personal.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.nonNull;

public final class DateUtils {

    public static String dateAsString(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return nonNull(data) ? data.format(formatter) : "";
    }
}