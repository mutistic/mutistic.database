package com.mutisitc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mutisitc.utils.PrintUtil;

/**
 * @program java.sql.Connection：数据库连接
 * @description
 * @author mutisitic
 * @date 2018年9月19日
 */
public class ConnectionMain {
	public static void main(String[] args) {
		PrintUtil.one("java.sql.Connection：数据库连接：");
		try {
			String jdbcURL = "jdbc:mysql://127.0.0.1:3306/study?useSSL=false&serverTimezone=GMT%2B8";
			String userName = "root";
			String password = "root";
			PrintUtil.two("0.Mysql数据库连接信息：", null);
			PrintUtil.three("0.1.JDBC URL", jdbcURL);
			PrintUtil.three("0.2.userName", userName);
			PrintUtil.three("0.2.password", password);
			
			Connection connection = DriverManager.getConnection(jdbcURL, userName, password);
			PrintUtil.two("2.通过DriverManager.getConnection()获取数据库连接（会话）", "Connection="+connection);

			
		} catch (SQLException e) {
			PrintUtil.err("演示java.sql.Connection：数据库连接出现异常，打印异常堆栈信息：");
			e.printStackTrace();
		}
	}
}
