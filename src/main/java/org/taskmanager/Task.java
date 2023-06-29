package org.taskmanager;

import javax.swing.*;

public class Task {

    private JPanel p;
    final int lineLength = 45;

    private String description;
    private DueDate dueDate;
    private Difficulty difficulty;

    private JLabel descLabel;
    private JLabel dateLabel;
    private JLabel diffLabel;

    private JButton modify, delete;
    private JCheckBox complete;

    private int id;

    public Task(){
        p = new JPanel();
    }

    public Task(String description, DueDate date, Difficulty diff, int id) {

        p = new JPanel();

        this.description = description;
        this.dueDate = date;
        this.difficulty = diff;

        this.id = id;

        descLabel = new JLabel(description);
        dateLabel = new JLabel(dueDate.toString());
        diffLabel = new JLabel(difficulty.name());

    }

    public String formatDescription() {
        int descLength = description.length();
        int lines = descLength / lineLength;
        for (int i = 0; i < lines; i++) {

        }
        return "";
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
        return descLabel;
    }

    public JLabel getDateLabel() {
        return dateLabel;
    }

    public JLabel getDiffLabel() {
        return diffLabel;
    }

    public JPanel getPanel(){ return p; }

    public JButton addModifyButton() {
        modify = new JButton("Change");
        modify.addActionListener(e -> {
            new ModifyTaskWindow(this);
                }
                );
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
                            GUI.removeTaskFromGUI(this);
                        } else if (n == JOptionPane.NO_OPTION) {
                            frame.dispose();
                        }
                    }
                }
        );
        return complete;
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
    public void decrementId () {
        this.id--;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setDescription(String desc){
        this.description = desc;
    }

    public void setDueDate(DueDate date){
        this.dueDate = date;
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
