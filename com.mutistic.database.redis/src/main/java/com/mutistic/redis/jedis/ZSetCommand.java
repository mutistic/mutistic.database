package com.mutistic.redis.jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.mutisitc.utils.JedisUtil;
import com.mutisitc.utils.PrintUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.ZParams;
import redis.clients.jedis.ZParams.Aggregate;

/**
 * @program 使用Jedis API操作ZSet数据类型
 * @description
 * @author mutisitic
 * @date 2019年1月4日
 */
public class ZSetCommand {
	public static void main(String[] args) {
		PrintUtil.one("使用Jedis API操作ZSet数据类型：");

		Jedis jedis = JedisUtil.getJedis();
		jedis.flushDB();

		showByManager(jedis);
		showByBatch(jedis);
		showByOperation(jedis);

		JedisUtil.close(jedis);
	}

	/**
	 * @description 1、ZSet的元素的检测与管理
	 * @author mutisitic
	 * @date 2019年1月3日
	 * @param jedis
	 */
	private static void showByManager(Jedis jedis) {
		PrintUtil.one("1、ZSet的元素的检测与管理：");

		Map<String, Double> scoreMembers = new HashMap<String, Double>();
		scoreMembers.put("将给定的元素及其分值添加到有序集合", 3D);
		scoreMembers.put("Hello", 0D);
		scoreMembers.put("ZSet", 0D);
		scoreMembers.put("!", 2D);
		Long size = jedis.zadd("ZSet:ZADD", scoreMembers);
		PrintUtil.two("1.1、zadd(String key, Map<String, Double> scoreMembers)："
				+ "将给定的元素及其分值添加到有序集合"
				+ "【ZADD sorted_set score member [[score member] [score member] ...]】",
				"key=ZSet:ZADD, scoreMembers=" + PrintUtil.toString(scoreMembers) + ", size=" + size);

		Double score = jedis.zincrby("ZSet:ZADD", 1D, "ZSet");
		PrintUtil.two("1.2、zincrby(String key, double score, String member)："
				+ "为元素的分值加上指定的整数增量"
				+ "【ZINCRBY sorted_set increment member】",
				"key=ZSet:ZADD, member=ZSet, score=" + score);

		Double source2 = jedis.zscore("ZSet:ZADD", "Hello");
		PrintUtil.two("1.3、zscore(String key, String member)："
				+ "返回给定元素的分值"
				+ "【ZSCORE sorted_set member】",
				"srckey=ZSet:ZADD, member=Hello, source=" + source2);

		Long size2 = jedis.zcard("ZSet:ZADD");
		PrintUtil.two("1.4、zcard(String key)："
				+ "返回有序集合包含的元素数量"
				+ "【ZCARD sorted_set】", "key=ZSet:ZADD, size=" + size2);

		Long result = jedis.zrank("ZSet:ZADD", "将给定的元素及其分值添加到有序集合");
		PrintUtil.two("1.5、zrank(String key, String member)："
				+ "返回有序集合元素在按照分值从小到大进行排列时，给定的元素在有序集合中所处的排名"
				+ "【ZRANK sorted_set member】",
				"key=ZSet:ZADD, member=将给定的元素及其分值添加到有序集合, result=" + result);

		Long result2 = jedis.zrevrank("ZSet:ZADD", "ZSet");
		PrintUtil.two("1.6、zrevrank(String key, String member)："
				+ "返回有序集合元素在按照分值从大到小进行排列时，给定的元素在有序集合中所处的排名"
				+ "【ZREVRANK sorted_set member】",
				"key=ZSet:ZADD, member=将给定的元素及其分值添加到有序集合, result=" + result2);

	}

