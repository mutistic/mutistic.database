package com.mutistic.redis.junit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisZSetCommands.Aggregate;
import org.springframework.data.redis.connection.RedisZSetCommands.Weights;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mutisitc.utils.PrintUtil;
import com.mutistic.redis.Application;

/**
 * @program 使用RedisTemplate API+JUnit操作简单操作Redis
 * @description 
 * @author mutisitic
 * @date 2019年1月14日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisTemplateCommandByJUnit {

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	@Test
	public void test() throws Exception {
		PrintUtil.one("1、使用RedisTemplate API+JUnit操作简单操作Redis：");
		PrintUtil.two("1.1、通过@Autowired自动注入org.springframework.data.redis.core.RedisTemplate<Object, Object>实例：", redisTemplate);
		
		operationString(redisTemplate.opsForValue());
		operationHash(redisTemplate.opsForHash());
		operationList(redisTemplate.opsForList());
		operationSet(redisTemplate.opsForSet());
		operationZSet(redisTemplate.opsForZSet());
	}


	/**
	 * @description 2、简单操作字符串：String
	 * @author mutisitic
	 * @date 2019年1月14日
	 * @param opration：org.springframework.data.redis.core.ValueOperations<Object, Object>
	 */
	private void operationString(ValueOperations<Object, Object> opration) {
		PrintUtil.one("2、操作字符串：String：");
		
		opration.set("String:Set", "test");
		PrintUtil.two("2.1、ValueOperations.set(String key, String value)：为字符串键设置值【SET key value】", 
				"key=String:Set, value=test");
		
		Object value = opration.get("String:Set");
		PrintUtil.two("2.2、ValueOperations.get(Object key)获取字符串键的值【GET key】", "key=String:Set, value="+value);
		
		Object oldValue = opration.getAndSet("String:SET", "GETSET key new-value：为字符串键设置值");
		PrintUtil.two("2.3、ValueOperations.getAndSet(String key, String value)：为字符串键设置值【 GETSET key new-value】",
				"key=String:SET, oldValue"+ oldValue);
		
		opration.set("String:SetByTime", "test", 100l, TimeUnit.SECONDS);
		PrintUtil.two("2.4、ValueOperations.set(String key, String value, long timeout, TimeUnit unit)：设置字符串的过期时间"
				+ "【SET key value [EX seconds] [PX milliseconds]】",
				"key=String:SetByTime, value=test, timeout=100l, unit=java.util.concurrent.TimeUnit.SECONDS");
	}
	
	/**
	 * @description 3、简单操作哈希表：Hash
	 * @author mutisitic
	 * @date 2019年1月14日
	 * @param operation：org.springframework.data.redis.core.HashOperations<Object, Object, Object>
	 */
	private void operationHash(HashOperations<Object, Object, Object> operation) {
		PrintUtil.one("3、简单操作哈希表：Hash：");

		operation.put("Hash:Put", "key1", "test");
		PrintUtil.two("3.1、HashOperations.put(String key, Object hashKey, Object value)：为散列中的键设置值【HSET hash key value】", 
				"key=Hash:Put, hashKey=key1, value=test");
		
		Object value = operation.get("Hash:Put", "key1");
		PrintUtil.two("3.2、HashOperations.get(String key, Object hashKey)：返回散列中与给定键相关联的值【HGET hash key】", 
				"key=Hash:Put, hashKey=key1, value="+value);
		
		Map<Object, Object> hashKeyMap = new HashMap<Object, Object>();
		hashKeyMap.put("key1", "t1");
		hashKeyMap.put("key2", "t2");
		operation.putAll("Hash:PutAll", hashKeyMap);
		PrintUtil.two("3.3、HashOperations.putAll(String key, Map<? extends Object, ? extends Object> m)：一次为散列中的多个键设置值"
				+ "【HMSET hash key value [key2 value]】", 
				"key=Hash:PutAll, hashKeyMap="+PrintUtil.toString(hashKeyMap));
		
		List<Object> hashKeyList = new ArrayList<Object>();
		hashKeyList.add("key1");
		hashKeyList.add("key2");
		List<Object> valueList = operation.multiGet("Hash:PutAll", hashKeyList);
		PrintUtil.two("3.3、HashOperations.multiGet(String key, Collection<Object> hashKeys)：一次获取散列中多个键的值"
				+ "【HMGET hash key [key2 ...]】", 
				"key=Hash:PutAll, hashKeys="+hashKeyList+", valueList="+valueList);
	}
	
	/**
	 * @description 4、简单操作列表：List
	 * @author mutisitic
	 * @date 2019年1月14日
	 * @param operation：org.springframework.data.redis.core.ListOperations<Object, Object>
	 */
	private void operationList(ListOperations<Object, Object> operation) {
		PrintUtil.one("4、简单操作列表：List：");
		
		Long size = operation.leftPush("List:LPUSH", "t1");
		PrintUtil.two("4.1、ListOperations.leftPush(Object key, Object value)：将一个元素添加到列表的左端。从头部加入元素（栈）先进后出。返回list的长度"
				+ "【LPUSH list item】", 
				"key=List:LPUSH, value=t1, size="+size);
		
		List<String> valueList = new ArrayList<String>();
		valueList.add("t1");
		valueList.add("t2");
		operation.leftPushAll("List:LPUSHALL", valueList);
		PrintUtil.two("4.2、ListOperations.leftPushAll(Object key, Object... values)：将一个或多个元素添加到列表的左端。从头部加入元素（栈）先进后出。返回list的长度"
				+ "【LPUSH list item [item ...]】", 
				"key=List:LPUSHALL, value="+valueList+", size="+size);
		
		Object oldValue = operation.rightPop("List:LPUSHALL");
		PrintUtil.two("4.3、ListOperations.rightPop(Object key)：移除并返回列表右端第一个元素。返回被移除元素的值【RPOP list】", 
				"key=List:LPUSHALL,  oldValue="+oldValue);
		
		operation.set("List:LPUSH", 0, "test");
		PrintUtil.two("4.4、ListOperations.set(Object key, long index, Object value)：把列表在指定索引上的值修改为给定的元素【LSET list index item】", 
				"key=List:LPUSH, index=0, value=test");
	}
	
	/**
	 * @description 5、简单操作集合：Set
	 * @author mutisitic
	 * @date 2019年1月14日
	 * @param operation：org.springframework.data.redis.core.SetOperations<Object, Object>
	 */
	private void operationSet(SetOperations<Object, Object> operation) {
		PrintUtil.one("5、简单操作集合：Set：");

		Long size = operation.add("SET:ADD", "t1", "t2", "t3");
		PrintUtil.two("5.1、SetOperations.add(Object key, Object... values)：将一个或多个元素添加到集合当中"
				+ "【SADD set element [element ...]】", 
				"key=SET:ADD, values=[t1,t2,t3], size="+size);

		Long size2 = operation.remove("SET:ADD", "t1");
		PrintUtil.two("5.2、SetOperations.remove(Object key, Object... values)：移除集合中的一个或多个元素"
				+ "【SREM set element [element ...]】", 
				"key=SET:ADD, values=[t1], size="+size2);
		
		Set<Object> valueSet = operation.members("SET:ADD");
		PrintUtil.two("5.3、SetOperations.members(Object key)：返回集合包含的所有元素【SMEMBERS set】", 
				"key=SET:ADD,valueSet="+valueSet);
		
		operation.add("SET:Test", "1", "2", "3");
		Set<Object> valueList = operation.union("SET:ADD", "SET:Test");
		PrintUtil.two("5.4、SetOperations.union(Object key, Object otherKey)：返回集合包含的所有元素【SMEMBERS set】", 
				"key=SET:ADD, otherKey=SET:Test, valueList="+valueList);
	}
	
	/**
	 * @description 6、简单操作集合：ZSet
	 * @author mutisitic
	 * @date 2019年1月14日
	 * @param operation：org.springframework.data.redis.core.ZSetOperations<Object, Object>
	 */
	private void operationZSet(ZSetOperations<Object, Object> operation) {
		PrintUtil.one("6、简单操作集合：zSet：");
		
		Boolean result = operation.add("ZSET:ADD", "1", 1D);
		PrintUtil.two("6.1、ZSetOperations.add(Object key, Object value, double score)：将给定的元素及其分值添加到有序集合，按照分数排序"
				+ "【ZADD sorted_set score member】", 
				"key=ZSET:ADD, value=1, score=1D, result="+result);
		
		Set<TypedTuple<Object>> tupleSet = new HashSet<ZSetOperations.TypedTuple<Object>>();
		TypedTuple<Object> t1 = new DefaultTypedTuple<Object>("t1", 1D);
		TypedTuple<Object> t2 = new DefaultTypedTuple<Object>("t2", 2D);
		tupleSet.add(t1);
		tupleSet.add(t2);
		Long addSize = operation.add("ZSET:AddByTuple", tupleSet);
		PrintUtil.two("6.2、ZSetOperations.add(Object key, Set<TypedTuple<Object>> tuples)：将给定的元素及其分值添加到有序集合，按照分数排序"
				+ "【ZADD sorted_set score member [[score member] [score member] ...]】", 
				"key=ZSET:AddByTuple, tuples="+PrintUtil.toString(tupleSet)+", addSize="+addSize);
		
		Long length = operation.count("ZSET:AddByTuple", 0d, 2d);
		PrintUtil.two("6.3、ZSetOperations.count(Object key, double min, double max)：返回有序集合中，分值介于指定区间之内的元素数量"
				+ "【ZCOUNT sorted_set min max】", 
				"key=ZSET:AddByTuple, min=0d, max=2d, length="+length);
		
		Set<TypedTuple<Object>> valueTupleSet = operation.rangeByScoreWithScores("ZSET:AddByTuple", 0d, 2d);
		PrintUtil.two("6.4、ZSetOperations.rangeByScoreWithScores(Object key, double min, double max)：按照分值从小到大的顺序，返回指定分值范围之内的元素"
				+ "【ZRANGEBYSCORE sorted_set min max [WITHSCORES] [LIMIT offset count]】", 
				"key=ZSET:AddByTuple, min=0d, max=2d, valueTupleSet="+PrintUtil.toString(valueTupleSet));
		
		
		List<Object> otherKeyList = new ArrayList<Object>();
		otherKeyList.add("ZSET:ADD");
		Long unionSize = operation.unionAndStore("ZSET:AddByTuple", otherKeyList, "ZSET:ZUNIONSTORE", 
				Aggregate.MAX, Weights.of(1d, 2d));
		PrintUtil.two("6.5、ZSetOperations.unionAndStore(Object key, Collection<Object> otherKeys, Object destKey, Aggregate aggregate, Weights weights)"
				+ "：对给定数量的有序集合执行并集计算，并将计算的结果储存到目标有序集合里面根据元素的大小对其进行处理"
				+ "【ZUNIONSTORE target number [sorted_set ...] [WEIGHTS weight [weight ...]][AGGREGATE SUM|MIN|MAX]]】", 
				"key=ZSET:AddByTuple, otherKeys=[ZSET:ADD], aggregate=Aggregate.MAX, weights=[1d,2d], unionSize="+unionSize);
	}
	
}