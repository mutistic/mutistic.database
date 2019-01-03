package com.mutistic.redis.jedis;

import java.util.List;
import java.util.Set;

import com.mutisitc.utils.JedisUtil;
import com.mutisitc.utils.PrintUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

/**
 * @program 使用Jedis API操作Set数据类型
 * @description 
 * @author mutisitic
 * @date 2019年1月3日
 */
public class SetCommand {
	public static void main(String[] args) {
		PrintUtil.one("使用Jedis API操作Set数据类型：");

		Jedis jedis = JedisUtil.getJedis();
		jedis.flushDB();

		showByAddAndPop(jedis);
		showByGet(jedis);
		showByOperation(jedis);

		JedisUtil.close(jedis);
	}

	/**
	 * @description 1、Set的元素的添加与移除
	 * @author mutisitic
	 * @date 2019年1月3日
	 * @param jedis
	 */
	private static void showByAddAndPop(Jedis jedis) {
		PrintUtil.one("1、Set的元素的添加与移除：");

		Long size = jedis.sadd("Set:SADD", "将一个或多个元素添加到集合当中", "Hello", "Set", "!");
		PrintUtil.two("1.1、sadd(String key, String... members)：将一个或多个元素添加到集合当中【SADD set element [element ...]】",
				"key=Set:SADD, size=" + size);

		String popValue = jedis.spop("Set:SADD");
		PrintUtil.two("1.2、spop(String key)：随机地移除并返回集合中的某个元素【SPOP set】", "key=Set:SADD, popValue=" + popValue);

		Long size2 = jedis.smove("Set:SADD", "Set:SMOVE", "Hello");
		PrintUtil.two(
				"1.3、smove(String srckey, String dstkey, String member)：将指定的元素从源集合移动到目标集合【SMOVE source_set target_set element】",
				"srckey=Set:SADD, dstkey=Set:SMOVE, member=Hello, size=" + size2);

		Long size3 = jedis.srem("Set:SADD", "Hello");
		PrintUtil.two("1.4、srem(String key, String... members)：移除集合中的一个或多个元素【SREM set element [element ...]】",
				"key=Set:SADD, member=Hello, size=" + size3);
	}

	/**
	 * @description 2、Set的元素的获取与检测
	 * @author mutisitic
	 * @date 2019年1月3日
	 * @param jedis
	 */
	private static void showByGet(Jedis jedis) {
		PrintUtil.one("2、Set的元素的获取与检测：");

		Long size = jedis.scard("Set:SADD");
		PrintUtil.two("2.1、scard(String key)：返回集合包含的元素数量【SCARD set】", "key=Set:SADD, size=" + size);

		Boolean exists = jedis.sismember("Set:SADD", "Set");
		PrintUtil.two("2.2、sismember(String key, String member)：检查集合是否包含了给定的元素【SISMEMBER set element】",
				"key=Set:SADD, member=Set, exists=" + exists);

		String value = jedis.srandmember("Set:SADD");
		PrintUtil.two("2.3、srandmember(String key)：随机地返回集合包含的元素【SRANDMEMBER set】", "key=Set:SADD, value=" + value);

		List<String> valueList = jedis.srandmember("Set:SADD", 2);
		PrintUtil.two("2.4、srandmember(String key, int count)：随机地返回集合包含的元素【SRANDMEMBER set [count]】",
				"key=Set:SADD, count=2, valueList=" + valueList);

		Set<String> valueSet = jedis.smembers("Set:SADD");
		PrintUtil.two("2.5、smembers(String key)：返回集合包含的所有元素【SRANDMEMBER set】", "key=Set:SADD, valueSet=" + valueSet);

		ScanResult<String> scanResult = jedis.sscan("Set:SADD", "0");
		PrintUtil.two("2.6、sscan(String key, String cursor)：以渐进的方式返回集合包含的元素【SSCAN set cursor】",
				"key=Set:SADD, scanResult=" + scanResult.getResult());

		ScanParams scanParams = new ScanParams();
		scanParams.match("*一*");
		scanParams.count(2);
		ScanResult<String> scanResult2 = jedis.sscan("Set:SADD", "0", scanParams);
		PrintUtil.two(
				"2.6.1、sscan(String key, String cursor, ScanParams params))：以渐进的方式返回集合包含的元素【SSCAN set cursor [MATCH pattern] [COUNT count]】",
				"key=Set:SADD, scanParams={match:一*,count:2}, scanResult=" + scanResult2.getResult());
	}

	/**
	 * @description 3、Set的集合运算
	 * @author mutisitic
	 * @date 2019年1月3日
	 * @param jedis
	 */
	private static void showByOperation(Jedis jedis) {
		PrintUtil.one("3、Set的集合运算：");

		Set<String> result = jedis.sdiff("Set:SADD", "Set:SMOVE");
		PrintUtil.two("3.1、sdiff(String... keys)：计算并返回多个集合的差集计算结果【SDIFF set [set ...]】",
				"key1=Set:SADD, key2=Set:SMOVE, result=" + result);
		
		Long size = jedis.sdiffstore("Set:SDIFFSTORE", "Set:SADD", "Set:SMOVE");
		PrintUtil.two("3.2、sdiffstore(String dstkey, String... keys)：对多个集合执行差集计算，并将结果储存到目标集合当中【SDIFFSTORE target_set set [set ...]】", 
				"dstKey=Set:SDIFFSTORE, key1=Set:SADD, key2=Set:SMOVE, size=" + size);
		
		Set<String> result2 = jedis.sinter("Set:SADD", "Set:SDIFFSTORE");
		PrintUtil.two("3.3、sinter(String... keys)：计算并返回多个集合的交集计算结果【SINTER set [set ...]】",
				"key1=Set:SADD, key2=Set:SDIFFSTORE, result=" + result2);
		
		Long size2 = jedis.sdiffstore("Set:SINTERSTORE", "Set:SADD", "Set:SDIFFSTORE");
		PrintUtil.two("3.4、sdiffstore(String dstkey, String... keys)：对多个集合执行交集计算，并将结果储存到目标集合当中【SDIFFSTORE target_set set [set ...]】", 
				"dstKey=Set:SINTERSTORE, key1=Set:SADD, key2=Set:SDIFFSTORE, size=" + size2);
		
		Set<String> result3 = jedis.sunion("Set:SADD", "Set:SINTERSTORE");
		PrintUtil.two("3.5、sinter(String... keys)：计算并返回多个集合的并集计算结果【SINTER set [set ...]】",
				"key1=Set:SADD, key2=Set:SINTERSTORE, result=" + result3);
		
		Long size3 = jedis.sunionstore("Set:SUNIONSTORE", "Set:SADD", "Set:SINTERSTORE");
		PrintUtil.two("3.6、sdiffstore(String dstkey, String... keys)：对多个集合执行并集计算，并将结果储存到目标集合当中【SDIFFSTORE target_set set [set ...]】", 
				"dstKey=Set:SUNIONSTORE, key1=Set:SADD, key2=Set:SINTERSTORE, size=" + size3);
	}

}
