package com.mutistic.redis.jedis.pubsub;

import java.util.List;
import java.util.Map;

import com.mutisitc.utils.JedisUtil;
import com.mutisitc.utils.PrintUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @program 使用Jedis API管理发布与订阅
 * @description 程序主入口
 * @author mutisitic
 * @date 2019年1月11日
 */
public class PubSubCommand {
	public static void main(String[] args) {
		PrintUtil.one("使用Jedis API管理发布与订阅：");

		JedisPool jedisPool = JedisUtil.getJedisPool();

		// 订阅线程：接收消息
		final Jedis subJedis = jedisPool.getResource();
		Subscriber subscriber = new Subscriber();
		new Thread(new Runnable() {
			public void run() {
				try {
					// 使用Subscriber订阅channels上的消息，这一句之后，线程进入订阅模式，阻塞。
					subJedis.subscribe(subscriber, "CHANNEL_1");
					PrintUtil.two("2.1、Jedis.subscribe(JedisPubSub jedisPubSub, String... channels)：订阅给定的一个或多个频道"
							+ "【SUBSCRIBE channel [channel ...]】",
							"jedisPubSub=" + subscriber + ", channels=CHANNEL_1");
					subJedis.psubscribe(subscriber, "*C*");
					PrintUtil.two("2.2、Jedis.psubscribe(JedisPubSub jedisPubSub, String... patterns)：订阅给定的一个或多个模式"
							+ "【PSUBSCRIBE pattern [pattern ...]】",
							"jedisPubSub=" + subscriber + ", patterns=*C*");
				} catch (Exception e) {
					PrintUtil.err(e.getMessage());
				}
			}
		}).start();

		// 主线程：发布消息到频道上
		Jedis pubJedis = jedisPool.getResource();
		showByLook(pubJedis);
		Publisher publisher = new Publisher(pubJedis, "CHANNEL_1");
//		 publisher.publish();
		publisher.publishByInput();
		
		subscriber.unsubscribe();
		PrintUtil.two("3.1、JedisPubSub.unsubscribe()：退订给定的一个或多个频道，如果没有给定频道则退订全部频道",
				"UNSUBSCRIBE [channel [channel ...]]");
		subscriber.punsubscribe();
		PrintUtil.two("3.2、JedisPubSub.punsubscribe()：退订给定的一个或多个模式，如果没有给定模式则退订全部模式",
				"PUNSUBSCRIBE [pattern [pattern ...]]");
	}
	
	/**
	 * @description 4、查看订阅信息
	 * @author mutisitic
	 * @date 2019年1月11日
	 * @param pubJedis 发布者Jedis
	 */
	private static void showByLook(Jedis pubJedis) {
		PrintUtil.one("4、查看订阅信息：");
		List<String> patternList = pubJedis.pubsubChannels("*C*");
		PrintUtil.two("4.1、Jedis.pubsubChannels(String pattern)：列出当前被订阅的频道【PUBSUB CHANNELS [pattern]】",
				"pattern=*C*, patternList="+patternList);
		
		Map<String, String> channelMap = pubJedis.pubsubNumSub("CHANNEL_1");
		PrintUtil.two("4.2、Jedis.pubsubNumSub(String... channels)：返回给定频道的订阅者数量【PUBSUB NUMSUB [channel channel ...]】",
				"channels=CHANNEL_1, channelMap="+PrintUtil.toString(channelMap));
		
		Long numPat = pubJedis.pubsubNumPat();
		PrintUtil.two("4.3、Jedis.pubsubNumPat()：返回当前被订阅模式的数量【PUBSUB NUMPAT】",
				"numPat="+numPat);
	}

}
