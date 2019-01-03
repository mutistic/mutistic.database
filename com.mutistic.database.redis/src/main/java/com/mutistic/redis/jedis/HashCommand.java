package com.mutistic.redis.jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.mutisitc.utils.JedisUtil;
import com.mutisitc.utils.PrintUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

/**
 * @program 使用Jedis API操作Hash数据类型
 * @description
 * @author mutisitic
 * @date 2018年11月30日
 */
public class HashCommand {
	public static void main(String[] args) {
		PrintUtil.one("使用Jedis API操作Hash数据类型：");

		Jedis jedis = JedisUtil.getJedis();
		jedis.flushDB();

		showByGetAndSet(jedis);
		showByIncrAndDecr(jedis);
		showByManager(jedis);
		JedisUtil.close(jedis);
	}

	/**
	 * @description Hash的设置与获取
	 * @author mutisitic
	 * @date 2018年11月30日
	 */
	private static void showByGetAndSet(Jedis jedis) {
		PrintUtil.one("1、Hash的设置与获取：");

		Long result = jedis.hset("Hash:HSET", "key1", "为散列中的键设置值");
		PrintUtil.two("1.1、hset(String key, String field, String value)：为散列中的键设置值【HSET hash key value】",
				"key=Hash:HSET, field=key1, result=" + result);

		Long result2 = jedis.hsetnx("Hash:HSET", "key2", "仅在散列中的给定键尚未有值的情况下，为该键设置值");
		PrintUtil.two(
				"1.2、hsetnx(String key, String field, String value)：仅在散列中的给定键尚未有值的情况下，为该键设置值【HSETNX hash key value】",
				"key=Hash:HSET, field=key2, result=" + result2);

		String value = jedis.hget("Hash:HSET", "key2");
		PrintUtil.two("1.3、hget(String key, String field)：返回散列中与给定键相关联的值【HGET hash key】",
				"key=Hash:HGET, field=key2, value=" + value);

		Map<String, String> valueMap = new HashMap<String, String>();
		valueMap.put("key1", "一次为散列中的多个键设置值");
		valueMap.put("key2", "为key2设置值");
		String result3 = jedis.hmset("Hash:HMSET", valueMap);
		PrintUtil.two(
				"1.4、hmset(String key, Map<String, String> hash)：一次为散列中的多个键设置值【HMSET hash key value [key2 value]】",
				"key=Hash:HMSET, field=key1,key2, result=" + result3);

		List<String> valueList = jedis.hmget("Hash:HMSET", "key1", "key2");
		PrintUtil.two("1.5、hmget(String key, String... fields)：一次获取散列中多个键的值【HMGET hash key [key2 ...]】",
				"key=Hash:HMSET, field=key1,key2, valueList=" + valueList);
	}

	/**
	 * @description Hash的自增与自减
	 * @author mutisitic
	 * @date 2018年11月30日
	 * @param jedis
	 */
	private static void showByIncrAndDecr(Jedis jedis) {
		PrintUtil.one("2、Hash的自增与自减：");

		Long result = jedis.hincrBy("Hash:HINCRBY", "key1", 1);
		PrintUtil.two(
				"2.1、hincrBy(String key, String field, long value)：为散列中给定键储存的整数值加上指定的整数增量【HINCRBY hash key increment】",
				"key=Hash:HINCRBY, field=key1, result=" + result);

		Long result2 = jedis.hincrBy("Hash:HINCRBY", "key1", -1);
		PrintUtil.two(
				"2.2、hincrBy(String key, String field, long -value)：为散列中给定键储存的整数值加上指定的整数减量【HINCRBY hash key -increment】",
				"key=Hash:HINCRBY, field=key1, result=" + result2);

		Double result3 = jedis.hincrByFloat("Hash:HINCRBYFLOAT", "key1", 0.5d);
		PrintUtil.two(
				"2.3、hincrByFloat(String key, String field, double value)：为散列中给定键储存的数字值加上指定的浮点数增量【HINCRBYFLOAT key increment】",
				"key=Hash:HINCRBYFLOAT, field=key1, result=" + result3);

		Double result4 = jedis.hincrByFloat("Hash:HINCRBYFLOAT", "key1", -0.8d);
		PrintUtil.two(
				"2.4、hincrByFloat(String key, String field, double -value)：为散列中给定键储存的数字值加上指定的浮点数减量【HINCRBYFLOAT key -increment】",
				"key=Hash:HINCRBYFLOAT, field=key1, result=" + result4);
	}

	/**
	 * @description Hash的检测与管理
	 * @author mutisitic
	 * @date 2018年11月30日
	 * @param jedis
	 */
	private static void showByManager(Jedis jedis) {
		PrintUtil.one("3、Hash的检测与管理：");

		Boolean exists = jedis.hexists("Hash:HSET", "key1");
		PrintUtil.two("3.1、hexists(String key, String field)：检查给定键在散列中是否存在【HEXISTS hash key】",
				"key=Hash:HSET, field=key1, exists=" + exists);

		Long length = jedis.hlen("Hash:HSET");
		PrintUtil.two("3.2、hlen(String key)：返回散列包含的键值对数量【HLEN hash】", "key=Hash:HSET, length=" + length);

		Long result = jedis.hdel("Hash:HSET", "key1", "key2");
		PrintUtil.two("3.3、hdel(String key, String... fields)：删除散列中的一个或多个键，以及这些键的值批量获取散列键值【HDEL hash key [key2 ...]】",
				"key=Hash:HSET, field=key1,key2, result=" + result);

		Set<String> keySet = jedis.hkeys("Hash:HMSET");
		PrintUtil.two("3.4、hkeys(String key)：返回散列包含的所有键【HKEYS hash】", "key=Hash:MHSET, keySet=" + keySet);

		List<String> valueList = jedis.hvals("Hash:HMSET");
		PrintUtil.two("3.5、hvals(String key)：返回散列包含的所有键的值【HVALS hash】", "key=Hash:MHSET, valueList=" + valueList);

		Map<String, String> allMap = jedis.hgetAll("Hash:HMSET");
		PrintUtil.two("3.6、hgetAll(String key)：返回散列包含的所有键值对【HGETALL hash】",
				"key=Hash:MHSET, allMap=" + PrintUtil.toString(allMap));
		
		ScanResult<Entry<String, String>> scanResult = jedis.hscan("Hash:HMSET", "0");
		PrintUtil.two("3.7、hscan(String key, String cursor)：以渐进的方式返回散列包含的键值对【HSCAN hash cursor】",
				"key=Hash:HMSET, scanResult=" + PrintUtil.toString(scanResult.getResult()));
		
		ScanParams params = new ScanParams();
		params.match("key*");
		params.count(2);
		ScanResult<Entry<String, String>> scanResult2 = jedis.hscan("Hash:HMSET", "2", params);
		PrintUtil.two("3.7.1、hscan(String key, String cursor, ScanParams params)：以渐进的方式返回散列包含的键值对【HSCAN hash cursor [MATCH pattern] [COUNT count]】",
				"key=Hash:HMSET, ScanParams={match:key*,count:2}, scanResult=" + PrintUtil.toString(scanResult2.getResult()));
	}
}