package com.viriminfotech.screens;

import com.viriminfotech.utilities.InternetConnectionChecker;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class SignInScreen extends JFrame {

    private String username = "";
    private String password = "";
    private JFrame jFrame;
    public SignInScreen() {
        super("V Logger");
        jFrame = this;

        // size of the main sign-in frame
        setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setIconImage(new ImageIcon("logo.png").getImage());
        this.getContentPane().setBackground(Color.white);

        JButton jButton = new JButton("Sign-in");
        jButton.setFont(new Font("", Font.BOLD, 18));
        jButton.setBounds(0, 300, 600, 50);
        jButton.setForeground(Color.white);
        jButton.setBackground(Color.decode("#b39ddb"));

        JLabel authenticating = new JLabel("Authenticating...");
        authenticating.setHorizontalAlignment(JTextField.CENTER);
        authenticating.setBounds(0, 400,   600, 100);
        authenticating.setFont(new Font("Serif", Font.PLAIN, 20));
        authenticating.setVisible(false);
        authenticating.setBackground(Color.decode("#000000"));

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jButton.setEnabled(false);
                jButton.setBackground(Color.lightGray);
                authenticating.setVisible(true);
                if (new InternetConnectionChecker().isInternetAvailable()) {
                    MainScreen mainScreen = new MainScreen();
                    setVisible(false);
                    dispose();
                }else {
                    authenticating.setText("Please check your internet connection.");
                    jButton.setEnabled(true);
                    jButton.setBackground(Color.decode("#b39ddb"));
                }
            }
        });


        JLabel jLabel = new JLabel("Welcome");
        jLabel.setBounds(230, 50,   200, 50);
        jLabel.setFont(new Font("Serif", Font.PLAIN, 40));

        JTextField jUsername = new JTextField("Username");
        jUsername.setForeground(Color.GRAY);
        jUsername.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                warn();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                warn();
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                warn();
            }

            public void warn() {
                if (jUsername.getText().length() != 0) {
                    System.out.println(jUsername.getText().toString());
                    username = jUsername.getText();
                }
            }
        });
        jUsername.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jUsername.getText().equals("Username")) {
                    jUsername.setText("");
                    jUsername.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (jUsername.getText().isEmpty()) {
                    jUsername.setForeground(Color.GRAY);
                    jUsername.setText("Username");
                }
            }
        });
        jUsername.setHorizontalAlignment(JTextField.CENTER);
        jUsername.setBounds(0, 200, 600, 50);

        JTextField jPassword = new JTextField("Password");
        jPassword.setForeground(Color.GRAY);

        jPassword.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                warn();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                warn();
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                warn();
            }

            public void warn() {
                if (jPassword.getText().length() != 0) {
                    System.out.println(jPassword.getText().toString());
                    password = jPassword.getText();
                    if(!username.isEmpty() && !password.isEmpty()) {
                        jButton.setBackground(Color.decode("#311b92"));
                    }
                }
            }
        });

        jPassword.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jPassword.getText().equals("Password")) {
                    jPassword.setText("");
                    jPassword.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (jPassword.getText().isEmpty()) {
                    jPassword.setForeground(Color.GRAY);
                    jPassword.setText("Password");
                }
            }
        });
        jPassword.setHorizontalAlignment(JTextField.CENTER);
        jPassword.setBounds(0, 245, 600, 50);

        this.add(jUsername);
        this.add(jPassword);
        this.add(jButton);
        this.add(jLabel);
        this.add(authenticating);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
