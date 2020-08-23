package com.frwk.jonasrc.techtest.exception;

import java.util.NoSuchElementException;

public class UserNotFoundException extends NoSuchElementException {
    public UserNotFoundException(Long id) {
        super("O usuário com ID \"" + id + "\" não foi encontrado.");
    }
}
