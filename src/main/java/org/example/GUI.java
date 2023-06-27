package org.example;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GUI extends Frames {

    final String nameOfApp = "Task Manager";

    static JFrame frame;

    JPanel mainPanel;  // panel that will hold sub-panels
    Box verticalBox;
    /*
     * Set up the components that will form the task viewing area
     */
    static JPanel taskPanel; // holds the tasks
    JTextArea test;
    JScrollPane taskScroll; // to scroll through tasks


    /*
     * Set up the components that will form the top panel
     */
    JPanel topPanel;
    JLabel appTitle;  // displays name of the application
    JButton addTask;
    JLabel yourScore;
    JLabel currentScore;   // displays the current score in points

    /*
     * Set up the components that will form the bottom panel
     */
    JPanel bottomPanel;
    JButton viewCompletedTasks;
    JButton viewLeaderboard;

    public GUI() {
        frame = new JFrame();
        taskPanel = new JPanel();
        System.out.println("calling cons 2");
    }

    public void createGUI() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1));

        verticalBox = Box.createVerticalBox();


        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
        test = new JTextArea(35,20);
        taskScroll = new JScrollPane(taskPanel);
        taskPanel.setAutoscrolls(true);
        taskScroll.setPreferredSize(new Dimension(350, 500));
        taskPanel.setPreferredSize(new Dimension(350, 550));

        topPanel = new JPanel();
        topPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        appTitle = new JLabel(nameOfApp);
        addTask = new JButton("+ New Task");
        yourScore = new JLabel("Your Score: ");
        currentScore = new JLabel("0");  // 0 for now as placeholder
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.25;
        //gbc.weighty = 0.5;
        topPanel.add(appTitle, gbc);
        gbc.gridy = 1;
        //gbc.weighty = 0.25;
        gbc.weightx = 0.5;
        topPanel.add(addTask, gbc);
        //gbc.ipady = 15;
        gbc.gridy = 0;
        gbc.gridx = 1;
        topPanel.add(yourScore, gbc);
        gbc.ipady = 0;
        gbc.gridy = 1;
        topPanel.add(currentScore, gbc);

        bottomPanel = new JPanel();
        viewCompletedTasks = new JButton("View Completed Tasks");
        viewLeaderboard = new JButton("View Score Leaderboard");
        bottomPanel.add(viewCompletedTasks);
        bottomPanel.add(viewLeaderboard);

        verticalBox.add(topPanel);
        //verticalBox.add(Box.createGlue());
        verticalBox.add(taskScroll);
        //verticalBox.add(Box.createVerticalGlue());
        verticalBox.add(bottomPanel);

        mainPanel.add(verticalBox);

        addListeners();

        frame.getContentPane().add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,750);
        frame.setVisible(true);
    }

    private void addListeners() {

        addTask.addActionListener(e -> {
            AddTaskWindow atw = new AddTaskWindow();
            atw.createTaskWindow();
        });

    }

    protected static void updateGUI(){
        System.out.println(TaskPanel.taskList.size());
        for ( Task t : TaskPanel.taskList) {
            JPanel p = TaskPanel.addLabelsToPanel(t);
            taskPanel.add(p);
        }

        taskPanel.validate();
        taskPanel.repaint();
    }

}
