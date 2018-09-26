package com.mutisitc.statement.executelargeupdate;

import java.sql.SQLException;
import java.sql.Statement;

import com.mutisitc.utils.JDBCUtil;
import com.mutisitc.utils.PrintUtil;

/**
 * @program 使用Statement.executeLargeUpdate接口实现数据操作
 * @description 
 * @author mutisitic
 * @date 2018年9月26日
 */
public class ExecuteLargeUpdateMain {
	public static void main(String[] args) {
		PrintUtil.one("使用Statement.executeLargeUpdate接口实现数据操作：");
		try {
			Statement statement = JDBCUtil.createStatement();
			
			// 使用Statement.executeLargeUpdate接口实现数据新增
			ExecuteLargeUpdateByInsert.mainByInsert(statement);
			// 使用Statement.executeLargeUpdate接口实现数据删除
			ExecuteLargeUpdateByDelete.mainByDelete(statement);
			// 使用Statement.executeLargeUpdate接口实现数据更新
			ExecuteLargeUpdateByUpdate.mainByUpdate(statement);
			
			PrintUtil.two("\nexecuteLargeUpdate()方法返回的实际上是this.updateCount", "也就是Statement.getUpdateCount()");
			
			JDBCUtil.close(statement);
		} catch (SQLException e) {
			PrintUtil.err("使用Statement.execute接口实现，打印异常堆栈信息：");
			e.printStackTrace();
		}
	}
}
