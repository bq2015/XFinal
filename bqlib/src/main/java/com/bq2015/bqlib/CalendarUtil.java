/*
 *
 *   Copyright 2016 YunDi
 *
 *   The code is part of Yunnan, Shenzhen Branch of the internal architecture of YunDi source group
 *
 *   DO NOT DIVULGE
 *
 */

package com.bq2015.bqlib;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/************************************************************
 * Author:  bq2015
 * Description:     // 模块描述
 * Date: 2016/4/12
 ************************************************************/
public class CalendarUtil {


    public static Calendar long2Calendar(long time) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);

        return calendar;
    }


    public static String formatData(String dataFormat, long timeStamp) {
        if (timeStamp == 0) {
            return "";
        }
        timeStamp = timeStamp * 1000;
        String result = "";
        SimpleDateFormat format = new SimpleDateFormat(dataFormat);
        result = format.format(new Date(timeStamp));
        return result;
    }


}
