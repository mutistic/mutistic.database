package com.mutisitc.preparedstatement.operation;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mutisitc.utils.JDBCUtil;
import com.mutisitc.utils.PrintUtil;

/**
 * @program 使用PreparedStatement实现数据查询
 * @description 
 * @author mutisitic
 * @date 2018年9月28日
 */
public class QueryByMain {
	public static void main(String[] args) {
		PrintUtil.one("使用PreparedStatement实现数据更新：");
		try {
			String preparedSQL = "SELECT bookId, title, author, remark, createrTime FROM book WHERE bookId >= ?";
			PreparedStatement preparedStatement = JDBCUtil.prepareStatement(preparedSQL);
			preparedStatement.setLong(1, 1538105479358l);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			PrintUtil.two("3.PreparedStatement.executeQuery()：执行SQL返回结果：", "ResultSet="+resultSet);
			int index = 1;
			while(resultSet.next()) {
				Long bookId = resultSet.getLong("bookId");
				String title = resultSet.getString("title");
				String author = resultSet.getString("author");
				String remark = resultSet.getString("remark");
				Date createrTime = resultSet.getDate("createrTime");
				PrintUtil.three("3."+index+".通过ResultSet结果集，获取数据列信息", "bookId="+bookId+", title="+title+
						", author="+author+", remark="+remark+", createrTime="+createrTime);
				index++;
			}
			
			JDBCUtil.close(preparedStatement);
		} catch (SQLException e) {
			PrintUtil.err("使用PreparedStatement实现数据更新，打印异常堆栈信息：");
			e.printStackTrace();
		}
	}
}
