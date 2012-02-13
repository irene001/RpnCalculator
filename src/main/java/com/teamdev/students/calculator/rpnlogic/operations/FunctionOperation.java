package com.teamdev.students.calculator.rpnlogic.operations;


/**
 * Created by IntelliJ IDEA.
 * User: Irene
 * Date: 14.01.12
 * To change this template use File | Settings | File Templates.
 */
public abstract class FunctionOperation extends BaseOperation {

    private final static int PRIORITY = 4;

    public int getPriority() {
        return PRIORITY;
    }

    public boolean isFunction() {
        return true;
    }
}
