package org.taskmanager;

public class Main {
    public static void main(String[] args) {
        Database db = new Database();
        db.initiatePoints();
        GUI gui = new GUI();
        gui.createGUI();
    }
}