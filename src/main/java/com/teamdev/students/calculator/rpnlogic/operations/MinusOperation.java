package com.teamdev.students.calculator.rpnlogic.operations;

import com.teamdev.students.calculator.Constants;
import com.teamdev.students.calculator.beans.RpnStack;

import java.util.LinkedList;


/**
 * Created by IntelliJ IDEA.
 * User: Irene
 * Date: 08.01.12
 * To change this template use File | Settings | File Templates.
 */
public class MinusOperation extends BaseOperation {
    private final static int PRIORITY = 1;
    private final static String ZERO_SYMBOL = "0";

    @Override
    public boolean isFormatValid(String previousToken){
        return super.isFormatValid(previousToken) || previousToken == null ||
                Constants.COMMA_SEPARATOR.equals(previousToken);
    }

    @Override
    public int getPriority(){
        return PRIORITY;
    }

    @Override
    public void execute(LinkedList<Double> operationStack) {
        double subtrahend = operationStack.pop();
        double minuend = operationStack.isEmpty()? 0 : operationStack.pop();
        operationStack.push(minuend -subtrahend);
    }

    @Override
    public void addToRpnStack(String operation ,
                              LinkedList<String> operationsStack, RpnStack rpnStack){
        //# if minus is unary operation: -1 => 0-1
        String previousToken = rpnStack.getPreviousToken();
		if (previousToken == null
				|| Constants.LEFT_BRACKET.equals(previousToken)
				|| Constants.COMMA_SEPARATOR.equals(previousToken)) {
            rpnStack.push(ZERO_SYMBOL);
		}

        operationsStack.push(operation);
    }

}
