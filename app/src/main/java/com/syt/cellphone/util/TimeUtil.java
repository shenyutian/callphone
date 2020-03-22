package com.syt.cellphone.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author shenyutian
 * @data 2020/3/5 5:20 PM
 * 功能 时间工具类
 */
public class TimeUtil {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static String getEstimateTime(long time) {
        Date date = new Date(time);
        return simpleDateFormat.format(date);
    }

}
