package org.injustice.squares.exceptions;

import javax.swing.*;

/**
 * Created by Azmat on 03/04/2014.
 * Shows a JOptionPane of the error
 */
public class IllegalInputException extends Exception {
    private final JFrame parent;
    public IllegalInputException(String message, JFrame parent) {
        super(message);
        this.parent = parent;
        displayProblem();
    }
    public IllegalInputException(String message) {
        this(message, null);
    }

    private void displayProblem() {
        JOptionPane.showMessageDialog(
                parent,
                "Input must be " + getMessage(),
                "IllegalInputException",
                JOptionPane.ERROR_MESSAGE);
    }
}
