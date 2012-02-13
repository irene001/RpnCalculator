package com.teamdev.students.calculator.rpnlogic.operations;

import com.teamdev.students.calculator.Constants;
import com.teamdev.students.calculator.beans.RpnStack;
import com.teamdev.students.calculator.utils.Utility;

import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: Irene
 * Date: 09.01.12
 * To change this template use File | Settings | File Templates.
 */
abstract public class BaseOperation implements Operation {

    @Override
    public void addToRpnStack(String operation,
                              LinkedList<String> operationStack, RpnStack rpnStack){
        operationStack.push(operation);
    }


	@Override
	public boolean isFormatValid(String previousToken) {
		return Utility.isNumber(previousToken)
				|| Constants.LEFT_BRACKET.equals(previousToken)
				|| Constants.RIGHT_BRACKET.equals(previousToken)
				|| isFunction();
	}

    public boolean isFunction() {
        return false;
    }
}
