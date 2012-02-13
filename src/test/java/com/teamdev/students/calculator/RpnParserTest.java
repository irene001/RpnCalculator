package com.teamdev.students.calculator;

import com.teamdev.students.calculator.beans.RpnStack;
import com.teamdev.students.calculator.rpnlogic.RpnParser;
import com.teamdev.students.calculator.rpnlogic.exceptions.SyntaxException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: Irene
 * Date: 14.01.12
 * Time: 0:54
 * To change this template use File | Settings | File Templates.
 */
public class RpnParserTest {
    private static RpnParser rpnParser;
    private final String testMsg = "Expected ''{0}'' but actual is ''{1}''";

    private String getMsg(List<String> expected, List<String> actual) {
        return MessageFormat.format(testMsg, expected, actual);
    }

    private void makeAssert(String expression, String expected) throws SyntaxException {
        List<String> expectedAsList = getExpressionAsList(expected);
        RpnStack actual = rpnParser.getInPostfixNotation(expression);
        assertEquals(getMsg(expectedAsList, actual.getExpressionAsList()), expectedAsList, actual.getExpressionAsList());
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        rpnParser = new RpnParser();
    }

    private List getExpressionAsList(String expression) {
        return Arrays.asList(expression.split(" "));
    }

    @Test
    public void testAddExpression() throws SyntaxException {
        String expression = "2 + 4";
        String expected = "2 4 + ";
        makeAssert(expression, expected);
    }

    @Test
    public void testMultipleExpression() throws SyntaxException {
        String expression = "10 * 4";
        String expected = "10 4 * ";
        makeAssert(expression, expected);
    }

    @Test
    public void testExpression1() throws SyntaxException {
        String expression = "100 * 3 + 4";
        String expected = "100 3 * 4 + ";
        makeAssert(expression, expected);
    }

    @Test
    public void testExpression2() throws SyntaxException {
        String expression = "90 + 100 - 5";
        String expected = "90 100 + 5 - ";
        makeAssert(expression, expected);
    }

    @Test
    public void testExpression3() throws SyntaxException {
        String expression = "5 + 4 * 3";
        String expected = "5 4 3 * + ";
        makeAssert(expression, expected);
    }

    @Test
    public void testBrackets1() throws SyntaxException {
        String expression = "10 * (9 + 8)";
        String expected = "10 9 8 + * ";
        makeAssert(expression, expected);
    }

    @Test
    public void testBrackets2() throws SyntaxException {
        String expression = "(5 + 6) * 7";
        String expected = "5 6 + 7 * ";
        makeAssert(expression, expected);
    }

    @Test
    public void testBrackets3() throws SyntaxException {
        String expression = "12+((45))";
        String expected = "12 45 +";
        makeAssert(expression, expected);
    }

    @Test
    public void testBrackets4() throws SyntaxException {
        String expression = "(1 - 2)";
        String expected = "1 2 -";
        makeAssert(expression, expected);
    }


    @Test
    public void testComplexBrackets1() throws SyntaxException {
        String expression = "(3 + 4) * (5+6)";
        String expected = "3 4 + 5 6 + * ";
        makeAssert(expression, expected);
    }

    @Test
    public void testSqr1() throws SyntaxException {
        String expression = "sqr(2 * 15)";
        String expected = "2 15 * 1 sqr";
        makeAssert(expression, expected);
    }

    @Test
    public void testSqr2() throws SyntaxException {
        String expression = " 8 * sqr(2 * 15)";
        String expected = "8 2 15 * 1 sqr * ";
        makeAssert(expression, expected);
    }

    @Test
    public void testParserUnaryMinus() throws SyntaxException {
        String expression = " -8 + 10";
        String expected = "0 8 - 10 +";
        makeAssert(expression, expected);
    }

    @Test
    public void testMin() throws SyntaxException {
        String expression = "min(100, 200, 300)";
        String expected = "100 200 300 3 min";
        makeAssert(expression, expected);
    }

    @Test
    public void testFunctionExpression2() throws SyntaxException {
        String expression = "min(100 + 200, 300, 400 - 500 * 600)";
        String expected = "100 200 + 300 400 500 600 * - 3 min";
        makeAssert(expression, expected);
    }


