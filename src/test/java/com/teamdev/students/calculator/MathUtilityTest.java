package com.teamdev.students.calculator;

import com.teamdev.students.calculator.utils.MathUtility;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
* Created by IntelliJ IDEA.
* User: Irene
* Date: 22.01.12
* Time: 20:43
* To change this template use File | Settings | File Templates.
*/
public class MathUtilityTest {

    private List getExpressionAsList(Double [] expressionArray){
        return Arrays.asList(expressionArray);
    }

    @Test
    public void testSum() {
        Double[] array = new Double[]{12., 13.1, 1.2, 5.3};
        List list = Arrays.asList(array);
        double  expected = 12 + 13.1 + 1.2 + 5.3;
        assertTrue(expected ==  MathUtility.sum(getExpressionAsList(array)));
    }

    @Test
    public void testMin() {
        Double[] array = new Double[]{12., 13.1, 1.2, 5.3};
        List list = Arrays.asList(array);
        double  expected = 1.2 ;

        assertTrue(expected ==  MathUtility.min(getExpressionAsList(array)));
    }

    @Test
    public void testMax() {
        Double[] array = new Double[]{12., 13.1, 1.2, 5.3};
        List list = Arrays.asList(array);
        double  expected = 13.1 ;

        assertTrue(expected ==  MathUtility.max(getExpressionAsList(array)));
    }

    @Test
    public void testSqr1() {
        double  expected = 3 ;
        assertTrue(expected ==  MathUtility.sqr(9));
    }

    @Test
    public void testSqr2() {
        double  expected = 3;
        assertTrue(expected ==  MathUtility.sqr(27, 3));
    }

    @Test
    public void testSqr3() {
        double  expected = 3.3 ;
        MathUtility.sqr(27);
        assertTrue(expected ==  MathUtility.sqr(3.3*3.3*3.3, 3));
    }



}
