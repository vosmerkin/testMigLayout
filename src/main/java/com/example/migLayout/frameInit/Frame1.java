package com.example.migLayout.frameInit;

import com.example.migLayout.services.DownloadUrl;
import com.example.migLayout.services.ImportExport;
import net.miginfocom.swing.MigLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Frame1 extends JFrame {
    private static final Logger log = LoggerFactory.getLogger(Frame1.class);
    private static final String PATH = "jtext.txt";
    private static final String URL = "http://ya.ru";
//    private static final String URL = "http://forum.ru-board.com/e.pl";
    private JTextArea text;
    JButton button1;
    JButton button2;
    public Frame1() {
        JPanel panel = new JPanel(new MigLayout());
        button1 = new JButton("Get from file");
        button2 = new JButton("Getfrom URL");
        text = new JTextArea(20,60);
        panel.add(button1);
        panel.add(button2);
        panel.add(text);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ImportExport ie = new ImportExport();
                text.setText( ie.importFromFile("./" + PATH));
            }
        });
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                DownloadUrl du = new DownloadUrl();
//                try {
                    text.setText( du.getByUrl( URL));
//                } catch (IOException ioException) {
//                    ioException.printStackTrace();
//                    log.info("Error");
//                }
            }
        });

        setTitle("MigLayoutDemo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        add(panel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
    }

}
//TODO Swing EDT, layouts variants, error window