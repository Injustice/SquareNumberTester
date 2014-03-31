package org.injustice.squares.ui;

import org.injustice.squares.Square;

import javax.swing.*;

/**
 * Created by Azmat on 28/03/2014.
 */
class QuestionPanel extends JPanel {

    private final Square s;
    private final JLabel questionLabel;
    private final JLabel verdictLabel;
    private final JTextField answerField;
    private final JLabel questionNumber;
    private final JLabel attemptLabel;
    private int attempt = 1;
    private int number;

    public QuestionPanel(Square s, int number) {
        this.s = s;
        this.number = number;
        final JButton checkButton;

        questionLabel = new JLabel("", SwingConstants.CENTER);
        answerField = new JTextField();
        checkButton = new JButton();
        verdictLabel = new JLabel("", SwingConstants.CENTER);
        questionNumber = new JLabel("");
        attemptLabel = new JLabel("" + attempt);

        setLayout(null);

        checkButton.setText("Check");
        verdictLabel.setText("");

        questionLabel.setBounds(5, 5, 345, 25);
        answerField.setBounds(148, 35, 55, 25);
        checkButton.setBounds(138, 65, 75, 25);
        verdictLabel.setBounds(5, 90, 345, 25);
        questionNumber.setBounds(5, 90, 50, 25);
        attemptLabel.setBounds(320, 90, 15, 25);

        answerField.addActionListener(ae -> checkSolution());
        checkButton.addActionListener(ae -> checkSolution());

        add(questionLabel);
        add(answerField);
        add(checkButton);
        add(verdictLabel);
        add(questionNumber);
        add(attemptLabel);
    }

    private void checkSolution() {
        answerField.requestFocus();
        if (answerField.getText().equals(Integer.toString(number * number))) {
            verdictLabel.setText("Correct!");
            s.setCorrect(attempt);
            attemptLabel.setText((attempt = 1) + "");
        } else {
            verdictLabel.setText("Try again!");
            ++attempt;
            attemptLabel.setText(attempt + "");
        }
    }

    public void setQuestion(int number) {
        this.number = number;
        String question = "What is " + number + " squared?";
        questionLabel.setText(question);
        answerField.setText("");
        questionNumber.setText("" + s.getGenerated().size() + "/" +
                s.getTotalNumberQuestions());
    }
}
