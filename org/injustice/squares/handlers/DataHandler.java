package org.injustice.squares.handlers;

import org.injustice.squares.ui.MainFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Azmat on 01/04/2014.
 */
public class DataHandler {

    private final List<Integer> answered;
    private final List<Integer> generated;
    private AtomicInteger totalNumberQuestions = new AtomicInteger(15);
    private final Random random;
    private final ConcurrentHashMap<Integer, Long> timeTakenMap;
    private final ConcurrentHashMap<Integer, Integer> attempts;
    private long startQuestionTime;
    private AtomicInteger correctInFirstGo;
    private MainFrame frame;
    private Handler handler;


    public DataHandler(final Handler handler) {
        answered = new ArrayList<>();
        generated = new ArrayList<>();
        correctInFirstGo = new AtomicInteger(totalNumberQuestions.intValue());
        timeTakenMap = new ConcurrentHashMap<>();
        attempts = new ConcurrentHashMap<>();
        random = new Random();
        this.handler = handler;
    }

    public List<Integer> getGenerated() {
        return generated;
    }

    public List<Integer> getAnswered() { return answered; }

    public AtomicInteger getTotalNumberQuestions() {
        return totalNumberQuestions;
    }

    public AtomicInteger getCorrectInFirstGo() {
        return correctInFirstGo;
    }

    public synchronized void setTotalNumberQuestions(int totalNumberQuestions) {
        this.totalNumberQuestions.set(totalNumberQuestions);
    }

    public synchronized void negateCorrect() {
        correctInFirstGo.decrementAndGet();
    }

    public synchronized ConcurrentHashMap<Integer, Integer> getAttempts() {
        return attempts;
    }

    public synchronized ConcurrentHashMap<Integer, Long> getTimeTakenMap() {
        return timeTakenMap;
    }

    public Random getRandom() {
        return random;
    }

    public long getStartQuestionTime() {
        return startQuestionTime;
    }

    public void setStartQuestionTime(long startQuestionTime) {
        this.startQuestionTime = startQuestionTime;
    }

    public void setCorrectInFirstGo(int correctInFirstGo) {
        this.correctInFirstGo.set(correctInFirstGo);
    }

    public void createFrame() {
        frame = new MainFrame(handler, handler.getQuestionHandler().generateNumber());
        SwingUtilities.invokeLater(() -> frame.setVisible(true));
        setStartQuestionTime(System.currentTimeMillis());
    }

    public MainFrame getFrame() {
        return frame;
    }
}
