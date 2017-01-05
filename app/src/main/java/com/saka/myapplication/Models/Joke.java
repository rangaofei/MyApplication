package com.saka.myapplication.Models;

import com.saka.myapplication.Utils.RegexUtil;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/1/4.
 */

public class Joke {
    private int error_code;
    private String reason;
    private Result result;


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

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Joke{" +
                "error_code=" + error_code +
                ", reason='" + reason + '\'' +
                ", result=" + result +
                '}';
    }

    public class Result {
        private Data[] data;

        public Data[] getData() {
            return data;
        }

        public void setData(Data[] data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "data=" + Arrays.toString(data) +
                    '}';
        }
    }

    //
    public class Data {

        private String content;
        private String hashId;
        private long unixtime;
        private String updatetime;

        public String getContent() {
            return RegexUtil.replaceString(content,RegexUtil.SPACE);
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "content='" + content + '\'' +
                    ", hashId='" + hashId + '\'' +
                    ", unixtime=" + unixtime +
                    ", updatetime='" + updatetime + '\'' +
                    '}';
        }
    }
}
