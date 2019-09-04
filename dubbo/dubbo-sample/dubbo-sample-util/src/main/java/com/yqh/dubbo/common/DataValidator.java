package com.yqh.dubbo.common;

/**
 * @author yangq
 * Create time in 2018-07-24 14:42
 */
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.Assert;

import java.text.ParseException;
import java.util.Date;

/**
 * <dl>
 *    <dt><b>Title:</b></dt>
 *    <dd>
 *    	数据校验器
 *    </dd>
 *    <dt><b>Description:</b></dt>
 *    <dd>
 *    	<p>facade of all kinds of relative utils,mainly be designed for using outside the framework
 *    </dd>
 * </dl>
 *
 * @author eric
 * @version 1.0, 2009-12-28
 * @since framework-1.4
 *
 */
public class DataValidator {
    /**
     * 检验 日期是否在指定区间内，如果格式错误，返回false
     *
     * 如果maxDateStr或minDateStr为空则比较时变为正负无穷大，如果都为空，则返回false
     *
     * @param aDate
     * @param minDateStr 必须是yyyy-MM-dd格式，时分秒为00:00:00
     * @param maxDateStr 必须是yyyy-MM-dd格式，时分秒为00:00:00
     * @return
     */
    public static final boolean isDateBetween(Date aDate, String minDateStr, String maxDateStr){
        return DateUtil.isDateBetween(aDate, minDateStr, maxDateStr);
    }

    /**
     * 判断字符串是否为空
     *
     * 没办法,有的程序员不习惯用Apache Common或Spring的工具类
     *
     * @param str
     * @return
     */
    public static final boolean isStrNotBlank(String str){
        return StringUtils.isNotBlank(str);
    }

    /**
     * 检查字符串是否为空
     * @param str
     */
    public static final void checkStringNotBlank(String str){
        Assert.hasText(str);
    }

    /**
     * 检查字符串是否为空，并且长度是否为指定值
     * @param str
     * @param l 长度
     * @return
     */
    public static final boolean isStrLenEqual(String str,int l){
        return (StringUtils.isNotBlank(str)&&(str.trim().length()==l));
    }

    /**
     * 检查字符串是否为空，并且长度是否小于指定值
     * @param str
     * @param l 长度
     * @return
     */
    public static final boolean isStrLenLess(String str,int l){
        return (StringUtils.isNotBlank(str)&&(str.trim().length()<l));
    }

    /**
     * 检查字符串是否为空，并且长度是否小等于指定值
     * @param str
     * @param l 长度
     * @return
     */
    public static final boolean isStrLenLessEqual(String str,int l){
        return (StringUtils.isNotBlank(str)&&(str.trim().length()<=l));
    }

    /**
     * 判断（如"0123","123L","12.3S"等带有小数点和后缀的）字串，是否代表数字类型
     * @param str
     * @return
     */
    public static final boolean isNumber(String str){
        return NumberUtils.isNumber(str);
    }

    /**
     * 检查（如"0123","123L","12.3S"等带有小数点和后缀的）字串，是否代表数字类型
     * @param str
     */
    public static final void checkNumber(String str){
        Assert.isTrue(isNumber(str),"'"+str+"' must be a Number format here.");
    }

    /**
     * 判断字符串中只含有数字字符
     * @param str
     * @return
     */
    public static final boolean isDigits(String str){
        return NumberUtils.isDigits(str);
    }

    /**
     * 判断Long、Integer、Short、Double、Float等数字是否为空或者0
     * @param number
     * @return
     */
    public static final boolean isNumberNotNullOrZero(Number number){
        return (number!=null&& number.doubleValue()!=0);
    }

    /**
     * 检查Long、Integer、Short、Double、Float等数字是否为空或者0
     * @param number
     */
    public static final void checkNumberNotNullOrZero(Number number){
        Assert.isTrue(isNumberNotNullOrZero(number),"Number must not be null or zero.");
    }

    /**
     * 判断字串是否符合yyyy-MM-dd格式
     * @param
     * @return
     */
    public static final boolean isShortDateStr(String aDateStr){
        try {
            DateUtil.parseShortDateString(aDateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * 检查字串是否符合yyyy-MM-dd格式
     * @param
     */
    public static final void checkShortDateStr(String aDateStr){
        Assert.isTrue(isShortDateStr(aDateStr),"The str-'"+aDateStr+"' must match 'yyyy-MM-dd' format.");
    }

    /**
     * 判断字串是否符合yyyy-MM-dd HH:mm:ss格式
     * @param
     * @return
     */
    public static final boolean isLongDateStr(String aDateStr){
        try {
            DateUtil.parseLongDateString(aDateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * 检查字串是否符合yyyy-MM-dd HH:mm:ss格式
     * @param
     */
    public static final void checkLongDateStr(String aDateStr){
        Assert.isTrue(isLongDateStr(aDateStr),"The str-'"+aDateStr+"' must match 'yyyy-MM-dd HH:mm:ss' format.");
    }

    /**
     * 判断字串是否符合yyyyMMddHHmmss格式
     * @param
     * @return
     */
    public static final boolean isMailDateStr(String aDateStr){
        try {
            DateUtil.parseMailDateString(aDateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * 判断字串是否符合yyyyMMdd格式
     * @param
     * @return
     */
    public static final boolean isMailDateDtPartStr(String aDateStr){
        try {
            DateUtil.parseMailDateDtPartString(aDateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * 检查字串是否符合yyyyMMddHHmmss格式
     * @param
     */
    public static final void checkMailDateStr(String aDateStr){
        Assert.isTrue(isMailDateStr(aDateStr),"The str-'"+aDateStr+"' must match 'yyyyMMddHHmmss' format.");
    }

    /**
     * 判断字串是否符合指定的日期格式
     * @param
     * @return
     */
    public static final boolean isDateStrMatched(String aDateStr,String formatter){
        try {
            DateUtil.parser(aDateStr, formatter);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * 判断对象数组是否为空
     * @param
     * @param object
     * @return
     */
    public static final boolean isArrayNotEmpty(Object[] object){
        return !ArrayUtils.isEmpty(object);
    }

    /**
     * 检查对象数组是否为空
     * @param
     * @param object
     */
    public static final void checkArrayNotEmpty(Object[] object){
        Assert.notEmpty(object);
    }
}

