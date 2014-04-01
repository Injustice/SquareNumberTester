package org.injustice.squares.handlers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Azmat on 01/04/2014.
 */
public class DataHolder {

    private final List<Integer> answered;
    private final List<Integer> generated;
    private int totalNumberQuestions = 15;


    public DataHolder() {
        answered = new ArrayList<>();
        generated = new ArrayList<>();
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
}
