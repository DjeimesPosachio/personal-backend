package com.personal.utils;

import com.personal.exceptions.EventNotFoundException;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class ValidatorUtils {

    public static void throwError(List<String> errors) {
        if (!CollectionUtils.isEmpty(errors))
            throw new EventNotFoundException(errors);
    }
}
