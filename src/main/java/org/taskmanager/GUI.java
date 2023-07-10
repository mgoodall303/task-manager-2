package org.taskmanager;

import java.awt.*;

import javax.swing.*;
import java.util.LinkedList;

public class GUI extends Frames {
    final String nameOfApp = "Task Manager";
    final int maxTasks = 15;
    static JFrame frame;

    static JPanel taskPanel; // holds the tasks
    JScrollPane taskScroll; // to scroll through tasks
    JButton addTask;
    static JLabel currentScore;   // displays the current score in points
    JButton viewCompletedTasks;
    JButton viewLeaderboard;
    static JTable table;  // hold return from Mongo

    public GUI() {
        frame = new JFrame();
        taskPanel = new JPanel();
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
        JPanel spacer = new JPanel();
        spacer.setPreferredSize(new Dimension(175,50));
        JLabel yourScore = new JLabel("Your Score: ");
        currentScore = new JLabel();
        setScoreLabel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        topPanel.add(appTitle, gbc);
        gbc.gridy = 0;
        gbc.gridx = 1;
        topPanel.add(spacer, gbc);
        gbc.weightx = 2;
        gbc.gridy = 0;
        gbc.gridx = 2;
         topPanel.add(yourScore, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.gridy = 1;
        gbc.gridx = 0;
        topPanel.add(addTask, gbc);
        gbc.gridy = 1;
        gbc.gridx = 1;
        topPanel.add(spacer, gbc);
         gbc.weightx = 2;
        gbc.gridx = 2;
        gbc.gridy = 1;
        topPanel.add(currentScore, gbc);

        JPanel bottomPanel = new JPanel();
        viewCompletedTasks = new JButton("View Completed Tasks");
        viewLeaderboard = new JButton("View Score Leaderboard");
        viewLeaderboard.setEnabled(false);
        bottomPanel.add(viewCompletedTasks);
        viewCompletedTasks.addActionListener(e -> {
            Database db = new Database();
            JTable jTable = db.displayCompletedTasks();
            JScrollPane jsp;
            JLabel noCompletedTasks;
            JFrame completedTasksFrame = new JFrame("Completed Tasks");
            if (jTable != null) {
                jsp = new JScrollPane(jTable);
                completedTasksFrame.add(jsp);
            } else {
                noCompletedTasks = new JLabel("You have not yet completed any tasks.");
                completedTasksFrame.add(noCompletedTasks);
            }
            completedTasksFrame.setSize(400,200);
            completedTasksFrame.setVisible(true);
        });
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

    // Used to update the score label
    public static void setScoreLabel() {
        Database db = new Database();
        currentScore.setText(String.valueOf(db.findPoints()));  // called from database
    }

    // Called in beginning to populate screen with JPanels
    private void populateTaskPanel() {
        Database db = new Database();
        LinkedList<Task> taskLinkedList = db.returnTasks("Tasks");  // Tasks currently in database
        if (taskLinkedList != null) {
            TaskDisplay.setTaskList(taskLinkedList);
            for (int i = taskLinkedList.size(); i < maxTasks; i++) {
                Task newTask = new Task();
                newTask.setId();
                TaskDisplay.taskList.add(newTask);
            }
        }
        for (int i = 0; i < TaskDisplay.taskList.size(); i++) {
            Task t = TaskDisplay.taskList.get(i);
            if (t.isFilledOut) {
                addTaskToGUI(t);
                TaskDisplay.numTasks++;
            }

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
        TaskDisplay.numTasks--;
        taskPanel.remove(t.getPanel());
        taskPanel.validate();
        taskPanel.repaint();
    }

}
