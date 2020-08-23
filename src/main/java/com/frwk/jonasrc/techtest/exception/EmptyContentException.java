package com.frwk.jonasrc.techtest.exception;

public class EmptyContentException extends Exception {
    public EmptyContentException() {
        super("Não é possível criar um post sem conteúdo.");
    }
}
