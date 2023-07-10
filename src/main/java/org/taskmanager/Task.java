package org.taskmanager;

import javax.swing.*;
import java.awt.*;

public class Task {

    private JPanel p;
    public JPanel spacer;
    final int lineLength = 4;

    private String description;
    private DueDate dueDate;
    private Difficulty difficulty;

    private JLabel descLabel;
    private JLabel dateLabel;
    private JLabel diffLabel;

    private JButton modify, delete;
    private JCheckBox complete;

    boolean isFilledOut;

    private int id;

    private DueDate dateCompleted;

    public Task(){

        p = new JPanel();
        spacer = new JPanel();
        spacer.setPreferredSize(new Dimension(25,25));
        isFilledOut = false;

    }

    public Task(String description, DueDate date, Difficulty diff, int id) {

        p = new JPanel();
        spacer = new JPanel();
        spacer.setPreferredSize(new Dimension(5,25));

        this.description = description;
        this.dueDate = date;
        this.difficulty = diff;

        this.id = id;

        descLabel = new JLabel(description);
        dateLabel = new JLabel(dueDate.toString());
        diffLabel = new JLabel(difficulty.name());

        isFilledOut = true;

    }
    public String getDescription() {
        return description;
    }

    public DueDate getDueDate() {
        return dueDate;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getId() { return id; }

    public JLabel getDescLabel() {
        descLabel.setPreferredSize(new Dimension(75,25));
        descLabel.setToolTipText(getDescription());
        return descLabel;
    }

    public JLabel getDateLabel() {
        dateLabel.setPreferredSize(new Dimension(130,25));
        return dateLabel;
    }

    public JLabel getDiffLabel() {
        diffLabel.setPreferredSize(new Dimension(50,25));
        return diffLabel;
    }

    public JPanel getPanel(){ return p; }

    public JButton addModifyButton() {
        modify = new JButton("Change");
        modify.addActionListener(e -> {
            new ModifyTaskWindow(this);
        });
        return modify;
    }

    public JButton addDeleteButton() {
        delete = new JButton("-");
        delete.addActionListener(e -> {
                    new DeleteTaskWindow(this);
                }
        );
        return delete;
    }

    public JCheckBox addCompleteCheck() {
        complete = new JCheckBox();
        complete.addActionListener(e -> {
                    if (complete.isSelected()) {
                        JFrame frame = new JFrame();
                        int n = JOptionPane.showConfirmDialog(
                                frame,
                                "Mark this task as complete?",
                                "Task completion confirmation",
                                JOptionPane.YES_NO_OPTION);
                        if (n == JOptionPane.YES_OPTION) {
                            DueDate currentDay = new DueDate().getCurrentDay();
                            this.setDateCompleted(currentDay);
                            Database db = new Database();
                            db.updatePoints(this);
                            db.deleteTask(this, true);
                            GUI.setScoreLabel();
                            GUI.removeTaskFromGUI(this);
                        } else if (n == JOptionPane.NO_OPTION) {
                            complete.setSelected(false);
                            frame.dispose();
                        }
                    }
                }
        );
        return complete;
    }

    public void setDateAndDifficulty(DueDate date, Difficulty difficulty) {

    }
    public JButton getModifyButton() {
        return modify;
    }
    public JButton getDeleteButton() {
        return delete;
    }
    public JCheckBox getCompleteCheck() {
        return complete;
    }

    public DueDate getDateCompleted() { return dateCompleted; }
    public void decrementId () {
        this.id--;
    }

    public void setId(){
        this.id = TaskDisplay.getId();
    }

    public void setDescription(String desc){
        this.description = desc;
    }

    public void setDueDate(DueDate date){
        this.dueDate = date;
    }

    public void setDateCompleted(DueDate completed) {
        this.dateCompleted = completed;
    }

    public void setDifficulty(Difficulty diff) {
        this.difficulty = diff;
    }

    public void setDescLabel(){
        this.descLabel = new JLabel(description);
    }

    public void setDateLabel() {
        this.dateLabel = new JLabel(dueDate.toString());
    }

    public void setDiffLabel() {
        this.diffLabel = new JLabel(difficulty.name());
    }

}
