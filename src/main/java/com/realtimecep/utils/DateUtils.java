package com.realtimecep.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Date Utility Class.
 * <p/>
 *
 * @author <a href=mailto:tedd824@gmail.com">Ted Won</a>
 * @version 1.0
 */
public class DateUtils {


    /**
     * 현재 날짜를 "yyyyMMdd" 형태로 변환한다.
     * <p>
     * <pre>
     * String today = DateUtils.getCurrentYyyymmdd();
     * </pre>
     * </p>
     *
     * @return yyyyMMdd
     */
    public static String getCurrentYyyymmdd() {
        return getCurrentDateTime().substring(0, 8);
    }

    /**
     * 현재 날짜를 "yyyyMM" 형태로 변환한다.
     * <p>
     * <pre>
     * String today = DateUtils.getCurrentYyyymm();
     * </pre>
     * </p>
     *
     * @return yyyyMM
     */
    public static String getCurrentYyyymm() {
        return getCurrentDateTime().substring(0, 6);
    }

    /**
     * 현재 날짜와 시각을 "yyyyMMddhhmmss" 형태로 변환한다.
     * <p>
     * <pre>
     * String today = DateUtils.getCurrentDateTime();
     * </pre>
     * </p>
     *
     * @return yyyyMMddhhmmss 형식의 문자열 날짜
     */
    public static String getCurrentDateTime() {
        Date today = new Date();
        Locale currentLocale = new Locale("KOREAN", "KOREA");
        String pattern = "yyyyMMddHHmmss";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, currentLocale);
        return formatter.format(today);
    }

    public static String getDateTime(long date) {
        Date today = new Date(date);
        Locale currentLocale = new Locale("KOREAN", "KOREA");
        String pattern = "yyyy-MM-dd HH:mm:ss,SSS";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, currentLocale);
        return formatter.format(today);
    }


    /**
     * 초단위 시간을 "H:M:S" 형식으로 포맷팅한다.
     *
     * @param diffLongTime 시간
     * @return "H:M:S" 형식의 시간
     */
    public static String formatTime(long diffLongTime) {
        StringBuffer buf = new StringBuffer();
        long hours = diffLongTime / (60 * 60 * 1000);
        long rem = (diffLongTime % (60 * 60 * 1000));
        long minutes = rem / (60 * 1000);
        rem = rem % (60 * 1000);
        long seconds = rem / 1000;

        if (hours != 0) {
            buf.append(hours);
            buf.append("h ");
        }
        if (minutes != 0) {
            buf.append(minutes);
            buf.append("m ");
        }
        // 차이가 없다면 0을 반환한다.
        buf.append(seconds);
        buf.append("s");
        return buf.toString();
    }
}
