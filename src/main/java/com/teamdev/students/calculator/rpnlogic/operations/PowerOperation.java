package com.teamdev.students.calculator.rpnlogic.operations;

import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: Irene
 * Date: 09.01.12
 * To change this template use File | Settings | File Templates.
 */
public class PowerOperation extends BaseOperation {
    private final static int PRIORITY = 3;

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public void execute(LinkedList<Double> operationStack) {
        double powerValue = operationStack.pop();
        double operand = operationStack.pop();
        double result = (int) Math.pow(operand, powerValue);
        operationStack.push(result);
    }

}
