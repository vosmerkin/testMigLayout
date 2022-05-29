package com.example.migLayout.frameInit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button1 extends JButton implements ActionListener {
    String caption;
    Panel panel;

    public Button1( String caption, Panel panel) {
        super(caption);

        this.panel = panel;
        this.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
