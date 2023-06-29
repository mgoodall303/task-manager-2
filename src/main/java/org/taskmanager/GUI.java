package org.taskmanager;

import java.awt.*;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.util.ArrayList;
import java.util.List;

public class GUI extends Frames {
    final String nameOfApp = "Task Manager";
    final int maxTasks = 15;
    static JFrame frame;

    static JPanel taskPanel; // holds the tasks
    JScrollPane taskScroll; // to scroll through tasks
    JButton addTask;
    JLabel currentScore;   // displays the current score in points
    JButton viewCompletedTasks;
    JButton viewLeaderboard;

    static List<JPanel> panelList;

    public GUI() {
        frame = new JFrame();
        taskPanel = new JPanel();
        panelList = new ArrayList<>();
    }

    public void createGUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1));

        Box verticalBox = Box.createVerticalBox();

        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
        taskScroll = new JScrollPane(taskPanel);
        taskPanel.setAutoscrolls(true);
        taskScroll.setPreferredSize(new Dimension(350, 500));
        taskPanel.setPreferredSize(new Dimension(350, 550));

        // Set up the top UI
        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(325,100));
        topPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel appTitle = new JLabel(nameOfApp);
        addTask = new JButton("+ New Task");
        JLabel yourScore = new JLabel("Your Score: ");
        currentScore = new JLabel("0");  // 0 for now as placeholder
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        topPanel.add(appTitle, gbc);
        gbc.weightx = 2;
        gbc.gridy = 0;
        gbc.gridx = 2;
         topPanel.add(yourScore, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.gridy = 1;
        gbc.gridx = 0;
        topPanel.add(addTask, gbc);
         gbc.weightx = 2;
        gbc.gridx = 2;
        gbc.gridy = 1;
        topPanel.add(currentScore, gbc);

        JPanel bottomPanel = new JPanel();
        viewCompletedTasks = new JButton("View Completed Tasks");
        viewLeaderboard = new JButton("View Score Leaderboard");
        bottomPanel.add(viewCompletedTasks);
        bottomPanel.add(viewLeaderboard);

        verticalBox.add(topPanel);
        verticalBox.add(taskScroll);
        verticalBox.add(bottomPanel);
        mainPanel.add(verticalBox);

        addTask.addActionListener(e -> {
            AddTaskWindow atw = new AddTaskWindow();
            atw.createTaskWindow();
        });

        populateTaskPanel();

        frame.getContentPane().add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,750);
        frame.setVisible(true);
    }

    private void populateTaskPanel() {
        for (int i = 0; i < maxTasks; i++){
            Task newTask = new Task();
            newTask.setId(i);
            TaskDisplay.taskList.add(newTask);
            JPanel p = new JPanel();
            panelList.add(p);
        }
        for (Task t : TaskDisplay.taskList){
            taskPanel.add(t.getPanel());
        }
    }
    protected static void addTaskToGUI(Task t){
        TaskDisplay.addLabelsToPanel(t);
        t.getPanel().validate();
        t.getPanel().repaint();
    }

    protected static void removeTaskFromGUI(Task t) {
        t.setDescription("");
        t.setDueDate(null);
        t.setDifficulty(null);
        taskPanel.remove(t.getPanel());
        taskPanel.validate();
        taskPanel.repaint();
    }

}
