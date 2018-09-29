package com.mutisitc.transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

import com.mutisitc.utils.CommonUtil;
import com.mutisitc.utils.JDBCUtil;
import com.mutisitc.utils.PrintUtil;

/**
 * @program JDBC事务
 * @description 
 * @author mutisitic
 * @date 2018年9月29日
 */
public class TransactionMain {
	public static void main(String[] args) {
		PrintUtil.one("JDBC事务");
		
		Connection connection = null;
		Savepoint savePoint = null;
		try {
			String insertSQL = "INSERT INTO `book` (`bookId`, `title`, `author`, `remark`, `createrTime`) VALUES (" + System.currentTimeMillis()
			+ "," + "'test by jdbc', 'test author', '使用Statement.execute接口实现数据新增', '" + CommonUtil.getCurrentTime() + "')";
			connection = JDBCUtil.getConnection();
			connection.setAutoCommit(false); 
			PrintUtil.two("2.通过Connection。setAutoCommit(false)设置AUTOCOMMIT=0", "取消自动提交处理，开启事务处理");
			
			Statement statement = connection.createStatement();
			statement.execute(insertSQL);
			PrintUtil.two("3.执行第一条insert语句", insertSQL);
			
			savePoint = connection.setSavepoint();
			PrintUtil.two("4.通过Connection.setSavepoint()设置事务回滚点：", "Savepoint="+savePoint);
			
			PrintUtil.two("5.再次insert语句，会报主键重复的异常", insertSQL);
			statement.execute(insertSQL);
			
		} catch (SQLException e) {
			PrintUtil.two("6.测试异常通过Savepoint回滚事务", "开始回滚");
			if(savePoint != null) {
				try {
//					connection.rollback();
					connection.rollback(savePoint);
					PrintUtil.two("7.通过Connection.rollback(Savepoint savepoint)：取消Savepoint设置给定对象后所做的所有更改", "回滚成功");
					PrintUtil.three("7.1也可以通过Connection.rollback()", "撤消当前事务中所做的所有更改，并释放此Connection对象当前持有的所有数据库锁");
				} catch (SQLException e1) {
					PrintUtil.err("通过Savepoint回滚事务出现异常，打印异常堆栈信息：");
					e1.printStackTrace();
				}
			}
			PrintUtil.err("JDBC事务，打印异常堆栈信息：");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection);
		}
	}
}
