package com.example.migLayout;


import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class migLayoutApplication {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("MigLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
        frame.setSize(600,600);

        JPanel panel = new JPanel(new MigLayout("wrap","[][][]","[][][]"));

        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");
        JButton button3 = new JButton("Button 3");

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                button1.setText(button1.getText() + "+");
            }
        });




        frame.add(panel,BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }
}
