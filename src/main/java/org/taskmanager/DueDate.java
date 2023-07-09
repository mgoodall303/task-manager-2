package org.taskmanager;

import java.util.Calendar;
public class DueDate {
    String year;
    Months month;
    String day;
    int intYear, intMonth, intDay;

    public DueDate(String year, Months month, String day) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.intMonth = month.getIndex();
        this.intYear = Integer.parseInt(year);
        this.intDay = Integer.parseInt(day);
    }

    public DueDate(int year, int month, int day) {
        this.year = String.valueOf(year);
        this.month = Months.returnMonth(month);
        this.day = String.valueOf(day);
        this.intYear = year;
        this.intMonth = month;
        this.intDay = day;
    }

    public DueDate() {

    }

    public String toString() {
        String m = month.name();
        return m + " " + day + ", " + year;
    }

    public boolean hasPassed() {
        Calendar cal = Calendar.getInstance();
        int currYear = cal.get(Calendar.YEAR);
        int currMonth = cal.get(Calendar.MONTH) + 1;
        int currDay = cal.get(Calendar.DAY_OF_MONTH);
        if (currYear < intYear) {
            return false;
        }
        if (currYear == intYear) {
            if (currMonth < intMonth) {
                return false;
            }
            if (currMonth == intMonth) {
                if (currDay < intDay) {
                    return false;
                }
            }
        }
        return true;
    }

    public void toData(String dueDateStr) {
        String[] str = dueDateStr.split(" ");
        this.month = Months.valueOf(str[0]);
        this.day = str[1].replace(",", "");
        this.year = str[2];
    }
}
