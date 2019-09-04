package com.yqh.dubbo.common;

/**
 * @author yangq
 * Create time in 2018-07-24 14:40
 */

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static java.util.Objects.nonNull;

/**
 * <dl>
 * <dt><b>Title:</b></dt>
 * <dd>
 * 时间工具类
 * </dd>
 * <dt><b>Description:</b></dt>
 * <dd>
 * <p>none
 * </dd>
 * </dl>
 *
 * @author eric
 * @version 1.0, 2009-12-28
 * @since framework-1.4
 */
public class DateUtil {

    public static final String DASH = "-";
    public static final String COLON = ":";

    //============================0.获取当前时间====================================

    /**
     * 获取当前日期类型时间
     */
    public static Date getNow() {
//    	Object b = SpringUtils.getSpringBeanById("generalDao");
//    	Assert.notNull(b);
//    	GeneralDao g = (GeneralDao)b;
//    	return g.getDbTime();
        return new Date();
    }


    /**
     * 获取当前时间戳
     */
    public static long getNowTimestamp() {
        return getNow().getTime();
    }

    /**
     * 获取当前日期 yyyyMMdd
     */
    public static String getCurrentDate() {
        return toMailDateDtPartString(getNow());
    }

    /**
     * 获取当期时间HHmmss
     *
     * @return
     */
    public static String getCurrentTime() {
        return toMailTimeTmPartString(getNow());
    }

    /**
     * 获取当期时间MM月dd日HH:mm
     *
     * @return
     */
    public static String getCurrentMmDdHmTime() {
        return toMailDtmPart(getNow());
    }

    /**
     * 获取当前日期和时间yyyyMMddHHmmss
     *
     * @return
     */
    public static String getCurrentDateTime() {
        return toMailDateString(getNow());
    }


    //============================1.Date2String=====================================

    /**
     * 将一个日期型转换为指定格式字串
     *
     * @param aDate
     * @param formatStr
     * @return
     */
    public static final String toFormatDateString(Date aDate, String formatStr) {
        if (aDate == null) {
            return StringUtils.EMPTY;
        }
        Assert.hasText(formatStr);
        return new SimpleDateFormat(formatStr).format(aDate);

    }


    /**
     * 将一个日期型转换为'yyyy-MM-dd'格式字串
     *
     * @param aDate
     * @return
     */
    public static final String toShortDateString(Date aDate) {
        return toFormatDateString(aDate, SHORT_DATE_FORMAT);
    }

    /**
     * 将一个日期型转换为'yyyyMMdd'格式字串
     *
     * @param aDate
     * @return
     */
    public static final String toMailDateDtPartString(Date aDate) {
        return toFormatDateString(aDate, MAIL_DATE_DT_PART_FORMAT);
    }

    /**
     * 将一个日期型转换为'HHmmss'格式字串
     *
     * @param aDate
     * @return
     */
    public static final String toMailTimeTmPartString(Date aDate) {
        return toFormatDateString(aDate, MAIL_TIME_TM_PART_FORMAT);
    }


    /**
     * 将一个日期型转换为'yyyyMMddHHmmss'格式字串
     *
     * @param aDate
     * @return
     */
    public static final String toMailDateString(Date aDate) {
        return toFormatDateString(aDate, MAIL_DATE_FORMAT);
    }

    /**
     *
     */
    /**
     * 将一个日期型转换为MM月dd日HH:mm格式字串
     *
     * @param aDate
     * @return
     */
    public static final String toMailDtmPart(Date aDate) {
        return toFormatDateString(aDate, MAIL_DATA_DTM_PART_FORMAT);
    }

    /**
     *
     */
    /**
     * 将一个日期型转换为yyyy.MM.dd格式字串
     *
     * @param aDate
     * @return
     */
    public static final String toPointDtmPart(Date aDate) {
        return toFormatDateString(aDate, POINT_DATA_DTM_PART_FORMAT);
    }


    /**
     * 将一个日期型转换为'yyyy-MM-dd HH:mm:ss'格式字串
     *
     * @param aDate
     * @return
     */
    public static final String toLongDateString(Date aDate) {
        return toFormatDateString(aDate, LONG_DATE_FORMAT);
    }

    /**
     * 将一个日期型转换为'HH:mm:ss'格式字串
     *
     * @param aDate
     * @return
     */
    public static final String toLongDateTmPartString(Date aDate) {
        return toFormatDateString(aDate, LONG_DATE_TM_PART_FORMAT);
    }

    /**
     * 将一个日期型转换为'yyyy年MM月dd日'格式字串
     *
     * @param aDate
     * @return
     */
    public static final String toShortDateGBKString(Date aDate) {
        return toFormatDateString(aDate, SHORT_DATE_GBK_FORMAT);
    }


