package com.teamdev.students.calculator.rpnlogic;

import com.teamdev.students.calculator.Constants;
import com.teamdev.students.calculator.beans.RpnStack;
import com.teamdev.students.calculator.rpnlogic.exceptions.ExceptionMessages;
import com.teamdev.students.calculator.rpnlogic.exceptions.SyntaxException;
import com.teamdev.students.calculator.utils.Utility;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: Irene
 * Date: 12.01.12
 * To change this template use File | Settings | File Templates.
 */
public class RpnParser {

    private RpnOperationFactory rpnOperations = RpnOperationFactory.getInstance();
    private final static HashSet<String> separatorsSet = new HashSet<String>();

    static {
        separatorsSet.add(Constants.LEFT_BRACKET);
        separatorsSet.add(Constants.RIGHT_BRACKET);
        separatorsSet.add(Constants.COMMA_SEPARATOR);
        separatorsSet.add(Constants.SPACE_SEPARATOR);
    }

    /**
     * This method parses and represents expression in a postfix notion
     *
     * @param expression - string math expression
     * @return - expression in postfix notion ,witch  stored in RpnStack
     * @throws SyntaxException
     */
    public RpnStack getInPostfixNotation(String expression) throws SyntaxException {

        LinkedList<String> operationsStack = new LinkedList<String>();
        RpnStack rpnStack = new RpnStack();

        // # parse the expression
        parseExpression(expression, rpnStack, operationsStack);
        finallyParserAction(expression, rpnStack, operationsStack);

        return rpnStack;
    }


    private void parseExpression(String expression, RpnStack rpnStack, LinkedList<String> operationsStack) throws SyntaxException {
        StringBuilder tmpCharSequence = new StringBuilder();
        char[] expressionAsChars = expression.toCharArray();
        for (int i = 0; i < expressionAsChars.length; i++) {
            try {
                String symbol = Character.toString(expressionAsChars[i]);

                if (isSeparator(symbol)) {
                    handleSeparator(symbol, operationsStack, rpnStack, tmpCharSequence);
                } else if (rpnOperations.isOperation(symbol)) {
                    handleAsOperand(tmpCharSequence, rpnStack);
                    handleAsOperation(operationsStack, symbol, rpnStack);
                } else {
                    //# if symbol isn't exist token then it should be a part of tmpCharSequence
                    tmpCharSequence.append(symbol);
                    if (rpnOperations.isOperation((tmpCharSequence.toString()))) {//#  check the tmpCharSequence
                        handleAsOperation(operationsStack, tmpCharSequence.toString(), rpnStack);
                        tmpCharSequence.setLength(0);
                    }
                }
            } catch (SyntaxException e) {
                throw new SyntaxException(e, i);
            }
        }
        handleAsOperand(tmpCharSequence, rpnStack);
    }

    /**
     * Clean up parser temporary storage (like stack of operation) and make all finally actions
     */
    private void finallyParserAction(String expression, RpnStack rpnStack,
                                     LinkedList<String> operationsStack) throws SyntaxException {
        //# replace all remained data from operation storage to rpnStack
        while (!operationsStack.isEmpty()) {
            if (separatorsSet.contains(operationsStack.peek())) {
                throw new SyntaxException(ExceptionMessages.SEPARATOR_EXCEPTION);
            }
            rpnStack.push(operationsStack.pop());
        }

        //# the end of expression can't be an operation
        if (rpnOperations.isOperation(rpnStack.getPreviousToken())) {
            throw new SyntaxException(ExceptionMessages.INCORRECT_LAST_TOKEN_EXCEPTION,
                    expression.lastIndexOf(rpnStack.getPreviousToken()));
        }
    }

    private void handleSeparator(String separator, LinkedList<String> operationsStack,
                                 RpnStack rpnStack, StringBuilder tmpCharSequence)
            throws SyntaxException {

        if (Constants.SPACE_SEPARATOR.equals(separator)) {
            return;
        }

        //# add to resultList the tmpCharSequence - it is an operand
        handleAsOperand(tmpCharSequence, rpnStack);

        if (Constants.LEFT_BRACKET.equals(separator)) {
            handleLeftBracket(separator, rpnStack, operationsStack);
        } else if (Constants.RIGHT_BRACKET.equals(separator)) {
            handleRightBracket(separator, operationsStack, rpnStack);
        } else if (Constants.COMMA_SEPARATOR.equals(separator)) {
            handleAttributeSeparator(separator, operationsStack,
                    rpnStack);
        }

        //# mark the separator as last token
        rpnStack.setPreviousToken(separator);
    }

