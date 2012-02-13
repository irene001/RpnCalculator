package com.teamdev.students.calculator.rpnlogic.operations;

import com.teamdev.students.calculator.utils.MathUtility;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: Irene
 * Date: 22.01.12
 * To change this template use File | Settings | File Templates.
 */
public class MaxOperation  extends FunctionOperation {

    @Override
    public void execute(LinkedList<Double> operationStack) {
        int argumentCount = (operationStack.pop().intValue());
        ArrayList<Double> argumentCollection = new ArrayList<Double>();

        while (argumentCount>0) {
            argumentCollection.add(operationStack.pop());
            argumentCount--;
        }

        double result = MathUtility.max(argumentCollection);
        operationStack.push(result);
    }



}