    /**
     * 将一个日期型转换为'yyyy年MM月dd日 HH时mm分'格式字串
     *
     * @param aDate
     * @return
     */
    public static final String toDateGBKString(Date aDate) {
        return toFormatDateString(aDate, DATE_GBK_FORMAT);
    }

    /**
     * 将一个日期型转换为'yyyy年MM月dd日 HH时mm分ss秒'格式字串
     *
     * @param aDate
     * @return
     */
    public static final String toLongDateGBKString(Date aDate) {
        return toFormatDateString(aDate, LONG_DATE_GBK_FORMAT);
    }

    /**
     * 将一个日期型转换为'HH时mm分ss秒'格式字串
     *
     * @param aDate
     * @return
     */
    public static final String toLongDateTmPartGBKString(Date aDate) {
        return toFormatDateString(aDate, Long_DATE_TM_PART_GBK_FORMAT);
    }

    /**
     * 将一个日期型转换为'yyyy-MM-dd HH:mm:ss:SSS'格式字串
     *
     * @param aDate
     * @return
     */
    public static final String toFullDateString(Date aDate) {
        return toFormatDateString(aDate, FULL_DATE_FORMAT);
    }

    /**
     * 将一个日期型转换为'yyyy年MM月dd日 HH时mm分ss秒SSS毫秒'格式字串
     *
     * @param aDate
     * @return
     */
    public static final String toFullDateGBKString(Date aDate) {
        return toFormatDateString(aDate, FULL_DATE_GBK_FORMAT);
    }

    /**
     * 将一个日期型转换为'yyyyMMddHHmmssSSS'格式字串
     *
     * @param aDate
     * @return
     */
    public static final String toFullDateCompactString(Date aDate) {
        return toFormatDateString(aDate, FULL_DATE_COMPACT_FORMAT);
    }

    /**
     * 将一个日期型转换为LDAP格式字串
     *
     * @param aDate
     * @return
     */
    public static final String toLDAPDateString(Date aDate) {
        return toFormatDateString(aDate, LDAP_DATE_FORMAT);
    }


    //============================2.String2Date=====================================

    /**
     * 将一个符合指定格式的字串解析成日期型
     *
     * @param aDateStr
     * @param formatter
     * @return
     * @throws ParseException
     */
    public static final Date parser(String aDateStr, String formatter) throws ParseException {
        if (StringUtils.isBlank(aDateStr)) {
            return null;
        }
        Assert.hasText(formatter);
        SimpleDateFormat sdf = new SimpleDateFormat(formatter);
        return sdf.parse(aDateStr);

    }

    /**
     * 将一个符合'yyyy-MM-dd HH:mm:ss'格式的字串解析成日期型
     *
     * @param aDateStr
     * @return
     */
    public static final Date parseLongDateString(String aDateStr) throws ParseException {
        return parser(aDateStr, LONG_DATE_FORMAT);

    }

    /**
     * 将一个符合'yyyy-MM-dd HH:mm:ss'格式的字串解析成日期型
     *
     * @param aDateStr
     * @return
     */
    public static final Date parseLongDateDtPartString(String aDateStr) throws ParseException {
        return parser(aDateStr, LONG_DATE_FORMAT);

    }

    /**
     * 将一个符合'yyyy-MM-dd HH:mm:ss'格式的字串解析成日期型
     *
     * @param aDateStr
     * @return
     */
    public static final Date parseLongDateTmPartString(String aDateStr) throws ParseException {
        return parser(aDateStr, LONG_DATE_FORMAT);

    }

    /**
     * 将一个符合'yyyy-MM-dd'格式的字串解析成日期型
     *
     * @param aDateStr
     * @return
     */
    public static final Date parseShortDateString(String aDateStr) throws ParseException {
        return parser(aDateStr, SHORT_DATE_FORMAT);

    }

    /**
     * 将一个符合'yyyyMMddHHmmss'格式的字串解析成日期型
     *
     * @param aDateStr
     * @return
     */
    public static final Date parseMailDateString(String aDateStr) throws ParseException {
        return parser(aDateStr, MAIL_DATE_FORMAT);

    }

    /**
     * 将一个符合'yyyyMMdd'格式的字串解析成日期型
     *
     * @param aDateStr
     * @return
     */
    public static final Date parseMailDateDtPartString(String aDateStr) throws ParseException {
        return parser(aDateStr, MAIL_DATE_DT_PART_FORMAT);
    }

    /**
     * 将一个符合'HHmmss'格式的字串解析成日期型
     *
     * @param aDateStr
     * @return
     */
    public static final Date parseMailDateTmPartString(String aDateStr) throws ParseException {
        return parser(aDateStr, MAIL_TIME_TM_PART_FORMAT);
    }


