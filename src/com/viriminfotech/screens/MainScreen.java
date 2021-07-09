package com.viriminfotech.screens;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class MainScreen extends JFrame implements ListSelectionListener {

    public MainScreen() {
        super("Virim Logger");


        // size of the main sign-in frame
        setSize(600, 600);
        this.setResizable(false);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.white);
        this.setLocationRelativeTo(null);


        DefaultListModel listModel = new DefaultListModel();
        listModel.addElement("Jane Doe");
        listModel.addElement("John Smith");
        listModel.addElement("Kathy Green");

        int bigNumber = 30001;
        for (int ii=0; ii<bigNumber; ii++) {
            listModel.addElement("String " + (ii+1));
        }
        JList list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        JScrollPane jScrollPane = new JScrollPane(list);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setSize(600, 600);


        this.add(jScrollPane);
        this.setVisible(true);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            System.out.println("You have selected something, but i don't know!!!");
//            if (list.getSelectedIndex() == -1) {
//                //No selection, disable fire button.
//                fireButton.setEnabled(false);
//
//            } else {
//                //Selection, enable the fire button.
//                fireButton.setEnabled(true);
//            }
        }
    }
}
