package org.injustice.squares.handlers;

import org.injustice.squares.ui.MainFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Azmat on 01/04/2014.
 */
@SuppressWarnings("ALL")
public class DataHandler {

    private final List<Integer> answered;
    private final List<Integer> generated;
    private final AtomicInteger totalNumberQuestions = new AtomicInteger(15);
    private final AtomicInteger correctInFirstGo;
    private final AtomicLong startQuestionTime;
    private final Random random;
    private final ConcurrentHashMap<Integer, Long> timeTakenMap;
    private final ConcurrentHashMap<Integer, Integer> attempts;
    private MainFrame frame;
    private final Handler handler;

    public DataHandler(final Handler handler) {
        answered = new ArrayList<>();
        generated = new ArrayList<>();
        startQuestionTime = new AtomicLong();
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

    public AtomicLong getStartQuestionTime() {
        return startQuestionTime;
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

    public synchronized void setStartQuestionTime(long startQuestionTime) {
        this.startQuestionTime.set(startQuestionTime);
    }

    public synchronized void setCorrectInFirstGo(int correctInFirstGo) {
        this.correctInFirstGo.set(correctInFirstGo);
    }

    public synchronized Random getRandom() {
        return random;
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
