package org.taskmanager;

import javax.swing.*;
import java.util.Random;

public class AddTaskWindow extends Popup {

    public AddTaskWindow() {
        frame = new JFrame();
        panel = new JPanel();
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
                        int id = TaskDisplay.id;
                        TaskDisplay.taskList.get(TaskDisplay.numTasks).setId(id);
                        TaskDisplay.taskList.get(TaskDisplay.numTasks).setDescription(desc);
                        TaskDisplay.taskList.get(TaskDisplay.numTasks).setDueDate(due);
                        TaskDisplay.taskList.get(TaskDisplay.numTasks).setDifficulty(diff);
                        TaskDisplay.taskList.get(TaskDisplay.numTasks).setDescLabel();
                        TaskDisplay.taskList.get(TaskDisplay.numTasks).setDateLabel();
                        TaskDisplay.taskList.get(TaskDisplay.numTasks).setDiffLabel();
                        GUI.addTaskToGUI(TaskDisplay.taskList.get(TaskDisplay.numTasks));
                        TaskDisplay.numTasks++;
                        TaskDisplay.id++;
                        frame.dispose();
                        //Add to Mongo
                        Database DB = new Database();
                        DB.sendData(desc, due.toString(), diff, id);
                    }
                }
            }

        });

    }

    @Override
    protected void setOldValues() {
    }
}
