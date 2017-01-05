package com.saka.myapplication.Models;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/1/5.
 */

public class HistoryModle {
    private int error_code;
    private String reason;
    private HistoryResult[] results;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public HistoryResult[] getResults() {
        return results;
    }

    public void setResults(HistoryResult[] results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "HistoryModle{" +
                "error_code=" + error_code +
                ", reason='" + reason + '\'' +
                ", results=" + Arrays.toString(results) +
                '}';
    }

    class HistoryResult {
        private int day;
        private String des;
        private int id;
        private String lunnar;
        private int month;
        private String pic;
        private String title;
        private int year;

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLunnar() {
            return lunnar;
        }

        public void setLunnar(String lunnar) {
            this.lunnar = lunnar;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        @Override
        public String toString() {
            return "HistoryResult{" +
                    "day=" + day +
                    ", des='" + des + '\'' +
                    ", id=" + id +
                    ", lunnar='" + lunnar + '\'' +
                    ", month=" + month +
                    ", pic='" + pic + '\'' +
                    ", title='" + title + '\'' +
                    ", year=" + year +
                    '}';
        }
    }
}
