package com.viriminfotech.screens;

import com.viriminfotech.models.MProject;
import com.viriminfotech.utilities.Constants;
import com.viriminfotech.utilities.InternetConnectionChecker;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class MainScreen extends JFrame implements ListSelectionListener, MouseListener, ActionListener {

    private JButton startButton, logoutButton;
    private JLabel taskLabel, loggedInTimeLabel, loadingLabel, idleTimeLabel, breakTimeLabel;
    private JDialog taskDialog, idleDialog, alertDialog;
    private JRadioButton testingRadioBtn, developmentRadioBtn, meetingRadioBtn, analysisRadioBtn;
    private ArrayList<String> myList;
    private ArrayList<MProject> mProjects = new ArrayList<>();
    private JList list;
    private String task;
    private String selectedProject;
    private int selectedProjectId;
    private int timerStared = 0;

    private volatile boolean isActive, isIdle = false, isBreak = false;

    private Date firstStartTime;
    private int activeTime = 0;
    private int idle = 0;
    private int breakTime = 0;

    private java.util.Timer activeTimer, idleTimer, captureTimer;
    public MainScreen() {
        super("V Logger");
        // Main frame configuration
        setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setIconImage(new ImageIcon("download.png").getImage());
        this.getContentPane().setBackground(Color.white);

        //Select project jLabel
        taskLabel = new JLabel("Click here to select project");
        taskLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        taskLabel.setVerticalAlignment(JLabel.CENTER);
        taskLabel.setHorizontalAlignment(JLabel.CENTER);
        taskLabel.setBounds(0, 50,   600, 50);
        taskLabel.setFont(new Font("Serif", Font.BOLD, 16));
        taskLabel.setForeground(Color.red);
        taskLabel.setBackground(Color.GRAY);
        taskLabel.addMouseListener(this);

        // Project dialog configuration
        taskDialog = new JDialog(this , "Projects", true);
        taskDialog.setLocation(500, 100);
        taskDialog.add(createProjectDialog());
        taskDialog.setSize(600,600);
        taskDialog.setResizable(false);
        taskDialog.setVisible(false);

        idleDialog = new JDialog(this , "Idle Detected", true);
        idleDialog.setLocationRelativeTo(null);
        idleDialog.add(idleDialogBox());
        idleDialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        idleDialog.setSize(300, 100);
        idleDialog.setResizable(false);
        idleDialog.setVisible(false);

        alertDialog = new JDialog(this , "Alert", true);
        alertDialog.setLocationRelativeTo(null);
        alertDialog.add(alertDialogBox());
        alertDialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        alertDialog.setSize(300,100);
        alertDialog.setResizable(false);
        alertDialog.setVisible(false);

        //radio button
        radioButtonConfiguration();


        startButton = new JButton("Start Timer");
        startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        startButton.setActionCommand("5");
        startButton.setBorder(null);
        startButton.addActionListener(this);
        startButton.setFont(new Font("Serif", Font.BOLD, 18));
        startButton.setBounds(0, 290, 600, 50);
        startButton.setForeground(Color.white);
        startButton.setBackground(Color.decode("#3d5afe"));


        loggedInTimeLabel = new JLabel("Active Time : 0h 0m 0s");
        loggedInTimeLabel.setVerticalAlignment(JLabel.CENTER);
        loggedInTimeLabel.setHorizontalAlignment(JLabel.CENTER);
        loggedInTimeLabel.setBounds(0, 350,   600, 50);
        loggedInTimeLabel.setFont(new Font("Serif", Font.BOLD, 16));
        loggedInTimeLabel.setForeground(Color.red);
        loggedInTimeLabel.setBackground(Color.GRAY);

        idleTimeLabel = new JLabel("Idle Time : 0h 0m 0s");
        idleTimeLabel.setVerticalAlignment(JLabel.CENTER);
        idleTimeLabel.setHorizontalAlignment(JLabel.CENTER);
        idleTimeLabel.setBounds(0, 380,   600, 50);
        idleTimeLabel.setFont(new Font("Serif", Font.BOLD, 16));
        idleTimeLabel.setForeground(Color.red);
        idleTimeLabel.setBackground(Color.GRAY);


        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());
        jPanel.setBounds(0, 510, 600, 50);
        logoutButton = new JButton("Logout");
        logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutButton.setActionCommand("6");
        logoutButton.addActionListener(this);
        logoutButton.setFont(new Font("Serif", Font.BOLD, 18));
        logoutButton.setBackground(Color.decode("#3d5afe"));
        logoutButton.setForeground(Color.white);
        jPanel.add(logoutButton);
        jPanel.setBackground(Color.white);


        this.add(loggedInTimeLabel);
        this.add(taskLabel);
        this.add(meetingRadioBtn);
        this.add(developmentRadioBtn);
        this.add(testingRadioBtn);
        this.add(analysisRadioBtn);
        this.add(startButton);
        //  this.add(logoutButton);
        this.add(idleTimeLabel);
        this.add(jPanel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    // Defined the project dialog which is responsible to show project list
    // user can select the task from dialog
    private JPanel createProjectDialog() {
        if (new InternetConnectionChecker().isInternetAvailable()) {
//            String data = getProjectDataFromAPI();
//            System.out.println(data);
//            if (data.length() > 0) {
//                JSONObject jsonObject = new JSONObject(data);
//                JSONArray jsonArray = jsonObject.getJSONArray("data");
//                System.out.println("array length"+jsonArray.length());
//                for (int i = 0; i < jsonArray.length(); i++) {
//                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//                    MProject mProject = new MProject();
//                    mProject.setProject(jsonObject1.getString("project"));
//                    mProject.setId(jsonObject1.getInt("id"));
//                    mProjects.add(mProject);
//                }
//            }
            for (int i = 0; i < 1000 ; i++) {
                MProject mProject = new MProject();
                mProject.setProject("Click to select Project " + i);
                mProject.setId(i+0);
                mProjects.add(mProject);
            }
        }
        JPanel jPanelTop = new JPanel(new BorderLayout());
        myList = new ArrayList<>();
        for (int index = 0; index < mProjects.size(); index++) {
            myList.add(mProjects.get(index).getProject());
        }
        list = new JList<String>((myList.toArray(new String[myList.size()])));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFixedCellHeight(50);
        list.setCursor(new Cursor(Cursor.HAND_CURSOR));
        list.setCellRenderer(new ListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList jList, Object o, int i, boolean b, boolean b1) {
                return createRenderer(i);
            }
        });
        list.setBackground(Color.white);
        list.addListSelectionListener(this);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(list);
        list.setLayoutOrientation(JList.VERTICAL);
        jPanelTop.add(scrollPane);
        return jPanelTop;
    }

    private JPanel idleDialogBox() {
        JPanel jPanelTop = new JPanel();
        jPanelTop.setLayout(new BorderLayout());
        JButton b = new JButton ("Resume");
        b.addActionListener ( new ActionListener()
        {
            public void actionPerformed( ActionEvent e )
            {
                activeTimer = new Timer();
                activeTimer.scheduleAtFixedRate(new TaskTimer(), Constants.TIMER_DELAY, Constants.TIMER_REPEAT_TIME);

                idleTimer.cancel();
                isIdle = false;
                idleDialog.setVisible(false);
            }
        });

        JButton onBreak = new JButton ("On Break?");
        onBreak.addActionListener ( new ActionListener()
        {
            public void actionPerformed( ActionEvent e )
            {
                if (activeTimer != null && idleTimer != null) {
                    activeTimer.cancel();
                    idleTimer.cancel();
                }
                isIdle = false;

                //when starting break
                isBreak = true;
                isActive = false;
                timerStared = 0;
                startButton.setText("Start Timer");
                startButton.setBackground(Color.blue);

                taskLabel.setEnabled(true);
                meetingRadioBtn.setEnabled(true);
                developmentRadioBtn.setEnabled(true);
                testingRadioBtn.setEnabled(true);
                analysisRadioBtn.setEnabled(true);

                System.out.println(isIdle);
                idleDialog.setVisible(false);
            }
        });
        jPanelTop.add(b, BorderLayout.SOUTH);
        jPanelTop.add(onBreak, BorderLayout.NORTH);
        return jPanelTop;
    }


    private JPanel alertDialogBox() {
        System.out.println("alert dialog");
        JPanel jPanelTop = new JPanel(new BorderLayout());
        JButton b = new JButton ("Please select Project and Task");
        b.addActionListener ( new ActionListener()
        {
            public void actionPerformed( ActionEvent e )
            {
                alertDialog.setVisible(false);
            }
        });
        jPanelTop.add(b);
        return jPanelTop;
    }



    private void radioButtonConfiguration() {
        meetingRadioBtn = new JRadioButton("Meeting");
        meetingRadioBtn.setActionCommand("1");
        meetingRadioBtn.setBounds(250,120,100,30);
        meetingRadioBtn.setBackground(Color.white);
        meetingRadioBtn.addActionListener(this);

        developmentRadioBtn = new JRadioButton("Development");
        developmentRadioBtn.setActionCommand("2");
        developmentRadioBtn.setBounds(250,160,100,30);
        developmentRadioBtn.setBackground(Color.white);
        developmentRadioBtn.addActionListener(this);

        testingRadioBtn = new JRadioButton("Testing");
        testingRadioBtn.setActionCommand("3");
        testingRadioBtn.setBounds(250,200,100,30);
        testingRadioBtn.setBackground(Color.white);
        testingRadioBtn.addActionListener(this);

        analysisRadioBtn = new JRadioButton("Analysis And Planning");
        analysisRadioBtn.setActionCommand("4");
        analysisRadioBtn.setBounds(220,240,150,30);
        analysisRadioBtn.setBackground(Color.white);
        analysisRadioBtn.addActionListener(this);

        ButtonGroup bg = new ButtonGroup();
        bg.add(meetingRadioBtn);
        bg.add(developmentRadioBtn);
        bg.add(testingRadioBtn);
        bg.add(analysisRadioBtn);

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int index = list.getSelectedIndex();
            if (list.getSelectedIndex() != -1) {
                selectedProject = myList.get(index);
                selectedProjectId = index;
                System.out.println(selectedProject);
                taskLabel.setText(selectedProject);
                taskDialog.setVisible(false);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (timerStared == 0)
            taskDialog.setVisible(true);
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equalsIgnoreCase("5")) {
            if (selectedProject != null && task != null) {
                if (timerStared == 0) {
                    timerStared = 1;
                    isActive = true;
                    isIdle = false;
                    isBreak= false;
                    startButton.setText("Stop Timer");
                    startButton.setBackground(Color.red);
                    taskLabel.setEnabled(false);
                    meetingRadioBtn.setEnabled(false);
                    developmentRadioBtn.setEnabled(false);
                    testingRadioBtn.setEnabled(false);
                    analysisRadioBtn.setEnabled(false);
                    System.out.println("timer stared");
                    activeTimer = new java.util.Timer();
                    activeTimer.scheduleAtFixedRate(new TaskTimer(), Constants.TIMER_DELAY, Constants.TIMER_REPEAT_TIME);
                }else {
                    timerStared = 0;
                    isActive = false;
                    isIdle = false;
                    isBreak= false;
                    startButton.setText("Start Timer");
                    startButton.setBackground(Color.blue);
                    taskLabel.setEnabled(true);
                    meetingRadioBtn.setEnabled(true);
                    developmentRadioBtn.setEnabled(true);
                    testingRadioBtn.setEnabled(true);
                    analysisRadioBtn.setEnabled(true);
                    System.out.println("timer stopped");
                    activeTimer.cancel();
                }
            }else {
                alertDialog.setVisible(true);
            }
        }else if (actionCommand.equalsIgnoreCase("1")) {
            task = "Meeting";
            System.out.println(task);
        }else if (actionCommand.equalsIgnoreCase("2")) {
            task = "Development";
            System.out.println(task);
        }else if (actionCommand.equalsIgnoreCase("3")) {
            task = "Testing";
            System.out.println(task);
        }else if (actionCommand.equalsIgnoreCase("4")) {
            task = "Analysis and Planning";
            System.out.println(task);
        }else if (actionCommand.equalsIgnoreCase("6")) {
            if (activeTimer != null)
                activeTimer.cancel();
            if (idleTimer != null)
                idleTimer.cancel();

            SignInScreen mainScreen = new SignInScreen();
            setVisible(false);
            dispose();
        }
    }

    private void captureImage() {
        try {
            Robot robot = new Robot();
            System.out.println("X = : " + MouseInfo.getPointerInfo().getLocation().getX() + " Y : = " + MouseInfo.getPointerInfo().getLocation().getY());
            robot.setAutoWaitForIdle(true);
            Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage bufferedImage = robot.createScreenCapture(rectangle);

            String fileName = "VLogger_"+ System.currentTimeMillis()+".png";

            File file = new File("");
            if (Constants.IS_WINDOWS) {
                file = new File("D:\\images\\" + fileName);
            }
            boolean status = ImageIO.write(bufferedImage,"png", file);
            System.out.println("Screen Captured ? " + status + " File Path:- " + file.getAbsolutePath());

            if (!isIdle) {
                if (MouseInfo.getPointerInfo().getLocation().getX() == Constants.MOUSE_X_POSITION &&
                        MouseInfo.getPointerInfo().getLocation().getY() == Constants.MOUSE_Y_POSITION) {
                    if (!isIdle) {
                        isActive = false;
                        isIdle = true;
                        activeTimer.cancel();
                        idleTimer = new Timer();
                        idleTimer.scheduleAtFixedRate(new IdleTimer(), Constants.TIMER_DELAY, Constants.TIMER_REPEAT_TIME);
                        idleDialog.setVisible(true);
                    }
                }else {
                    Constants.MOUSE_X_POSITION = MouseInfo.getPointerInfo().getLocation().getX();
                    Constants.MOUSE_Y_POSITION = MouseInfo.getPointerInfo().getLocation().getY();
                    isActive = true;
                }
            }else {
                System.out.println("you are still idle");
            }
        } catch (AWTException | IOException ex) {
            System.err.println(ex);
        }
    }

    private String getProjectDataFromAPI() {
        String https_url = "http://192.168.45.209:4300/project";
        URL url;
        String data = "";
        try {
            url = new URL(https_url);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            data = content(con);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    private String content(HttpURLConnection con) {
        StringBuilder stringBuilder = new StringBuilder();
        if(con!=null){

            try {

                System.out.println("****** Content of the URL ********");
                BufferedReader br =
                        new BufferedReader(
                                new InputStreamReader(con.getInputStream()));

                String input;

                while ((input = br.readLine()) != null) {
                    stringBuilder.append(input);
                }
                br.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }

    private Component createRenderer(int i) {
        JButton jButton = new JButton();
        jButton.setText(mProjects.get(i).getProject());
        jButton.setBackground(Color.white);
        jButton.setForeground(Color.black);
        jButton.setHorizontalAlignment(JButton.LEFT);
        jButton.setFont(new Font("Arial", Font.BOLD, 16));
        jButton.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
        return jButton;
    }



    //Timer for active and idle time
    // Also send to required information on the server in requested time interval

    // this is responsible to run for active time to calculate
    class TaskTimer extends TimerTask {
        @Override
        public void run() {
            activeTime += TimeUnit.MILLISECONDS.toSeconds(1000);
            loggedInTimeLabel.setText("Active Time : " + Constants.formatSecondDateTime(activeTime));
            captureImage();
        }
    }

    class IdleTimer extends TimerTask {
        @Override
        public void run() {
            // activeTime += TimeUnit.MILLISECONDS.toSeconds(1000);
            idle += TimeUnit.MILLISECONDS.toSeconds(1000);
            idleTimeLabel.setText("Idle Time : " + Constants.formatSecondDateTime(idle));
        }
    }

    class CaptureTimer extends TimerTask {
        @Override
        public void run() {
            captureImage();
        }
    }

    class ServerSenderTimer extends TimerTask {
        @Override
        public void run() {

        }
    }

    class BreakTimer extends TimerTask {
        @Override
        public void run() {

        }
    }

}