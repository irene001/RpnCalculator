package com.teamdev.students.calculator.rpnlogic.exceptions;

/**
 * Created by IntelliJ IDEA.
 * User: Irene
 * Date: 20.01.12
 * Time: 1:55
 * To change this template use File | Settings | File Templates.
 */
public class ExceptionMessages {
    public static final String COMMA_SEPARATOR_EXCEPTION = "\"comma\" separator used incorrectly";
    public static final String LEFT_BRACKET_EXCEPTION = "Pleas add operation before bracket";
    public static final String RIGHT_BRACKET_EXCEPTION = "\")\" separator is used incorrectly";
    public static final String NOT_A_NUMBER_EXCEPTION = "Operand ''{0}'' is not a number";
    public static final String OPERATOR_EXCEPTION = "You should put operation after bracket";
    public static final String OPERATION_EXCEPTION = "you should to put operator between two operations";
    public static final String SEPARATOR_EXCEPTION = "Incorrect usage of separators";
    public static final String INCORRECT_LAST_TOKEN_EXCEPTION = "Last token is incorrect";

    private ExceptionMessages() {
        //# do nothing
    }
}
