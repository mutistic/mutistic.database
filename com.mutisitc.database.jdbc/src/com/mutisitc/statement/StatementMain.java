package com.mutisitc.statement;

import java.sql.SQLException;
import java.sql.Statement;

import com.mutisitc.utils.JDBCUtil;
import com.mutisitc.utils.PrintUtil;

/**
 * @program java.sql.Statement：基本语句
 * @description 
 * @author mutisitic
 * @date 2018年9月21日
 */
public class StatementMain {
	public static void main(String[] args) {
		PrintUtil.one("java.sql.Statement：基本语句：");
		try {
			Statement statement = JDBCUtil.createStatement();
			statement.addBatch("");
			
			
		} catch (SQLException e) {
			PrintUtil.err("演示 java.sql.Statement：基本语句，打印异常堆栈信息：");
			e.printStackTrace();
		}
	}
}
