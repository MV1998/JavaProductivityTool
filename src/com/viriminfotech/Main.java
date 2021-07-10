package com.viriminfotech;

import com.viriminfotech.screens.SignInScreen;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
	// write your code here
//        JPanel panel = new JPanel(new BorderLayout());
//        List<String> myList = new ArrayList<>(10);
//        for (int index = 0; index < 20; index++) {
//            myList.add("List Item " + index);
//        }
//        final JList<String> list = new JList<String>(myList.toArray(new String[myList.size()]));
//        JScrollPane scrollPane = new JScrollPane();
//        scrollPane.setViewportView(list);
//        list.setLayoutOrientation(JList.VERTICAL);
//        panel.add(scrollPane);
//        JFrame frame = new JFrame("Demo");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.add(panel);
//        frame.setSize(500, 250);
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);

       SignInScreen signInScreen = new SignInScreen();

        try {
            String line;
            Process p = Runtime.getRuntime().exec
                    (System.getenv("windir") +"\\system32\\"+"tasklist.exe");
            BufferedReader input =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                System.out.println(line); //<-- Parse data here.
            }
            input.close();
        } catch (Exception err) {
            err.printStackTrace();
        }

        ProcessHandle.allProcesses()
                .forEach(process -> System.out.println(processDetails(process)));


//        JPanel panel = createPanel();
//        JFrame frame = createFrame();
//        frame.add(panel);
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
    }

    private static String processDetails(ProcessHandle process) {
        return String.format("%8d %8s %10s %26s %-40s",
                process.pid(),
                text(process.parent().map(ProcessHandle::pid)),
                text(process.info().user()),
                text(process.info().startInstant()),
                text(process.info().commandLine()));
    }

    private static String text(Optional<?> optional) {
        return optional.map(Object::toString).orElse("-");
    }

    private void createWindowWithButton() {
        JFrame f=new JFrame();//creating instance of JFrame

        JButton b=new JButton("click");//creating instance of JButton
        b.setBounds(130,100,100, 40);//x axis, y axis, width, height

        f.add(b);//adding button in JFrame

        f.setSize(400,500);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                captureImage(b);
            }
        });
    }

    private void captureImage(JButton jButton) {
        try {
            Robot robot = new Robot();
            System.out.println("X = : " + MouseInfo.getPointerInfo().getLocation().getX() + " Y : = " + MouseInfo.getPointerInfo().getLocation().getY());
            jButton.setText("x: " + MouseInfo.getPointerInfo().getLocation().getX() + " y: " + MouseInfo.getPointerInfo().getLocation().getY());

            robot.setAutoWaitForIdle(true);

            Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage bufferedImage = robot.createScreenCapture(rectangle);
            File file = new File("D:\\screen-capture.png");
            boolean status = ImageIO.write(bufferedImage, "png", file);
            System.out.println("Screen Captured ? " + status + " File Path:- " + file.getAbsolutePath());

        } catch (AWTException | IOException ex) {
            System.err.println(ex);
        }
    }

    private static JPanel createPanel() {
        JPanel mainPanel = new JPanel(){
            @Override
            public boolean isOptimizedDrawingEnabled() {
                return false;
            }
        };
        mainPanel.setLayout(new OverlayLayout(mainPanel));

        JButton button = new JButton("Show Message");
        button.setAlignmentX(0.5f);
        button.setAlignmentY(0.5f);

        JPanel popupPanel = createPopupPanel(button);
        popupPanel.setAlignmentX(0.1f);
        popupPanel.setAlignmentY(0.1f);

        button.addActionListener(e -> {
            button.setEnabled(false);
            popupPanel.setVisible(true);
        });

        mainPanel.add(popupPanel);
        mainPanel.add(button);

        return mainPanel;
    }

    private static JPanel createPopupPanel(JComponent overlapComponent) {
        JPanel popupPanel = new JPanel(new BorderLayout());
        popupPanel.setOpaque(false);
        popupPanel.setMaximumSize(new Dimension(400, 300));
        popupPanel.setBorder(new LineBorder(Color.gray));
        popupPanel.setVisible(false);

        JLabel label = new JLabel("HI there!");
        popupPanel.add(wrapInPanel(label), BorderLayout.CENTER);

        JButton popupCloseButton = new JButton("Close");
        popupPanel.add(wrapInPanel(popupCloseButton), BorderLayout.SOUTH);

        popupCloseButton.addActionListener(e -> {
            overlapComponent.setEnabled(true);
            popupPanel.setVisible(false);
        });

        return popupPanel;
    }

    private static JPanel wrapInPanel(JComponent component) {
        JPanel jPanel = new JPanel();
        jPanel.setBackground(new Color(50, 210, 250, 150));
        jPanel.add(component);
        return jPanel;
    }


    private static JFrame createFrame() {
        JFrame frame = new JFrame("OverlayLayout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(400, 300));
        return frame;
    }
}

