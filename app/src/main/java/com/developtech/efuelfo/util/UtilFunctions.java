package com.developtech.efuelfo.util;

import android.content.Context;
import android.net.ConnectivityManager;

import com.developtech.efuelfo.network.AllUrls;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.inject.Inject;

/**
 * Created by dt on 1/9/18.
 */

public class UtilFunctions {
    private Context context;
    private AllUrls allUrls;

    @Inject
    public UtilFunctions(Context context, AllUrls allUrls) {
        this.context = context;
        this.allUrls = allUrls;
    }

    public boolean isInternetOn() {
        ConnectivityManager localNetworkInfo = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return localNetworkInfo.getActiveNetworkInfo() != null;
    }

    public String getImageFullUrl(String imgUrl) {
        return allUrls.BASE_IMAGE_URL + imgUrl;
    }

    public String timeFormat(String timeOfPost) {
        try {
//            var locale =Locale.getDefault()
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
            df.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = df.parse(timeOfPost);
            df.setTimeZone(TimeZone.getDefault());
            String formattedDate = df.format(date);
            SimpleDateFormat lf =new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");
            lf.setTimeZone(TimeZone.getDefault());
            return lf.format(date);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getParsedDate(String strDate)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = dateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
        formatter.setTimeZone(TimeZone.getTimeZone("IST"));
        return formatter.format(date);
    }

    public String getParsedDateOnly(String strDate)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = dateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        formatter.setTimeZone(TimeZone.getTimeZone("IST"));
        return formatter.format(date);
    }

    public String toLocal(String strDate)
    {
        String convertedTime="";

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = dateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        final SimpleDateFormat sdfLocal = new SimpleDateFormat("dd MMM yyyy hh:mm a");
        String timeZone = Calendar.getInstance().getTimeZone().getID();
        sdfLocal.setTimeZone(TimeZone.getTimeZone(timeZone));
        convertedTime = sdfLocal.format(date);
        return convertedTime;
    }

    public Date getLocalDateFromUtc(String strDate)
    {
        Date convertedDate=null;

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = dateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        final SimpleDateFormat sdfLocal = new SimpleDateFormat("yyyy MM dd HH mm");
        String timeZone = Calendar.getInstance().getTimeZone().getID();
        sdfLocal.setTimeZone(TimeZone.getTimeZone(timeZone));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        try {
            convertedDate = sdfLocal.parse(calendar.get(Calendar.YEAR)+" "+(calendar.get(Calendar.MONTH)+1)+" "+calendar.get(Calendar.DAY_OF_MONTH)+" "+calendar.get(Calendar.HOUR_OF_DAY)+" "+calendar.get(Calendar.MINUTE));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertedDate;
    }

    public String toUtc(String strDate)
    {
        String convertedTime="";

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String timeZone = Calendar.getInstance().getTimeZone().getID();
        dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        Date date = null;
        try {
            date = dateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        final SimpleDateFormat sdfLocal = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        sdfLocal.setTimeZone(TimeZone.getTimeZone("UTC"));
        convertedTime = sdfLocal.format(date);
        return convertedTime;
    }

    public String getLocalDate(String strDate)
    {
        String convertedTime="";

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = dateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        final SimpleDateFormat sdfLocal = new SimpleDateFormat("dd/MM/yyyy");
        String timeZone = Calendar.getInstance().getTimeZone().getID();
        sdfLocal.setTimeZone(TimeZone.getTimeZone(timeZone));
        convertedTime = sdfLocal.format(date);
        return convertedTime;
    }

    public Date getDate(String strDate)
    {
        Date convertedDate=null;

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = dateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        final SimpleDateFormat sdfLocal = new SimpleDateFormat("yyyy MM dd HH mm");
        String timeZone = Calendar.getInstance().getTimeZone().getID();
        sdfLocal.setTimeZone(TimeZone.getTimeZone(timeZone));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        try {
            convertedDate = sdfLocal.parse(calendar.get(Calendar.YEAR)+" "+(calendar.get(Calendar.MONTH)+1)+" "+calendar.get(Calendar.DAY_OF_MONTH)+" "+calendar.get(Calendar.HOUR_OF_DAY)+" "+calendar.get(Calendar.MINUTE));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertedDate;
    }

    public String getLocalTime(String strDate)
    {
        String convertedTime="";

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = dateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        final SimpleDateFormat sdfLocal = new SimpleDateFormat("hh:mm a");
        String timeZone = Calendar.getInstance().getTimeZone().getID();
        sdfLocal.setTimeZone(TimeZone.getTimeZone(timeZone));
        convertedTime = sdfLocal.format(date);
        return convertedTime;
    }

    public Date toDate(String strDate)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }



    public String getParsedDate2(String strDate)
    {
        if (strDate==null)
            return "";

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = dateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat formatter = new SimpleDateFormat("dd MMM yyyy hh:mm a");
        formatter.setTimeZone(TimeZone.getTimeZone("IST"));
        String strFormatted =  formatter.format(date);
        return strFormatted;
    }

    public String getTimeLaps(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String str;
        Date curent = Calendar.getInstance().getTime();
        long millis = curent.getTime() - date.getTime();
        return friendlyTimeDiff(millis);
    }

    public String friendlyTimeDiff(long timeDifferenceMilliseconds) {
        long diffSeconds = timeDifferenceMilliseconds / 1000;
        long diffMinutes = timeDifferenceMilliseconds / (60 * 1000);
        long diffHours = timeDifferenceMilliseconds / (60 * 60 * 1000);
        long diffDays = timeDifferenceMilliseconds / (60 * 60 * 1000 * 24);
        long diffWeeks = timeDifferenceMilliseconds / (60 * 60 * 1000 * 24 * 7);
        long diffMonths = (long) (timeDifferenceMilliseconds / (60 * 60 * 1000 * 24 * 30.41666666));
        long diffYears = (long) (timeDifferenceMilliseconds / (60 * 60 * 1000 * 24 * 365));
        if (diffSeconds < 1) {
            return "Just now";
        } else if (diffMinutes < 1) {
            return diffSeconds + " sec ago";
        } else if (diffHours < 1) {
            return diffMinutes + " min ago";
        } else if (diffDays < 1) {
            return diffHours + " hour ago";
        } else if (diffWeeks < 1) {
            return diffDays + " days ago";
        } else if (diffMonths < 1) {
            return diffWeeks + "w ago";
        } else if (diffYears < 1) {
            return diffMonths + " month ago";
        } else {
            return "";
        }
    }
}
