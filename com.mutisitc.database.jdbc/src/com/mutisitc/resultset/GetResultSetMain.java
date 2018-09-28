package com.mutisitc.resultset;

import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mutisitc.utils.JDBCUtil;
import com.mutisitc.utils.PrintUtil;

/**
 * @program 获取ResultSet的方法
 * @description 
 * @author mutisitic
 * @date 2018年9月28日
 */
public class GetResultSetMain {
	public static void main(String[] args) {
		try {
			getByStatement();
			getByPreparedStatement();
		} catch (SQLException e) {
			PrintUtil.err("获取ResultSet的方法，打印异常堆栈信息：");
			e.printStackTrace();
		}
	}

	/**
	 * @description 通过Statement.executeQuery(String sql)
	 * @author mutisitic
	 * @date 2018年9月28日
	 * @throws SQLException
	 */
	private static void getByStatement() throws SQLException {
		String selectSQL = "SELECT bookId, title, author, remark, createrTime FROM book WHERE title = 'test by result'";
		Statement statement = JDBCUtil.createStatement();
		ResultSet resultSet = statement.executeQuery(selectSQL);;
		PrintUtil.two("3.使用Statement.executeQuery(String sql)获取数据库查询结果集", "ResultSet="+resultSet);
	}
	
	/**
	 * @description 通过PreparedStatement.executeQuery()
	 * @author mutisitic
	 * @date 2018年9月28日
	 * @throws SQLException
	 */
	private static void getByPreparedStatement() throws SQLException {
		String preparedSQL = "SELECT bookId, title, author, remark, createrTime FROM book WHERE title = ?";
		PreparedStatement preparedStatement = JDBCUtil.prepareStatement(preparedSQL);
		preparedStatement.setString(1, "test by result");
		
		ResultSet resultSet = preparedStatement.executeQuery();
		PrintUtil.two("3.使用PreparedStatement.executeQuery()获取数据库查询结果集", "ResultSet="+resultSet);
	}
}
