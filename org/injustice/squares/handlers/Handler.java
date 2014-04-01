package org.injustice.squares.handlers;

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
public class Handler {

    private final Random random;
    private final MainFrame frame;
//    private int totalNumberQuestions = 15;
//    private final List<Integer> answered;
//    private final List<Integer> generated;
    private final HashMap<Integer, Long> timeTakenMap;
    private final HashMap<Integer, Integer> attempts;
    private int correctInFirstGo;
    private long startQuestionTime;
    private Computer computer;
    private DataHolder dataHolder;
    private UIHandler uiHandler;

    public Handler() {
        uiHandler = new UIHandler();
        uiHandler.setLookAndFeel();
        dataHolder = new DataHolder();
        timeTakenMap = new HashMap<>();
        attempts = new HashMap<>();
        random = new Random();
        new MultiInputOptionPane(Handler.this);
        correctInFirstGo = dataHolder.getTotalNumberQuestions();
        frame = new MainFrame(Handler.this, generateNumber());
        SwingUtilities.invokeLater(() -> frame.setVisible(true));
        startQuestionTime = System.currentTimeMillis();
    }

    public void setCorrect(int attempts) {
        if (attempts != 1) {
            correctInFirstGo--;
            System.out.println("Negating attempts");
        }
        dataHolder.getAnswered().add(frame.getNumber());
        this.attempts.put(frame.getNumber(), attempts);
        final long time = System.currentTimeMillis() - startQuestionTime;
        startQuestionTime = System.currentTimeMillis();
        timeTakenMap.put(frame.getNumber(), time);
        if (dataHolder.getAnswered().size() == dataHolder.getTotalNumberQuestions()) {
            validateFinished();
            DrawChart chart = new DrawChart(timeTakenMap, computer, this.attempts);
            checkRetry(chart);
            return;
        }
        final Integer number = generateNumber();
        if (!dataHolder.getAnswered().contains(number) && dataHolder.getGenerated().contains(number)) {
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
                "You got " + correctInFirstGo + "/" + dataHolder.getTotalNumberQuestions() + ". Retry?",
                "Handler Number Tester",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Yes", "No"},
                "Yes"
        );
        switch (result) {
            case 0:
                applicationFrame.setVisible(false);
                new Handler();
            case 1:
                applicationFrame.requestFocus();
        }
    }

    private Integer generateNumber() {
        Integer number = random.nextInt(dataHolder.getTotalNumberQuestions()+ 1);
        return !dataHolder.getGenerated().contains(number) && number != 0 ? addToList(number) : generateNumber();
    }

    private Integer addToList(Integer number) {
        dataHolder.getGenerated().add(number);
        return number;
    }

    private void validateFinished() {
        if (dataHolder.getAnswered().size() == dataHolder.getTotalNumberQuestions()) {
            computer = new Computer(timeTakenMap);
            frame.dispose();
        }
    }

    public DataHolder getDataHolder() {
        return dataHolder;
    }

}
