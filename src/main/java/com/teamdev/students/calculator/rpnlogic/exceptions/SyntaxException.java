package com.teamdev.students.calculator.rpnlogic.exceptions;

/**
 * Created by IntelliJ IDEA.
 * User: Irene
 * Date: 15.01.12
 * Time: 3:23
 * To change this template use File | Settings | File Templates.
 */
public class SyntaxException extends Exception {
    private int failPosition = -1;
    private String message;


    public SyntaxException(String message, int failPosition) {
        super(message);
        this.message = message;
        this.failPosition = failPosition;
    }

    public SyntaxException(Exception ex, int failPosition) {
        super(ex);
        this.failPosition = failPosition;
        this.message = ex.getMessage();
    }

    public SyntaxException(String message) {
        super(message);
        this.message = message;
    }

    public int getFailPosition() {
        return failPosition;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
