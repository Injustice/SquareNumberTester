package org.injustice.squares.handlers;

import org.injustice.squares.ui.MultiInputOptionPane;
import org.jfree.ui.ApplicationFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Azmat on 01/04/2014.
 */
public class UIHandler {
    private Handler handler;

    public UIHandler(Handler handler) {
        this.handler = handler;
    }

    public void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayFrames() {
        new MultiInputOptionPane(handler);
        handler.getDataHandler().createFrame();
    }



    public void checkRetry(ApplicationFrame applicationFrame) {
        int result = JOptionPane.showOptionDialog(new JFrame() {
                                                      public boolean isShowing() {
                                                          return true;
                                                      }

                                                      public Rectangle getBounds() {
                                                          return new Rectangle(handler.getDataHandler().getFrame().getBounds().x, handler.getDataHandler().getFrame().getBounds().y + 270, 0, 0);
                                                      }
                                                  },
                "You got " + handler.getDataHandler().getCorrectInFirstGo() + "/" + handler.getDataHandler().getTotalNumberQuestions() + ". Retry?",
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
}
