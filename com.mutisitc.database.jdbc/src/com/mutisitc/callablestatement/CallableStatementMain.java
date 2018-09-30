package com.mutisitc.callablestatement;

import java.sql.CallableStatement;
import java.sql.ParameterMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

import com.mutisitc.utils.JDBCUtil;
import com.mutisitc.utils.PrintUtil;

/**
 * @program java.sql.CallableStatement：执行SQL存储过程
 * @description 
 * @author mutisitic
 * @date 2018年9月29日
 */
public class CallableStatementMain {
	public static void main(String[] args) {
		PrintUtil.one("java.sql.CallableStatement：执行SQL存储过程：");
		try {
			String callSQL = "{CALL pro_testByQuery(?, ?)}";
			CallableStatement callableStatement = JDBCUtil.prepareCall(callSQL);

			Long p_bookId = 1L;
			callableStatement.setLong(1, p_bookId);
			PrintUtil.two("3.CallableStatement.setXXX(int parameterIndex, XXX xxx)：将指定IN参数设置为给定的值", "parameterIndex=1, p_bookId="+p_bookId);
			PrintUtil.three("3.1.也可以通过CallableStatement.setXXX(String parameterName, XXX xxx)", "将指定IN参数设置为给定的值");
			
			callableStatement.registerOutParameter("p_title", Types.VARCHAR);
			PrintUtil.two("4.CallableStatement.registerOutParameter(String parameterName, int sqlType)：注册名为parameterName JDBC类型 的OUT参数 sqlType", "p_titleOUT参数类型=java.sql.Types.VARCHAR="+Types.VARCHAR);
			PrintUtil.three("4.1.CallableStatement.registerOutParameter(String parameterName, int sqlType, int scale)", "注册名为parameterName JDBC类型 的参数 sqlTyp");
			PrintUtil.three("4.2.CallableStatement.registerOutParameter(String parameterName, SqlType sqlType, String typeName)", "注册指定的输出参数，SqlType=com.mysql.cj.MysqlType.class");

			callableStatement.execute();
			PrintUtil.two("5.CallableStatement.execute()", "执行调用存储过程的SQL语句");
			
			String p_title = callableStatement.getString(2);
			PrintUtil.two("6.CallableStatement.getXXX(int parameterIndex)：获取JDBC参数的值", "parameterIndex=2，p_title="+p_title);

			PrintUtil.two("7.CallableStatement调用存储过程时", "如果存在IN参数(入参)着必须指定具体的值(可以为null)、如果存在OUT参数(返参)则必须注册JDBC类型");
			
			ParameterMetaData parameterMetaData = callableStatement.getParameterMetaData();
			PrintUtil.two("8.PreparedStatement.getParameterMetaData()：获取此 PreparedStatement 对象的参数的编号、类型和属性", "ParameterMetaData="+parameterMetaData);
			if(null != parameterMetaData) {
				int parameterCount = parameterMetaData.getParameterCount();
				PrintUtil.three("8.1.使用ParameterMetaData.getParameterCount()获取此 PreparedStatement 此ParameterMetaData对象包含信息的对象中的参数数", "parameterCount="+parameterCount);
				PrintUtil.three("8.2.如果要使用ParameterMetaData参数元数据需要在URL开启参数：", "generateSimpleParameterMetadata=true");
				PrintUtil.three("  8.2.1.如果不开启参数generateSimpleParameterMetadata=true在使用ParameterMetaData会抛出异常：",
						"SQL java.sql.SQLException: Parameter metadata not available for the given statement");
				for (int i = 1; i <= parameterCount; i++) {
					PrintUtil.three("8."+(i+2)+".获取参数信息：", "ClassName="+parameterMetaData.getParameterClassName(i)
					+", TypeName="+ parameterMetaData.getParameterTypeName(i) +", Type="+ parameterMetaData.getParameterType(i)
					+", Mode="+ parameterMetaData.getParameterMode(i));
				}
			}
			
			ResultSet resultSet = callableStatement.getResultSet();
			PrintUtil.two("9.CallableStatement.getResultSet()：将当前结果获取为ResultSet对象", "ResultSet="+resultSet);
			if(null !=  resultSet) {
				while(resultSet.next()) {
					String p_titles = callableStatement.getString("p_title");
					PrintUtil.three("9.1.也可以通过CallableStatement.getXXX(String parameterName)：获取JDBC参数的值", "parameterIndex=p_title，p_title="+p_titles);
				}
			}
			
			ResultSetMetaData resultSetMetaData = callableStatement.getMetaData();
			PrintUtil.two("10.PreparedStatement.getMetaData()：获取一个ResultSetMetaData对象，该对象包含有关执行ResultSet此PreparedStatement对象时将返回的对象列的信息", "ResultSetMetaData="+resultSetMetaData);
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
			
			
			JDBCUtil.close(callableStatement);
		} catch (SQLException e) {
			PrintUtil.err("演示 java.sql.CallableStatement：执行SQL存储过程，打印异常堆栈信息：");
			e.printStackTrace();
		}
	}
}
