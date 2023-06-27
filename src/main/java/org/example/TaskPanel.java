package org.example;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class TaskPanel extends JPanel {

    static ArrayList<Task> taskList;

    public TaskPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        taskList = new ArrayList<>();
    }


    public static void addTaskToList(Task t) {
        taskList.add(t);
    }

    public static JPanel addLabelsToPanel(Task t) {
        JPanel tempPanel = new JPanel();
        tempPanel.add(t.getDescLabel());
        tempPanel.add(t.getDateLabel());
        tempPanel.add(t.getDiffLabel());
        tempPanel.add(t.getModifyButton());
        return tempPanel;
    }


}
