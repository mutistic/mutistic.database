package com.mutisitc.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @program Jedis Client 工具类
 * @description 
 * @author mutisitic
 * @date 2018年11月23日
 */
public class JedisUtil {
	/** redis IP地址 */
	private static String REDIS_HOST = "192.168.16.170";
	/** redis 端口号 */
	private static int REDIS_PORT = 6379;
	/** redis.clients.jedis.JedisPool实例 */
	private static JedisPool JEDIS_POOL = null;
	
	/**
	 * @description 获取Jedis实例对象 
	 * @author mutisitic
	 * @date 2018年11月23日
	 */
	public static Jedis getJedis() {
		PrintUtil.one("0、使用JedisPool获取Jedis实例");
		
		JedisPoolConfig config = new JedisPoolConfig();
		PrintUtil.two("0.1、创建redis.clients.jedis.JedisPoolConfig实例对象：", config);
		
		PrintUtil.two("0.2、获取配置的redis的IP地址和端口号", "host="+REDIS_HOST+",prot="+REDIS_PORT);
		
		if(JEDIS_POOL == null) {
			 JEDIS_POOL = new JedisPool(config, REDIS_HOST, REDIS_PORT);
		}
		PrintUtil.two("0.3、创建redis.clients.jedis.JedisPool实例对象", JEDIS_POOL);
		
		Jedis jedis = null;
		try {
			jedis = JEDIS_POOL.getResource();
			PrintUtil.two("0.4、通过JedisPool.getResource()获取redis.clients.jedis.Jedis实例对象", jedis);
		} catch (Exception e) {
			PrintUtil.err("使用JedisPool获取Jedis实例，出现异常，打印异常信息：");
			e.printStackTrace();
			close(jedis);
		} 
		return jedis;
	}
	
	/**
	 * @description 关闭Jedis链接 
	 * @author mutisitic
	 * @date 2018年11月23日
	 * @param jedis
	 */
	public static void close(Jedis jedis) {
		if(jedis != null) {
			jedis.close();
		}
		if(JEDIS_POOL != null) {
			JEDIS_POOL.close();
		}
	}
}
