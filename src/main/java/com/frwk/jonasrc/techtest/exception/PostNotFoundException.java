package com.frwk.jonasrc.techtest.exception;

import java.util.NoSuchElementException;

public class PostNotFoundException extends NoSuchElementException {
    public PostNotFoundException(Long id) {
        super("A postagem com ID \"" + id + "\" não foi encontrada.");
    }
}
