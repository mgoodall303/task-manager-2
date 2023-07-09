package org.taskmanager;

public enum Months {
    JANUARY(1,31), FEBRUARY(2,28), MARCH(3,31), APRIL(4,30), MAY(5,31),
    JUNE(6,30), JULY(7,31), AUGUST(8,31), SEPTEMBER(9,30), OCTOBER(10,31),
    NOVEMBER(11,30), DECEMBER(12,31);

    final int index;
    final int day;
    Months(int i, int days) {
        index = i;
        day = days;
    }
    int getIndex() {
        return index;
    }
    int getDays() {
        return day;
    }

    public static Months returnMonth(int ind) {
        return Months.values()[ind - 1];
    }
}
