package com.mutisitc.statement.executequery;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mutisitc.utils.JDBCUtil;
import com.mutisitc.utils.PrintUtil;

/**
 * @program 使用Statement.executeQuery接口实现数据查询
 * @description 
 * @author mutisitic
 * @date 2018年9月26日
 */
public class ExecuteQueryMain {
	public static void main(String[] args) {
		PrintUtil.one("使用Statement.executeQuery接口实现数据查询：");
		try {
			Statement statement = JDBCUtil.createStatement();
			
			String querySQL = "SELECT bookId, title, author, remark, createrTime FROM book";
			PrintUtil.two("3.数据查询SQL语句：", querySQL);
			
			ResultSet resultSet = statement.executeQuery(querySQL);
			PrintUtil.two("4.Statement.executeQuery(String sql)：数据查询执行结果，返回单 ResultSet对象且不为null", "ResultSet=" + resultSet);
			
			PrintUtil.two("5.通过ResultSet对象循环得到数据", null);
			int index = 1;
			while(resultSet.next()) {
				Long bookId = resultSet.getLong("bookId");
				String title = resultSet.getString("title");
				String author = resultSet.getString("author");
				String remark = resultSet.getString("remark");
				Date createrTime = resultSet.getDate("createrTime");
				PrintUtil.three("5."+index+".通过ResultSet结果集，获取数据列信息", "bookId="+bookId+", title="+title+
						", author="+author+", remark="+remark+", createrTime="+createrTime);
				index++;
			}
			
			JDBCUtil.close(statement);
		} catch (SQLException e) {
			PrintUtil.err("使用Statement.execute接口实现，打印异常堆栈信息：");
			e.printStackTrace();
		}
	}
}
