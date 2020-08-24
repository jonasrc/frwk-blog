package com.frwk.jonasrc.techtest.exception;

import java.util.NoSuchElementException;

public class AlbumNotFoundException extends NoSuchElementException {
    public AlbumNotFoundException(Long id) {
        super("O álbum de fotos com ID \"" + id + "\" não foi encontrado.");
    }
}
