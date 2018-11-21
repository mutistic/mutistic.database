package com.mutistic.redis.java;

import com.mutisitc.utils.PrintUtil;

import redis.clients.jedis.Jedis;

/**
 * @program 直接创建Jedis实例
 * @description 
 * @author mutisitic
 * @date 2018年11月21日
 */
public class ClientByJedis {

	public static void main(String[] args) {
		PrintUtil.one("直接创建Jedis实例：");
		
		String host = "192.168.16.113";
		PrintUtil.two("1、定义redis的IP地址(和redis.conf的bind参数一致)：", host);
		
		int port = 6379;
		PrintUtil.two("2、定义redis的端口号(和redis.conf的port参数一致，默认为：6379)：", port);
		
		Jedis jedis = null;
		try {
			jedis = new Jedis(host, port);
			PrintUtil.two("3、通过构造函数创建redis.clients.jedis.Jedis实例对象", jedis);
			
			String ping = jedis.ping();
			PrintUtil.two("4、通过Jedis.ping()测试连接redis服务", ping);
			
			String result = jedis.set("test", "hello Jedis!");
			PrintUtil.two("5、通过Jedis.set(String key, String value)为字符串键设置值", result);
			
			String value = jedis.get("test");
			PrintUtil.two("6、通过Jedis.get(String key)获取字符串键的值", value);
		} catch (Exception e) {
			PrintUtil.err("直接创建Jedis实例，出现异常，打印异常信息：");
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
				PrintUtil.two("7、Jedis.close()", "关闭Jedis");
			}
		}
	}

}
