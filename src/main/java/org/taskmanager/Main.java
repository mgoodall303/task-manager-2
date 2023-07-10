package org.taskmanager;

public class Main {
    public static void main(String[] args) {
        Database db = new Database();
        db.initiatePoints();  // Sets score to 0, if first time running app

        GUI gui = new GUI();
        gui.createGUI();  // initializes GUI
    }
}