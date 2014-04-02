package org.injustice.squares.handlers;

import org.injustice.squares.mechanics.Computer;

/**
 * Created by Azmat on 28/03/2014.
 */
public class Handler {

    private Computer computer;
    private DataHandler dataHandler;
    private UIHandler uiHandler;
    private QuestionHandler questionHandler;

    public Handler() {
        dataHandler = new DataHandler(Handler.this);
        questionHandler = new QuestionHandler(Handler.this);
        uiHandler = new UIHandler(Handler.this);
        uiHandler.setLookAndFeel();
        uiHandler.displayFrames();
    }

    public void validateFinished() {
        if (dataHandler.getAnswered().size() == dataHandler.getTotalNumberQuestions()) {
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
}
