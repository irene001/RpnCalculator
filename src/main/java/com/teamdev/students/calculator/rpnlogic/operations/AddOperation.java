package com.teamdev.students.calculator.rpnlogic.operations;

import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: Irene
 * Date: 08.01.12
 * To change this template use File | Settings | File Templates.
 */
public class AddOperation extends BaseOperation {
    private final static int PRIORITY = 1;

    @Override
    public int getPriority(){
         return PRIORITY;
    }

    @Override
    public void execute(LinkedList<Double> operationStack) {
        double a = operationStack.pop();
        double b = operationStack.pop();
        operationStack.push(a + b);
    }

}
