package com.mutistic.redis.junit;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mutisitc.utils.PrintUtil;
import com.mutistic.redis.Application;

/**
 * @program 使用StringRedisTemplate API+JUnit操作简单操作Redis事物
 * @description 
 * @author mutisitic
 * @date 2019年1月11日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class TransactionCommandByJUnit {

	@Autowired
	private StringRedisTemplate template;

	@Test
	public void test() throws Exception {
		PrintUtil.one("1、使用StringRedisTemplate API+JUnit操作简单操作Redis：");
		PrintUtil.two("1.1、通过@Autowired自动注入org.springframework.data.redis.core.StringRedisTemplate实例：", template);
		
		template.setEnableTransactionSupport(true);
		PrintUtil.two("2.1、StringRedisTemplate.setEnableTransactionSupport(true)：开启redis事务支持，默认关闭", 
				"RedisTemplate.enableTransactionSupport=true");
		
		template.multi();
		PrintUtil.two("2.2、StringRedisTemplate.multi()：开始一次事务", "MULTI");
		
		template.opsForValue().set("SET:String", "test");
		template.opsForValue().setIfAbsent("SET:String", "test2");
		PrintUtil.two("2.3、StringRedisTemplate.setIfAbsent(String key, String value)：仅在字符串键尚未有值的情况下，为它设置值"
				+ "【SETNX key value】", "key=SET:String, value=test2");
		template.opsForValue().set("SET:String3", "test3");

		List<Object> execResult = template.exec();
		PrintUtil.two("2.4、StringRedisTemplate.exec()：执行事务【EXEC】", "execResult="+execResult);
	}
}