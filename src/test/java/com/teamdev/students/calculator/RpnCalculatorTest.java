package com.teamdev.students.calculator;

import com.teamdev.students.calculator.rpnlogic.RpnCalculator;
import com.teamdev.students.calculator.rpnlogic.exceptions.CalculationException;
import com.teamdev.students.calculator.rpnlogic.exceptions.SyntaxException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.MessageFormat;

import static org.junit.Assert.assertTrue;

/**
* Created by IntelliJ IDEA.
* User: Irene
* Date: 14.01.12
* Time: 0:53
* To change this template use File | Settings | File Templates.
*/
public class RpnCalculatorTest {

    private static RpnCalculator rpnCalculator;

    @BeforeClass
    public static void  setUpBeforeClass() throws Exception {
        rpnCalculator = new RpnCalculator();
    }

    private String getMsg(double expected, double actual) {
        String testMsg = "Expected ''{0}'' but actual is ''{1}''";
        return MessageFormat.format(testMsg, expected, actual);
    }

    @Test
    public void testAddingExpression() throws SyntaxException, CalculationException {
        String expression = "12+10";
        int expected = 22;
        double actual = rpnCalculator.calculate(expression);
        assertTrue(getMsg(expected, actual), expected == actual);
    }

    @Test
    public void testMinusExpression()throws SyntaxException, CalculationException {
        String expression = "12-10";
        int expected = 2;
        double actual = rpnCalculator.calculate(expression);
        assertTrue(getMsg(expected, actual), expected == actual);
    }

    @Test
    public void testMinusExpression2()throws SyntaxException, CalculationException {
        String expression = "10 - 12";
        int expected = -2;
        double actual = rpnCalculator.calculate(expression);
        assertTrue(getMsg(expected, actual), expected == actual);
    }

    @Test
    public void testCalculatorUnaryMinus1()throws SyntaxException, CalculationException {
        String  expression = " -8+10" ;
        int expected = -8 + 10;
        double actual = rpnCalculator.calculate(expression);
        assertTrue(getMsg(expected, actual), expected == actual);
    }

    @Test
    public void testParserUnaryMinus2()throws SyntaxException, CalculationException {
        String  expression = "10 - (5 - 10)" ;
        int expected = 10 - (5 - 10);
        double actual = rpnCalculator.calculate(expression);
        assertTrue(getMsg(expected, actual), expected == actual);
    }

    @Test
    public void testParserUnaryMinus3()throws SyntaxException, CalculationException {
        String  expression = "10 - (-10)" ;
        int expected = 10 - (-10);
        double actual = rpnCalculator.calculate(expression);
        assertTrue(getMsg(expected, actual), expected == actual);
    }


    @Test
    public void testMultiplyCalculator()throws SyntaxException, CalculationException {
        String expression = "12 * 10";
        int expected = 12 * 10;
        double actual = rpnCalculator.calculate(expression);
        assertTrue(getMsg(expected, actual), expected == actual);
    }


    @Test
    public void testCalculatorSqrt()throws SyntaxException, CalculationException {
        String  expression = " sqr(25)" ;
        int expected = (int)Math.sqrt(25);
        double actual = rpnCalculator.calculate(expression);
        assertTrue(getMsg(expected, actual), expected == actual);
    }

    @Test
    public void testCalculatorSqrt2()throws SyntaxException, CalculationException {
        String  expression = " sqr(27, 3)" ;
        int expected = 3;
        double actual = rpnCalculator.calculate(expression);
        assertTrue(getMsg(expected, actual), expected == actual);
    }

    @Test (expected = SyntaxException.class)
    public void testCalculatorSqrt3()throws SyntaxException, CalculationException {
        String  expression = " sqr(27, 3, 6)" ;
        int expected = 3;
        double actual = rpnCalculator.calculate(expression);
        assertTrue(getMsg(expected, actual), expected == actual);
    }

    @Test (expected = SyntaxException.class)
    public void testCalculatorEmptySqrt()throws SyntaxException, CalculationException {
        String  expression = " sqr()" ;
        rpnCalculator.calculate(expression);
    }

    @Test
    public void testDivide1()throws SyntaxException, CalculationException {
        String  expression = "10 / (-10)" ;
        double expected = -1;
        double actual = rpnCalculator.calculate(expression);
        assertTrue(getMsg(expected, actual), expected == actual);
    }

    @Test
    public void testDivide2()throws SyntaxException, CalculationException {
        String  expression = "27 / 3" ;
        double expected = 27 / 3;
        double actual = rpnCalculator.calculate(expression);
        assertTrue(getMsg(expected, actual), expected == actual);
    }

    @Test (expected = SyntaxException.class)
    public void testDivide3()throws SyntaxException, CalculationException {
        String  expression = "27 / 0" ;
        rpnCalculator.calculate(expression);
    }

    @Test
    public void testBracket() throws SyntaxException, CalculationException {
        String  expression = "27 / (0+7)" ;
        double expected = (double) 27 / 7;
        double actual = rpnCalculator.calculate(expression);
        assertTrue(getMsg(expected, actual), expected == actual);
    }

    @Test
    public void testMin() throws SyntaxException, CalculationException {
        String  expression = "min(1,56,13,24,43)" ;
        double expected = 1;
        double actual = rpnCalculator.calculate(expression);
        assertTrue(getMsg(expected, actual), expected == actual);
    }

    @Test
    public void testMin2() throws SyntaxException, CalculationException {
        String  expression = "min(1,56,(-7),24,43)" ;
        double expected = -7;
        double actual = rpnCalculator.calculate(expression);
        assertTrue(getMsg(expected, actual), expected == actual);
    }

    @Test
    public void testMin3() throws SyntaxException, CalculationException {
        String  expression = "min(1,56,7,24,0)" ;
        double expected = 0;
        double actual = rpnCalculator.calculate(expression);
        assertTrue(getMsg(expected, actual), expected == actual);
    }

    @Test
    public void testMax() throws SyntaxException, CalculationException {
        String  expression = "max(1,56,7,24,0)" ;
        double expected = 56;
        double actual = rpnCalculator.calculate(expression);
        assertTrue(getMsg(expected, actual), expected == actual);
    }
    @Test
    public void testSum() throws SyntaxException, CalculationException {
        String  expression = "sum(1,4,7,2,0)" ;
        double expected = 1 + 4 + 7 +2 +0;
        double actual = rpnCalculator.calculate(expression);
        assertTrue(getMsg(expected, actual), expected == actual);
    }


    @Test
    public void testPow() throws SyntaxException, CalculationException {
        String  expression = "4 ^ 3" ;
        double expected = Math.pow(4,3);
        double actual = rpnCalculator.calculate(expression);
        assertTrue(getMsg(expected, actual), expected == actual);
    }

}
