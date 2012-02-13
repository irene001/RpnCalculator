package com.teamdev.students.calculator.rpnlogic.operations;

import com.teamdev.students.calculator.rpnlogic.exceptions.SyntaxException;

import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: Irene
 * Date: 09.01.12
 * To change this template use File | Settings | File Templates.
 */
public class DivideOperation extends BaseOperation {
    private final static int PRIORITY = 2;

    @Override
    public int getPriority(){
        return PRIORITY;
    }

    @Override
    public void execute(LinkedList<Double> operationStack) throws SyntaxException {
        double denominator = operationStack.pop();//# divider
        double numerator = operationStack.pop();
        
        if (denominator==0) {
            throw new SyntaxException("You can't divide on zero");
        }
        
        operationStack.push(numerator/ denominator);
    }

}