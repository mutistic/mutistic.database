package com.mutisitc.statement.executeupdate;

import java.sql.SQLException;
import java.sql.Statement;

import com.mutisitc.utils.PrintUtil;

/**
 * @program 使用Statement.executeUpdate接口实现数据删除
 * @description
 * @author mutisitic
 * @date 2018年9月26日
 */
public class ExecuteUpdateByDelete {
	/**
	 * @description 使用Statement.executeUpdate接口实现数据删除
	 * @author mutisitic
	 * @date 2018年9月26日
	 * @param statement
	 * @throws SQLException
	 */
	public static void mainByDelete(Statement statement) throws SQLException {
		PrintUtil.one("一、使用 Statement.executeUpdate(String sql)：数据删除：");
		
		String deleteSQL = "DELETE FROM book WHERE bookId = "+ 1537931746405L;
		PrintUtil.two("3.数据删除SQL语句：", deleteSQL);

		int executeUpdateResult = statement.executeUpdate(deleteSQL);
		PrintUtil.two("4.Statement.executeUpdate(String sql)：数据删除执行结果", "executeUpdateResult=" + executeUpdateResult);
		PrintUtil.three("4.1：具体描述:", "执行给定的SQL语句，它可以是一个INSERT， UPDATE或者DELETE语句，或者不返回任何内容的SQL语句(如SQL DDL语句)");
		PrintUtil.three("4.2：方法返回结果：", "SQL数据操作语言（DML）语句的行数 或 0表示不返回任何内容的SQL语句");
	}
}
