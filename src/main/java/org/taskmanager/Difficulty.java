package org.taskmanager;

public enum Difficulty {
    EASY(1), MEDIUM(2), HARD(3);

    final int multiplier;
    Difficulty(int x) {
        multiplier = x;
    }

    int getMultiplier() {
        return multiplier;
    }
}
