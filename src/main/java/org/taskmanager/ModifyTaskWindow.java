package org.taskmanager;

import javax.swing.*;

public class ModifyTaskWindow extends Popup {
    Task t;
    public ModifyTaskWindow(Task t){
        frame = new JFrame();
        panel = new JPanel();
        this.t = t;
        System.out.println(t.getDescription());
        createTaskWindow();
    }

@Override
    protected void submitListener() {

        submit.addActionListener(e -> {
            TaskDisplay.removeLabelsFromPanels(t);
            String desc = taskDescriptionText.getText();
            DueDate due = new DueDate(yearText.getText(), (Months) monthSelector.getSelectedItem(), dayText.getText());
            Difficulty diff = (Difficulty) difficultySelector.getSelectedItem();
            t.setDescription(desc);
            t.setDescLabel();
            t.setDueDate(due);
            t.setDateLabel();
            t.setDifficulty(diff);
            t.setDiffLabel();

            GUI.addTaskToGUI(t);
            Database DB = new Database();
            DB.modTask(desc, due.toString(), diff, t.getId());
            frame.dispose();
        });

    }
    @Override
    protected void setOldValues() {
        taskDescriptionText.setText(t.getDescription());
        DueDate oldDueDate = t.getDueDate();
        String year = oldDueDate.year;
        String day = oldDueDate.day;
        dayText.setText(day);
        yearText.setText(year);
        monthSelector.setSelectedItem(oldDueDate.month);
        difficultySelector.setSelectedItem(t.getDifficulty());
    }


}
