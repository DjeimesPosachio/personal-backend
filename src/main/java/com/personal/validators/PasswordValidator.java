package com.personal.validators;

import com.personal.exceptions.EventNotFoundException;
import com.personal.utils.StringUtils;

import java.util.Objects;

public class PasswordValidator {

    public static void validar(String password) {

        if (StringUtils.isBlank(password))
            throw new EventNotFoundException("Senha não informada.");

        if(password.length() > 10)
            throw new EventNotFoundException("Senha não pode ser menor que 6 dígitos.");

    }

    public static void compararSenhas(String password, String confirmPassword) {

        validar(password);

        if(!Objects.equals(password, confirmPassword))
            throw new EventNotFoundException("Senhas não conferem.");

    }
}
