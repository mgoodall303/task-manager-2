package org.taskmanager;

import java.time.*;
public class DueDate {
    String year;
    Months month;
    String day;

    public DueDate(String year, Months month, String day) {
        Year y = Year.now();
        int currYear = Integer.parseInt(y.toString());

        this.year = year;
        try {
            int yVal = Integer.parseInt(year);
        } catch (NumberFormatException e) {
            int yVal = currYear;
        }
        this.month = month;
        this.day = day;
    }

    public String toString() {
        String m = month.name();
        return m + " " + day + ", " + year;
    }
}
