package com.teamdev.students.calculator.beans;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Irene
 * Date: 08.02.12
 * Time: 23:47
 * To change this template use File | Settings | File Templates.
 */
public class RpnStack {
    private LinkedList<String> resultStack;
    private String previousToken;

    public RpnStack() {
        resultStack = new LinkedList<String>();
    }

    public void setPreviousToken(String previousToken) {
        this.previousToken = previousToken;
    }

    public String getPreviousToken() {
        return previousToken;
    }

    public void push(String token) {
        resultStack.addLast(token);
    }

    public void pop() {
        resultStack.removeLast();
    }

    public void peek() {
        resultStack.getLast();
    }

    public boolean isEmpty() {
        return resultStack.isEmpty();
    }

    /**
     * @return expression as list of it's token
     */
    public List<String> getExpressionAsList() {
        return resultStack;
    }

}
