package com.mutistic.redis.jedis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.mutisitc.utils.JedisUtil;
import com.mutisitc.utils.PrintUtil;

import redis.clients.jedis.Jedis;

/**
 * @program 使用Jedis API管理发布与订阅
 * @description 发布者：向频道推送消息
 * @author mutisitic
 * @date 2019年1月11日
 */
public class Publisher {
	
	private Jedis jedis;
	/** 发布频道 */
	private String channelName;
	
	public Publisher(Jedis jedis, String channelName) {
		this.jedis = jedis;  
		this.channelName = channelName;
	}
	
	/**
	 * @description 1、直接发布消息 
	 * @author mutisitic
	 * @date 2019年1月11日
	 */
	public void publish() {
		PrintUtil.one("1、直接发布消息 ：");
		Long publishResult = jedis.publish(channelName, "测试消息");
		PrintUtil.two("1.1、(发布者)Jedis.publish(String channel, String message)：向指定频道发布一条消息【PUBLISH channel message】",
				"channel=" + channelName + ", message=测试消息, publishResult=" + publishResult);
		JedisUtil.close(jedis);
	}
	
	/**
	 * @description 2、从控制台输入发布消息
	 * @author mutisitic
	 * @date 2019年1月11日
	 */
	public void publishByInput() {
		PrintUtil.one("2、从控制台输入发布消息：");
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				PrintUtil.one("请输入需要推送的消息：");
				String message = reader.readLine();
				if (!"quit".equals(message)) {
					Long publishResult = jedis.publish(channelName, message);
					PrintUtil.two("1.1、(发布者)Jedis.publish(String channel, String message)：向指定频道发布一条消息【PUBLISH channel message】",
							"channel=" + channelName + ", message=" + message + ", publishResult=" + publishResult);
				} else {
					JedisUtil.close(jedis);
					break;
				}
			}
		} catch (IOException e) {
			PrintUtil.err(e.getMessage());
		}
	}
	
}
