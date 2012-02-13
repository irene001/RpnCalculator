package com.teamdev.students.calculator.clients;

import com.teamdev.students.calculator.Calculator;
import com.teamdev.students.calculator.rpnlogic.RpnCalculator;
import com.teamdev.students.calculator.rpnlogic.exceptions.CalculationException;
import com.teamdev.students.calculator.rpnlogic.exceptions.SyntaxException;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Irene
 * Date: 13.01.12
 * Time: 0:11
 * To change this template use File | Settings | File Templates.
 */
public class GuiClient extends JFrame {

    private JLabel resultLabel;
    private JTextField expressionTextField;

    private final static int WIDTH = 400;
    private final static int HEIGHT = 200;
    private final static int TEXT_FIELD_HEIGHT = 40;

    public GuiClient() {
        createComponents();
    }

    private void createComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        expressionTextField = new JTextField();
        expressionTextField.setHorizontalAlignment(JLabel.RIGHT);

        JLabel expressionLabel = new JLabel();
        expressionLabel.setText(MessageConstants.EXPRESSION_LABEL);

        resultLabel = new JLabel();

        JButton calculateButton = new JButton();
        calculateButton.setText(MessageConstants.BUTTON_TEXT);
        calculateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if ("".equals(expressionTextField.getText().trim())) {
                    resultLabel.setText("You didn't type anything");
                    resultLabel.setForeground(Color.RED);
                } else {
                    calculate();
                }
            }
        });

        JFrame frame = new JFrame(MessageConstants.GUI_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel horizontalPanel = new JPanel();
        horizontalPanel.setLayout(new BoxLayout(horizontalPanel, BoxLayout.LINE_AXIS));

        horizontalPanel.add(expressionLabel);
        horizontalPanel.add(expressionTextField);

        JPanel verticalPanel = new JPanel();
        verticalPanel.setLayout(new BoxLayout(verticalPanel, BoxLayout.Y_AXIS));

        verticalPanel.add(horizontalPanel);
        verticalPanel.add(calculateButton);
        calculateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        verticalPanel.add(resultLabel);
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        verticalPanel.add(Box.createHorizontalStrut(0));

        frame.add(verticalPanel);
        horizontalPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, TEXT_FIELD_HEIGHT));

        frame.pack();
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);


    }

    private void calculate() {

        Calculator calculator = new RpnCalculator();
        int focusPosition = 0;

        try {
            double calculatorResult = calculator.calculate(expressionTextField.getText());
            resultLabel.setText(MessageConstants.RESULT_LABEL + calculatorResult);
            resultLabel.setForeground(Color.BLACK);
            focusPosition = expressionTextField.getText().length();
        } catch (SyntaxException e) {
            if (e.getFailPosition() != -1) {
                focusPosition = e.getFailPosition() + 1;// # string indexation begins from 0
            }
            resultLabel.setText(e.getMessage());
            resultLabel.setForeground(Color.RED);
        } catch (CalculationException e) {
            resultLabel.setText("Calculation completed unsuccessfully");
            resultLabel.setForeground(Color.RED);
        }

        expressionTextField.setCaretPosition(focusPosition);
        expressionTextField.requestFocus();

    }


    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GuiClient();
            }
        });
    }


}

