package com.mutisitc.preparedstatement.operation;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mutisitc.utils.CommonUtil;
import com.mutisitc.utils.JDBCUtil;
import com.mutisitc.utils.PrintUtil;

/**
 * @program 使用PreparedStatement实现数据新增
 * @description
 * @author mutisitic
 * @date 2018年9月28日
 */
public class InsertByMain {
	public static void main(String[] args) {
		PrintUtil.one("使用PreparedStatement实现数据新增：");
		try {
			String preparedSQL = "INSERT INTO book (bookId, title, author, remark, createrTime) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = JDBCUtil.prepareStatement(preparedSQL);
			preparedStatement.setLong(1, System.currentTimeMillis());
			preparedStatement.setString(2, "test by jdbc");
			preparedStatement.setString(3, "test author");
			preparedStatement.setString(4, "使用PreparedStatement.execute接口实现数据新增");
			preparedStatement.setString(5, CommonUtil.getCurrentTime());
			
			boolean executeResult = preparedStatement.execute();
			PrintUtil.two("3.使用PreparedStatement.execute()：执行SQL结果：", "executeResult="+executeResult);
			PrintUtil.three("3.1.使用PreparedStatement.getUpdateCount()：获取执行影响行数：", "updateCount="+preparedStatement.getUpdateCount());
			
			preparedStatement.setLong(1, System.currentTimeMillis());
			long executeLargeUpdateResult = preparedStatement.executeLargeUpdate();
			PrintUtil.two("4.使用PreparedStatement.executeLargeUpdate()：执行SQL结果：", "executeLargeUpdateResult="+executeLargeUpdateResult);
			
			preparedStatement.setLong(1, System.currentTimeMillis());
			int executeUpdateResult = preparedStatement.executeUpdate();
			PrintUtil.two("5.使用PreparedStatement.executeUpdate()：执行SQL结果：", "executeUpdateResult="+executeUpdateResult);
			
			PrintUtil.two("6.PreparedStatement不支持继承至Statement的以下方法", "execute(String sql)等重载方法、executeLargeUpdate(String sql)等重载方法、"
					+ "executeUpdate(String sql)等重载方法、addBatch(String  sql)");
			
			JDBCUtil.close(preparedStatement);
		} catch (SQLException e) {
			PrintUtil.err("使用PreparedStatement实现数据新增，打印异常堆栈信息：");
			e.printStackTrace();
		}
	}
}
