package com.example.migLayout.frameInit;

import com.example.migLayout.entity.Name;
import com.example.migLayout.services.CrudActions;
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

public class Frame1 {
    private static final Logger log = LoggerFactory.getLogger(Frame1.class);
    private static final String PATH = "jtext.txt";
    private static final String URL = "http://ya.ru";
    //    private static final String URL = "http://forum.ru-board.com/e.pl";
    private JFrame frame;
    DownloadUrl du = new DownloadUrl();
    private JTextArea text;
    private JButton button1;
    private JButton button2;

    private CrudActions crudActions;

    public Frame1() {
        initialize();
        crudActions = new CrudActions();
    }

    public void initialize() {
        frame = new JFrame();

        this.frame.setTitle("MigLayoutDemo");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(600, 600);
        this.frame.add(jpanelCrud(), BorderLayout.CENTER);
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);

    }

    private JPanel jpanelCrud() {
        JPanel panel = new JPanel(new MigLayout("wrap, debug", "[][][]", "[][][][][]"));
        JButton createButton = new JButton("Create Entry");
        JLabel createNamelabel = new JLabel("Name");
        JTextArea createNameText = new JTextArea(1, 10);
        panel.add(createButton);
        panel.add(createNamelabel);
        panel.add(createNameText);

        JButton requestButton = new JButton("Request Entry");
        JLabel requestNamelabel = new JLabel("Name");
        JTextArea requestNameText = new JTextArea(1, 10);
        requestNameText.setToolTipText("Comma delimited id and Name");
        panel.add(requestButton);
        panel.add(requestNamelabel);
        panel.add(requestNameText);

        JButton updateButton = new JButton("Edit Name");
        JLabel updateNamelabel = new JLabel("New Name");
        JTextArea updateNameText = new JTextArea(1, 10);
        panel.add(updateButton);
        panel.add(updateNamelabel);
        panel.add(updateNameText);


        JButton deleteButton = new JButton("Delete Entry");
        JLabel deleteNamelabel = new JLabel("Name");
        JTextArea deleteNameText = new JTextArea(1, 10);
        panel.add(deleteButton);
        panel.add(deleteNamelabel);
        panel.add(deleteNameText);

        JTextArea responseText = new JTextArea(5, 30);
        panel.add(responseText, "span");

        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SwingWorker() {
                    @Override
                    protected Object doInBackground() throws Exception {
                        log.info("createButton clicked");
                        Name name = new Name(createNameText.getText());
                        String result = crudActions.createAction(name.toJson());
                        responseText.setText(result);
                        return null;
                    }
                }.execute();
            }
        });
        requestButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SwingWorker() {
                    @Override
                    protected Object doInBackground() throws Exception {
                        log.info("requestButton clicked");
                        String result = crudActions.requestAction(requestNameText.getText());
                        responseText.setText(result);
                        return null;
                    }
                }.execute();
            }
        });
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SwingWorker() {
                    @Override
                    protected Object doInBackground() throws Exception {
                        log.info("updateButton clicked");
                        String result = crudActions.updateAction(updateNameText.getText());
                        responseText.setText(result);
                        return null;
                    }
                }.execute();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SwingWorker() {
                    @Override
                    protected Object doInBackground() throws Exception {
                        log.info("deleteButton clicked");
                        String result = crudActions.deleteAction(deleteNameText.getText());
                        responseText.setText(result);
                        return null;
                    }
                }.execute();
            }
        });

        return panel;
    }

    private JPanel jpanel1() {
        JPanel panel = new JPanel(new MigLayout("debug"));
        button1 = new JButton("Get from file");
        button2 = new JButton("Getfrom URL");
        text = new JTextArea(20, 20);
        panel.add(button1);
        panel.add(button2);
        panel.add(text);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ImportExport ie = new ImportExport();
                text.setText(ie.importFromFile("./" + PATH));
            }
        });
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SwingWorker() {
                    @Override
                    protected Object doInBackground() throws Exception {
                        try {
                            text.setText(du.getByUrl(URL));
                        } catch (IOException e) {
                            log.info("Error donloading URl  {}.", e.toString());
                            JOptionPane.showMessageDialog(null, "Error downloding", "InfoBox: " + "Error", JOptionPane.INFORMATION_MESSAGE);
//                            result = "Error downloading URL " + url;
                        }
                        return null;
                    }
                }.execute();
//                SwingUtilities.invokeLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        text.setText(du.getByUrl(URL));
//                        try {
//                            Thread.sleep(2000);
//                        } catch (InterruptedException ex) {
//                            throw new RuntimeException(ex);
//                        }
//                    }
//                });
            }
        });
        return panel;
    }

}
//TODO Swing EDT, layouts variants, error window