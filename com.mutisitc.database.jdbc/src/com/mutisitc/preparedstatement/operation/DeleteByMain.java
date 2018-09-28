package com.mutisitc.preparedstatement.operation;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mutisitc.utils.JDBCUtil;
import com.mutisitc.utils.PrintUtil;

/**
 * @program 使用PreparedStatement实现数据删除
 * @description 
 * @author mutisitic
 * @date 2018年9月28日
 */
public class DeleteByMain {
	public static void main(String[] args) {
		PrintUtil.one("使用PreparedStatement实现数据删除：");
		try {
			String preparedSQL = "DELETE FROM book WHERE bookId=?";
			PreparedStatement preparedStatement = JDBCUtil.prepareStatement(preparedSQL);
			
			preparedStatement.setLong(1, 1538104150648L);
			boolean executeResult = preparedStatement.execute();
			PrintUtil.two("3.使用PreparedStatement.execute()：执行SQL结果：", "executeResult="+executeResult);
			PrintUtil.three("3.1.使用PreparedStatement.getUpdateCount()：获取执行影响行数：", "updateCount="+preparedStatement.getUpdateCount());
			
			preparedStatement.setLong(1, 1538104210204L);
			long executeLargeUpdateResult = preparedStatement.executeLargeUpdate();
			PrintUtil.two("4.使用PreparedStatement.executeLargeUpdate()：执行SQL结果：", "executeLargeUpdateResult="+executeLargeUpdateResult);
			
			preparedStatement.setLong(1, 1538104307831L);
			int executeUpdateResult = preparedStatement.executeUpdate();
			PrintUtil.two("5.使用PreparedStatement.executeUpdate()：执行SQL结果：", "executeUpdateResult="+executeUpdateResult);
			
			JDBCUtil.close(preparedStatement);
		} catch (SQLException e) {
			PrintUtil.err("使用PreparedStatement实现数据删除，打印异常堆栈信息：");
			e.printStackTrace();
		}
	}
}
