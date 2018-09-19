package com.mutisitc.drivermanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;

import com.mutisitc.utils.PrintUtil;

/**
 * @program java.sql.DriverManager：驱动管理
 * @description 
 * @author mutisitic
 * @date 2018年9月19日
 */
public class DriverManagerMain {
	public static void main(String[] args) {
		PrintUtil.one("java.sql.DriverManager：驱动管理：");
		try {
			String jdbcURL = "jdbc:mysql://127.0.0.1:3306/study?useSSL=false&serverTimezone=GMT%2B8";
			String userName = "root";
			String password = "root";
			PrintUtil.two("0.Mysql数据库连接信息：", null);
			PrintUtil.three("0.1.JDBC URL", jdbcURL);
			PrintUtil.three("0.2.userName", userName);
			PrintUtil.three("0.3.password", password);
			
			PrintUtil.two("1.使用DriverManager.getConnection()获取数据库连接", "DriverManager.getConnection(String url, Properties info)");
			Properties properties = new Properties();
			properties.put("user", userName); 
			properties.put("password", password);
			PrintUtil.three("1.1.配置Properties属性，至少包含user和password信息", properties.getClass()+"="+properties);
			
			Connection connection = DriverManager.getConnection(jdbcURL, properties);
			PrintUtil.three("1.2.DriverManager.getConnection(String url, Properties info)：尝试建立与给定数据库URL的连接", "Connection="+connection);
			connection.close();
			
			Connection connection2 = DriverManager.getConnection(jdbcURL, userName, password);
			PrintUtil.two("2.DriverManager.getConnection(String url, String user, String password)：尝试建立与给定数据库URL的连接", "Connection="+connection2);
			connection2.close();
			
			Driver driver = DriverManager.getDriver(jdbcURL);
			PrintUtil.two("3.DriverManager.getDriver()：获取到的数据库驱动", "Driver="+driver);
			
			com.mysql.jdbc.Driver dirver2 = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(dirver2);
			PrintUtil.two("4.DriverManager.registerDriver(Driver driver)：注册给定的驱动程序DriverManager", "Driver="+dirver2);
			
			Enumeration<Driver> driverEnums = DriverManager.getDrivers();
			PrintUtil.two("5.DriverManager.getDrivers()：检索当前调用者可以访问的所有当前加载的JDBC驱动程序的枚举", "Enumeration<Driver>="+driverEnums);
			int index = 0;
			while(driverEnums.hasMoreElements()) {
				index++;
				Driver driverTemp = driverEnums.nextElement();
				PrintUtil.three("5."+index+".当前加载的JDBC驱动程序", "Driver="+driverTemp);
			}
			
			DriverManager.deregisterDriver(dirver2);
			PrintUtil.two("6.DriverManager.deregisterDriver(Driver driver)：从已DriverManager注册的驱动程序列表中删除指定的驱动程序", "Driver="+dirver2);
			
			int loginTimeout = DriverManager.getLoginTimeout();
			PrintUtil.two("7.DriverManager.getLoginTimeout()：获取驱动程序在尝试登录数据库时可以等待的最长时间（以秒为单位）", "loginTimeout="+loginTimeout);
			
			loginTimeout = 10000;
			DriverManager.setLoginTimeout(loginTimeout);
			PrintUtil.two("8.DriverManager.setLoginTimeout(int seconds)：设置驱动程序在识别驱动程序后尝试连接数据库时等待的最长时间（以秒为单位）", "loginTimeout="+loginTimeout);
			
			PrintStream printStream = DriverManager.getLogStream();
			PrintUtil.two("9.DriverManager.getLogStream()：已过时，检索由DriverManager和所有驱动程序使用的日志记录/跟踪PrintStream", "PrintStream="+printStream);
			
			File file = new File("src/com/mutisitc/drivermanager/logStream.txt");
			PrintUtil.two("10.加载文件", "File="+file);
			
			PrintStream newPrintStream = new PrintStream(file);
			DriverManager.setLogStream(newPrintStream);
			PrintUtil.three("10.1.通过DriverManager.setLogStream(PrintStream out)：已过时，检索由DriverManager 和所有驱动程序使用的日志记录/跟踪PrintStream", "PrintStream="+newPrintStream);
			
			PrintWriter printWriter = DriverManager.getLogWriter();
			PrintUtil.two("11.通过DriverManager.getLogStream()：检索日志编写器", "PrintWriter="+printWriter);
			
			PrintUtil.two("12.加载文件", "File="+file);
			PrintWriter newPrintWriter = new PrintWriter(file);
			DriverManager.setLogWriter(newPrintWriter);
			PrintUtil.three("12.1.通过DriverManager.setLogWriter(PrintWriter out)：设置和所有驱动程序PrintWriter使用的日志记录/跟踪对象DriverManager", "PrintWriter="+newPrintWriter);
		
			String message = "测试打印数据";
			DriverManager.println(message);
			PrintUtil.two("13.DriverManager.println(String message)：将消息打印到当前JDBC日志流", "Message="+message);
			
		} catch (SQLException e) {
			PrintUtil.err("演示 java.sql.DriverManager：驱动管理出现异常，打印异常堆栈信息：");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			PrintUtil.err("加载文件路径出现异常，打印异常堆栈信息：");
			e.printStackTrace();
		}
	}
}
