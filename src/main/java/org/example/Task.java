package org.example;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Task {

    final int lineLength = 45;

    private final String description;
    private final DueDate dueDate;
    private final Difficulty difficulty;

    private final JLabel descLabel;
    private final JLabel dateLabel;
    private final JLabel diffLabel;

    private final JButton modify;

    private int id;

    public Task(String description, DueDate date, Difficulty diff, int id) {
        this.description = description;
        this.dueDate = date;
        this.difficulty = diff;

        this.id = id;

        descLabel = new JLabel(description);
        dateLabel = new JLabel(dueDate.toString());
        diffLabel = new JLabel(difficulty.name());

        modify = new JButton("Change Task");

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

    public JLabel getDescLabel() {
        return descLabel;
    }

    public JLabel getDateLabel() {
        return dateLabel;
    }

    public JLabel getDiffLabel() {
        return diffLabel;
    }

    public JButton getModifyButton() {
        modify.addActionListener(e -> System.out.println("testing"));
        return modify;
    }

    public void removeTask() {
        this.id--;
    }

}
