package org.taskmanager;

import javax.swing.*;


public class AddTaskWindow extends Popup {

    public AddTaskWindow() {
        frame = new JFrame();
        panel = new JPanel();
    }

@Override
    protected void submitListener() {

        submit.addActionListener(e -> {

            String desc = taskDescriptionText.getText();
            DueDate due = new DueDate(yearText.getText(), (Months) monthSelector.getSelectedItem(), dayText.getText());
            Difficulty diff = (Difficulty) difficultySelector.getSelectedItem();

            TaskDisplay.taskList.get(TaskDisplay.numTasks).setDescription(desc);
            TaskDisplay.taskList.get(TaskDisplay.numTasks).setDueDate(due);
            TaskDisplay.taskList.get(TaskDisplay.numTasks).setDifficulty(diff);
            TaskDisplay.taskList.get(TaskDisplay.numTasks).setDescLabel();
            TaskDisplay.taskList.get(TaskDisplay.numTasks).setDateLabel();
            TaskDisplay.taskList.get(TaskDisplay.numTasks).setDiffLabel();
            GUI.addTaskToGUI(TaskDisplay.taskList.get(TaskDisplay.numTasks));
            TaskDisplay.numTasks++;
            frame.dispose();
        });

    }

    @Override
    protected void setDescText() {
    }
}
