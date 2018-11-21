package com.mutistic.redis.connection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mutisitc.utils.PrintUtil;
import com.mutistic.redis.Application;

/**
 * @program 使用Junit测试使用redis
 * @description 
 * @author mutisitic
 * @date 2018年11月21日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ClientByJUnit {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void test() throws Exception {
		PrintUtil.one("使用Junit测试使用redis：");
		
		PrintUtil.two("1、通过@Autowired自动注入org.springframework.data.redis.core.StringRedisTemplate实例：", stringRedisTemplate);
		
		stringRedisTemplate.opsForValue().set("testByJUnit", "Hello StringRedisTemplate By JUnit");
		PrintUtil.two("2、通过StringRedisTemplate.opsForValue().set()：", "字符串键设置值");
		
		String value = stringRedisTemplate.opsForValue().get("testByJUnit");
		PrintUtil.two("3、通过StringRedisTemplate.opsForValue().get(Object key)获取字符串键的值：", value);
	}

}