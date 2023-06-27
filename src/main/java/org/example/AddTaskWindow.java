package org.example;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class AddTaskWindow extends Frames {

    JLabel taskDescriptionLabel;
    JTextArea taskDescriptionText;
    JLabel dayLabel;
    JTextArea dayText;

    JLabel monthLabel;
    JComboBox<Months> monthSelector;

    JLabel yearLabel;
    JTextArea yearText;

    JLabel difficultyLabel;
    JComboBox<Difficulty> difficultySelector;

    JButton submit;

    JFrame frame;

    JPanel panel;

    public AddTaskWindow() {

        frame = new JFrame();

        panel = new JPanel();

    }

    public void createTaskWindow() {
        panel.setLayout(new BoxLayout (panel, BoxLayout.Y_AXIS));

        JPanel top = new JPanel();
        top.setLayout(new BoxLayout (top, BoxLayout.Y_AXIS));
        JPanel middle = new JPanel();
        middle.setLayout(new BoxLayout (middle, BoxLayout.Y_AXIS));
        JPanel bottom = new JPanel();

        taskDescriptionLabel = new JLabel("Task description: ");
        taskDescriptionText = new JTextArea(20, 20);

        top.add(taskDescriptionLabel);
        top.add(taskDescriptionText);
        top.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel datePanel = new JPanel();
        datePanel.setLayout(new GridLayout(0,3));

        JLabel dueDateLabel = new JLabel("Due Date (optional)");
        dueDateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dayLabel = new JLabel("Day: ");
        dayText = new JTextArea(1, 2);

        monthLabel = new JLabel("Month: ");
        monthSelector = new JComboBox<>(Months.values());

        yearLabel = new JLabel("Year: ");
        yearText = new JTextArea("2023", 1, 3);


        datePanel.add(monthLabel);
        datePanel.add(dayLabel);
        datePanel.add(yearLabel);
        datePanel.add(monthSelector);
        datePanel.add(dayText);
        datePanel.add(yearText);

        difficultyLabel = new JLabel("Difficulty");
        difficultySelector = new JComboBox<>(Difficulty.values());

        bottom.add(difficultyLabel);
        bottom.add(difficultySelector);

        submit = new JButton("Add Task");
        submit.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitListener();

        panel.add(top);
        panel.add(dueDateLabel);
        panel.add(datePanel);
        panel.add(bottom);
        panel.add(submit);

        frame.getContentPane().add(panel);

        frame.setSize(300,320);

        frame.setVisible(true);
    }

    private void submitListener() {

        submit.addActionListener(e -> {

            String desc = taskDescriptionText.getText();
            DueDate due = new DueDate(yearText.getText(), (Months) monthSelector.getSelectedItem(), dayText.getText());
            Difficulty diff = (Difficulty) difficultySelector.getSelectedItem();

            Task t = new Task(desc, due, diff, TaskPanel.taskList.size());
            TaskPanel.addTaskToList(t);
            GUI.updateGUI();

            frame.dispose();
        });

    }

}
