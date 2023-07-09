package org.taskmanager;

public class PointCalculator {
    Task t;
    public PointCalculator() {
    }
    public PointCalculator(Task t) {
        this.t = t;
    }
    public void assignTask(Task t) {
        this.t = t;
    }

    public int setPointsFromTask() {
        if (t.getDueDate().hasPassed()) {
            return 1;
        } else {
            return 2 * t.getDifficulty().getMultiplier();
        }
    }
}
