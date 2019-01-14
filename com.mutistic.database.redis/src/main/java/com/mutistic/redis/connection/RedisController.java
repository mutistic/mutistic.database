package com.mutistic.redis.connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program 在Controller中使用Redis
 * @description 
 * @author mutisitic
 * @date 2019年1月14日
 */
@RestController
@RequestMapping("/redisController/")
public class RedisController {

	/**
	 * 自动注入StringRedisTemplate实例
	 */
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * @description 为字符串键设置值
	 * @author mutisitic
	 * @date 2019年1月14日
	 * @param key 字符串键
	 * @param value 字符串值
	 * @return
	 */
	@GetMapping("setString")
	public String set(String key, String value) {
		stringRedisTemplate.opsForValue().set(key, value);
		return "SUCCESS";
	}
	
	/**
	 * @description 获取字符串键的值
	 * @author mutisitic
	 * @date 2019年1月14日
	 * @param key 字符串键
	 * @return 字符串值
	 */
	@GetMapping("getString")
	public String get(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}
	
}