    /**
     * 将一个符合'yyyy-MM-dd HH:mm:ss:SSS'格式的字串解析成日期型
     *
     * @param aDateStr
     * @return
     */
    public static final Date parseFullDateString(String aDateStr) throws ParseException {
        return parser(aDateStr, FULL_DATE_FORMAT);

    }

    /**
     * 将一个符合'yyyy-MM-dd'、'yyyy-MM-dd HH:mm:ss'或'EEE MMM dd HH:mm:ss zzz yyyy'格式的字串解析成日期型，
     * 如果为blank则返回空，如果不为blank又不符合格式则报错
     *
     * @param aDateStr
     * @return
     */
    public static Date parseDateString(String aDateStr) {
        Date ret = null;
        if (StringUtils.isNotBlank(aDateStr)) {
            try {
                if (DataValidator.isLongDateStr(aDateStr)) {
                    ret = DateUtil.parseLongDateString(aDateStr);
                } else if (DataValidator.isShortDateStr(aDateStr)) {
                    ret = DateUtil.parseShortDateString(aDateStr);
                } else if (DataValidator.isDateStrMatched(aDateStr, DateUtil.DEFAULT_DATE_FORMAT)) {
                    ret = DateUtil.parser(aDateStr, DateUtil.DEFAULT_DATE_FORMAT);
                } else {
                    throw new IllegalArgumentException(MessageFormat.format("date format mismatch{0}", aDateStr));
                }
            } catch (ParseException e) {
                //不可能到这里
            }
        }
        return ret;
    }


    //============================3.String2String=====================================


    /**
     * 转换日期格式 yyyy-MM-dd => yyyyMMdd
     *
     * @param dt yyyy-MM-dd
     * @return yyyyMMdd
     */
    public static String transfer2ShortDate(String dt) {
        if (dt == null || dt.length() != 10) {
            return dt;
        }
        Assert.notNull(dt);
        String[] tmp = StringUtils.split(dt, DASH);
        Assert.isTrue(tmp != null && tmp.length == 3);
        return tmp[0].concat(StringUtils.leftPad(tmp[1], 2, "0")).concat(StringUtils.leftPad(tmp[2], 2, "0"));
    }

    /**
     * 转换日期格式 yyyyMMdd => yyyy-MM-dd
     *
     * @param dt yyyyMMdd
     * @return yyyy-MM-dd
     */
    public static String transfer2LongDateDtPart(String dt) {
        if (dt == null || dt.length() != 8) {
            return dt;
        }
        Assert.notNull(dt);
        Assert.isTrue(dt.length() == 8);
        return dt.substring(0, 4).concat(DASH).concat(dt.substring(4, 6)).concat(DASH).concat(dt.substring(6));
    }

    /**
     * 转换日期格式 HHmmss => HH:mm:ss
     *
     * @param tm HHmmss
     * @return HH:mm:ss
     */
    public static String transfer2LongDateTmPart(String tm) {
        if (tm == null || tm.length() != 6) {
            return tm;
        }
        Assert.notNull(tm);
        Assert.isTrue(tm.length() == 6);
        return tm.substring(0, 2).concat(COLON).concat(tm.substring(2, 4)).concat(COLON).concat(tm.substring(4));
    }


    /**
     * 转换日期格式 yyyyMMdd => yyyy年MM月dd日
     *
     * @param dt yyyyMMdd
     * @return yyyy年MM月dd日
     */
    public static String transfer2LongDateGbkDtPart(String dt) {
        if (dt == null || dt.length() != 8) {
            return dt;
        }
        Assert.notNull(dt);
        Assert.isTrue(dt.length() == 8);
        return dt.substring(0, 4).concat("年").concat(dt.substring(4, 6)).concat("月").concat(dt.substring(6)).concat("日");
    }


    /**
     * 转换日期格式HHmmss => HH时mm分ss秒
     *
     * @param tm HHmmss
     * @return HH时mm分ss秒
     */
    public static String transfer2LongDateGbkTmPart(String tm) {
        if (tm == null || tm.length() != 6) {
            return tm;
        }
        Assert.notNull(tm);
        Assert.isTrue(tm.length() == 6);
        return tm.substring(0, 2).concat("时").concat(tm.substring(2, 4)).concat("分").concat(tm.substring(4)).concat("秒");
    }

    /**
     * 符合日期格式的字符串 => yyyy-MM-dd
     *
     * @param dateStr
     * @return
     */
    public static String trunc2String(String dateStr) {
        if (dateStr == null || dateStr.length() < 6) {
            return dateStr;
        }
        try {
            Date date = parseLongDateDtPartString(dateStr);
            return toShortDateString(date);
        } catch (ParseException e) {
            return dateStr;
        }
    }


    //============================4.时间加减=====================================


