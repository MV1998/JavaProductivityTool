package com.viriminfotech.screens;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TaskScreen extends JFrame implements ListSelectionListener {

    public TaskScreen() {
        super("Virim Logger");


        // size of the main sign-in frame
        setSize(600, 600);
        this.setResizable(false);
//        this.setLayout(null);
        this.getContentPane().setBackground(Color.white);
        this.setLocationRelativeTo(null);
  //      this.setUndecorated(true);

//
//        DefaultListModel listModel = new DefaultListModel();
//        listModel.addElement("Jane Doe");
//        listModel.addElement("John Smith");
//        listModel.addElement("Kathy Green");
//
//        int bigNumber = 30001;
//        for (int ii=0; ii<bigNumber; ii++) {
//            listModel.addElement("String " + (ii+1));
//        }
//        JList list = new JList(listModel);
//        list.setLayoutOrientation(JList.VERTICAL);
//        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        list.setSelectedIndex(0);
//        list.addListSelectionListener(this);
//        JScrollPane jScrollPane = new JScrollPane();
//        jScrollPane.setViewportView(list);

        JPanel jPanelTop = new JPanel(new BorderLayout());
        List<String> myList = new ArrayList<>();
        for (int index = 0; index < 1000; index++) {
            myList.add("List Item " + index);
        }
        final JList<String> list = new JList<String>(myList.toArray(new String[myList.size()]));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(list);
        list.setLayoutOrientation(JList.VERTICAL);
        jPanelTop.add(scrollPane);


        this.add(jPanelTop);
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