	/**
	 * @description 2、ZSet的批量处理多个元素
	 * @author mutisitic
	 * @date 2019年1月3日
	 * @param jedis
	 */
	private static void showByBatch(Jedis jedis) {
		PrintUtil.one("2、ZSet的批量处理多个元素：");

		Long size = jedis.zcount("ZSet:ZADD", 0D, 1D);
		PrintUtil.two("2.1、zcount(String key, double min, double max)："
				+ "返回有序集合中，分值介于指定区间之内的元素数量"
				+ "【ZCOUNT sorted_set min max】",
				"key=ZSet:ZADD, mix=0D, max=1D, size=" + size);

		Set<String> valueSet = jedis.zrange("ZSet:ZADD", 0, 3);
		PrintUtil.two(
				"2.2、zrange(String key, long start, long end)："
				+ "按照分值从小到大的顺序，返回指定索引范围之内的元素"
				+ "【ZRANGE sorted_set start end】",
				"key=ZSet:ZADD, start=0, end=3, valueSet=" + valueSet);

		Set<Tuple> tupleSet = jedis.zrangeWithScores("ZSet:ZADD", 0, 3);
		PrintUtil.two("2.2.1、zrangeWithScores(String key, long start, long end)："
				+ "按照分值从小到大的顺序，返回指定索引范围之内的元素及其分值（可选）"
				+ "【ZRANGE sorted_set start end [WITHSCORES]】",
				"key=ZSet:ZADD, start=0, end=3, tupleSet=" + tupleSet);

		Set<String> valueSet2 = jedis.zrevrange("ZSet:ZADD", 0, 3);
		PrintUtil.two("2.3、zrange(String key, long start, long end)："
				+ "按照分值从大到小的顺序，返回指定索引范围之内的元素"
				+ "【ZRANGE sorted_set start end】",
				"key=ZSet:ZADD, start=0, end=3, valueSet=" + valueSet2);

		Set<Tuple> tupleSet2 = jedis.zrevrangeWithScores("ZSet:ZADD", 0, 3);
		PrintUtil.two("2.3.1、zrangeWithScores(String key, long start, long end)："
				+ "按照分值从大到小的顺序，返回指定索引范围之内的元素及其分值（可选）"
				+ "【ZRANGE sorted_set start end [WITHSCORES]】",
				"key=ZSet:ZADD, start=0, end=3, tupleSet=" + tupleSet2);

		Set<String> valueSet3 = jedis.zrangeByScore("ZSet:ZADD", 0D, 3D, 0, 10);
		PrintUtil.two("2.4、zrangeByScore(String key, double min, double max, int offset, int count)："
				+ "按照分值从小到大的顺序，返回指定分值范围之内的元素"
				+ "【ZRANGEBYSCORE sorted_set min max [LIMIT offset count]】",
				"key=ZSet:ZADD, mix=0D, max=1D, offset=0, count=10, valueSet=" + valueSet3);

		Set<Tuple> tupleSet3 = jedis.zrangeByScoreWithScores("ZSet:ZADD", 0D, 3D, 0, 10);
		PrintUtil.two("2.4.1、zrangeByScoreWithScores(String key, double min, double max, int offset, int count)："
						+ "按照分值从小到大的顺序，返回指定分值范围之内的元素及其分值（可选）"
						+ "【ZRANGEBYSCORE sorted_set min max [WITHSCORES] [LIMIT offset count]】",
				"key=ZSet:ZADD,  mix=0D, max=1D, offset=0, count=10, tupleSet=" + tupleSet3);

		Set<String> valueSet4 = jedis.zrevrangeByScore("ZSet:ZADD", 0D, 3D, 0, 10);
		PrintUtil.two("2.5、zrangeByScore(String key, double min, double max, int offset, int count)："
						+ "按照分值从大到小的顺序，返回指定分值范围之内的元素" + "【ZRANGEBYSCORE sorted_set min max [LIMIT offset count]】",
				"key=ZSet:ZADD, mix=0D, max=1D, offset=0, count=10, valueSet=" + valueSet4);

		Set<Tuple> tupleSet4 = jedis.zrevrangeByScoreWithScores("ZSet:ZADD", 0D, 3D, 0, 10);
		PrintUtil.two("2.5.1、zrangeByScoreWithScores(String key, double min, double max, int offset, int count)："
						+ "按照分值从大到小的顺序，返回指定分值范围之内的元素及其分值（可选）"
						+ "【ZRANGEBYSCORE sorted_set min max [WITHSCORES] [LIMIT offset count]】",
				"key=ZSet:ZADD,  mix=0D, max=1D, offset=0, count=10, tupleSet=" + tupleSet4);

		ScanResult<Tuple> scanResult = jedis.zscan("ZSet:ZADD", "0");
		PrintUtil.two("2.6、zscan(String key, String cursor)：以渐进的方式，返回有序集合包含的元素及其分值"
				+ "【ZSCAN sorted_set cursor】",
				"key=Set:SADD, scanResult=" + scanResult.getResult());

		ScanParams scanParams = new ScanParams();
		scanParams.match("*e*");
		scanParams.count(2);
		ScanResult<Tuple> scanResult2 = jedis.zscan("ZSet:ZADD", "0", scanParams);
		PrintUtil.two("2.6.1、zscan(String key, String cursor, ScanParams params)：以渐进的方式，返回有序集合包含的元素及其分值"
				+ "【ZSCAN sorted_set cursor [MATCH pattern] [COUNT count]】",
				"key=Set:SADD, scanParams={match:一*,count:2}, scanResult=" + scanResult2.getResult());

		Long result = jedis.zrem("ZSet:ZADD", "将给定的元素及其分值添加到有序集合");
		PrintUtil.two("2.7、zrem(String key, String... members)：从有序集合中移除指定的一个或多个元素"
				+ "【ZREM sorted_set member [member ...]】",
				"key=ZSet:ZADD, members=将给定的元素及其分值添加到有序集合, result=" + result);
		
		Long result2 = jedis.zremrangeByRank("ZSet:ZADD", 2, 3);
		PrintUtil.two("2.8、zremrangeByRank(String key, long start, long end)：移除有序集合中，位于指定排名范围内的元素，其中元素按照分值从小到大进行排列"
				+ "【ZREMRANGEBYRANK sorted_set start end]】",
				"key=ZSet:ZADD, start=3, end=3, result=" + result2);
		
		Long result3 = jedis.zremrangeByScore("ZSet:ZADD", 2D, 3D);
		PrintUtil.two("2.9、zremrangeByScore(String key, double start, double end)：移除有序集合中，分值位于指定范围内的元素"
				+ "【ZREMRANGEBYSCORE sorted_set min max]】",
				"key=ZSet:ZADD, start=3D, end=3D, result=" + result3);
	}