    @Test
    public void testFunctionInComplex1() throws SyntaxException {
        String expression = "min(100, min (200, 300), 400)";
        String expected = "100 200 300 2 min 400 3 min";
        makeAssert(expression, expected);
    }


    @Test
    public void testFunctionInComplex2() throws SyntaxException {
        String expression = "min(100, min (200, 300, 400, 500), 600)";
        String expected = "100 200 300 400 500 4 min 600 3 min";
        makeAssert(expression, expected);
    }

    @Test
    public void testFunctionInComplex3() throws SyntaxException {
        String expression = "min(min(100,200), min (300, 400, 500, 600), 700)";
        String expected = "100 200 2 min 300 400 500 600 4 min 700 3 min";
        makeAssert(expression, expected);
    }

    @Test
    public void testFunctionInComplex4() throws SyntaxException {
        String expression = "min(100, min (200, 300, 400, 500), min(600, 700, 800))";
        String expected = "100 200 300 400 500 4 min 600 700 800 3 min 3 min";
        makeAssert(expression, expected);
    }


    @Test(expected = SyntaxException.class)
    public void testIncorrectBracket1() throws SyntaxException {
        String expression = "min(100, min (200, 300, 400, 500, min(600, 700, 800))";
        String expected = "100 200 300 400 500 4 min 600 700 800 3 min 3 min";
        makeAssert(expression, expected);
    }

    @Test(expected = SyntaxException.class)
    public void testIncorrectBracket2() throws SyntaxException {
        String expression = "4(100,200)+5";
        rpnParser.getInPostfixNotation(expression);
    }

    @Test(expected = SyntaxException.class)
    public void testIncorrectBracket3() throws SyntaxException {
        String expression = "4+(100,200)5";
        rpnParser.getInPostfixNotation(expression);
    }

    @Test(expected = SyntaxException.class)
    public void testIncorrectBracket4() throws SyntaxException {
        String expression = "56(4, 6)";
        rpnParser.getInPostfixNotation(expression);
    }

    @Test(expected = SyntaxException.class)
    public void testIncorrectNumberExpression() throws SyntaxException {
        String expression = "1 + a+9";
        rpnParser.getInPostfixNotation(expression);
    }


    @Test(expected = SyntaxException.class)
    public void testIncorrectComma1() throws SyntaxException {
        String expression = "min(100, min (200, ,300), 400)";
        String expected = "100 200 300 2 min 400 3 min";

        makeAssert(expression, expected);
    }


    @Test(expected = SyntaxException.class)
    public void testTwoOperations() throws SyntaxException {
        String expression = "1++2";
        rpnParser.getInPostfixNotation(expression);
    }

    @Test
    public void testFunctionParser() throws SyntaxException {
        String expression = "4+min(100,200)+5";
        String expected = "4 100 200 2 min + 5 +";
        makeAssert(expression, expected);
    }


    @Test(expected = SyntaxException.class)
    public void testCommas1() throws SyntaxException {
        String expression = "4,6";
        rpnParser.getInPostfixNotation(expression);
    }

    @Test(expected = SyntaxException.class)
    public void testCommas2() throws SyntaxException {
        String expression = "min(, 6)";
        rpnParser.getInPostfixNotation(expression);
    }

    @Test(expected = SyntaxException.class)
    public void testCommas3() throws SyntaxException {
        String expression = "min(4, 6,)";
        rpnParser.getInPostfixNotation(expression);
    }

    @Test(expected = SyntaxException.class)
    public void testIncorrectFunction() throws SyntaxException {
        String expression = "min()";
        rpnParser.getInPostfixNotation(expression);
    }

    @Test(expected = SyntaxException.class)
    public void testIncorrectOperation() throws SyntaxException {
        String expression = "12+7+";
        rpnParser.getInPostfixNotation(expression);
    }


    @Test(expected = SyntaxException.class)
    public void testLatIsSeparator() throws SyntaxException {
        String expression = "10+5)";
        rpnParser.getInPostfixNotation(expression);
    }

    @Test
    public void testFunctionParser2() throws SyntaxException {
        String expression = "min(100,-200,300)";
        String expected = "100 0 200 - 300 3 min";
        makeAssert(expression, expected);
    }

    @Test
    public void testFunctionParser3() throws SyntaxException {
        String expression = "min(100,(-200),300)";
        String expected = "100 0 200 - 300 3 min";
        makeAssert(expression, expected);
    }

}
