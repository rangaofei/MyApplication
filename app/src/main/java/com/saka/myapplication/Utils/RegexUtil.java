package com.saka.myapplication.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/1/5.
 */

public class RegexUtil {

    public static final String SPACE = "&nbsp";

    public static String replaceString(String input, String replacechar) {
        Pattern p = Pattern.compile(replacechar);
        Matcher matcher = p.matcher(input);
        String output = matcher.replaceAll(" ");
        return output;
    }


}
