package org.injustice.squares.ui;

import org.injustice.squares.Square;

import javax.swing.*;

/**
 * Created by Azmat on 30/03/2014.
 */
public class MultiInputOptionPane extends JOptionPane {
    public MultiInputOptionPane(Square square) {
        JTextField option = new JTextField(3);
        option.addAncestorListener(new RequestFocusListener());
        JPanel panel = new JPanel();
        panel.add(new JLabel("Maximum number:"));
        panel.add(option);
        int result = JOptionPane.showConfirmDialog(null, panel,
                "Square Tester", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            if (!option.getText().matches("^[a-zA-Z]+$")) {
                System.out.println("Max number: " + option.getText());
                square.setTotalNumberQuestions(Integer.parseInt(option.getText()));
            } else {
                new MultiInputOptionPane(square);
            }
        } else if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
            System.exit(0);
        } else {
            new MultiInputOptionPane(square);
        }


    }
}
