package com.mutisitc.step;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mutisitc.utils.PrintUtil;

/**
 * @program JDBC连接数据库步骤
 * @description
 * @author mutisitic
 * @date 2018年9月18日
 */
public class DriverMain {
	public static void main(String[] args) {
		PrintUtil.one("JDBC连接数据库步骤");
		
		Connection connection = null;
		try {
			Class<?> driver = Class.forName("com.mysql.cj.jdbc.Driver");
			PrintUtil.two("加载Driver驱动类", driver);
			
			connection = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/study?useSSL=false&serverTimezone=GMT%2B8", "root", "root");
			PrintUtil.two("DriverManager链接数据库获取Connection", connection);

		} catch (ClassNotFoundException e) {
			PrintUtil.err("Class.forName()加载Dirver驱动类出现异常，打印异常堆栈信息：");
			e.printStackTrace();
		} catch (SQLException e) {
			PrintUtil.err("DriverManager.getConnection()获取连接Connection出现异常，打印异常堆栈信息：");
			e.printStackTrace();
		} finally {
			if (null != connection) {
				try {
					connection.close();
					PrintUtil.two("Connection.close()", "成功关闭连接");
				} catch (SQLException e) {
					PrintUtil.err("Connection.close()关闭连接出现异常，打印异常堆栈信息：");
					e.printStackTrace();
				}
			}
		}
	}
}
