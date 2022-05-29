package com.example.migLayout.frameInit;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame1 extends JFrame implements ActionListener {


    public Frame1() {
    }

    private void init() {
        JFrame frame = new JFrame();

        frame.setTitle("MigLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 600);

        JPanel panel = new JPanel(new MigLayout());

        JButton button1 = new JButton("Button 1");
//        JTextArea text = new JTextArea();

        panel.add(button1);


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                button1.setText(button1.getText() + "+");
            }
        });


        frame.add(panel, BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
