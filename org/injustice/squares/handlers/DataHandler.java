package org.injustice.squares.handlers;

import org.injustice.squares.ui.MainFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by Azmat on 01/04/2014.
 */
public class DataHandler {

    private final List<Integer> answered;
    private final List<Integer> generated;
    private int totalNumberQuestions = 15;
    private final Random random;
    private final HashMap<Integer, Long> timeTakenMap;
    private final HashMap<Integer, Integer> attempts;
    private long startQuestionTime;
    private int correctInFirstGo;
    private MainFrame frame;
    private Handler handler;


    public DataHandler(final Handler handler) {
        answered = new ArrayList<>();
        generated = new ArrayList<>();
        startQuestionTime = System.currentTimeMillis();
        correctInFirstGo = totalNumberQuestions;
        timeTakenMap = new HashMap<>();
        attempts = new HashMap<>();
        random = new Random();
        this.handler = handler;
    }

    public List<Integer> getGenerated() {
        return generated;
    }

    public List<Integer> getAnswered() { return answered; }

    public int getTotalNumberQuestions() {
        return totalNumberQuestions;
    }

    public void setTotalNumberQuestions(int totalNumberQuestions) {
        this.totalNumberQuestions = totalNumberQuestions;
    }

    public HashMap<Integer, Integer> getAttempts() {
        return attempts;
    }

    public Random getRandom() {
        return random;
    }

    public HashMap<Integer, Long> getTimeTakenMap() {
        return timeTakenMap;
    }

    public long getStartQuestionTime() {
        return startQuestionTime;
    }

    public void setStartQuestionTime(long startQuestionTime) {
        this.startQuestionTime = startQuestionTime;
    }

    public void negateCorrect() {
        correctInFirstGo--;
    }

    public int getCorrectInFirstGo() {
        return correctInFirstGo;
    }

    public void setCorrectInFirstGo(int correctInFirstGo) {
        this.correctInFirstGo = correctInFirstGo;
    }


    public void createFrame() {
        frame = new MainFrame(handler, handler.getQuestionHandler().generateNumber());
        SwingUtilities.invokeLater(() -> frame.setVisible(true));
    }

    public MainFrame getFrame() {
        return frame;
    }
}
