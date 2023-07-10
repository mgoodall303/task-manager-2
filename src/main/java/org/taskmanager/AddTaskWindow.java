package org.taskmanager;

import javax.swing.*;
public class AddTaskWindow extends Popup {

    public AddTaskWindow() {
        frame = new JFrame();
        panel = new JPanel();
        this.submitString = "Add Task";
    }

    private void openErrorFrame() {
        JFrame errFrame = new JFrame();
        JOptionPane.showMessageDialog(errFrame, "Invalid input");
    }
@Override
    protected void submitListener() {

        submit.addActionListener(e -> {

            String desc = taskDescriptionText.getText();
            String yrText = yearText.getText();
            String dText = dayText.getText();
            Months m = (Months) monthSelector.getSelectedItem();
            if (desc.isEmpty() || yrText.isEmpty() || dText.isEmpty()) {
                openErrorFrame();
            } else {
                DueDate due = new DueDate(yrText, m, dText);
                String regex = "^\\d+";
                if (due.hasPassed() || !dText.matches(regex) || !yrText.matches(regex)) {
                    openErrorFrame();
                } else {
                    int day = Integer.parseInt(dText);
                    if (day > m.getDays()) {
                        openErrorFrame();
                    } else {
                        Difficulty diff = (Difficulty) difficultySelector.getSelectedItem();
                        int n = TaskDisplay.numTasks;
                        TaskDisplay.taskList.get(n).setId();
                        TaskDisplay.taskList.get(n).setDescription(desc);
                        TaskDisplay.taskList.get(n).setDueDate(due);
                        TaskDisplay.taskList.get(n).setDifficulty(diff);
                        TaskDisplay.taskList.get(n).setDescLabel();
                        TaskDisplay.taskList.get(n).setDateLabel();
                        TaskDisplay.taskList.get(n).setDiffLabel();
                        GUI.addTaskToGUI(TaskDisplay.taskList.get(n));
                        TaskDisplay.numTasks++;
                        frame.dispose();
                        //Add to Mongo
                        Database DB = new Database();
                        DB.sendData(desc, due.toString(), diff, TaskDisplay.getId());
                    }
                }
            }

        });

    }

    @Override
    protected void setOldValues() {
    }
}
