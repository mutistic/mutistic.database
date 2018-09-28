package com.mutisitc.preparedstatement;

import java.sql.Date;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.mutisitc.utils.JDBCUtil;
import com.mutisitc.utils.PrintUtil;

/**
 * @program java.sql.PreparedStatement：预编译SQL语句
 * @description
 * @author mutisitic
 * @date 2018年9月21日
 */
public class PreparedStatementMain {
	public static void main(String[] args) {
		PrintUtil.one("java.sql.PreparedStatement：预编译SQL语句：");
		try {
			String preparedSQL = "SELECT bookId, title, author, remark, createrTime FROM book WHERE bookId = ? AND title = ?";
			PreparedStatement preparedStatement = JDBCUtil.prepareStatement(preparedSQL);
			preparedStatement.setLong(1, 1538104150648L);
			preparedStatement.setString(2, "test by jdbc");
			PrintUtil.two("3.PreparedStatement.setXXX(int int parameterIndex, XXX xxx)", "将指定参数设置为给定的值");
			PrintUtil.two("4.PreparedStatement.clearParameters()", "立即清除当前参数值");
			
			ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();
			PrintUtil.two("5.PreparedStatement.getParameterMetaData()：获取此 PreparedStatement 对象的参数的编号、类型和属性", "ParameterMetaData="+parameterMetaData);
			if(null != parameterMetaData) {
				int parameterCount = parameterMetaData.getParameterCount();
				PrintUtil.three("5.1.使用ParameterMetaData.getParameterCount()获取此 PreparedStatement 此ParameterMetaData对象包含信息的对象中的参数数", "parameterCount="+parameterCount);
				PrintUtil.three("5.2.如果要使用ParameterMetaData参数元数据需要在URL开启参数：", "generateSimpleParameterMetadata=true");
				PrintUtil.three("  5.2.1.如果不开启参数generateSimpleParameterMetadata=true在使用ParameterMetaData会抛出异常：",
						"SQL java.sql.SQLException: Parameter metadata not available for the given statement");
				for (int i = 1; i <= parameterCount; i++) {
					PrintUtil.three("5."+(i+2)+".获取参数信息：", "ClassName="+parameterMetaData.getParameterClassName(i)
					+", TypeName="+ parameterMetaData.getParameterTypeName(i) +", Type="+ parameterMetaData.getParameterType(i)
					+", Mode="+ parameterMetaData.getParameterMode(i));
				}
			}
			
			ResultSet resultSet = preparedStatement.executeQuery();
			PrintUtil.two("6.PreparedStatement.executeQuery()：在此PreparedStatement对象中执行SQL查询并返回ResultSet查询生成的对象", "ResultSet="+resultSet);
			int index = 1;
			while(resultSet.next()) {
				Long bookId = resultSet.getLong("bookId");
				String title = resultSet.getString("title");
				String author = resultSet.getString("author");
				String remark = resultSet.getString("remark");
				Date createrTime = resultSet.getDate("createrTime");
				PrintUtil.three("6."+index+".通过ResultSet结果集，获取数据列信息", "bookId="+bookId+", title="+title+
						", author="+author+", remark="+remark+", createrTime="+createrTime);
				index++;
			}
			
			PrintUtil.two("7.PreparedStatement.execute()", "在此PreparedStatement对象中执行SQL语句，该语句可以是任何类型的SQL语句");
			PrintUtil.two("8.PreparedStatement.executeLargeUpdate()", "在此PreparedStatement对象中执行SQL语句，该语句必须是SQL数据操作语言（DML）语句，例如INSERT，UPDATE或 DELETE; 或者不返回任何内容的SQL语句，例如DDL语句");
			PrintUtil.two("9.PreparedStatement.executeUpdate()", "在此PreparedStatement对象中执行SQL语句，该语句必须是SQL数据操作语言（DML）语句，例如INSERT，UPDATE或 DELETE; 或者不返回任何内容的SQL语句，例如DDL语句");
			
			ResultSetMetaData resultSetMetaData = preparedStatement.getMetaData();
			PrintUtil.two("10.PreparedStatement.getMetaData()：获取一个ResultSetMetaData对象，该对象包含有关执行ResultSet此PreparedStatement对象时将返回的对象列的信息", "ResultSetMetaData="+resultSetMetaData.getClass()+"@"+resultSetMetaData.hashCode());
			if(null != resultSetMetaData) {
				int columnCount = resultSetMetaData.getColumnCount();
				PrintUtil.three("10.1.使用ResultSetMetaData.getColumnCount()返回此ResultSet对象中的列数", "columnCount="+columnCount);
				for (int i = 1; i <= columnCount; i++) {
					PrintUtil.three("10."+(i+1)+".使用ResultSetMetaData获取返回列信息：", "CatalogName="+resultSetMetaData.getCatalogName(i)+" ,TableName="+resultSetMetaData.getTableName(i)
						+" ,ColumnLabel="+resultSetMetaData.getColumnLabel(i)+" ,ColumnName="+resultSetMetaData.getColumnName(i)
						+" ,TypeName="+resultSetMetaData.getColumnTypeName(i)+" ,ClassName="+resultSetMetaData.getColumnClassName(i)+" ,Type="+resultSetMetaData.getColumnType(i)
						+" ,SchemaName="+resultSetMetaData.getSchemaName(i)+" ,Scale="+resultSetMetaData.getScale(i)
						+" ,Precision="+resultSetMetaData.getPrecision(i)+" , DisplaySize="+resultSetMetaData.getColumnDisplaySize(i)+"");
				}
			}
			
			JDBCUtil.close(preparedStatement);
		} catch (SQLException e) {
			PrintUtil.err("演示 java.sql.PreparedStatement：预编译SQL语句，打印异常堆栈信息：");
			e.printStackTrace();
		}
	}
}
