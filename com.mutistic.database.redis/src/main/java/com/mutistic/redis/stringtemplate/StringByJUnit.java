package com.mutistic.redis.stringtemplate;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mutisitc.utils.PrintUtil;
import com.mutistic.redis.Application;

/**
 * @program 使用RedisTemplate API操作String数据类型
 * @description 
 * @author mutisitic
 * @date 2018年11月21日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class StringByJUnit {

	@Autowired
	private StringRedisTemplate template;

	@Test
	public void test() throws Exception {
		PrintUtil.one("使用Jedis API操作String数据类型：");
		PrintUtil.two("1、通过@Autowired自动注入org.springframework.data.redis.core.StringRedisTemplate实例：", template);
		
		showByGetAndSet();
	}
	
	/**
	 * @description 字符串的设置与获取
	 * @author mutisitic
	 * @date 2018年11月23日
	 */
	private void showByGetAndSet() {
		PrintUtil.one("2、字符串的设置与获取：");
		template.opsForValue().set("showByGetAndSet", "字符串的设置与获取");
		PrintUtil.two("2.1、set(String key, String value)：为字符串键设置值，无返回", "SET key value");
		
		String value = template.opsForValue().get("showByGetAndSet");
		PrintUtil.two("2.2、get(Object key)获取字符串键的值："+value, "GET key");
		
		String oldValue = template.opsForValue().getAndSet("showByGetAndSet", "GETSET key new-value：为字符串键设置值，无返回");
		PrintUtil.two("2.3、getAndSet(String key, String value)：为字符串键设置值，返回旧值："+ oldValue, "GETSET key new-value");
		
		template.opsForValue().set("setByTimeout", "设置字符串的过期时间", 100l, TimeUnit.SECONDS);
		PrintUtil.two("2.4、set(String key, String value, long timeout, TimeUnit unit)：设置字符串的过期时间", "SET key value [EX seconds] [PX milliseconds]命令");
	}
	
}