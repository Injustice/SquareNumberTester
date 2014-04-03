package org.injustice.squares.handlers;

import org.injustice.squares.mechanics.Computer;

/**
 * Created by Azmat on 28/03/2014.
 * Handler with Singleton design pattern which puts all the other handlers
 * In a place easy to get
 *
 * Handles everything in the program
 */
public class Handler {

    private Computer computer;
    private final DataHandler dataHandler;
    private final UIHandler uiHandler;
    private final QuestionHandler questionHandler;

    private Handler() {
        dataHandler = new DataHandler(Handler.this);
        questionHandler = new QuestionHandler(Handler.this);
        uiHandler = new UIHandler(Handler.this);
        uiHandler.setLookAndFeel();
        uiHandler.displayFrames();
    }

    public void validateFinished() {
        if (dataHandler.getAnswered().size() == dataHandler.getTotalNumberQuestions().intValue()) {
            computer = new Computer(getDataHandler().getTimeTakenMap());
            dataHandler.getFrame().dispose();
        }
    }

    public DataHandler getDataHandler() {
        return dataHandler;
    }

    public Computer getComputer() {
        return computer;
    }

    public UIHandler getUiHandler() {
        return uiHandler;
    }

    public QuestionHandler getQuestionHandler() {
        return questionHandler;
    }

    public void reset() {
        new Handler();
    }

    public static Handler getInstance() {
        return Holder.instance;
    }

    public static Handler createInstance() {
        Holder.instance = new Handler();
        return getInstance();
    }

    private static class Holder {
        static Handler instance;
    }
}
