package com.mutistic.redis.jedis;

import com.mutisitc.utils.JedisUtil;
import com.mutisitc.utils.PrintUtil;

import redis.clients.jedis.Jedis;

/**
 * @program 使用RedisTemplate API操作String数据类型
 * @description
 * @author mutisitic
 * @date 2018年11月21日
 */
public class TestString {
	public static void main(String[] args) {
		PrintUtil.one("使用Jedis API操作String数据类型：");

		Jedis jedis = JedisUtil.getJedis();
		jedis.flushDB();
		PrintUtil.two("1、通过@Autowired自动注入org.springframework.data.redis.core.StringRedisTemplate实例：", jedis);

		showByGetAndSet(jedis);

		JedisUtil.close(jedis);
	}

	/**
	 * @description 字符串的设置与获取
	 * @author mutisitic
	 * @date 2018年11月23日
	 */
	private static void showByGetAndSet(Jedis jedis) {
		PrintUtil.one("2、字符串的设置与获取：");
		String result = jedis.set("testSET", "字符串的设置与获取");
		PrintUtil.two("2.1、set(String key, String value)：为字符串键设置值[SET key value]", "key=testSET, result=" + result);

		String value = jedis.get("testSET");
		PrintUtil.two("2.2、get(Object key)：获取字符串键的值[GET key]", "key=testSET, value=" + value);

		String oldValue = jedis.getSet("testSET", "为字符串键设置值，返回旧值");
		PrintUtil.two("2.3、getSet(String key, String value)：为字符串键设置值，返回旧值[GETSET key new-value]",
				"key=testSET, oldValue=" + oldValue);

		String result2 = jedis.set("testSETByTimeout", "为字符串键设置值、方式、过期时间", "nx", "ex", 100l);
		PrintUtil.two(
				"2.3、set(String key, String value, String nxxx, String expx, long time)："
						+ "为字符串键设置值、方式、过期时间[SET key value [EX seconds] [PX milliseconds]]",
				"key=testSETByTimeout, result=" + result2);
	}

}