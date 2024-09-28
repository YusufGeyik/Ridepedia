package com.mvc.RidePedia.exceptions;

public class ReviewNotFoundEx extends  RuntimeException{
    private static final long serialVersionId=2;

    public ReviewNotFoundEx(String message)
    {

        super(message);

    }
}
