package org.injustice.squares.ui;

import org.injustice.squares.handlers.Handler;

import javax.swing.*;
import java.awt.Dimension;


/**
 * Created by Azmat on 28/03/2014.
 * The main frame of the question panel (NOT the option dialogue)
 */
public class MainFrame extends JFrame {

    private final QuestionPanel panel;
    private int number;

    public MainFrame(Handler s, Integer initialNumber) {
        setNumber(initialNumber);
        panel = new QuestionPanel(s, getNumber());
        initComponents();
    }

    private void initComponents() {
        add(panel);
        panel.setQuestion(number);
        setTitle("Square Numbers Test");
        setResizable(false);
        setPreferredSize(new Dimension(350, 150));
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void setQuestion(Integer number) {
        setNumber(number);
        panel.setQuestion(number);
    }

    public int getNumber() {
        return number;
    }

    void setNumber(int number) {
        this.number = number;
    }
}
