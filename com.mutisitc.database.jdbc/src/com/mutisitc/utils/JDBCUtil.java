package com.mutisitc.utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @program JDBC工具类
 * @description
 * @author mutisitic
 * @date 2018年9月21日
 */
public class JDBCUtil {
	/** Database ip:port */
	private final static String IP_PORT = "127.0.0.1:3306";
	/** Database 名称 */
	private final static String DB_NAME = "study";
	/** Database user name */
	private final static String USER_NAME = "root";
	/** Database 密码 */
	private final static String PASS_WORD = "root";
	/** JDBC 驱动类名 */
	private final static String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
	/** JDBC URL */
	private final static String JDBC_URL = "jdbc:mysql://" + IP_PORT + "/" + DB_NAME
			+ "?useSSL=false&serverTimezone=GMT%2B8";

	/**
	 * @description 获取数据库连接
	 * @author mutisitic
	 * @date 2018年9月21日
	 * @return 创建后的数据库连接
	 */
	public static Connection getConnection() {
		Connection connection = null;
		try {
			PrintUtil.two("0.Mysql数据库连接信息：", "JDBC URL=" + JDBC_URL + ", userName=" + USER_NAME + ", password="
					+ PASS_WORD + "，driver class name=" + DRIVER_CLASS_NAME);

			// 加载驱动-JDBC 4.0新特性可以不用显示加载
			Class.forName(DRIVER_CLASS_NAME);

			connection = DriverManager.getConnection(JDBC_URL, USER_NAME, PASS_WORD);
			PrintUtil.two("1.通过DriverManager.getConnection()获取数据库连接（会话）", "Connection=" + connection);
			return connection;
		} catch (ClassNotFoundException e) {
			PrintUtil.err("加载驱动" + DRIVER_CLASS_NAME + "出现异常，打印异常堆栈信息：");
			e.printStackTrace();
		} catch (SQLException e) {
			PrintUtil.err("获取数据库连接出现异常，打印异常堆栈信息：");
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * @description 创建Statement对象
	 * @author mutisitic
	 * @date 2018年9月21日
	 * @return 创建后的Statement对象
	 */
	public static Statement createStatement() {
		try {
			PrintUtil.two("0.Mysql数据库连接信息：", "JDBC URL=" + JDBC_URL + ", userName=" + USER_NAME + ", password="
					+ PASS_WORD + "，driver class name=" + DRIVER_CLASS_NAME);

			// 加载驱动-JDBC 4.0新特性可以不用显示加载
			Class.forName(DRIVER_CLASS_NAME);

			Connection connection = DriverManager.getConnection(JDBC_URL, USER_NAME, PASS_WORD);
			PrintUtil.two("1.通过DriverManager.getConnection()获取数据库连接（会话）", "Connection=" + connection);

			Statement statement = connection.createStatement();
			PrintUtil.two("2.通过Connection.createStatement()：创建Statement对象", "Statement=" + statement);
			return statement;
		} catch (ClassNotFoundException e) {
			PrintUtil.err("加载驱动" + DRIVER_CLASS_NAME + "出现异常，打印异常堆栈信息：");
			e.printStackTrace();
		} catch (SQLException e) {
			PrintUtil.err("获取数据库连接出现异常，打印异常堆栈信息：");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @description 创建PreparedStatement对象
	 * @author mutisitic
	 * @date 2018年9月21日
	 * @return 创建后的PreparedStatement对象
	 */
	public static PreparedStatement prepareStatement(String sql) {
		try {
			PrintUtil.two("0.Mysql数据库连接信息：", "JDBC URL=" + JDBC_URL + ", userName=" + USER_NAME + ", password="
					+ PASS_WORD + "，driver class name=" + DRIVER_CLASS_NAME);

			// 加载驱动-JDBC 4.0新特性可以不用显示加载
			Class.forName(DRIVER_CLASS_NAME);

			Connection connection = DriverManager.getConnection(JDBC_URL, USER_NAME, PASS_WORD);
			PrintUtil.two("1.通过DriverManager.getConnection()获取数据库连接（会话）", "Connection=" + connection);

			PreparedStatement prepared = connection.prepareStatement(sql);
			PrintUtil.two("2.通过Connection.prepareStatement(String sql)：创建PreparedStatement对象",
					"PreparedStatement=" + prepared + ", sql=" + sql);
			return prepared;
		} catch (ClassNotFoundException e) {
			PrintUtil.err("加载驱动" + DRIVER_CLASS_NAME + "出现异常，打印异常堆栈信息：");
			e.printStackTrace();
		} catch (SQLException e) {
			PrintUtil.err("获取数据库连接出现异常，打印异常堆栈信息：");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @description 获取CallableStatement对象
	 * @author mutisitic
	 * @date 2018年9月21日
	 * @return 创建后的CallableStatement对象
	 */
	public static CallableStatement prepareCall(String sql) {
		try {
			PrintUtil.two("0.Mysql数据库连接信息：", "JDBC URL=" + JDBC_URL + ", userName=" + USER_NAME + ", password="
					+ PASS_WORD + "，driver class name=" + DRIVER_CLASS_NAME);

			// 加载驱动-JDBC 4.0新特性可以不用显示加载
			Class.forName(DRIVER_CLASS_NAME);

			Connection connection = DriverManager.getConnection(JDBC_URL, USER_NAME, PASS_WORD);
			PrintUtil.two("1.通过DriverManager.getConnection()获取数据库连接（会话）", "Connection=" + connection);

			CallableStatement callable = connection.prepareCall(sql);
			PrintUtil.two("2.通过Connection.prepareCall(String sql)：创建CallableStatement对象",
					"CallableStatement=" + callable + ", sql=" + sql);
			return callable;
		} catch (ClassNotFoundException e) {
			PrintUtil.err("加载驱动" + DRIVER_CLASS_NAME + "出现异常，打印异常堆栈信息：");
			e.printStackTrace();
		} catch (SQLException e) {
			PrintUtil.err("获取数据库连接出现异常，打印异常堆栈信息：");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @description 关闭数据库连接
	 * @author mutisitic
	 * @date 2018年9月21日
	 * @param connection
	 *            需要关闭的数据库连接
	 */
	public static void close(Connection connection) {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
				PrintUtil.two("成功关闭数据库连接", "Connection.close()");
			}
		} catch (SQLException e) {
			PrintUtil.err("关闭数据库连接出现异常，打印异常堆栈信息：");
			e.printStackTrace();
		}
	}

	/**
	 * @description 关闭Statement对象
	 * @author mutisitic
	 * @date 2018年9月21日
	 * @param statement
	 *            需要关闭的Statement对象
	 */
	public static void close(Statement statement) {
		try {
			if (statement != null && !statement.isClosed()) {
				statement.close();
				PrintUtil.two("成功Statement对象", "Statement.close()");
			}
		} catch (SQLException e) {
			PrintUtil.err("关闭Statement对象出现异常，打印异常堆栈信息：");
			e.printStackTrace();
		}
	}

	/**
	 * @description 关闭PreparedStatement对象
	 * @author mutisitic
	 * @date 2018年9月21日
	 * @param prepared
	 *            需要关闭的PreparedStatement对象
	 */
	public static void close(PreparedStatement prepared) {
		try {
			if (prepared != null && !prepared.isClosed()) {
				prepared.close();
				PrintUtil.two("成功PreparedStatement对象", "PreparedStatement.close()");
			}
		} catch (SQLException e) {
			PrintUtil.err("关闭PreparedStatement对象出现异常，打印异常堆栈信息：");
			e.printStackTrace();
		}
	}

	/**
	 * @description 关闭CallableStatement对象
	 * @author mutisitic
	 * @date 2018年9月21日
	 * @param callable
	 *            需要关闭的CallableStatement对象
	 */
	public static void close(CallableStatement callable) {
		try {
			if (callable != null && !callable.isClosed()) {
				callable.close();
				PrintUtil.two("成功CallableStatement对象", "Statement.close()");
			}
		} catch (SQLException e) {
			PrintUtil.err("关闭CallableStatement对象出现异常，打印异常堆栈信息：");
			e.printStackTrace();
		}
	}
}
