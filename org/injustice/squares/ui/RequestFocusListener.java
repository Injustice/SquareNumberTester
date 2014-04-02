package org.injustice.squares.ui;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

class RequestFocusListener implements AncestorListener {

    public RequestFocusListener() {
    }

    @Override
    public void ancestorAdded(AncestorEvent e) {
        JComponent component = e.getComponent();
        component.requestFocusInWindow();
        component.removeAncestorListener(this);
    }

    @Override
    public void ancestorMoved(AncestorEvent e) {
    }

    @Override
    public void ancestorRemoved(AncestorEvent e) {
    }
}