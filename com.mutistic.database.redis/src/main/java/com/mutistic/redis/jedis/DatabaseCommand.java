package com.mutistic.redis.jedis;

import java.util.List;
import java.util.Set;

import com.mutisitc.utils.JedisUtil;
import com.mutisitc.utils.PrintUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.SortingParams;

/**
 * @program 使用Jedis API操作数据库
 * @description 
 * @author mutisitic
 * @date 2019年1月10日
 */
public class DatabaseCommand {
	public static void main(String[] args) {
		PrintUtil.one("使用Jedis API操作数据库：");

		Jedis jedis = JedisUtil.getJedis();
		
		prepareTestData(jedis);
		showByGet(jedis);
		showByDetection(jedis);
		showByManager(jedis);
		showByCommon(jedis);
		
		JedisUtil.close(jedis);
	}

	/**
	 * @description 准备测试数据 
	 * @author mutisitic
	 * @date 2019年1月10日
	 * @param jedis
	 */
	private static void prepareTestData(Jedis jedis) {
		if(!jedis.exists("test")) {
			jedis.lpush("test", "1", "2", "3", "4");
		}
	}
	
	/**
	 * @description 1、数据库的获取命令
	 * @author mutisitic
	 * @date 2019年1月10日
	 * @param jedis
	 */
	private static void showByGet(Jedis jedis) {
		PrintUtil.one("1、数据库的获取命令：");
		
		Set<String> keySet = jedis.keys("*e*");
		PrintUtil.two("1.1、keys(String pattern)：从数据库里面获取所有符合给定模式的键【KEYS pattern】",
				"pattern=*e*, keySet=" + keySet);
		
		ScanResult<String> scanKey = jedis.scan("0");
		PrintUtil.two("1.2、scan(String cursor)：以渐进的方式获取数据库中的键【SCAN cursor】",
				"cursor=0, scanKey=" + scanKey.getResult());

		ScanParams scanParams = new ScanParams();
		scanParams.match("*e*");
		scanParams.count(2);
		ScanResult<String> scanKey2 = jedis.scan("0", scanParams);
		PrintUtil.two("1.2.1、scan(String key, String cursor, ScanParams params)：以渐进的方式获取数据库中的键"
				+ "【SCAN cursor [MATCH pattern] [COUNT count]】",
				"cursor=0, scanParams={match:一*,count:2}, scanKey=" + scanKey2.getResult());
		
		String key = jedis.randomKey();
		PrintUtil.two("1.3、randomKey()：从数据库里面随机地返回一个键【RANDOMKEY】", "key=" + key);
		
		List<String> sortValueList = jedis.sort("test");
		PrintUtil.two("1.4、sort(String key)：对给定的键进行排序【SORT key】", "key=test, sortValueList=" + sortValueList);
		
		Long sortSize = jedis.sort("test", "SORT:test");
		PrintUtil.two("1.4.1、sort(String key, String dstkey)：对给定的键进行排序【SORT key】", "key=test, dstkey=SORT:test, sortSize=" + sortSize);
		
		// SORT命令参考：http://doc.redisfans.com/key/sort.html
		SortingParams sortingParams = new SortingParams();
		// BY：通过使用 BY 选项，可以让 uid 按其他键的元素来排序（默认情况下， SORT uid 直接按 uid 中的值排序）
		sortingParams.by("*e*");
		// [LIMIT offset count]：使用 LIMIT 修饰符限制返回结果
		//   offset 指定要跳过的元素数量。
		//   count 指定跳过 offset 个指定的元素之后，要返回多少个对象
		sortingParams.limit(0, 10);
		// [ASC/DESC]：升序降序
		sortingParams.desc(); 
		// [ALPHA]：使用 ALPHA 修饰符对字符串进行排序(SORT命令默认排序对象为数字)
		sortingParams.alpha(); 
		
		List<String> sortValueList2 = jedis.sort("ZSet:ZADD", sortingParams);
		PrintUtil.two("1.4.2、sort(String key, SortingParams sortingParameters)：对给定的键进行排序"
				+ "【SORT key [BY pattern] [LIMIT offset count] [GET pattern [GET pattern ...]][ASC/DESC] [ALPHA] [STORE destination]】", 
				"key=ZSet:ZADD,sortingParams={[BY pattern=*e*],[LIMIT:offset=0,count=10], DESC, ALPHA}, sortValueList=" + sortValueList2);
	}
	
	/**
	 * @description 2、数据库的检测命令
	 * @author mutisitic
	 * @date 2019年1月10日
	 * @param jedis
	 */
	private static void showByDetection(Jedis jedis) {
		PrintUtil.one("2、数据库的检测命令：");
		
		Boolean exists = jedis.exists("test");
		PrintUtil.two("2.1、exists(String key)：检查给定的键是否存在于数据库【EXISTS key】",
				"key=test, exists=" + exists);
		
		Long dbSize = jedis.dbSize();
		PrintUtil.two("2.2、dbSize()：返回当前正在使用的数据库包含的键值对数量【DBSIZE】",
				"DBIndex=0, dbSize=" + dbSize);
		
		String type = jedis.type("ZSet:ZADD");
		PrintUtil.two("2.3、type(String key)：返回给定键储存的值的类型【TYPE key】",
				"key=ZSet:ZADD, type=" + type);
	}
	
