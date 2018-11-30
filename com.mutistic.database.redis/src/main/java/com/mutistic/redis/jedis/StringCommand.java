package com.mutistic.redis.jedis;

import java.util.List;

import com.mutisitc.utils.JedisUtil;
import com.mutisitc.utils.PrintUtil;

import redis.clients.jedis.Jedis;

/**
 * @program 使用Jedis API操作String数据类型
 * @description
 * @author mutisitic
 * @date 2018年11月21日
 */
public class StringCommand {
	public static void main(String[] args) {
		PrintUtil.one("使用Jedis API操作String数据类型：");

		Jedis jedis = JedisUtil.getJedis();
		jedis.flushDB();

		showByGetAndSet(jedis);
		showByBatch(jedis);
		showByUpdate(jedis);
		showByIncrAndDecr(jedis);
		
		JedisUtil.close(jedis);
	}

	/**
	 * @description 字符串的设置与获取
	 * @author mutisitic
	 * @date 2018年11月23日
	 */
	private static void showByGetAndSet(Jedis jedis) {
		PrintUtil.one("1、字符串的设置与获取：");
		
		String result = jedis.set("String:SET", "字符串的设置与获取");
		PrintUtil.two("1.1、set(String key, String value)：为字符串键设置值【SET key value】", "key=String:SET, result=" + result);

		String value = jedis.get("String:SET");
		PrintUtil.two("1.2、get(Object key)：获取字符串键的值【GET key】", "key=String:SET, value=" + value);

		String oldValue = jedis.getSet("String:GETSET", "为字符串键设置值，返回旧值");
		PrintUtil.two("1.3、getSet(String key, String value)：为字符串键设置值，返回旧值【GETSET key new-value】",
				"key=String:SET, oldValue=" + oldValue);

		String result2 = jedis.set("String:SETByTimeout", "为字符串键设置值、方式、过期时间", "nx", "ex", 100l);
		PrintUtil.two(
				"1.3、set(String key, String value, String nxxx, String expx, long time)："
						+ "为字符串键设置值、方式、过期时间【SET key value [EX seconds][PX milliseconds]】",
				"key=String:SETByTimeout, result=" + result2);
		
		Long result3 = jedis.setnx("String:SETNX", "仅在字符串键尚未有值的情况下，为它设置值");
		PrintUtil.two("1.4、setnx(String key, String value)：为仅在字符串键尚未有值的情况下，为它设置值【SETNX key value】",
				"key=String:SETNX, result=" + result3);
		
		String result4 = jedis.setex("String:SETEX", 100, "为字符串键设置值和秒级精度的过期时间");
		PrintUtil.two("1.5、setex(String key, int seconds, String value)：为字符串键设置值和秒级精度的过期时间【SETEX key seconds value】",
				"key=String:SETEX, result=" + result4);
		
		jedis.psetex("String:PSETEX", 10000l, "为字符串键设置值和毫秒级精度的过期时间");
		PrintUtil.two("1.6、psetex(String key, long milliseconds, String value)：为字符串键设置值和毫秒级精度的过期时间【PSETEX key milliseconds value】",
				"key=String:PSETEX, result=" + result4);
	}
	
	/**
	 * @description 字符串的批量设置与获取
	 * @author mutisitic
	 * @date 2018年11月30日
	 * @param jedis
	 */
	private static void showByBatch(Jedis jedis) {
		PrintUtil.one("2、字符串的批量设置与获取：");
		
		String result = jedis.mset("String:MSET", "一次为多个字符串键设置值", "String:MSET2", "批量为字符串键设置值");
		PrintUtil.two("2.1、mset(String... keysvalues)：一次为多个字符串键设置值【MSET key value [key2 value ...]】", 
				"key=String:MSET,String:MSET2, result=" + result);
		
		List<String> valueList = jedis.mget("String:MSET", "String:MSET2");
		PrintUtil.two("2.2、mget(String... keys)：一次获取多个字符串键的值【MGET key [key2 ...]】", 
				"key=String:MSET,String:MSET2, values=" + valueList);
		
		Long result2 = jedis.msetnx("String:MSETNX", "仅在所有给定字符串键都尚未有值的情况下，为它们设置值", "String:MSETNX2", "批量为字符串键未有值的情况设置值");
		PrintUtil.two("2.3、msetnx(String... keysvalues)：仅在所有给定字符串键都尚未有值的情况下，为它们设置值【MSETNX key value [key2 value ...]】", 
				"key=String:MSETNX,String:MSETNX2, result=" + result2);
	}

	/**
	 * @description 字符串的获取或修改内容
	 * @author mutisitic
	 * @date 2018年11月30日
	 * @param jedis
	 */
	private static void showByUpdate(Jedis jedis) {
		PrintUtil.one("3、字符串的获取或修改内容：");
		
		Long length = jedis.strlen("String:STRLEN");
		PrintUtil.two("3.1、strlen(String key)：获取字符串值的长度【STRLEN key】", "key=String:STRLEN, length=" + length);
		
		Long length2 = jedis.setrange("String:SETRANGE", 1, "对字符串值在指定索引位置上的内容进行修改");
		PrintUtil.two("3.2、setrange(String key, long offset, String value)：对字符串值在指定索引位置上的内容进行修改【SETRANGE key offset value】", 
				"key=String:SETRANGE, length=" + length2);

		String value = jedis.getrange("String:GETRANGE", 1, jedis.strlen("String:SET"));
		PrintUtil.two("3.3、getrange(String key, long startOffset, long endOffset)：获取字符串值在指定索引范围内的内容【GETRANGE key start end】", 
				"key=String:GETRANGE, value=" + value);
		
		Long length3 = jedis.append("String:APPEND", "将指定的内容追加到字符串值的末尾");
		PrintUtil.two("3.3、append(String key, String value)：将指定的内容追加到字符串值的末尾【APPEND key value】", 
				"key=String:APPEND, length=" + length3);
	}
	
	/**
	 * @description 字符串的自增与自减
	 * @author mutisitic
	 * @date 2018年11月30日
	 * @param jedis
	 */
	private static void showByIncrAndDecr(Jedis jedis) {
		PrintUtil.one("4、字符串的自增与自减：");
		
		Long result = jedis.incr("String:INCR");
		PrintUtil.two("4.1、incr(String key)：为字符串键储存的整数值加上一【INCR key】", "key=String:INCR, result=" + result);
		
		result = jedis.decr("String:INCR");
		PrintUtil.two("4.2、decr(String key)：为字符串键储存的整数值减上一【DECR key】", "key=String:INCR, result=" + result);
		
		result = jedis.incrBy("String:INCR", 10);
		PrintUtil.two("4.3、incrBy(String key, long integer)：为字符串键储存的整数值加上指定的整数增量【INCRBY key increment】",
				"key=String:INCR, result=" + result);

		result = jedis.decrBy("String:INCR", 5);
		PrintUtil.two("4.3、decrBy(String key, long integer)：为字符串键储存的整数值减去指定的整数减量【DECRBY key increment】",
				"key=String:INCR, result=" + result);
		
		Double result2 = jedis.incrByFloat("String:INCR", 0.5);
		PrintUtil.two("4.3、incrByFloat(String key, double value)：为字符串键储存的数字值加上指定的浮点数增量【INCRBYFLOAT key increment】",
				"key=String:INCR, result=" + result2);
		
		result2 = jedis.incrByFloat("String:INCR", -0.8);
		PrintUtil.two("4.3.2、incrByFloat(String key, double -value)：为字符串键储存的数字值加上指定的浮点数减量【INCRBYFLOAT key -increment】",
				"key=String:INCR, result=" + result2);
	}
}