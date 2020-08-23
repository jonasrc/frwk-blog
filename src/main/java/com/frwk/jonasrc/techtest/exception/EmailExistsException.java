package com.frwk.jonasrc.techtest.exception;

public class EmailExistsException extends Exception {
    public EmailExistsException(String email) {
        super("Já existe um usuário cadastrado com o e-mail \"" + email + "\".");
    }
}
