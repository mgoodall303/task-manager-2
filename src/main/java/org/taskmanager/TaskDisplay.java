package org.taskmanager;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class TaskDisplay extends JPanel {

    static LinkedList<Task> taskList;
    static int numTasks;

    public TaskDisplay() {
        numTasks = 0;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        taskList = new LinkedList<>();
    }

    public static void removeTaskFromList(Task t){
        for (int i = t.getId(); i < taskList.size(); i++) {
            taskList.get(i).decrementId();
        }
    }

    public static void addLabelsToPanel(Task t) {
        t.getPanel().add(t.getDescLabel());
        t.getPanel().add(t.getDateLabel());
        t.getPanel().add(t.getDiffLabel());
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


}
