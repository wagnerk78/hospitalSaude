package com.medicina.saude.exceptions;

public class EntidadeNaoEncontrada extends RuntimeException {

    public EntidadeNaoEncontrada(String menssagem) {
        super(menssagem);
    }
}
