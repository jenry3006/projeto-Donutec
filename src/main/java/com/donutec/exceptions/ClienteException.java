package com.donutec.exceptions;

public class ClienteException extends Exception {
    String msg;

    public ClienteException(String msg) {
        this.msg = msg;
    }

    public ClienteException() {

    }


    @Override
    public String toString() {
        return "Ocorreu um teste";
    }
}
