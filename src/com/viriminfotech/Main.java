package com.viriminfotech;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Main main = new Main();
        main.createWindowWithButton();
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
            jButton.setText("x: " +  MouseInfo.getPointerInfo().getLocation().getX() + " y: " +  MouseInfo.getPointerInfo().getLocation().getY());

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


}

