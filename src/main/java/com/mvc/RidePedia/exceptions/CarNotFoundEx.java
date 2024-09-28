package com.mvc.RidePedia.exceptions;

public class CarNotFoundEx extends RuntimeException {
    private static final long serialVersionID=1;

    public CarNotFoundEx(String message)
    {
        super(message);

    }
}
