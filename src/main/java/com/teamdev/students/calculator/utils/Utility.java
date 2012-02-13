package com.teamdev.students.calculator.utils;

/**
 * Created by IntelliJ IDEA.
 * User: Irene
 * Date: 22.01.12
 * To change this template use File | Settings | File Templates.
 */

public class Utility {

	private final static String REGULAR_EXPRESSION = "[0-9]*(\\.[0-9]*)?";

	public static boolean isNumber(String string) {
		if (string == null) {
			return false;
		}
		return string.matches(REGULAR_EXPRESSION);
	}
}
