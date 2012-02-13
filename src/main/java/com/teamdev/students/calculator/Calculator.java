package com.teamdev.students.calculator;


import com.teamdev.students.calculator.rpnlogic.exceptions.CalculationException;
import com.teamdev.students.calculator.rpnlogic.exceptions.SyntaxException;

/**
 * Created by IntelliJ IDEA.
 * User: Irene
 * Date: 08.01.12
 * To change this template use File | Settings | File Templates.
 */
public interface Calculator {
    public double calculate(String expression) throws SyntaxException, CalculationException;
}
