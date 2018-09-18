package com.mutisitc.step;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mutisitc.utils.PrintUtil;

/**
 * @program JDBC连接数据库步骤
 * @description
 * @author mutisitic
 * @date 2018年9月18日
 */
public class StepMain {
	public static void main(String[] args) {
		PrintUtil.one("JDBC连接数据库步骤");
		
		Connection connection = null;
		PrintUtil.two("0.创建Connection数据库连接引用", connection);
		
		try {
			Class<?> driver = Class.forName("com.mysql.cj.jdbc.Driver");
			PrintUtil.two("1.通过Class.forName()加载Driver驱动类：com.mysql.cj.jdbc.Driver", driver);

			connection = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/study?useSSL=false&serverTimezone=GMT%2B8", "root", "root");
			PrintUtil.two("2.通过DriverManager.getConnection()，获取数据库连接Connection", connection);

			Statement statement = connection.createStatement();
			PrintUtil.two("3.通过Connection.createStatement()连接，创建Statement对象", statement);
			
			ResultSet rs = statement.executeQuery("SELECT bookId,title,author,remark,createrTime FROM book");
			PrintUtil.two("4.通过Statement.executeQuery()，执行查询SQL，获取查询结果集ResultSet对象", rs);
			
			PrintUtil.println();
			while (rs.next()) {
				Long bookId = rs.getLong("bookId");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String remark = rs.getString("remark");
				Date createrTime = rs.getDate("createrTime");
				PrintUtil.three("5.通过ResultSet结果集，获取数据列信息", "bookId="+bookId+", title="+title+
						",author="+author+"remark="+remark+",createrTime="+createrTime);
			}
			statement.close();
			PrintUtil.two("6.通过Statement.close()","关闭Statement对象，释放资源");
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
					PrintUtil.two("7.通过Connection.close()","关闭数据库连接，释放资源");
				} catch (SQLException e) {
					PrintUtil.err("Connection.close()关闭连接出现异常，打印异常堆栈信息：");
					e.printStackTrace();
				}
			}
		}
	}
}
