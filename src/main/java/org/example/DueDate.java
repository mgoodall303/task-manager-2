package org.example;

public class DueDate {
    String year;
    Months month;
    String day;

    public DueDate(String year, Months month, String day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public String toString() {
        String m = month.name();

        return m + " " + day + ", " + year;
    }
}
