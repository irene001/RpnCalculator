package com.teamdev.students.calculator.clients;

import com.teamdev.students.calculator.Calculator;
import com.teamdev.students.calculator.rpnlogic.RpnCalculator;
import com.teamdev.students.calculator.rpnlogic.exceptions.CalculationException;
import com.teamdev.students.calculator.rpnlogic.exceptions.SyntaxException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by IntelliJ IDEA.
 * User: Irene
 * Date: 13.01.12
 * Time: 0:11
 * To change this template use File | Settings | File Templates.
 */
public class ConsoleClient {


    private static final String STOP_MARKER = "stop";

    public static void main(String[] args) throws FileNotFoundException, SyntaxException {

        System.out.println(MessageConstants.NOTE_INSTRUCTION_MSG);
        System.out.println(MessageConstants.EXIT_INSTRUCTION);
        System.out.println(MessageConstants.APP_INSTRUCTION);


        BufferedReader bufferedReader = null;
        String expression;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            Calculator calculator = new RpnCalculator();
            while (!STOP_MARKER.equals(expression = bufferedReader.readLine())) {
                try {
                    if (expression == null || expression.trim().isEmpty()) {
                        System.err.println("You didn't type anything.");
                        continue;
                    }
                    double result = calculator.calculate(expression);
                    System.out.println(expression + " = " + result);

                } catch (SyntaxException e) {
                    System.err.println(e.getMessage());
                    if (e.getFailPosition() != -1) {
                        System.err.println("Exception in position " + (e.getFailPosition() + 1));// # string indexation begins from 0
                    }
                } catch (CalculationException e) {
                    System.err.println("Calculation completed unsuccessfully");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                //#  do nothing
            }

        }
    }
}
