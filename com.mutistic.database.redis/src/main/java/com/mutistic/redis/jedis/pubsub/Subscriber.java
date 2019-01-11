package com.mutistic.redis.jedis.pubsub;

import com.mutisitc.utils.PrintUtil;

import redis.clients.jedis.JedisPubSub;

/**
 * @program 使用Jedis API管理发布与订阅
 * @description 订阅者：实现订阅抽象JedisPubSub，重写订阅、接收、退订方法
 * @author mutisitic
 * @date 2019年1月11日
 */
public class Subscriber extends JedisPubSub {
	/**
	 * @description 重写onSubscribe：订阅频道
	 * @author mutisitic
	 * @date 2019年1月11日
	 * @param channel
	 * @param subscribedChannels
	 * @see redis.clients.jedis.JedisPubSub#onSubscribe(java.lang.String, int)
	 */
	@Override
	public void onSubscribe(String channel, int subscribedChannels) {
		PrintUtil.two("\n订阅者：Subscriber.onSubscribe()：订阅频道",
				"channel=" + channel + ", subscribedChannels=" + subscribedChannels);
	}
	
	/**
	 * @description 重写onMessage：接收到频道消息
	 * @author mutisitic
	 * @date 2019年1月11日
	 * @param channel
	 * @param message
	 * @see redis.clients.jedis.JedisPubSub#onMessage(java.lang.String, java.lang.String)
	 */
	@Override
	public void onMessage(String channel, String message) {
		PrintUtil.two("订阅者：Subscriber.onMessage()：接收到频道消息", "channel=" + channel + ", message=" + message);
	}

	/**
	 * @description 重写onUnsubscribe：退订频道
	 * @author mutisitic
	 * @date 2019年1月11日
	 * @param channel
	 * @param subscribedChannels
	 * @see redis.clients.jedis.JedisPubSub#onUnsubscribe(java.lang.String, int)
	 */
	@Override
	public void onUnsubscribe(String channel, int subscribedChannels) {
		PrintUtil.two("\n订阅者：Subscriber.onUnsubscribe()：退订频道",
				"channel=" + channel + ", subscribedChannels=" + subscribedChannels);
	}

	/**
	 * @description 重写onPSubscribe：订阅模式
	 * @author mutisitic
	 * @date 2019年1月11日
	 * @param pattern
	 * @param subscribedChannels
	 * @see redis.clients.jedis.JedisPubSub#onPSubscribe(java.lang.String, int)
	 */
	@Override
	public void onPSubscribe(String pattern, int subscribedChannels) {
		PrintUtil.two("\n订阅者：Subscriber.onPSubscribe()：订阅模式",
				"pattern=" + pattern + ", subscribedChannels=" + subscribedChannels);
	}
	
	/**
	 * @description 重写onPMessage：接收到模式消息
	 * @author mutisitic
	 * @date 2019年1月11日
	 * @param pattern
	 * @param channel
	 * @param message
	 * @see redis.clients.jedis.JedisPubSub#onPMessage(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void onPMessage(String pattern, String channel, String message) {
		PrintUtil.two("订阅者：Subscriber.onPMessage()：接收到模式消息",
				"pattern=" + pattern + ", channel=" + channel + ", message=" + message);
	}
	
	/**
	 * @description 重写onPUnsubscribe：退订模式
	 * @author mutisitic
	 * @date 2019年1月11日
	 * @param pattern
	 * @param subscribedChannels
	 * @see redis.clients.jedis.JedisPubSub#onPUnsubscribe(java.lang.String, int)
	 */
	@Override
	public void onPUnsubscribe(String pattern, int subscribedChannels) {
		PrintUtil.two("\n订阅者：Subscriber.onPUnsubscribe()：退订模式",
				"pattern=" + pattern + ", subscribedChannels=" + subscribedChannels);
	}

}
