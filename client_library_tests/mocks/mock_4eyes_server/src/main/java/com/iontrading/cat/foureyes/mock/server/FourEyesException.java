package com.iontrading.cat.foureyes.mock.server;

public class FourEyesException extends Exception {

    private static final long serialVersionUID = 3151226841154371505L;

    public FourEyesException(String string) {
        super(string);
    }

    public FourEyesException(Throwable exception) {
        super(exception);
    }

}
