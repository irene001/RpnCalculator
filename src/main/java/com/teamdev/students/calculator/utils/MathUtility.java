package com.teamdev.students.calculator.utils;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Irene
 * Date: 15.01.12
 * To change this template use File | Settings | File Templates.
 */
public class MathUtility {
    
    public static double sum(List<Double> operandList) {
        double result = 0;
        for(Double operand: operandList) {
             result+= operand;
        }
        return result;
    }

    public static double min(List<Double> operandList) {
        double minOperand = operandList.get(0);
        for(int i = 1; i<operandList.size(); i++) {
            if(minOperand>operandList.get(i)) {
                minOperand = operandList.get(i);
            }
        }
        return minOperand;
    }

    public static double max(List<Double> operandList) {
        double minOperand = operandList.get(0);
        for(int i = 1; i<operandList.size(); i++) {
            if(minOperand<operandList.get(i)) {
                minOperand = operandList.get(i);
            }
        }
        return minOperand;
    }

    public static double sqr(double a, double power) {
        return Math.pow(a, 1/power);
    }

    public static double sqr(double a) {
        return sqr(a, 2);
    }
    
}
