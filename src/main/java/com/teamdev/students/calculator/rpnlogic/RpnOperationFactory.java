package com.teamdev.students.calculator.rpnlogic;

import com.teamdev.students.calculator.rpnlogic.exceptions.SyntaxException;
import com.teamdev.students.calculator.rpnlogic.operations.*;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: Irene
 * Date: 12.01.12
 * To change this template use File | Settings | File Templates.
 */
public class RpnOperationFactory {

    private HashMap<String, Operation> operationMap = new HashMap<String, Operation>();
    private static RpnOperationFactory instance = new RpnOperationFactory();

    private RpnOperationFactory() {
        operationMap.put("+", new AddOperation());
        operationMap.put("-", new MinusOperation());
        operationMap.put("*", new MultiplyOperation());
        operationMap.put("/", new DivideOperation());
        operationMap.put("^", new PowerOperation());
        operationMap.put("sqr", new SqrtOperation());
        operationMap.put("min", new MinOperation());
        operationMap.put("max", new MaxOperation());
        operationMap.put("sum", new SumOperation());
    }

    public static RpnOperationFactory getInstance() {
        return instance;
    }

    public boolean isOperation(String operation) {
        return operationMap.containsKey(operation);
    }

    public boolean isOperationExist(Character operation) {
        return operationMap.containsKey(Character.toString(operation));
    }

    public Operation getOperation(String operation) {
        return operationMap.get(operation);
    }

    public int getOperationPriority(String operation) {
        return operationMap.get(operation).getPriority();
    }

    public void executeOperation(String operation, LinkedList<Double> operandsStack) throws SyntaxException {
        getOperation(operation).execute(operandsStack);
    }

    public boolean isFunction(String operation) {
        return getOperation(operation).isFunction();
    }

    public boolean isFormatValid(String operation, String previousToken) {
        return getOperation(operation).isFormatValid(previousToken);
    }


}
