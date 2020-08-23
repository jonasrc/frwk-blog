package com.frwk.jonasrc.techtest.exception;

public class EmptyContentException extends Exception {
    public EmptyContentException() {
        super("O conteúdo não pode estar vazio.");
    }
}
