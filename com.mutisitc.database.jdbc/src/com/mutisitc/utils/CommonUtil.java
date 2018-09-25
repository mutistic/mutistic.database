package com.mutisitc.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program 普通工具类 
 * @description 
 * @author mutisitic
 * @date 2018年9月25日
 */
public class CommonUtil {

	/** 日期格式：yyyy-MM-dd hh24:mi:ss*/
	public final static String YDMS = "yyyy-MM-dd HH:mm:ss";
	/**
	 * @description 获取当前日期字符串
	 * @author mutisitic
	 * @date 2018年9月25日
	 * @return 当前日期字符串
	 */
	public static String getCurrentTime() {
		return new SimpleDateFormat(YDMS).format(new Date());
	}
	
	/**
	 * @description 数组转换成字符串
	 * @author mutisitic
	 * @date 2018年9月25日
	 * @param result
	 * @return
	 */
	public static String toString(int[] result) {
		String str = "";
		if(null == result || result.length == 0) {
			return str;
		}
		
		for (int i : result) {
			str += i+",";
		}
		return str.substring(0, str.length()-1);
	}
	
	/**
	 * @description 数组转换成字符串
	 * @author mutisitic
	 * @date 2018年9月25日
	 * @param result
	 * @return
	 */
	public static String toString(String[] result) {
		String str = "";
		if(null == result || result.length == 0) {
			return str;
		}
		
		for (String i : result) {
			str += i+",";
		}
		return str.substring(0, str.length()-1);
	}
}