	/**
	 * @description 3、数据库的管理命令
	 * @author mutisitic
	 * @date 2019年1月10日
	 * @param jedis
	 */
	private static void showByManager(Jedis jedis) {
		PrintUtil.one("3、数据库的管理命令：");
		
		String oldKey = jedis.rename("test", "RENAME:Test");
		PrintUtil.two("3.1、rename(String oldkey, String newkey)：为给定键设置一个新名字【RENAME key new-key】",
				"oldKey=test, newKey=RENAME:Test, oldKey=" + oldKey);
		
		Long result = jedis.renamenx("RENAME:Test", "RENAMENX:Test");
		PrintUtil.two("3.2、renamenx(String oldkey, String newkey)：仅在新名字尚未被使用的情况下，为给定键设置一个新名字【RENAMENX key new-key】",
				"oldkey=RENAME:Test, newKey=RENAMENX:Test, result=" + result);
		
		Long result2 =jedis.move("RENAMENX:Test", 1);
		PrintUtil.two("3.3、move(String key, int dbIndex)：将当前数据库中的给定键移动到指定的数据库【MOVE key db】",
				"key=RENAMENX:Test, dbIndex=1, result=" + result2);
		
		Long dbIndex = jedis.getDB();
		PrintUtil.two("3.4、getDB()：获取当前数据库索引", "dbIndex=" + dbIndex);
		
		String result3 = jedis.select(1);
		PrintUtil.two("3.5、select(int index)：切换至指定的数据库【SELECT number】", "index=1, result=" + result3);
		
		Long result4 = jedis.del("RENAMENX:Test", "test");
		PrintUtil.two("3.6、del(String key)：从数据库中删除给定的一个或多个键【DEL key [key ...]】", "key=[RENAMENX:Test, test], result=" + result4);
		
		String result5 = jedis.flushDB();
		PrintUtil.two("3.7、flushDB()：删除当前数据库中的所有键【FLUSHDB】", "dbIndex=1, result=" + result5);
		
		String result6 = jedis.flushAll();
		PrintUtil.two("3.7、flushAll()：删除服务器中，所有数据库的所有键【FLUSHALL】", "result=" + result6);
	}
	
	/**
	 * @description 4、 数据库的常用命令
	 * @author mutisitic
	 * @date 2019年1月10日
	 * @param jedis
	 */
	private static void showByCommon(Jedis jedis) {
		PrintUtil.one("4、数据库的常用命令：");
		
		String result = jedis.echo("测试连接");
		PrintUtil.two("4.1、echo(String string)：让服务器打印指定的消息，用于测试连接【ECHO message】", "string=测试连接, result=" + result);
		
		String result2 = jedis.ping();
		PrintUtil.two("4.2、ping()：向服务器发送一条 PING 消息，用于测试连接或者测量延迟值【PING】", "result=" + result2);
		
		String result3 = jedis.configSet("timeout", "10000");
		PrintUtil.two("4.3、configSet(String parameter, String value)：为给定的配置选项设置值【CONFIG SET option value】", 
				"parameter=timeout, value=10000, result=" + result3);
		
//		String result4 = jedis.auth(null);
		PrintUtil.two("4.4、auth(String password)：使用给定的密码连接服务器【AUTH password】", "password=null");
		
		List<String> parameterValueList = jedis.configGet("bind");
		PrintUtil.two("4.5、configGet(String pattern)：返回给定配置选项的值【CONFIG GET option】", 
				"parameter=timeout, parameterValueList=" + parameterValueList);
		
		// CONFIG REWRITE：对服务器的配置选项文件进行重写，并将改写后的文件储存在硬盘里面
		
		String result5 = jedis.configResetStat();
		PrintUtil.two("4.5、configResetStat()：重置服务器的某些统计数据【CONFIG RESETSTAT】", "result=" + result5);

		List<String> time = jedis.time();
		PrintUtil.two("4.6、time()：返回服务器当前的 UNIX 时间戳【TIME】", "time=" + time);
		
//		String info = jedis.info("redis.conf");
		String info = jedis.info();
		PrintUtil.two("4.7、info()：返回与服务器相关的统计信息【INFO [section]】", "info=" + info);

		String quit = jedis.quit();
		PrintUtil.two("4.8、quit()：请求服务器关闭与当前客户端的连接【QUIT】", "quit=" + quit);
		
		// SHUTDOWN [SAVE|NOSAVE]：关闭服务器 [SAVE|NOSAVE]：可选参数，是否备份数据。SAVE备份数据，NOSAVE不备份。
//		String shutdown = jedis.shutdown();
		PrintUtil.two("4.9、shutdown()：关闭服务器【SHUTDOWN [SAVE|NOSAVE]】", "shutdown");
	}
}
