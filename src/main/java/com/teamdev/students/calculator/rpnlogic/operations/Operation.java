package com.teamdev.students.calculator.rpnlogic.operations;

import com.teamdev.students.calculator.beans.RpnStack;
import com.teamdev.students.calculator.rpnlogic.exceptions.SyntaxException;

import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: Irene
 * Date: 08.01.12
 * To change this template use File | Settings | File Templates.
 */
public interface Operation {

    public int getPriority();
    public void execute(LinkedList<Double> operationStack) throws SyntaxException;
    public void addToRpnStack(String operation, LinkedList<String> operationStack, RpnStack rpnStack);
    public boolean isFunction();
    public boolean isFormatValid(String previousToken);
}
