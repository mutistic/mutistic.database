package com.mutisitc.callablestatement;

import java.sql.CallableStatement;
import java.sql.ResultSet;
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
			
			ResultSet resultSet = callableStatement.getResultSet();
			PrintUtil.two("8.CallableStatement.getResultSet()：将当前结果获取为ResultSet对象", "ResultSet="+resultSet);
			if(null !=  resultSet) {
				while(resultSet.next()) {
					String p_titles = callableStatement.getString("p_title");
					PrintUtil.three("8.1.也可以通过CallableStatement.getXXX(String parameterName)：获取JDBC参数的值", "parameterIndex=p_title，p_title="+p_titles);
				}
			}
			
			JDBCUtil.close(callableStatement);
		} catch (SQLException e) {
			PrintUtil.err("演示 java.sql.CallableStatement：执行SQL存储过程，打印异常堆栈信息：");
			e.printStackTrace();
		}
	}
}
