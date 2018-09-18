package com.mutisitc.driver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Properties;

import com.mutisitc.utils.PrintUtil;

/**
 * @program java.sql.Driver：数据库驱动
 * @description
 * @author mutisitic
 * @date 2018年9月18日
 */
public class DriverMain {
	public static void main(String[] args) {
		PrintUtil.one("java.sql.Driver：数据库驱动：");
		try {
			String jdbcURL = "jdbc:mysql://127.0.0.1:3306/study?useSSL=false&serverTimezone=GMT%2B8";
			String userName = "root";
			String password = "root";
			PrintUtil.three("0.Mysql数据库连接信息：", null);
			PrintUtil.three("JDBC URL", jdbcURL);
			PrintUtil.three("userName", userName);
			PrintUtil.three("password", password);
			
			Driver driver = DriverManager.getDriver(jdbcURL);
			PrintUtil.two("1.通过DriverManager.getDriver()：获取到的数据库驱动", "Driver="+driver);
			
			boolean isacceptsURL = driver.acceptsURL(jdbcURL);
			PrintUtil.two("2.Driver.isacceptsURL()：获取驱动程序是否认为它可以打开与给定URL的连接：获取结果", "isacceptsURL="+isacceptsURL);
			
			PrintUtil.two("3.使用Driver获取数据库连接", "Driver.connect(String url, Properties info)");
			Properties properties = new Properties();
			properties.put("user", userName); 
			properties.put("password", password);
			PrintUtil.three("3.1.配置Properties属性，至少包含user和password信息", properties.getClass()+"="+properties);
			
			Connection connection = driver.connect(jdbcURL, properties);
			PrintUtil.three("3.2.Driver.connect()：尝试与给定的URL建立数据库连接", "Connection="+connection);
			connection.close();
			
			int majorVersion = driver.getMajorVersion();
			PrintUtil.two("4.通过Driver.getMajorVersion()：获取驱动程序的主要版本号", "MajorVersion="+majorVersion);
			
			int minorVersion = driver.getMinorVersion();
			PrintUtil.two("5.通过Driver.getMinorVersion()：获取驱动程序的次要版本号", "MinorVersion="+minorVersion);
			
			boolean jdbcCompliant = driver.jdbcCompliant();
			PrintUtil.two("6.通过Driver.jdbcCompliant()：报告此驱动程序是否为真正的JDBC Compliant驱动程序", "jdbcCompliant="+jdbcCompliant);
			
			DriverPropertyInfo[] driverPropertyInfos = driver.getPropertyInfo(jdbcURL, properties);
			PrintUtil.two("7.通过Driver.getPropertyInfo()：获取有关此驱动程序的可能属性的信息", "DriverPropertyInfo="+Arrays.asList(driverPropertyInfos));
			int index = 0;
			for (DriverPropertyInfo driverPropertyInfo : driverPropertyInfos) {
				index++;
				PrintUtil.two("  7."+index+".驱动程序的属性信息：DriverPropertyInfo", driverPropertyInfo);
				PrintUtil.three("DriverPropertyInfo.name：属性的名称", driverPropertyInfo.name);
				PrintUtil.three("DriverPropertyInfo.required：是否在Driver.connect期间必须为此属性提供一个值", driverPropertyInfo.required);
				PrintUtil.three("DriverPropertyInfo.value：value 字段通过综合为 getPropertyInfo 方法提供的信息、Java 环境和驱动程序提供的默认值来指定当前属性值", driverPropertyInfo.value);
				PrintUtil.three("DriverPropertyInfo.choices：可以从特定值集中选择字段的值：", driverPropertyInfo.choices == null ? null : Arrays.asList(driverPropertyInfo.choices));
				PrintUtil.three("DriverPropertyInfo.description：属性的简要描述",driverPropertyInfo.description); 
			}
		} catch (SQLException e) {
			PrintUtil.err("演示 java.sql.Driver：数据库驱动，打印异常堆栈信息：");
			e.printStackTrace();
		}
	}
}
