package com.viriminfotech;

import com.github.sarxos.webcam.Webcam;
import com.viriminfotech.screens.SignInScreen;
import com.viriminfotech.utilities.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
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


        // webcam camera accessing and taking picture
        try {

//           Class.forName("org.hsqldb.jdbc.JDBCDriver");
//           Connection connection = DriverManager.getConnection("jdbc:hsqldb:mem:myDb;shutdown=true", "sa", "");
//           BasicConfigurator.configure();
//
//           Statement statement = connection.createStatement();
//           statement.executeQuery("CREATE TABLE tutorials_tbl ( id INT NOT NULL, title VARCHAR(50) NOT NULL, author VARCHAR(20) NOT NULL, submission_date DATE, PRIMARY KEY (id)); ");
//
//
//           statement.executeQuery("INSERT INTO tutorials_tbl VALUES (100,'Learn PHP', 'John Poul', NOW())");
//           connection.commit();
//
//
//           ResultSet resul1 = statement.executeQuery("SELECT id, title, author FROM tutorials_tbl");
//           //System.out.println("rows affected " + result);
//           while(resul1.next()){
//               System.out.println(resul1.getInt("id")+" | "+
//                       resul1.getString("title")+" | "+
//                       resul1.getString("author"));
//           }
//           connection.prepareStatement("SHUTDOWN").execute();
//           statement.close();
//           connection.close();

//           Class.forName("org.sqlite.JDBC");
//           Connection c = DriverManager.getConnection("jdbc:sqlite:test.db");
//           System.out.println("Opened database successfully");
//

            //Statement stmt = c.createStatement();
//           String sql = "CREATE TABLE COMPANY " +
//                   "(ID INT PRIMARY KEY     NOT NULL," +
//                   " NAME           TEXT    NOT NULL, " +
//                   " AGE            INT     NOT NULL, " +
//                   " ADDRESS        CHAR(50), " +
//                   " SALARY         REAL)";
//           stmt.executeUpdate(sql);
//
//           String st = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
//                   "VALUES (1, 'Paul', 32, 'California', 20000.00 );";
//           stmt.executeUpdate(st);


//           ResultSet resultSet =  stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='COMPANY';");
//           System.out.println(resultSet.getRow());
//           System.out.println(resultSet.next());
//
//           ResultSet resultSet1 =  stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='USERDATA';");
//           System.out.println(resultSet1.getRow());
//           System.out.println(resultSet1.next());
//
//
//           ResultSet resultSet2 = stmt.executeQuery("DROP TABLE COMPANY;");

//           ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
//
//           while ( rs.next() ) {
//               int id = rs.getInt("id");
//               String  name = rs.getString("name");
//               int age  = rs.getInt("age");
//               String  address = rs.getString("address");
//               float salary = rs.getFloat("salary");
//
//               System.out.println( "ID = " + id );
//               System.out.println( "NAME = " + name );
//               System.out.println( "AGE = " + age );
//               System.out.println( "ADDRESS = " + address );
//               System.out.println( "SALARY = " + salary );
//               System.out.println();
//           }
//           rs.close();
//
//           stmt.close();
//           c.close();

            Webcam webcam = Webcam.getDefault();
            if (webcam != null) {
                System.out.println("name"+ webcam.getName());

                webcam.open();

                // get image
                BufferedImage image = webcam.getImage();

                BufferedImage newImage = Constants.resize(image, 500, 500);

                // save image to PNG file
                ImageIO.write(newImage, "PNG", new File("D:\\test.png"));
                webcam.close();
            }else {
                System.out.println("no webcam detected.");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }


        // listing running process of particular system
        if (Constants.IS_WINDOWS) {
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
        }else if (Constants.IS_UNIX) {
            System.out.println("you are on linux");

            try {
                String line;
                Process p = Runtime.getRuntime().exec("ps -e --sort=-size");
                BufferedReader input =
                        new BufferedReader(new InputStreamReader(p.getInputStream()));
                while ((line = input.readLine()) != null) {
                    System.out.println(line); //<-- Parse data here.
                }
                input.close();

            }catch(Exception e) {
                e.printStackTrace();
            }

        }else if(Constants.IS_MAC) {
            System.out.println("You are on mac");

            try {
                String line;
                Process p = Runtime.getRuntime().exec("ps -e --sort=-size");
                BufferedReader input =
                        new BufferedReader(new InputStreamReader(p.getInputStream()));
                while ((line = input.readLine()) != null) {
                    System.out.println(line); //<-- Parse data here.
                }
                input.close();



            }catch(Exception e) {
                e.printStackTrace();
            }
        }




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

    public static String getUrl(){
        try {
            Thread.sleep(3000);//
            Robot r=new Robot();
            r.keyPress(KeyEvent.VK_ALT); /* to get focus on taskbar
   r.keyPress(KeyEvent.VK_D);       */
            r.keyRelease(KeyEvent.VK_ALT);
            r.keyRelease(KeyEvent.VK_D);
            r.keyPress(KeyEvent.VK_CONTROL);  /* to copy it*/
            r.keyPress(KeyEvent.VK_C);
            r.keyRelease(KeyEvent.VK_CONTROL);
            r.keyRelease(KeyEvent.VK_C);
            String selectedText =(String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor); // it extracts the highlighted text of system clipboard
            System.out.println(selectedText);
            return selectedText;
        }catch ( Exception e) {
            e.printStackTrace();
        }
        return null;
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

