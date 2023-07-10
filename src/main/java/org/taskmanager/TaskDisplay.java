package org.taskmanager;
import java.util.LinkedList;

import javax.swing.*;

public class TaskDisplay extends JPanel {

    static LinkedList<Task> taskList;
    static int numTasks;
    static int id;

    public TaskDisplay() {
        numTasks = 0;
        Database db = new Database();
        id = db.getCurrentID() + 1;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        taskList = new LinkedList<>();
    }

    public static void removeTaskFromList(Task t){
        for (int i = t.getId(); i < taskList.size(); i++) {
            taskList.get(i).decrementId();
        }
    }

    public static void addLabelsToPanel(Task t) {
        JLabel descLab = t.getDescLabel();
        descLab.setToolTipText(t.getDescription());
        t.getPanel().add(descLab);
        t.getPanel().add(t.getDateLabel());
        t.getPanel().add(t.getDiffLabel());
        t.getPanel().add(t.spacer);
        t.getPanel().add(t.addModifyButton());
        t.getPanel().add(t.addDeleteButton());
        t.getPanel().add(t.addCompleteCheck());
    }

    public static void removeLabelsFromPanels(Task t) {
        t.getPanel().remove(t.getDescLabel());
        t.getPanel().remove(t.getDateLabel());
        t.getPanel().remove(t.getDiffLabel());
        t.getPanel().remove(t.getModifyButton());
        t.getPanel().remove(t.getDeleteButton());
        t.getPanel().remove(t.getCompleteCheck());
    }

    public static void setTaskList(LinkedList<Task> taskLinkedList) {
        taskList = taskLinkedList;
    }

    public static int getId() {
        Database db = new Database();
        return db.getCurrentID() + 1;
    }


}