    /**
     * 为一个日期加上指定年数
     *
     * @param aDate
     * @param amount 年数
     * @return
     */
    public static final Date addYears(Date aDate, int amount) {
        return addTime(aDate, Calendar.YEAR, amount);
    }

    /**
     * 为一个日期加上指定月数
     *
     * @param aDate
     * @param amount 月数
     * @return
     */
    public static final Date addMonths(Date aDate, int amount) {
        return addTime(aDate, Calendar.MONTH, amount);
    }

    /**
     * 为一个日期加上指定天数
     *
     * @param aDate
     * @param amount 天数
     * @return
     */
    public static final Date addDays(Date aDate, int amount) {
        return addTime(aDate, Calendar.DAY_OF_YEAR, amount);
    }

    /**
     * 为一个日期加上指定天数
     *
     * @param aDate  yyyyMMdd格式字串
     * @param amount 天数
     * @return
     */
    public static final String addDays(String aDate, int amount) {
        try {
            return DateUtil.toMailDateDtPartString(addTime(DateUtil.parseMailDateDtPartString(aDate), Calendar.DAY_OF_YEAR, amount));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 为一个日期加上指定小时数
     *
     * @param aDate
     * @param amount 小时数
     * @return
     */
    public static final Date addHours(Date aDate, int amount) {
        return addTime(aDate, Calendar.HOUR, amount);

    }

    /**
     * 为一个日期加上指定分钟数
     *
     * @param aDate
     * @param amount 分钟数
     * @return
     */
    public static final Date addMinutes(Date aDate, int amount) {
        return addTime(aDate, Calendar.MINUTE, amount);
    }

    /**
     * 为一个日期加上指定秒数
     *
     * @param aDate
     * @param amount 秒数
     * @return
     */
    public static final Date addSeconds(Date aDate, int amount) {
        return addTime(aDate, Calendar.SECOND, amount);

    }

    private static final Date addTime(Date aDate, int timeType, int amount) {
        if (aDate == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(aDate);
        cal.add(timeType, amount);
        return cal.getTime();
    }


    //======================================5.时间国际化=================================

    /**
     * 得到当前时间的UTC时间
     *
     * @return
     */
    public static final String getUTCTime() {
        return getSpecifiedZoneTime(Calendar.getInstance().getTime(), TimeZone.getTimeZone("GMT+0"));
    }

    /**
     * 得到指定时间的UTC时间
     *
     * @param aDate 时间戳
     * @return yyyy-MM-dd HH:mm:ss 格式
     */
    public static final String getUTCTime(Date aDate) {
        return getSpecifiedZoneTime(aDate, TimeZone.getTimeZone("GMT+0"));
    }

    /**
     * 得到当前时间的指定时区的时间
     *
     * @param tz
     * @return
     */
    public static final String getSpecifiedZoneTime(TimeZone tz) {
        return getSpecifiedZoneTime(Calendar.getInstance().getTime(), tz);

    }

    /**
     * 得到指定时间的指定时区的时间
     *
     * @param aDate 时间戳,Date是一个瞬间的long型距离历年的位移偏量，
     *              在不同的指定的Locale/TimeZone的jvm中，它toString成不同的显示值，
     *              所以没有必要为它再指定一个TimeZone变量表示获取它时的jvm的TimeZone
     * @param tz    要转换成timezone
     * @return yyyy-MM-dd HH:mm:ss 格式
     */
    public static final String getSpecifiedZoneTime(Date aDate, TimeZone tz) {
        if (aDate == null) {
            return StringUtils.EMPTY;
        }
        Assert.notNull(tz);
        SimpleDateFormat sdf = new SimpleDateFormat(LONG_DATE_FORMAT);
        sdf.setTimeZone(tz);
        return sdf.format(aDate);
    }


    //==================================6. miscellaneous==========================

    /**
     * 计算两个日期之间相差的月数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static final int getDifferenceMonths(Date startDate, Date endDate) {
        Assert.notNull(startDate);
        Assert.notNull(endDate);
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);

        return Math.abs((startCal.get(Calendar.YEAR) - endCal.get(Calendar.YEAR)) * 12
                + (startCal.get(Calendar.MONTH) - endCal.get(Calendar.MONTH)));
    }

    /**
     * 计算两个日期之间相差的月数
     *
     * @param startDateStr yyyy-mm-dd
     * @param endDateStr   yyyy-mm-dd
     * @return
     */
    public static final int getDifferenceMonths(String startDateStr, String endDateStr) {
        DataValidator.checkShortDateStr(startDateStr);
        DataValidator.checkShortDateStr(endDateStr);
        try {
            return getDifferenceMonths(parseShortDateString(startDateStr), parseShortDateString(endDateStr));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param startDateStr yyyy-mm-dd
     * @param endDateStr   yyyy-mm-dd
     * @return
     */
    public static final int getDifferenceDays(String startDateStr, String endDateStr) {
        return (int) (getDifferenceMillis(startDateStr, endDateStr) / (NANO_ONE_DAY));
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param startDateStr yyyymmdd
     * @param endDateStr   yyyymmdd
     * @return
     */
    public static final int getDifferenceDays2(String startDateStr, String endDateStr) {
        return (int) (getDifferenceMillis(startDateStr, endDateStr, MAIL_DATE_DT_PART_FORMAT) / (NANO_ONE_DAY));
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static final int getDifferenceDays(Date startDate, Date endDate) {
        return (int) (getDifferenceMillis(startDate, endDate) / (NANO_ONE_DAY));

    }

    /**
     * 计算两个日期之间相差的的毫秒数
     *
     * @param startDateStr yyyy-mm-dd
     * @param endDateStr   yyyy-mm-dd
     * @return
     * @throws ParseException
     */
    public static final long getDifferenceMillis(String startDateStr, String endDateStr) {
        return getDifferenceMillis(startDateStr, endDateStr, SHORT_DATE_FORMAT);
    }

    /**
     * 计算两个日期之间相差的的毫秒数
     *
     * @param startDateStr yyyyMMddHHmmss
     * @param endDateStr   yyyyMMddHHmmss
     * @return
     * @throws ParseException
     */
    public static final long getDifferenceMillis2(String startDateStr, String endDateStr) {
        return getDifferenceMillis(startDateStr, endDateStr, MAIL_DATE_FORMAT);
    }

    /**
     * 计算两个日期之间相差的的毫秒数
     *
     * @param startDateStr
     * @param endDateStr
     * @param dateFormat
     * @return
     */
    public static final long getDifferenceMillis(String startDateStr, String endDateStr, String dateFormat) {
        try {
            return getDifferenceMillis(parser(startDateStr, dateFormat), parser(endDateStr, dateFormat));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 计算两个日期之间相差的的毫秒数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static final long getDifferenceMillis(Date startDate, Date endDate) {
        Assert.notNull(startDate);
        Assert.notNull(endDate);
        return Math.abs(endDate.getTime() - startDate.getTime());
    }


    /**
     * 检验 日期是否在指定区间内，如果格式错误，返回false
     * <p>
     * 如果maxDateStr或minDateStr为空则比较时变为正负无穷大，如果都为空，则返回false
     *
     * @param aDate
     * @param minDateStr 必须是yyyy-MM-dd格式，时分秒为00:00:00
     * @param maxDateStr 必须是yyyy-MM-dd格式，时分秒为00:00:00
     * @return
     */
    public static final boolean isDateBetween(Date aDate, String minDateStr, String maxDateStr) {
        Assert.notNull(aDate);
        boolean ret = false;
        try {
            Date dMaxDate = null;
            Date dMinDate = null;
            dMaxDate = DateUtil.parseShortDateString(maxDateStr);
            dMinDate = DateUtil.parseShortDateString(minDateStr);
            switch ((nonNull(dMaxDate) ? 5 : 3) + (nonNull(dMinDate) ? 1 : 0)) {
                case 6:
                    ret = aDate.before(dMaxDate) && aDate.after(dMinDate);
                    break;
                case 5:
                    ret = aDate.before(dMaxDate);
                    break;
                case 4:
                    ret = aDate.after(dMinDate);
                    break;
                default:
                    break;
            }
        } catch (ParseException e) {
        }
        return ret;
    }

    /**
     * 计算某日期所在月份的天数
     *
     * @param aDateStr yyyy-mm-dd
     * @return
     */
    public static final int getDaysInMonth(String aDateStr) {
        DataValidator.checkShortDateStr(aDateStr);
        try {
            return getDaysInMonth(parseShortDateString(aDateStr));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 计算某日期所在月份的天数
     *
     * @param aDate
     * @return
     */
    public static final int getDaysInMonth(Date aDate) {
        Assert.notNull(aDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(aDate);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }


    public static String[] getWeekDay(String date, int num) {
        String[] weekDay = new String[7];
        Calendar cal = Calendar.getInstance();
        cal.setTime(parseDateString(date));
        //n为推迟的周数，1本周，-1向前推迟一周，2下周，依次类推
        int n = num;
        String monday;
        cal.add(Calendar.DATE, n * 7);
        //想周几，这里就传几Calendar.MONDAY（TUESDAY...）
        for (int i = 0; i < 7; i++) {
            cal.set(Calendar.DAY_OF_WEEK, i + 1);
            monday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
            weekDay[i] = monday;
            //System.out.println(monday);
        }
        return weekDay;
    }

//   public static void getWeekDay(String date,int num){
//	   String[] weekDay = new String[7];
//	   Calendar cal = Calendar.getInstance();
//	   cal.setTime(parseDateString(date));
//	 //n为推迟的周数，1本周，-1向前推迟一周，2下周，依次类推
//	 int n = num;
//	 String monday;
//	 cal.add(Calendar.DATE, n*7);
//	 //想周几，这里就传几Calendar.MONDAY（TUESDAY...）
//	 for(int i=0;i<7;i++){
//		 cal.set(Calendar.DAY_OF_WEEK,i+1);
//		 monday = new SimpleDateFormat("dd").format(cal.getTime());
//		 weekDay[i]=monday;
//		 System.out.println(monday);
//	 }
//   }

    /**
     * <b>获取当前日期：formatted as yyyy-MM-dd</b>
     *
     * @return
     */
    public static String getCurrentShortDate() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat(SHORT_DATE_FORMAT);
        return dateFormat.format(new Date());
    }


    /**
     * <b>获取当前日期：</b>
     *
     * @param pattern 日期模式
     * @return
     */
    public static String getCurrentDate(String pattern) throws Exception {
        if (pattern == null) {
            throw new IllegalArgumentException("pattern is null");
        }
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(new Date());
    }


    /**
     * <b>获取年份：根据日期</b>
     *
     * @param date 日期
     * @return
     */
    public static String getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return String.valueOf(calendar.get(Calendar.YEAR));
    }


    /**
     * <b>获取月份：根据日期</b>
     *
     * @param date
     * @return
     */
    public static String getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return String.valueOf(calendar.get(Calendar.MONTH) + 1);
    }


    /**
     * <b>获取日：根据日期</b>
     *
     * @param date
     * @return
     */
    public static String getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return String.valueOf(calendar.get(Calendar.DATE));
    }


    /**
     * <b>获取本月最后一天：formatted as yyyy-MM-dd</b>
     *
     * @return
     */
    public static String getLastDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat(SHORT_DATE_FORMAT);
        calendar.set(Calendar.DATE, 1);
        calendar.roll(Calendar.DATE, -1);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * <b>获取本月最后一天：</b>
     *
     * @param date    日期
     * @param pattern 日期模式
     * @return
     */
    public static String getLastDayOfMonth(Date date, String pattern) {
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        calendar.roll(Calendar.DATE, -1);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * <b>获取本月第一天：formatted as yyyy-MM-dd</b>
     *
     * @return
     */
    public static String getFirstDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        DateFormat dateFormat = new SimpleDateFormat(SHORT_DATE_FORMAT);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * <b>获取本月第一天：</b>
     *
     * @param date    日期
     * @param pattern 日期模式
     * @return
     */
    public static String getFirstDayOfMonth(Date date, String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * <b>获取本周星期一：formatted as yyyy-MM-dd</b>
     *
     * @return
     */
    public static String getMondayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat(SHORT_DATE_FORMAT);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * <b>获取星期一：根据指定日期和日期模式</b>
     *
     * @param date    日期
     * @param pattern 日期模式
     * @return
     */
    public static String getMondayOfWeek(Date date, String pattern) {
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * <b>获取本周星期天：formatted as yyyy-MM-dd</b>
     *
     * @return
     */
    public static String getSundayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat(SHORT_DATE_FORMAT);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        calendar.add(Calendar.WEEK_OF_YEAR, 1);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * <b>获取星期天：根据指定日期和日期模式</b>
     *
     * @param date    日期
     * @param pattern 日期模式
     * @return
     */
    public static String getSundayOfWeek(Date date, String pattern) {
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        calendar.add(Calendar.WEEK_OF_YEAR, 1);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * <b>获取上周星期天：formatted as yyyy-MM-dd</b>
     *
     * @return
     */
    public static String getSundayOfLastWeek() {
        DateFormat dateFormat = new SimpleDateFormat(SHORT_DATE_FORMAT);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, 1);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * <b>获取上周星期天：</b>
     *
     * @param date    日期
     * @param pattern 日期模式
     * @return
     */
    public static String getSundayOfLastWeek(Date date, String pattern) {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, 1);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * <b>获取上周星期一：formatted as yyyy-MM-dd</b>
     *
     * @return
     */
    public static String getMondayOfLastWeek() {
        DateFormat dateFormat = new SimpleDateFormat(SHORT_DATE_FORMAT);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_MONTH, -1);
        calendar.set(Calendar.DAY_OF_WEEK, 2);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * <b>获取上周星期一：</b>
     *
     * @param date    日期
     * @param pattern 日期模式
     * @return
     */
    public static String getMondayOfLastWeek(Date date, String pattern) {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_MONTH, -1);
        calendar.set(Calendar.DAY_OF_WEEK, 2);
        calendar.setTime(date);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * <b>获取上月第一天：formatted as yyyy-MM-dd</b>
     *
     * @return
     */
    public static String getFirstDayOfLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DATE, 1);
        DateFormat dateFormat = new SimpleDateFormat(SHORT_DATE_FORMAT);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * <b>获取上月第一天：</b>
     *
     * @param date    日期
     * @param pattern 日期模式
     * @return
     */
    public static String getFirstDayOfLastMonth(Date date, String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DATE, 1);
        calendar.setTime(date);
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(calendar.getTime());
    }


    /**
     * <b>获取上月最后一天：formatted as yyyy-MM-dd</b>
     *
     * @return
     */
    public static String getLastDayOfLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.roll(Calendar.DATE, -1);
        DateFormat dateFormat = new SimpleDateFormat(SHORT_DATE_FORMAT);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * <b>获取上月最后一天：</b>
     *
     * @param date    日期
     * @param pattern 日期模式
     * @return
     */
    public static String getLastDayOfLastMonth(Date date, String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.roll(Calendar.DATE, -1);
        calendar.setTime(date);
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(calendar.getTime());
    }

    public static int getAge(String dateOfBirth) {
        int age = 0;
        Calendar born = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        if (dateOfBirth != null) {
            now.setTime(new Date());
            born.setTime(parseDateString(dateOfBirth));
            if (born.after(now)) {
                throw new IllegalArgumentException(
                        "Can't be born in the future");
            }
            age = now.get(Calendar.YEAR) - born.get(Calendar.YEAR);
            if (now.get(Calendar.DAY_OF_YEAR) < born.get(Calendar.DAY_OF_YEAR)) {
                age -= 1;
            }
        }
        return age;
    }

    /**
     * 获取当天的开始
     *
     * @return
     */
    public static Date getDayStart() {
        Calendar currentDate = new GregorianCalendar();
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        return (Date) currentDate.getTime().clone();
    }

    /**
     * 获取当天结束
     *
     * @return
     */
    public static Date getDayEnd() {
        Calendar currentDate = new GregorianCalendar();
        currentDate.set(Calendar.HOUR_OF_DAY, 23);
        currentDate.set(Calendar.MINUTE, 59);
        currentDate.set(Calendar.SECOND, 59);
        return (Date) currentDate.getTime().clone();
    }

    public static String getYesterdayDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(SHORT_DATE_FORMAT);
        return dateFormat.format(org.apache.commons.lang3.time.DateUtils.addDays(date, -1));
    }

    public static String getTomorrowDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(SHORT_DATE_FORMAT);
        return dateFormat.format(org.apache.commons.lang3.time.DateUtils.addDays(date, 1));
    }

    static class Month {

        public static final String JANUARY = "January";
        public static final String FEBRUARY = "February";
        public static final String MARCH = "March";
        public static final String APRIL = "April";
        public static final String MAY = "May";
        public static final String JUNE = "June";
        public static final String JULY = "July";
        public static final String AUGUST = "August";
        public static final String SEPTEMBER = "September";
        public static final String OCTOBER = "October";
        public static final String NOVEMBER = "November";
        public static final String DECEMBER = "December";

        public static final String JAN = "Jan";
        public static final String FEB = "Feb";
        public static final String MAR = "Mar";
        public static final String APR = "Apr";
        //	public static final String MAY = "May";
        public static final String JUN = "Jun";
        public static final String JUL = "Jul";
        public static final String AUG = "Aug";
        public static final String SEP = "Sep";
        public static final String OCT = "Oct";
        public static final String NOV = "Nov";
        public static final String DEC = "Dec";

        public static final String JAN_NUMBER = "01";
        public static final String FEB_NUMBER = "02";
        public static final String MAR_NUMBER = "03";
        public static final String APR_NUMBER = "04";
        public static final String MAY_NUMBER = "05";
        public static final String JUN_NUMBER = "06";
        public static final String JUL_NUMBER = "07";
        public static final String AUG_NUMBER = "08";
        public static final String SEP_NUMBER = "09";
        public static final String OCT_NUMBER = "10";
        public static final String NOV_NUMBER = "11";
        public static final String DEC_NUMBER = "12";

    }

    public static String getMonthByName(String monthName) {
        //String month = monthMap.get(monthName);
        String month = "";
        if (StringUtils.isBlank(month)) {
            if (Month.JANUARY.equals(monthName)) {
                month = Month.JAN_NUMBER;
            } else if (Month.FEBRUARY.equals(monthName)) {
                month = Month.FEB_NUMBER;
            } else if (Month.MARCH.equals(monthName)) {
                month = Month.MAR_NUMBER;
            } else if (Month.APRIL.equals(monthName)) {
                month = Month.APR_NUMBER;
            } else if (Month.MAY.equals(monthName)) {
                month = Month.MAY_NUMBER;
            } else if (Month.JUNE.equals(monthName)) {
                month = Month.JUN_NUMBER;
            } else if (Month.JULY.equals(monthName)) {
                month = Month.JUL_NUMBER;
            } else if (Month.AUGUST.equals(monthName)) {
                month = Month.AUG_NUMBER;
            } else if (Month.SEPTEMBER.equals(monthName)) {
                month = Month.SEP_NUMBER;
            } else if (Month.OCTOBER.equals(monthName)) {
                month = Month.OCT_NUMBER;
            } else if (Month.NOVEMBER.equals(monthName)) {
                month = Month.NOV_NUMBER;
            } else if (Month.DECEMBER.equals(monthName)) {
                month = Month.DEC_NUMBER;
            }
        }
        return month;
    }

    public static String getMonthByShortName(String shortName) {
        if (!StringUtils.isBlank(shortName)) {
            if (Month.JAN.equals(shortName)) {
                return Month.JAN_NUMBER;
            } else if (Month.FEB.equals(shortName)) {
                return Month.FEB_NUMBER;
            } else if (Month.MAR.equals(shortName)) {
                return Month.MAR_NUMBER;
            } else if (Month.APR.equals(shortName)) {
                return Month.APR_NUMBER;
            } else if (Month.MAR.equals(shortName)) {
                return Month.MAR_NUMBER;
            } else if (Month.JUN.equals(shortName)) {
                return Month.JUN_NUMBER;
            } else if (Month.JUL.equals(shortName)) {
                return Month.JUL_NUMBER;
            } else if (Month.AUG.equals(shortName)) {
                return Month.AUG_NUMBER;
            } else if (Month.SEP.equals(shortName)) {
                return Month.SEP_NUMBER;
            } else if (Month.OCT.equals(shortName)) {
                return Month.OCT_NUMBER;
            } else if (Month.NOV.equals(shortName)) {
                return Month.NOV_NUMBER;
            } else if (Month.DEC.equals(shortName)) {
                return Month.DEC_NUMBER;
            }
        }
        return null;
    }

    public static String getShortMonthName(String month) {
        String result = null;
        int m = Integer.parseInt(month);
        switch (m) {
            case 1:
                result = "Jan";
                break;
            case 2:
                result = "Feb";
                break;
            case 3:
                result = "Mar";
                break;
            case 4:
                result = "Apr";
                break;
            case 5:
                result = "May";
                break;
            case 6:
                result = "Jun";
                break;
            case 7:
                result = "Jul";
                break;
            case 8:
                result = "Aug";
                break;
            case 9:
                result = "Sep";
                break;
            case 10:
                result = "Oct";
                break;
            case 11:
                result = "Nov";
                break;
            case 12:
                result = "Dec";
                break;
            default:
                break;
        }
        return result;
    }


    public static final String SHORT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String SHORT_DATE_GBK_FORMAT = "yyyy年MM月dd日";
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String DATE_GBK_FORMAT = "yyyy年MM月dd日 HH时mm分";
    public static final String LONG_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String LONG_DATE_GBK_FORMAT = "yyyy年MM月dd日 HH时mm分ss秒";
    public static final String MAIL_DATE_FORMAT = "yyyyMMddHHmmss";
    public static final String MAIL_DATE_HHMM_FORMAT = "HH:mm";
    public static final String FULL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";
    public static final String FULL_DATE_GBK_FORMAT = "yyyy年MM月dd日 HH时mm分ss秒SSS毫秒";
    public static final String FULL_DATE_COMPACT_FORMAT = "yyyyMMddHHmmssSSS";
    public static final String LDAP_DATE_FORMAT = "yyyyMMddHHmm'Z'";
    public static final String US_LOCALE_DATE_FORMAT = "EEE MMM dd HH:mm:ss zzz yyyy";

    public static final String MAIL_DATE_DT_PART_FORMAT = "yyyyMMdd";
    public static final String MAIL_TIME_TM_PART_FORMAT = "HHmmss";
    public static final String LONG_DATE_TM_PART_FORMAT = "HH:mm:ss";
    public static final String Long_DATE_TM_PART_GBK_FORMAT = "HH时mm分ss秒";
    public static final String MAIL_DATA_DTM_PART_FORMAT = "MM月dd日HH:mm";
    public static final String POINT_DATA_DTM_PART_FORMAT = "yyyy.MM.dd";

    public static final String DEFAULT_DATE_FORMAT = US_LOCALE_DATE_FORMAT;

    public final static long NANO_ONE_SECOND = 1000;
    public final static long NANO_ONE_MINUTE = 60 * NANO_ONE_SECOND;
    public final static long NANO_ONE_HOUR = 60 * NANO_ONE_MINUTE;
    public final static long NANO_ONE_DAY = 24 * NANO_ONE_HOUR;

    private DateUtil() {
    }
}