    /**
     * this method handles attribute separator for function of expression
     */
    private void handleAttributeSeparator(String separator, LinkedList<String> operationsStack,
                                          RpnStack rpnStack) throws SyntaxException {

        boolean isPreviousTokenCorrect = (Constants.RIGHT_BRACKET.equals(rpnStack.getPreviousToken())
                || Utility.isNumber(rpnStack.getPreviousToken()));
        if (!isPreviousTokenCorrect) {
            throw new SyntaxException(ExceptionMessages.COMMA_SEPARATOR_EXCEPTION);
        }

        while (!operationsStack.isEmpty() && !(isSeparator(operationsStack.peek()))) {
            rpnStack.push(operationsStack.pop());
        }
        operationsStack.push(separator);
    }

    private void handleLeftBracket(String separator, RpnStack rpnStack, LinkedList<String> operationsStack)
            throws SyntaxException {

        boolean isPreviousTokenCorrect = rpnStack.getPreviousToken() == null ||
                rpnOperations.isOperation(rpnStack.getPreviousToken()) ||
                Constants.LEFT_BRACKET.equals(rpnStack.getPreviousToken()) ||
                Constants.COMMA_SEPARATOR.equals(rpnStack.getPreviousToken());
        if (!isPreviousTokenCorrect) {
            throw new SyntaxException(ExceptionMessages.LEFT_BRACKET_EXCEPTION);
        }

        operationsStack.push(separator);
    }

    private void handleRightBracket(String separator, LinkedList<String> operationsStack,
                                    RpnStack rpnStack) throws SyntaxException {
        boolean isPreviousTokenValid = separator.equals(rpnStack.getPreviousToken())
                || Utility.isNumber(rpnStack.getPreviousToken());
        if (!isPreviousTokenValid) {
            throw new SyntaxException(ExceptionMessages.RIGHT_BRACKET_EXCEPTION);
        }

        //# if operation is function: attributeCount is used to show how many attributes function has
        int methodAttributeCount = 0;
        boolean isLeftBracketFound = false;
        while (!operationsStack.isEmpty()) {
            String operation = operationsStack.pop();

            if (Constants.LEFT_BRACKET.equals(operation)) {
                if (!operationsStack.isEmpty() && rpnOperations.isOperation(operationsStack.peek())
                        && rpnOperations.isFunction(operationsStack.peek())) {
                    methodAttributeCount++;
                    rpnStack.push(String.valueOf(methodAttributeCount));
                }
                isLeftBracketFound = true;
                break;
            }

            if (Constants.COMMA_SEPARATOR.equals(operation)) {
                methodAttributeCount++;
                continue;
            }

            rpnStack.push(operation);
        }
        if (!isLeftBracketFound) {
            throw new SyntaxException(ExceptionMessages.RIGHT_BRACKET_EXCEPTION);
        }
    }

    private void handleAsOperation(LinkedList<String> operationsStack, String token, RpnStack rpnStack)
            throws SyntaxException {

        if (!rpnOperations.isFormatValid(token, rpnStack.getPreviousToken())) {
            throw new SyntaxException(ExceptionMessages.OPERATION_EXCEPTION);
        }

        while (!operationsStack.isEmpty() && !isSeparator(operationsStack.peek())
                && rpnOperations.getOperationPriority(operationsStack.peek()) >=
                rpnOperations.getOperationPriority(token)) {
            rpnStack.push(operationsStack.pop());
        }

        rpnOperations.getOperation(token).addToRpnStack(token, operationsStack, rpnStack);
        rpnStack.setPreviousToken(token);
    }

    private void handleAsOperand(StringBuilder tmpCharSequence, RpnStack rpnStack)
            throws SyntaxException {
        if (tmpCharSequence.length() > 0) {
            if (Constants.RIGHT_BRACKET.equals(rpnStack.getPreviousToken())) {
                throw new SyntaxException(ExceptionMessages.OPERATOR_EXCEPTION);
            }

            if (!Utility.isNumber(tmpCharSequence.toString())) {
                throw new SyntaxException(
                        MessageFormat.format(ExceptionMessages.NOT_A_NUMBER_EXCEPTION, tmpCharSequence.toString()));
            }

            rpnStack.push(tmpCharSequence.toString());

            rpnStack.setPreviousToken(tmpCharSequence.toString());
            tmpCharSequence.setLength(0);
        }
    }

    private boolean isSeparator(String separator) {
        return separatorsSet.contains(separator);
    }
}
