package com.mutistic.redis.jedis;

import java.util.List;

import com.mutisitc.utils.JedisUtil;
import com.mutisitc.utils.PrintUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

/**
 * @program 使用Jedis API管理事务
 * @description 
 * @author mutisitic
 * @date 2019年1月11日
 */
public class TransactionCommand {

	public static void main(String[] args) {
		PrintUtil.one("使用Jedis API管理事务：");

		Jedis jedis = JedisUtil.newJedis();
		
		jedis.set("t1", "a");
		
		showByBase(jedis);
		showByWath(jedis);
		
		JedisUtil.close(jedis);
	}

	/**
	 * @description 1、基本事务操作 
	 * @author mutisitic
	 * @date 2019年1月11日
	 * @param jedis
	 */
	private static void showByBase(Jedis jedis) {
		PrintUtil.one("1、基本事务操作：");

		Transaction transaction = jedis.multi();
		PrintUtil.two("1.1、multi()：开始一次事务【MULTI】", "transaction=" + transaction);

		Response<String> response = transaction.set("t1", "a");
		transaction.set("t2", "b");
		PrintUtil.two("1.1.1、Transaction.set(String key, String value)：使用Transaction方法操作Redis", 
				"key=t1, value=a, response=" + response);
		
		List<Object> execResult = transaction.exec();
		PrintUtil.two("1.2、Transaction.exec()：执行事务【EXEC】", "execResult=" + execResult);

		transaction = jedis.multi();
		String discardResult = transaction.discard();
		PrintUtil.two("1.2、Transaction.discard()：取消事务【DISCARD】", "discardResult=" + discardResult);
		
	}

	/**
	 * @description 2、乐观锁事务操作
	 * @author mutisitic
	 * @date 2019年1月11日
	 * @param jedis
	 */
	private static void showByWath(Jedis jedis) {
		PrintUtil.one("2、乐观锁事务操作：");
		
		String watchResult = jedis.watch("t1");
		PrintUtil.two("2.1、watch(String... keys)：监视给定的键，看它们在事务执行之前是否已被修改【WATCH key [key ...]】", 
				"watchResult=" + watchResult);
		
		String unwatchResult = jedis.unwatch();
		PrintUtil.two("2.2、unwatch()：取消对所有键的监视【UNWATCH】", 
				"unwatchResult=" + unwatchResult);
	}
}