	/**
	 * @description 3、ZSet的集合运算
	 * @author mutisitic
	 * @date 2019年1月3日
	 * @param jedis
	 */
	private static void showByOperation(Jedis jedis) {
		PrintUtil.one("3、ZSet的集合运算：");
		
		Map<String, Double> scoreMembers = new HashMap<String, Double>();
		scoreMembers.put("Hello", 0D);
		scoreMembers.put("ZSet", 0D);
		scoreMembers.put("!", 2D);
		jedis.zadd("ZSet:Test", scoreMembers);

		Long size = jedis.zinterstore("ZSet:ZINTERSTORE", "ZSet:ZADD", "ZSet:Test");
		PrintUtil.two("3.1、zinterstore(String dstkey, String... sets)：对给定数量的有序集合执行交集计算，并将计算的结果储存到目标有序集合里面"
				+ "【ZINTERSTORE target number】",
				"dstkey=ZSet:ZINTERSTORE, number=sets.length, sets={ZSet:ZADD,ZSet:Test}, size=" + size);
		
		ZParams zparams = new ZParams();
//		[WEIGHTS]：可选参数，可以为每个给定的有序集指定一个乘法因子，每个给定有序集的所有成员的score值在传递给聚合函数之前都要先乘以该因子。如果WEIGHTS没有给定，默认就是1
		zparams.weightsByDouble(1D, 2D); 
//		[AGGREGATE]：可选参数，指定计算的结果集的聚合方式，默认使用的参数SUM。
//	      SUM：可以将所有集合中某个成员的score值之和作为结果集中该成员的score值
//	      MIN：结果集就是所有集合中元素按照分值排列的最小的元素
//	      MAX：结果集就是所有集合中元素按照分值排列的最大的元素
		zparams.aggregate(Aggregate.MIN);
		
		Long size2 = jedis.zinterstore("ZSet:ZINTERSTORE:ZPARAMS", zparams, "ZSet:ZADD", "ZSet:Test");
		PrintUtil.two("3.2、zinterstore(String dstkey, ZParams params, String... sets)：对给定数量的有序集合执行交集计算，并将计算的结果储存到目标有序集合里面"
				+ "【ZINTERSTORE target number [sorted_set ...] [WEIGHTS weight [weight ...]][AGGREGATE SUM|MIN|MAX]】",
				"dstkey=ZSet:ZINTERSTORE:ZPARAMS, number=sets.length, params={weight=[1D,2D],aggregat=min}, sets={ZSet:ZADD,ZSet:Test}, size=" + size2);
		
		Long size3 = jedis.zunionstore("ZSet:ZUNIONSTORE", "ZSet:ZADD", "ZSet:Test");
		PrintUtil.two("3.3、zinterstore(String dstkey, String... sets)：对给定数量的有序集合执行并集计算，并将计算的结果储存到目标有序集合里面"
				+ "【ZINTERSTORE target number】",
				"dstkey=ZSet:ZUNIONSTORE, number=sets.length, sets={ZSet:ZADD,ZSet:Test}, size=" + size3);
		
		Long size4 = jedis.zunionstore("ZSet:ZUNIONSTORE:ZPARAMS", zparams, "ZSet:ZADD", "ZSet:Test");
		PrintUtil.two("3.4、zinterstore(String dstkey, ZParams params, String... sets)：对给定数量的有序集合执行并集计算，并将计算的结果储存到目标有序集合里面"
				+ "【ZINTERSTORE target number [sorted_set ...] [WEIGHTS weight [weight ...]][AGGREGATE SUM|MIN|MAX]】",
				"dstkey=ZSet:ZUNIONSTORE:ZPARAMS, number=sets.length, params={weight=[1,2,3],aggregat=min}, sets={ZSet:ZADD,ZSet:Test}, size=" + size4);
		
		Map<String, Double> scoreMembers2 = new HashMap<String, Double>();
		scoreMembers2.put("a", 0D);
		scoreMembers2.put("b", 1D);
		scoreMembers2.put("c", 2D);
		scoreMembers2.put("d", 3D);
		jedis.zadd("ZSet:Test2", scoreMembers2);
		
		Long size5 = jedis.zlexcount("ZSet:Test2", "-", "+");
		PrintUtil.two("3.5、zlexcount(String key, String min, String max)：统计有序集合里面，位于指定字典区间内的元素的数量"
				+ "【ZLEXCOUNT sorted_set min max】",
				"key=ZSet:Test2, min=-, max=+, size=" + size5);
		
		Set<String> valueSet = jedis.zrangeByLex("ZSet:Test2", "[a", "(d");
		PrintUtil.two("3.6、zrangeByLex(String key, String min, String max)：按照从小到大的顺序，返回有序集合里面位于指定字典区间内的元素"
				+ "【ZRANGEBYLEX sorted_set min max】",
				"key=ZSet:ZUNIONSTORE, min=[a, max=(d, valueSet=" + valueSet);
		
		Set<String> valueSet2 = jedis.zrangeByLex("ZSet:Test2", "(a", "[d", 0, 2);
		PrintUtil.two("3.6.1、zrangeByLex(String key, String min, String max, int offset, int count)：按照从小到大的顺序，返回有序集合里面位于指定字典区间内的元素"
				+ "【RANGEBYLEX sorted_set min max [LIMIT offset count]】",
				"key=ZSet:ZUNIONSTORE, min=(a, max=(d, offset=0, count=2, valueSet=" + valueSet2);
		
		Long remSize = jedis.zremrangeByLex("ZSet:Test2", "[a", "[d");
		PrintUtil.two("3.7、zrangeByLex(String key, String min, String max)：从有序集合里面，移除位于指定大小范围之内的元素"
				+ "【ZREMRANGEBYLEX sorted_set min max】",
				"key=ZSet:ZINTERSTORE, min=[a, max=[d, remSize=" + remSize);
	}

}
