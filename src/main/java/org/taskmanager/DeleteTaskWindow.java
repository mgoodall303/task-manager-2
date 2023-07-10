package org.taskmanager;

import javax.swing.*;

public class DeleteTaskWindow {
    private JFrame frame;
    public DeleteTaskWindow(Task t) {
        frame = new JFrame();
        int n = JOptionPane.showConfirmDialog(
                frame,
                "Are you sure you want to delete this task?",
                "Task deletion confirmation",
                JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            Database db = new Database();
            db.deleteTask(t, false);
            GUI.removeTaskFromGUI(t);
        } else if (n == JOptionPane.NO_OPTION) {
            frame.dispose();
        }
    }

}
