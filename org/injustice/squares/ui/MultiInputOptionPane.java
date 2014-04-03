package org.injustice.squares.ui;

import org.injustice.squares.exceptions.IllegalInputException;
import org.injustice.squares.handlers.Handler;

import javax.swing.*;

/**
 * Created by Azmat on 30/03/2014.
 * Custom class which displays a JOptionPane with a JLabel into which
 * The user may enter the information required
 */
public class MultiInputOptionPane extends JOptionPane {
    private final int squareRootOfIntegerMaxValue = 46340;
    public MultiInputOptionPane(Handler handler) {
        JTextField option = new JTextField(3);
        option.addAncestorListener(new RequestFocusListener());
        JPanel panel = new JPanel();
        panel.add(new JLabel("Maximum number:"));
        panel.add(option);
        int result = JOptionPane.showConfirmDialog(null, panel,
                "Square Number Tester", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                if (!option.getText().matches("^[a-zA-Z]+$")) {
                    if (Double.parseDouble(option.getText()) ==
                            (int) Double.parseDouble(option.getText())) {
                        int number = (int) Double.parseDouble(option.getText());
                        if (number > 0) {
                            if (number <= squareRootOfIntegerMaxValue) {
                                System.out.println("Max number: " + option.getText());
                                handler.getDataHandler().setTotalNumberQuestions(number);
                                handler.getDataHandler().setCorrectInFirstGo(handler.getDataHandler().getTotalNumberQuestions().intValue());
                            } else {
                                throw new IllegalInputException("less than 46350");
                            }
                        } else {
                            throw new IllegalInputException("positive");
                        }
                    } else {
                        throw new IllegalInputException("an integer");
                    }
                } else {
                    throw new IllegalInputException("a number");
                }
            } catch (IllegalInputException e) {
                e.printStackTrace();
                new MultiInputOptionPane(handler);
            }
        } else if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
            System.exit(0);
        } else {
            new MultiInputOptionPane(handler);
        }
    }
}
