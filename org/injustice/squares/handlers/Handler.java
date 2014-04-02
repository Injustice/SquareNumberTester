package org.injustice.squares.handlers;

import org.injustice.squares.mechanics.Computer;

/**
 * Created by Azmat on 28/03/2014.
 */
@SuppressWarnings("DefaultFileTemplate")
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

    public synchronized DataHandler getDataHandler() {
        return dataHandler;
    }

    public synchronized Computer getComputer() {
        return computer;
    }

    public synchronized UIHandler getUiHandler() {
        return uiHandler;
    }

    public synchronized QuestionHandler getQuestionHandler() {
        return questionHandler;
    }

    public synchronized void reset() {
        new Handler();
    }

    public static Handler getInstance() {
        return Holder.instance;
    }

    private static class Holder {
        static final Handler instance = new Handler();
    }
}
