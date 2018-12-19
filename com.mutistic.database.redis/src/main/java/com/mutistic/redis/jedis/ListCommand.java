package com.mutistic.redis.jedis;

import java.util.List;

import com.mutisitc.utils.JedisUtil;
import com.mutisitc.utils.PrintUtil;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.Jedis;

/**
 * @program 使用Jedis API操作List数据类型
 * @description
 * @author mutisitic
 * @date 2018年12月19日
 */
public class ListCommand {
	public static void main(String[] args) {
		PrintUtil.one("使用Jedis API操作List数据类型：");

		Jedis jedis = JedisUtil.getJedis();
		jedis.flushDB();

		showByPush(jedis);
		showByPop(jedis);
		showByManager(jedis);
		
		JedisUtil.close(jedis);
	}

	/**
	 * @description 1、List的添加元素
	 * @author mutisitic
	 * @date 2018年12月19日
	 * @param jedis
	 */
	private static void showByPush(Jedis jedis) {
		PrintUtil.one("1、List的添加元素：");

		Long result = jedis.lpush("List:LPUSH", "将一个或多个元素添加到列表的左端", "Hello", "Redis", "!", "This", "is", "test");
		PrintUtil.two("1.1、lpush(String key, String... strings)：将一个或多个元素添加到列表的左端【LPUSH list item [item ...]】",
				"key=List:LPUSH, result=" + result);

		Long result2 = jedis.lpushx("List:LPUSH", "仅在列表已经存在的情况下，将一个元素添加到列表的左端");
		PrintUtil.two("1.2、lpushx(String key, String... strings)：仅在列表已经存在的情况下，将一个元素添加到列表的左端【LPUSHX list item】",
				"key=List:LPUSH, result=" + result2);

		Long result3 = jedis.rpush("List:RPUSH", "将一个或多个元素添加到列表的右端");
		PrintUtil.two("1.3、rpush(String key, String... strings)：将一个或多个元素添加到列表的右端【RPUSH list item [item ...]】",
				"key=List:RPUSH, result=" + result3);

		Long result4 = jedis.rpushx("List:RPUSH", "仅在列表已经存在的情况下，将一个元素添加到列表的右端");
		PrintUtil.two("1.4、rpushx(String key, String... strings)：仅在列表已经存在的情况下，将一个元素添加到列表的右端【RPUSHX list item】",
				"key=List:RPUSH, result=" + result4);
	}

	/**
	 * @description 2、List的移除元素
	 * @author mutisitic
	 * @date 2018年12月19日
	 * @param jedis
	 */
	private static void showByPop(Jedis jedis) {
		PrintUtil.one("2、List的移除元素：");

		String value = jedis.lpop("List:LPUSH");
		PrintUtil.two("2.1、lpop(String key)：移除并返回列表左端第一个元素【LPOP list】", "key=List:LPUSH, value=" + value);

		String value2 = jedis.rpop("List:LPUSH");
		PrintUtil.two("2.2、rpop(String key)：移除并返回列表右端第一个元素【RPOP list】", "key=List:LPUSH, value=" + value2);

		List<String> valueList = jedis.blpop(100, "List:LPUSH");
		PrintUtil.two("2.3、blpop(int timeout, String key)：在指定的时限内，移除首个非空列表的最左端元素【BLPOP list [list ...] timeout】",
				"key=List:LPUSH, valueList=" + valueList);

		List<String> valueList2 = jedis.brpop(100, "List:LPUSH");
		PrintUtil.two("2.4、brpop(int timeout, String key)：在指定的时限内，移除首个非空列表的最右端元素【BRPOP list [list ...] timeout】",
				"key=List:LPUSH, valueList=" + valueList2);
		
		String value3 = jedis.rpoplpush("List:LPUSH", "List:RPOPLPUSH");
		PrintUtil.two("2.5、rpoplpush(String srckey, String dstkey)：移除源列表的最右端元素，并将该元素添加到目标列表的左端【RPOPLPUSH source_list target_list】",
				"srckey=List:LPUSH, dstkey=List:RPOPLPUSH, value=" + value3);
		
		String value4 = jedis.brpoplpush("List:RPOPLPUSH", "List:BRPOPLPUSH", 100);
		PrintUtil.two("2.6、brpoplpush(String srckey, String dstkey, int timeout)：在指定的时限内，尝试移除源列表的最右端元素，并将该元素添加到目标列表的左端【BRPOPLPUSH source_list target_list timeout】",
				"srckey=List:RPOPLPUSH, dstkey=List:BRPOPLPUSH, value=" + value4);
	}
	
	/**
	 * @description 3、List的元素的获取与管理
	 * @author mutisitic
	 * @date 2018年12月19日
	 * @param jedis
	 */
	private static void showByManager(Jedis jedis) {
		PrintUtil.one("3、List的元素的获取与管理：");

		String value = jedis.lindex("List:LPUSH", 0);
		PrintUtil.two("3.1、lindex(String key, long index)：获取列表在给定索引上的元素【LINDEX list index】", 
				"key=List:LPUSH, index=0, value=" + value);
	
		Long length = jedis.llen("List:LPUSH");
		PrintUtil.two("3.2、llen(String key)：返回列表包含的元素数量【LLEN list】", "key=List:LPUSH, length=" + length);
		
		List<String> valueList = jedis.lrange("List:LPUSH", 0, length);
		PrintUtil.two("3.3、lrange(String key, long start, long end)：返回列表在指定索引范围内的所有元素【LRANGE list start end】",
				"key=List:LPUSH, start=0, end="+length+", valueList=" + valueList);

		Long result = jedis.linsert("List:LPUSH", LIST_POSITION.BEFORE, "is", "test");
		PrintUtil.two("3.4、linsert(String key, LIST_POSITION where, String pivot, String value)：将给定的元素插入到目标元素的前面或者后面【LINSERT list BEFORE/AFTER target item】",
				"key=List:LPUSH, where=LIST_POSITION.BEFORE, pivot=is, value=test, result=" + result);
		
		Long result2 = jedis.lrem("List:LPUSH", 0, "!");
		PrintUtil.two("3.5、lrem(String key, long count, String value)：根据参数COUNT的值，移除列表中与参数VALUE相等的元素【LREM list count item】",
				"key=List:LPUSH, count=0, value=!, result=" + result2);

		String result3 = jedis.lset("List:LPUSH", 0, "Hello Redis!");
		PrintUtil.two("3.6、lset(String key, long index, String value)：把列表在指定索引上的值修改为给定的元素【LSET list index item】",
				"key=List:LPUSH, index=0, value=Hello Redis!, result=" + result3);
		
		String result4 = jedis.ltrim("List:LPUSH", 0, 1);
		PrintUtil.two("3.7、ltrim(String key, long start, long end)：对列表进行截断，只保留指定索引范围内的元素【LTRIM list start end】",
				"key=List:LPUSH, start=0, end=1, result=" + result4);
		
	}

}