package org.injustice.squares;

import org.injustice.squares.mechanics.Computer;
import org.injustice.squares.mechanics.DrawChart;
import org.injustice.squares.ui.MainFrame;
import org.injustice.squares.ui.MultiInputOptionPane;
import org.jfree.ui.ApplicationFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by Azmat on 28/03/2014.
 */
public class Square {

    private final Random random;
    private final MainFrame frame;
    private int totalNumberQuestions = 15;
    private final List<Integer> answered;
    private final List<Integer> generated;
    private final HashMap<Integer, Long> timeTakenMap;
    private final HashMap<Integer, Integer> attempts;
    private int correctInFirstGo;
    private long startQuestionTime;
    private Computer computer;

    public Square() {
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        answered = new ArrayList<>();
        generated = new ArrayList<>();
        timeTakenMap = new HashMap<>();
        attempts = new HashMap<>();
        random = new Random();
        new MultiInputOptionPane(Square.this);
        correctInFirstGo = getTotalNumberQuestions();
        frame = new MainFrame(Square.this, generateNumber());
        SwingUtilities.invokeLater(() -> frame.setVisible(true));
        startQuestionTime = System.currentTimeMillis();
        // lol
    }

    public static void main(String[] args) {
        new Square();
    }

    public void setCorrect(int attempts) {
        if (attempts != 1) {
            correctInFirstGo--;
            System.out.println("Negating attempts");
        }
        getAnswered().add(frame.getNumber());
        this.attempts.put(frame.getNumber(), attempts);
        final long time = System.currentTimeMillis() - getStartQuestionTime();
        startQuestionTime = System.currentTimeMillis();
        timeTakenMap.put(frame.getNumber(), time);
        if (getAnswered().size() == getTotalNumberQuestions()) {
            validateFinished();
            DrawChart chart = new DrawChart(timeTakenMap, computer, this.attempts);
            checkRetry(chart);
            return;
        }
        final Integer number = generateNumber();
        if (!getAnswered().contains(number) && getGenerated().contains(number)) {
            frame.setQuestion(number);
        }
        validateFinished();
    }

    private void checkRetry(ApplicationFrame applicationFrame) {
        int result = JOptionPane.showOptionDialog(new JFrame() {
                                                      public boolean isShowing() {
                                                          return true;
                                                      }

                                                      public Rectangle getBounds() {
                                                          return new Rectangle(frame.getBounds().x, frame.getBounds().y + 270, 0, 0);
                                                      }
                                                  },
                "You got " + correctInFirstGo + "/" + getTotalNumberQuestions() + ". Retry?",
                "Square Number Tester",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Yes", "No"},
                "Yes"
        );
        switch (result) {
            case 0:
                applicationFrame.setVisible(false);
                new Square();
            case 1:
                applicationFrame.requestFocus();
        }
    }

    private Integer generateNumber() {
        Integer number = random.nextInt(getTotalNumberQuestions() + 1);
        return !getGenerated().contains(number) && number != 0 ? addToList(number) : generateNumber();
    }

    private Integer addToList(Integer number) {
        getGenerated().add(number);
        return number;
    }

    private void validateFinished() {
        if (getAnswered().size() == getTotalNumberQuestions()) {
            computer = new Computer(timeTakenMap);
            frame.dispose();
        }
    }

    List<Integer> getAnswered() {
        return answered;
    }

    public List<Integer> getGenerated() {
        return generated;
    }

    public int getTotalNumberQuestions() {
        return totalNumberQuestions;
    }

    public void setTotalNumberQuestions(int totalNumberQuestions) {
        this.totalNumberQuestions = totalNumberQuestions;
    }

    long getStartQuestionTime() {
        return startQuestionTime;
    }


}
