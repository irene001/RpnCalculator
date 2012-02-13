package com.teamdev.students.calculator;

import com.teamdev.students.calculator.utils.Utility;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: Irene
 * Date: 22.01.12
 * Time: 20:44
 * To change this template use File | Settings | File Templates.
 */
public class UtilityTest {

    @Test
    public void testIntIsNumber() {
        String  number = "123";
        assertTrue(Utility.isNumber(number));
    }

    @Test
    public void testCharsIsNumber() {
        String  number = "abs";
        assertFalse(Utility.isNumber(number));
    }

    @Test
    public void testCharsWithDotIsNumber() {
        String  number = "abs";
        assertFalse(Utility.isNumber(number));
    }


    @Test
    public void testCharsWithDotIsNumber2() {
        String  number = "abs.3";
        assertFalse(Utility.isNumber(number));
    }

    @Test
    public void testCharsWithDotIsNumber3() {
        String  number = "3.sdfs";
        assertFalse(Utility.isNumber(number));
    }

    @Test
    public void testDoubleIsNumber() {
        String  number = "12.345";
        assertTrue(Utility.isNumber(number));
    }

    @Test
    public void testDoubleIsNumber2() {
        String  number = "12.";
        assertTrue(Utility.isNumber(number));
    }
}
