package org.injustice.squares.handlers;

import javax.swing.*;

/**
 * Created by Azmat on 01/04/2014.
 */
public class UIHandler {

    public UIHandler() {

    }

    public void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
