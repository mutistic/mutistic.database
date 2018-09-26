package com.mutisitc.statement.executeupdate;

import java.sql.SQLException;
import java.sql.Statement;

import com.mutisitc.utils.JDBCUtil;
import com.mutisitc.utils.PrintUtil;

/**
 * @program 使用Statement.executeUpdate接口实现数据操作
 * @description 
 * @author mutisitic
 * @date 2018年9月26日
 */
public class ExecuteUpdateMain {
	public static void main(String[] args) {
		PrintUtil.one("使用Statement.executeUpdate接口实现数据操作：");
		try {
			Statement statement = JDBCUtil.createStatement();
			
			// 使用Statement.executeUpdate接口实现数据新增
			ExecuteUpdateByInsert.mainByInsert(statement);
			// 使用Statement.executeUpdate接口实现数据删除
			ExecuteUpdateByDelete.mainByDelete(statement);
			// 使用Statement.executeUpdate接口实现数据更新
			ExecuteUpdateByUpdate.mainByUpdate(statement);
			
			JDBCUtil.close(statement);
		} catch (SQLException e) {
			PrintUtil.err("使用Statement.execute接口实现，打印异常堆栈信息：");
			e.printStackTrace();
		}
	}
}
