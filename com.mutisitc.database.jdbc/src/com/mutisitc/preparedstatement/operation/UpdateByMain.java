package com.mutisitc.preparedstatement.operation;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mutisitc.utils.CommonUtil;
import com.mutisitc.utils.JDBCUtil;
import com.mutisitc.utils.PrintUtil;

/**
 * @program 使用PreparedStatement实现数据更新
 * @description 
 * @author mutisitic
 * @date 2018年9月28日
 */
public class UpdateByMain {
	public static void main(String[] args) {
		PrintUtil.one("使用PreparedStatement实现数据更新：");
		try {
			String preparedSQL = "UPDATE book SET title=?, author=?, remark=?, createrTime=? WHERE bookId=?";
			PreparedStatement preparedStatement = JDBCUtil.prepareStatement(preparedSQL);
			preparedStatement.setString(1, "test by jdbc");
			preparedStatement.setString(2, "test author");
			preparedStatement.setString(3, "使用PreparedStatement.execute接口实现数据更新");
			preparedStatement.setString(4, CommonUtil.getCurrentTime());
			preparedStatement.setLong(5, 1538120200851l);
			
			boolean executeResult = preparedStatement.execute();
			PrintUtil.two("3.使用PreparedStatement.execute()：执行SQL结果：", "executeResult="+executeResult);
			PrintUtil.three("3.1.使用PreparedStatement.getUpdateCount()：获取执行影响行数：", "updateCount="+preparedStatement.getUpdateCount());
			
			preparedStatement.setString(1, "PreparedStatement executeLargeUpdate");
			long executeLargeUpdateResult = preparedStatement.executeLargeUpdate();
			PrintUtil.two("4.使用PreparedStatement.executeLargeUpdate()：执行SQL结果：", "executeLargeUpdateResult="+executeLargeUpdateResult);
			
			preparedStatement.setString(4, CommonUtil.getCurrentTime());
			int executeUpdateResult = preparedStatement.executeUpdate();
			PrintUtil.two("5.使用PreparedStatement.executeUpdate()：执行SQL结果：", "executeUpdateResult="+executeUpdateResult);
			
			JDBCUtil.close(preparedStatement);
		} catch (SQLException e) {
			PrintUtil.err("使用PreparedStatement实现数据更新，打印异常堆栈信息：");
			e.printStackTrace();
		}
	}
}
