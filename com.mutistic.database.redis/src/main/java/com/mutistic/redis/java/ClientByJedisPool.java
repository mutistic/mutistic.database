package com.mutistic.redis.java;

import com.mutisitc.utils.PrintUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @program 使用JedisPool获取Jedis实例
 * @description 
 * @author mutisitic
 * @date 2018年11月21日
 */
public class ClientByJedisPool {

	public static void main(String[] args) {
		PrintUtil.one("使用JedisPool获取Jedis实例");
		
		JedisPoolConfig config = new JedisPoolConfig();
		PrintUtil.two("1、创建redis.clients.jedis.JedisPoolConfig实例对象：", config);
		
		String host = "192.168.16.113";
		PrintUtil.two("2、定义redis的IP地址(和redis.conf的bind参数一致)：", host);
		
		JedisPool pool = new JedisPool(config, host);
		PrintUtil.two("3、创建redis.clients.jedis.JedisPool实例对象", pool);
		
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			PrintUtil.two("4、通过JedisPool.getResource()获取redis.clients.jedis.Jedis实例对象", jedis);
			
			String ping = jedis.ping();
			PrintUtil.two("5、通过Jedis.ping()测试连接redis服务", ping);
			
			String result = jedis.set("test", "hello JedisPool!");
			PrintUtil.two("6、通过Jedis.set(String key, String value)为字符串键设置值", result);
			
			String value = jedis.get("test");
			PrintUtil.two("7、通过Jedis.get(String key)获取字符串键的值", value);
		} catch (Exception e) {
			PrintUtil.err("使用JedisPool获取Jedis实例，出现异常，打印异常信息：");
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
				PrintUtil.two("8、Jedis.close()", "关闭Jedis");
			}
			pool.close();
			PrintUtil.two("9、JedisPool.close()", "关闭JedisPool");
		}
	}

}
