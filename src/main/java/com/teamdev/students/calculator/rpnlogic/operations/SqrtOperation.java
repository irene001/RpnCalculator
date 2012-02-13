package com.teamdev.students.calculator.rpnlogic.operations;


import com.teamdev.students.calculator.rpnlogic.exceptions.SyntaxException;
import com.teamdev.students.calculator.utils.MathUtility;

import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: Irene
 * Date: 09.01.12
 * To change this template use File | Settings | File Templates.
 */
public class SqrtOperation extends FunctionOperation {
    private final static int PRIORITY = 3;
    private final static int MAX_ATTRIBUTES = 2;
    private final static int DEFAULT_POWER = 2;
    private final static int DEFAULT_ATTRIBUTE_COUNT = 1;


    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public void execute(LinkedList<Double> operationStack) throws SyntaxException {
        double operandCount = operationStack.pop();
        double firstAttribute = operationStack.pop();
        double power = DEFAULT_POWER;

        if (operandCount > DEFAULT_ATTRIBUTE_COUNT) {
            power = firstAttribute;
            firstAttribute = operationStack.pop();
        }

        if (operandCount > MAX_ATTRIBUTES) {
            throw new SyntaxException("Too many attributes for \"Square\" operation");
        }

        if (power == 0) {
            throw new SyntaxException("Power cant be 0 for \"square\" operation");
        }

        double result = MathUtility.sqr(firstAttribute, power);
        operationStack.push(result);
    }

}

