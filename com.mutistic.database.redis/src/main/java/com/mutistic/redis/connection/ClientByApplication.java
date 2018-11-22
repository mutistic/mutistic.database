package com.mutistic.redis.connection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.mutisitc.utils.PrintUtil;

/**
 * @program 在spring boot run启动时使用redis
 * @description 
 * @author mutisitic
 * @date 2018年11月21日
 */
@SpringBootApplication
public class ClientByApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context  = SpringApplication.run(ClientByApplication.class, args);
		PrintUtil.one("在spring boot run启动时使用redis：");
		
		PrintUtil.two("1、通过SpringApplication.run()获取ConfigurableApplicationContext实例：：", context);
		
		StringRedisTemplate stringRedisTemplate = context.getBean(StringRedisTemplate.class);
		PrintUtil.two("2、通过ConfigurableApplicationContext.getBean(StringRedisTemplate.class)"
				+ "获取org.springframework.data.redis.core.StringRedisTemplate实例：：", stringRedisTemplate);
		
		stringRedisTemplate.opsForValue().set("testByRun", "Hello StringRedisTemplate By Run");
		PrintUtil.two("3、通过StringRedisTemplate.opsForValue().set()：", "字符串键设置值");
		
		String value = stringRedisTemplate.opsForValue().get("testByRun");
		PrintUtil.two("4、通过StringRedisTemplate.opsForValue().get(Object key)获取字符串键的值：", value);
		
		PrintUtil.two("注意：redis连接超时时间（单位毫秒）不能设置为小于等于0的数字，"
				+ "小于0会导致启动报错，等于0会照成直接超时，与原本redis定义的timeout=0（禁止含义冲突）。可以不设置或设置为：", "spring.redis.timeout=1000000");
		
		context.close();
	}
}
