package com.mutistic.redis.jedis;

import com.mutisitc.utils.JedisUtil;
import com.mutisitc.utils.PrintUtil;

import redis.clients.jedis.Jedis;

/**
 * @program 使用Jedis API管理过期时间
 * @description 
 * @author mutisitic
 * @date 2019年1月11日
 */
public class ExpirationCommand {

	public static void main(String[] args) {
		PrintUtil.one("使用Jedis API管理过期时间：");

		Jedis jedis = JedisUtil.newJedis();
		
		prepareTestData(jedis);
		showBySet(jedis);
		showByGet(jedis);
		
		JedisUtil.close(jedis);
	}

	/**
	 * @description 准备测试数据 
	 * @author mutisitic
	 * @date 2019年1月11日
	 * @param jedis
	 */
	private static void prepareTestData(Jedis jedis) {
		jedis.set("t1", "a");
		jedis.set("t2", "b");
		jedis.set("t3", "c");
		jedis.set("t4", "d");
	}
	
	/**
	 * @description 1、设置过期时间
	 * @author mutisitic
	 * @date 2019年1月10日
	 * @param jedis
	 */
	private static void showBySet(Jedis jedis) {
		PrintUtil.one("1、设置过期时间：");
		
		Long result = jedis.expire("t1", 10);
		PrintUtil.two("1.1、expire(String key, int seconds)：为键设置秒级精度的过期时间【EXPIRE key seconds】", 
				"key=t1, result=" + result);
		
		Long result2 = jedis.pexpire("t2", 10000l);
		PrintUtil.two("1.2、pexpire(String key, long milliseconds)：为键设置毫秒级精度的过期时间【PEXPIRE key milliseconds】", 
				"key=t2, result=" + result2);
		
		Long result3 = jedis.expireAt("t3", 10);
		PrintUtil.two("1.3、expireAt(String key, long unixTime)：为键设置秒级精度的过期 UNIX 时间戳【EXPIREAT key timestamp-in-seconds】", 
				"key=t3, result=" + result3);
		
		Long result4 = jedis.pexpireAt("t4", 100000l);
		PrintUtil.two("1.4、pexpireAt(String key, long millisecondsTimestamp)：为键设置毫秒精度的过期 UNIX 时间戳【PEXPIREAT key timestamp-in-milliseconds】", 
				"key=t4, result=" + result4);
	}
	
	/**
	 * @description 2、查看剩余存活时间
	 * @author mutisitic
	 * @date 2019年1月11日
	 * @param jedis
	 */
	private static void showByGet(Jedis jedis) {
		PrintUtil.one("2、查看剩余存活时间：");
		
		Long seconds = jedis.ttl("t1");
		PrintUtil.two("2.1、ttl(String key)：以秒级精度返回给定键的剩余存活时间【TTL key】", 
				"key=t1, seconds=" + seconds);
		
		Long milliseconds = jedis.pttl("t2");
		PrintUtil.two("2.2、pttl(String key)：以毫秒级精度返回给定键的剩余存活时间移除过期时间【PTTL key】", 
				"key=t2, milliseconds=" + milliseconds);
		
		Long result = jedis.persist("t2");
		PrintUtil.two("2.2、persist(String key)：移除键的过期时间【PERSIST key】", 
				"key=t2, result=" + result);
	}
}
