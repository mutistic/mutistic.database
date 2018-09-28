package com.mutisitc.resultset;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.mutisitc.utils.JDBCUtil;
import com.mutisitc.utils.PrintUtil;

/**
 * @program java.sql.ResultSet：数据库结果集
 * @description 
 * @author mutisitic
 * @date 2018年9月28日
 */
public class ResultSetMain {
	public static void main(String[] args) {
		PrintUtil.one("java.sql.PreparedStatement：预编译SQL语句：");
		try {
			String preparedSQL = "SELECT bookId, title, author, remark, createrTime FROM book WHERE title = ?";
			PreparedStatement preparedStatement = JDBCUtil.prepareStatement(preparedSQL);
			preparedStatement.setString(1, "test by result");
			
			ResultSet resultSet = preparedStatement.executeQuery();
			PrintUtil.two("3.使用PreparedStatement.executeQuery()获取数据库查询结果集", "ResultSet="+resultSet);
			
			int fetchDirection = resultSet.getFetchDirection();
			PrintUtil.two("4.ResultSet.getFetchDirection()获取此ResultSet对象的获取方向", "fetchDirection=ResultSet.FETCH_FORWARD="+fetchDirection);

			fetchDirection = ResultSet.FETCH_FORWARD;
			resultSet.setFetchDirection(fetchDirection);
			PrintUtil.two("5.ResultSet.setFetchDirection(int direction)提供有关此ResultSet对象中的行的处理方向的提示", "fetchDirection=ResultSet.FETCH_FORWARD="+fetchDirection);
			
			int fetchSize = resultSet.getFetchSize();
			PrintUtil.two("6.ResultSet.getFetchSize()获取此ResultSet对象的提取大小", "fetchSize="+fetchSize);
			
			fetchSize = 0;
			resultSet.setFetchSize(fetchSize);
			PrintUtil.two("7.ResultSet.setFetchSize(int rows)为JDBC驱动程序提供有关此ResultSet对象需要更多行时应从数据库中提取的行数的提示", "fetchSize="+fetchSize);
			
			PrintUtil.two("8.ResultSet.getHoldability()", "通过Connection中设置holdability属性，com.mysql.cj.jdbc.result.ResultSetImpl不支持获取通过getHoldability()获取");
			
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			PrintUtil.two("9.ResultSet.getMetaData()获取此ResultSet对象列的数量，类型和属性对象", "ResultSetMetaData="+resultSetMetaData);

			Statement statement = resultSet.getStatement();
			PrintUtil.two("10.ResultSet.getStatement()获取Statement生成此 ResultSet对象的对象", "Statement="+statement.getClass()+"@"+statement.hashCode());

			int type = resultSet.getType();
			PrintUtil.two("11.ResultSet.getType()获取此ResultSet对象的类型", "type="+type);

			int concurrency = resultSet.getConcurrency();
			PrintUtil.two("12.ResultSet.getConcurrency()获取此ResultSet对象的并发模式", "concurrency=ResultSet.CONCUR_READ_ONLY="+concurrency);
			
			PrintUtil.two("13.ResultSet.getCursorName()", "com.mysql.cj.jdbc.result.ResultSetImpl不支持此方法");

			PrintUtil.two("14.ResultSet.next()", "将光标从当前位置向前移动一行，用于获取数据");
			int index = 1;
			while(resultSet.next()) {
				int row = resultSet.getRow();
				Long bookId = resultSet.getLong(1);
				String title = resultSet.getString("title");
				String author = resultSet.getString("author");
				String remark = resultSet.getString("remark");
				Date createrTime = resultSet.getDate("createrTime");
				PrintUtil.three("14."+index+".通过getXXX(int columnIndex)或getXXX(String columnLabel)获取数据列信息", 
						"row="+row+" ,bookId="+bookId+", title="+title+
						", author="+author+", remark="+remark+", createrTime="+createrTime);
				index++;
			}
			PrintUtil.two("15.com.mysql.cj.jdbc.result.ResultSetImpl", "不支持根据列索引更新数据updateXXX(int columnIndex, XXX xxx)、"
					+ "insertRow()、updateRowId()、updateRow()等方法");
			
			PrintUtil.two("16.由于Mysql的holdability属性值固定为：ResultSet.CLOSE_CURSORS_AT_COMMIT=2", "所以不支持通过ResultSet对象的updateXXX方法直接更新数据");
			
			JDBCUtil.close(preparedStatement);
		} catch (SQLException e) {
			PrintUtil.err("java.sql.ResultSet：数据库结果集，打印异常堆栈信息：");
			e.printStackTrace();
		}
	}
}
