package com.developtech.efuelfo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by android on 9/14/17.
 */

public class TimeUtils
{
    public static String getConvertedDateTime(String time)
    {
        String convertedTime="";
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            final Date dateObj = sdf.parse(time);
            convertedTime = new SimpleDateFormat("dd MMM yy HH:mma").format(dateObj);
        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return convertedTime;
    }

    public static String getConvertedTime(String time)
    {
        String convertedTime="";
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            final Date dateObj = sdf.parse(time);
            convertedTime = new SimpleDateFormat("HH:mma").format(dateObj);
        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return convertedTime;
    }

    public String get12HourTime(String time)
    {
        String convertedTime="";
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            final Date dateObj = sdf.parse(time);
            convertedTime = new SimpleDateFormat("K:mm a").format(dateObj);
        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return convertedTime;
    }

}
