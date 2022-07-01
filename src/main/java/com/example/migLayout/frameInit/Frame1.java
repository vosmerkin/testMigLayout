package com.example.migLayout.frameInit;

import com.example.migLayout.entity.Name;
import com.example.migLayout.services.*;
import net.miginfocom.swing.MigLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
    private JButton createButton;
    private JButton requestButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JTextArea createNameText;
    private JTextArea requestNameText;
    private JTextArea updateNameText;
    private JTextArea deleteNameText;

//    private CrudActions crudActions;
    private BackendClient backendClient;

    public Frame1() {
        initialize();
        backendClient = new CurlBackendClient();
//        crudActions = new CrudActions();
    }

    public void initialize() {
        frame = new JFrame();
        this.frame.setTitle("MigLayoutDemo");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(600, 600);
        this.frame.add(jPanelCrud(), BorderLayout.CENTER);
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
        changeCreateButtonEnabledState();
        changeRequestButtonEnabledState();
        changeUpdateButtonEnabledState();
        changeDeleteButtonEnabledState();
    }

    private void changeCreateButtonEnabledState() {
        createButton.setEnabled(!createNameText.getText().isEmpty());
    }

    private void changeRequestButtonEnabledState() {
        requestButton.setEnabled(!requestNameText.getText().isEmpty());
    }

    private void changeUpdateButtonEnabledState() {
        updateButton.setEnabled(!updateNameText.getText().isEmpty());
    }

    private void changeDeleteButtonEnabledState() {
        deleteButton.setEnabled(!deleteNameText.getText().isEmpty());
    }

    private JPanel jPanelCrud() {
        JPanel panel = new JPanel(new MigLayout("wrap, fill, debug", "[][][]", "[][][][][]"));

        createButton = new JButton("Create Entry");
        JLabel createNameLabel = new JLabel("Name");
        createNameText = new JTextArea(1, 10);
        panel.add(createButton);
        panel.add(createNameLabel);
        panel.add(createNameText);

        requestButton = new JButton("Request Entry");
        JLabel requestNameLabel = new JLabel("Name");
        requestNameText = new JTextArea(1, 10);
        requestNameText.setToolTipText("Comma delimited id and Name");
        panel.add(requestButton);
        panel.add(requestNameLabel);
        panel.add(requestNameText);

        updateButton = new JButton("Edit Name");
        JLabel updateNameLabel = new JLabel("New Name");
        updateNameText = new JTextArea(1, 10);
        panel.add(updateButton);
        panel.add(updateNameLabel);
        panel.add(updateNameText);


        deleteButton = new JButton("Delete Entry");
        JLabel deleteNameLabel = new JLabel("Name");
        deleteNameText = new JTextArea(1, 10);
        panel.add(deleteButton);
        panel.add(deleteNameLabel);
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
//                        String result = crudActions.createAction(name.toJson());
                        String result = backendClient.createAction(name.toJson());
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
//                        String result = crudActions.requestAction(requestNameText.getText());
                        String result = backendClient.requestAction(requestNameText.getText());
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
//                        String result = crudActions.updateAction(updateNameText.getText());
                        String result = backendClient.updateAction(updateNameText.getText());
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
//                        String result = crudActions.deleteAction(deleteNameText.getText());
                        String result = backendClient.deleteAction(deleteNameText.getText());
                        responseText.setText(result);
                        return null;
                    }
                }.execute();
            }
        });


        createNameText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changeCreateButtonEnabledState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changeCreateButtonEnabledState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                changeCreateButtonEnabledState();
            }


        });

        requestNameText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changeRequestButtonEnabledState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changeRequestButtonEnabledState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                changeRequestButtonEnabledState();
            }


        });
        updateNameText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changeUpdateButtonEnabledState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changeUpdateButtonEnabledState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                changeUpdateButtonEnabledState();
            }


        });
        deleteNameText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changeDeleteButtonEnabledState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changeDeleteButtonEnabledState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                changeDeleteButtonEnabledState();
            }


        });

        return panel;
    }

    private JPanel jPanel1() {
        JPanel panel = new JPanel(new MigLayout("debug"));
        button1 = new JButton("Get from file");
        button2 = new JButton("Get from URL");
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
                            log.info("Error downloading URl  {}.", e.toString());
                            JOptionPane.showMessageDialog(null, "Error downloading", "InfoBox: " + "Error", JOptionPane.INFORMATION_MESSAGE);
//                            result = "Error downloading URL " + url;
                        }
                        return null;
                    }
                }.execute();
            }
        });
        return panel;
    }

}
//TODO Swing EDT, layouts variants, error window