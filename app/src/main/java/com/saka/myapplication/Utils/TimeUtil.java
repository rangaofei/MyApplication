package com.saka.myapplication.Utils;

import java.util.Calendar;

/**
 * Created by saka on 2017/1/5.
 */

public class TimeUtil {

    private static Calendar calendar = Calendar.getInstance();

    public static int getCurrentYear() {
        int year = calendar.get(Calendar.YEAR);
        return year;
    }

    public static int getCurrentMonth() {
        int month = calendar.get(Calendar.MONTH);
        return month + 1;
    }

    public static int getCurrentDay() {
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    public static int getWeekDay() {
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        return day;
    }
}
