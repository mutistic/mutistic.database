package com.mutisitc.utils;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.data.redis.core.ZSetOperations.TypedTuple;

/**
 * @program 打印工具类
 * @description
 * @author mutisitic
 * @date 2018年8月9日
 */
public class PrintUtil {
	private final static String T_LEFT = "【";
	private final static String T_RIGTH = "】";
	// private final static String LINE_LONG = "------------------------";
	// private final static String LINE_LONG_LN = "\n" + LINE_LONG;
	// private final static String LINE_SHORT = "----------------";
	// private final static String LINE_SHORT_LN = "\n" + LINE_SHORT;

	public static void println() {
		System.out.println();
	}

	public static void ln(Object str) {
		System.out.println(str);
	}

	public static String err(Object str) {
		String val = (str == null ? "" : str + "");
		System.err.println(val);
		return val;
	}

	public static String one(Object str) {
		String val = "\n" + (str == null ? "" : str);
		System.out.println(val);
		return val;
	}

	public static String two(Object str, Object obj) {
		String val = "" + (str == null ? "" : str) + (obj == null ? "" : T_LEFT + obj + T_RIGTH);
		System.out.println(val);
		return val;
	}

	public static String three(Object str, Object obj) {
		String val = "  " + (str == null ? "" : str) + (obj == null ? "" : T_LEFT + obj + T_RIGTH);
		System.out.println(val);
		return val;
	}

	@SuppressWarnings("rawtypes")
	public static String toString(Map obj) {
		if (obj == null) {
			return "";
		}

		StringBuffer str = new StringBuffer(64);
		str.append("[");
		for (Object key : obj.keySet()) {
			str.append(key + ":" + obj.get(key) + ",");
		}

		return str.substring(0, str.length() - 1) + "]";
	}

	public static String toString(List<Entry<String, String>> obj) {
		if (obj == null) {
			return "";
		}

		StringBuffer str = new StringBuffer(64);
		for (Entry<String, String> entry : obj) {
			str.append("[key:" + entry.getKey() + ",value:" + entry.getValue() + "]");
		}

		return str.toString();
	}

	public static <E> String toString(Set<TypedTuple<E>> obj) {
		if (obj == null) {
			return "";
		}

		StringBuffer str = new StringBuffer(64);
		for (TypedTuple<E> tuple : obj) {
			str.append("[value:" + tuple.getValue() + ",score:" + tuple.getScore() + "]");
		}

		return str.toString();
	}

}
