package com.viriminfotech.screens;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MainScreen extends JFrame implements ListSelectionListener {

    private JButton jButton;
    private JLabel startButton, taskLabel;
    private JDialog taskDialog;
    private ArrayList<String> myList;
    private JList list;

    public MainScreen() {
        super("V Logger");

        // size of the main sign-in frame
        setSize(600, 600);
        this.setResizable(false);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.white);
        this.setLocationRelativeTo(null);

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

//        JLayeredPane jLayeredPane = getLayeredPane();
//
//        JPanel jPanelBottom = new JPanel();
//
        JPanel jPanelTop = new JPanel(new BorderLayout());
        myList = new ArrayList<>();
        for (int index = 0; index < 1000; index++) {
            myList.add("List Item " + index);
        }
        list = new JList<String>(myList.toArray(new String[myList.size()]));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(list);
        list.setLayoutOrientation(JList.VERTICAL);
        jPanelTop.add(scrollPane);



        taskDialog = new JDialog(this , "Dialog Example", true);
        taskDialog.setLocationRelativeTo(null);
        taskDialog.add(jPanelTop);
        taskDialog.setSize(600,600);
        taskDialog.setVisible(false);


        taskLabel = new JLabel("Click here to select project");
        taskLabel.setVerticalAlignment(JLabel.CENTER);
        taskLabel.setHorizontalAlignment(JLabel.CENTER);
        taskLabel.setBounds(0, 50,   600, 50);
        taskLabel.setFont(new Font("Serif", Font.BOLD, 16));
        taskLabel.setForeground(Color.red);
        taskLabel.setBackground(Color.GRAY);

        JLabel tasking = new JLabel("Click here to select project");
        tasking.setVerticalAlignment(JLabel.CENTER);
        tasking.setHorizontalAlignment(JLabel.CENTER);
        tasking.setBounds(0, 110,   600, 50);
        tasking.setFont(new Font("Serif", Font.BOLD, 16));
        tasking.setForeground(Color.red);
        tasking.setBackground(Color.GRAY);

        taskLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                taskDialog.setVisible(true);
            }
        });



//        JLabel task = new JLabel("Task");
//        jLabel.setHorizontalAlignment(JLabel.CENTER);
//        jLabel.setBounds(0, 120,   600, 50);
//        task.setForeground(Color.black);

        JButton jButton = new JButton("Start Timer");
        jButton.setFont(new Font("", Font.BOLD, 18));
        jButton.setBounds(0, 150, 600, 50);
        jButton.setForeground(Color.white);
        jButton.setBackground(Color.decode("#3d5afe"));

        this.add(taskLabel);
        this.add(new JSeparator());
        this.add(jButton);
        this.add(new JSeparator());
        this.setVisible(true);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            int index = list.getSelectedIndex();
            if (list.getSelectedIndex() == -1) {

            } else {
                taskDialog.setVisible(false);
                System.out.println(myList.get(index));
                taskLabel.setText(myList.get(index));
            }
        }
    }
}
