package com.teamdev.students.calculator.rpnlogic;

import com.teamdev.students.calculator.Calculator;
import com.teamdev.students.calculator.beans.RpnStack;
import com.teamdev.students.calculator.rpnlogic.exceptions.CalculationException;
import com.teamdev.students.calculator.rpnlogic.exceptions.SyntaxException;

import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: Irene
 * Date: 08.01.12
 * To change this template use File | Settings | File Templates.
 */
public class RpnCalculator implements Calculator {
    private static final int SIZE_OF_RESULT_LIST = 1;

    public double calculate(String expression) throws SyntaxException, CalculationException {
        if (expression == null || expression.trim().isEmpty()) {
            throw new NullPointerException("Expression cannot be empty");
        }
        RpnOperationFactory rpnOperations = RpnOperationFactory.getInstance();
        RpnParser rpnParser = new RpnParser();
        RpnStack rpnStack = rpnParser.getInPostfixNotation(expression);

        return calculate(rpnStack, rpnOperations);
    }

    private double calculate(RpnStack rpnStack, RpnOperationFactory rpnOperations)
            throws SyntaxException, CalculationException {
        LinkedList<Double> operandsStack = new LinkedList<Double>();

        for (String token : rpnStack.getExpressionAsList()) {
            if (!rpnOperations.isOperation(token)) {
                operandsStack.push(Double.valueOf(token));
            } else {
                rpnOperations.executeOperation(token, operandsStack);
            }
        }
        if (operandsStack.size() > SIZE_OF_RESULT_LIST) {
            throw new CalculationException("Calculation completed incorrectly. " +
                    "The result list size is " + operandsStack.size());
        }
        return operandsStack.peek();
    }


}
