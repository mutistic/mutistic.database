package com.mutisitc.connection;

import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.util.Map;
import java.util.Properties;

import com.mutisitc.utils.PrintUtil;

/**
 * @program java.sql.Connection：数据库连接
 * @description
 * @author mutisitic
 * @date 2018年9月19日
 */
public class ConnectionMain {
	public static void main(String[] args) {
		PrintUtil.one("java.sql.Connection：数据库连接：");
		try {
			String jdbcURL = "jdbc:mysql://127.0.0.1:3306/study?useSSL=false&serverTimezone=GMT%2B8";
			String userName = "root";
			String password = "root";
			PrintUtil.two("0.Mysql数据库连接信息：", null);
			PrintUtil.three("0.1.JDBC URL", jdbcURL);
			PrintUtil.three("0.2.userName", userName);
			PrintUtil.three("0.2.password", password);

			Connection connection = DriverManager.getConnection(jdbcURL, userName, password);
			PrintUtil.two("1.通过DriverManager.getConnection()获取数据库连接（会话）", "Connection=" + connection);

			PrintUtil.two("2.Connection.abort(Executor executor)", "终止打开的连接");

			connection.clearWarnings();
			PrintUtil.two("3.Connection.clearWarnings()", "清除为此Connection对象报告的所有警告");

			PrintUtil.two("4.Connection.createArrayOf(String typeName, Object[] elements)：用于创建java.sql.Array对象的工厂方法",
					"Mysql没有具体实现改方法，会抛出java.sql.SQLFeatureNotSupportedException异常");

			Blob blob = connection.createBlob();
			PrintUtil.two("5.Connection.createBlob()：构造一个实现Blob接口的对象", "Blob=" + blob);

			Clob clob = connection.createClob();
			PrintUtil.two("6.Connection.createClob()：构造一个实现Clob接口的对象", "Clob=" + clob);

			NClob nClob = connection.createNClob();
			PrintUtil.two("7.Connection.createNClob()：构造一个实现NClob接口的对象", "NClob=" + nClob);

			SQLXML sqlXML = connection.createSQLXML();
			PrintUtil.two("8.Connection.createSQLXML()：构造一个实现SQLXML接口的对象", "SQLXML=" + sqlXML);

			Statement statement = connection.createStatement();
			PrintUtil.two("9.Connection.createStatement()：创建Statement用于将SQL语句发送到数据库的对象", "Statement=" + statement);

			Statement statement2 = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			PrintUtil.two(
					"10.Connection.createStatement(int resultSetType, int resultSetConcurrency)：创建一个Statement将生成 ResultSet具有给定类型和并发性的对象的对象",
					"resultSetType=java.sql.ResultSet.TYPE_FORWARD_ONLY=" + ResultSet.TYPE_FORWARD_ONLY
							+ ", resultSetConcurrency=java.sql.ResultSet.CONCUR_READ_ONLY=" + ResultSet.CONCUR_READ_ONLY
							+ ", Statement=" + statement2);

			Statement statement3 = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY,
					ResultSet.HOLD_CURSORS_OVER_COMMIT);
			PrintUtil.two(
					"11.Connection.createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)：创建一个Statement对象，该对象将生成ResultSet具有给定类型，并发性和可保持性的对象",
					"resultSetType=java.sql.ResultSet.TYPE_FORWARD_ONLY=" + ResultSet.TYPE_FORWARD_ONLY
							+ ", resultSetConcurrency=java.sql.ResultSet.CONCUR_READ_ONLY=" + ResultSet.CONCUR_READ_ONLY
							+ ", resultSetHoldability=java.sql.ResultSet.HOLD_CURSORS_OVER_COMMIT="
							+ ResultSet.HOLD_CURSORS_OVER_COMMIT + ", Statement=" + statement3);

			PrintUtil.two("12.Connection.createStruct(String typeName, Object[] attributes)：用于创建java.sql.Struct对象的工厂方法",
					"Mysql没有具体实现改方法，会抛出java.sql.SQLFeatureNotSupportedException异常");

			boolean autoCommit = connection.getAutoCommit();
			PrintUtil.two("13.Connection.getAutoCommit()：检索此Connection 对象的当前是否是自动提交模式", "AutoCommit=" + autoCommit);

			autoCommit = false;
			connection.setAutoCommit(autoCommit);
			PrintUtil.two("14.Connection.setAutoCommit(boolean autoCommit)：将此连接的自动提交模式设置为给定状态",
					"autoCommit=" + autoCommit);

			String catalog = connection.getCatalog();
			PrintUtil.two("15.Connection.getCatalog()：检索此Connection对象的当前目录名称", "Catalog=" + catalog);

			connection.setCatalog(catalog);
			PrintUtil.two("16.Connection.setCatalog(String catalog)：设置给定的目录名称，以便选择要Connection在其中工作的此对象的数据库的子空间",
					"Catalog=" + catalog);

			Properties properties = new Properties();
			connection.setClientInfo(properties);
			PrintUtil.two("17.Connection.setClientInfo(Properties properties)：设置连接的客户端信息属性的值",
					"properties=" + properties);

			connection.setClientInfo("test", "test");
			PrintUtil.two("18.Connection.setClientInfo(String name, String value)：将name指定的客户端信息属性的值设置为value指定的值",
					"name=test, value=test");

			properties = connection.getClientInfo();
			PrintUtil.two("19.Connection.getClientInfo()：返回一个列表，其中包含驱动程序支持的每个客户端信息属性的名称和当前值",
					"java.util.Properties=" + properties);

			int holdability = connection.getHoldability();
			PrintUtil.two("20.Connection.getHoldability()：检索ResultSet使用此Connection对象创建的对象的当前可保存性",
					"Holdability=固定值java.sql.ResultSet.CLOSE_CURSORS_AT_COMMIT=" + holdability);

			PrintUtil.two("21.Connection.setHoldability(int holdability)：将ResultSet使用此Connection对象创建的对象的默认可保存性更改为给定的可保持性",
					"com.mysql.cj.jdbc.ConnectionImpl空实现该方法，即该常量指示提交当前事务时，具有此可保存性的打开的 ResultSet 对象将被关闭");

			DatabaseMetaData databaseMetaData = connection.getMetaData();
			PrintUtil.two("22.Connection.getMetaData()：检索DatabaseMetaData包含有关此Connection对象表示连接的数据库的元数据的对象",
					"DatabaseMetaData=" + databaseMetaData);

			int networkTimeout = connection.getNetworkTimeout();
			PrintUtil.two("23.Connection.getNetworkTimeout()：检索驱动程序等待数据库请求完成的毫秒数", "NetworkTimeout=" + networkTimeout);

			PrintUtil.two("24.Connection.setNetworkTimeout(Executor executor, int milliseconds)",
					"设置从连接创建的连接或对象的最长时间，将等待数据库回复任何一个请求");

			String schema = connection.getSchema();
			PrintUtil.two("25.Connection.getSchema()：检索此Connection对象的当前架构名称", "Schema=" + schema);

			int transactionIsolation = connection.getTransactionIsolation();
			PrintUtil.two("26.Connection.getTransactionIsolation()：检索此Connection对象的当前事务隔离级别",
					"transactionIsolation=java.sql.Connection.TRANSACTION_REPEATABLE_READ=" + transactionIsolation);

			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			PrintUtil.two("27.Connection.setTransactionIsolation(int level)：尝试将此Connection对象的事务隔离级别更改为给定的对象",
					"level=java.sql.Connection.TRANSACTION_READ_COMMITTED=" + Connection.TRANSACTION_READ_COMMITTED);

			Map<String, Class<?>> typeMap = connection.getTypeMap();
			PrintUtil.two("28.Connection.getTypeMap()：检索Map与此Connection对象关联的对象",
					"typeMap.keySet()=" + typeMap.keySet());

			connection.setTypeMap(typeMap);
			PrintUtil.two("29.Connection.setTypeMap(Map<String,Class<?>> map)：将给定TypeMap对象安装为此Connection对象的类型映射",
					"typeMap=" + typeMap);

			SQLWarning sqlWarning = connection.getWarnings();
			PrintUtil.two("30.Connection.getWarnings()：检索此Connection对象上的调用报告的第一个警告",
					"java.sql.SQLWarning=" + sqlWarning);

			boolean isClosed = connection.isClosed();
			PrintUtil.two("31.Connection.isClosed()：检索此Connection对象是否已关闭", "isClosed=" + isClosed);

			boolean isReadOnly = connection.isReadOnly();
			PrintUtil.two("32.Connection.isReadOnly()：检索此Connection对象是否处于只读模式", "isReadOnly=" + isReadOnly);

			connection.setReadOnly(true);
			PrintUtil.two("33.Connection.setReadOnly()：将此连接置于只读模式，作为驱动程序的提示以启用数据库优化", "isReadOnly=true");

			String sql = "select * from book";
			String nativeSQL = connection.nativeSQL(sql);
			PrintUtil.two("34.Connection.nativeSQL(String sql)：将给定的SQL语句转换为系统的本机SQL语法",
					"sql=" + sql + ", nativeSQL=" + nativeSQL);

			String procedureSQL = "{CALL pro_testByQuery(?, ?)}";
			CallableStatement callableStatement = connection.prepareCall(procedureSQL);
			PrintUtil.two("35.Connection.prepareCall(String sql)：创建一个CallableStatement用于调用数据库存储过程的对象",
					"sql="+procedureSQL+", CallableStatement=" + callableStatement);
			callableStatement.close();

			CallableStatement callableStatement2 = connection.prepareCall(procedureSQL, ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);
			PrintUtil.two(
					"36.Connection.prepareCall(String sql, int resultSetType, int resultSetConcurrency)：创建一个CallableStatement将生成 ResultSet具有给定类型和并发性的对象的对象",
					"sql="+procedureSQL+", resultSetType=java.sql.ResultSet.TYPE_FORWARD_ONLY=" + ResultSet.TYPE_FORWARD_ONLY
							+ ", resultSetConcurrency=java.sql.ResultSet.CONCUR_READ_ONLY=" + ResultSet.CONCUR_READ_ONLY
							+ ", CallableStatement=" + callableStatement2);
			callableStatement2.close();

			CallableStatement callableStatement3 = connection.prepareCall("test", ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			PrintUtil.two(
					"37.Connection.prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability)：创建一个CallableStatement将生成 ResultSet具有给定类型和并发性的对象的对象",
					"sql=test" + "resultSetType=java.sql.ResultSet.TYPE_FORWARD_ONLY=" + ResultSet.TYPE_FORWARD_ONLY
							+ ", resultSetConcurrency=java.sql.ResultSet.CONCUR_READ_ONLY=" + ResultSet.CONCUR_READ_ONLY
							+ ", resultSetHoldability=java.sql.ResultSet.HOLD_CURSORS_OVER_COMMIT="
							+ ResultSet.HOLD_CURSORS_OVER_COMMIT + ", CallableStatement=" + callableStatement3);
			callableStatement3.close();

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			PrintUtil.two("38.Connection.prepareStatement(String sql)：创建PreparedStatement用于将参数化SQL语句发送到数据库的对象",
					"sql=" + sql + ", PreparedStatement=" + preparedStatement);
			preparedStatement.close();

			PreparedStatement preparedStatement2 = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			PrintUtil.two(
					"39.Connection.prepareStatement(String sql, int autoGeneratedKeys)：创建一个PreparedStatement能够检索自动生成的密钥的默认对象",
					"sql=" + sql + ", autoGeneratedKeys=Statement.RETURN_GENERATED_KEYS="
							+ Statement.RETURN_GENERATED_KEYS + ", PreparedStatement=" + preparedStatement2);
			preparedStatement2.close();

			int[] columnIndexes = new int[] { 1 };
			PreparedStatement preparedStatement3 = connection.prepareStatement(sql, columnIndexes);
			PrintUtil.two(
					"40.Connection.prepareStatement(String sql, int[] columnIndexes)：创建一个PreparedStatement能够返回给定数组指定的自动生成的键的默认对象",
					"sql=" + sql + ", columnIndexes=" + columnIndexes + ", PreparedStatement=" + preparedStatement3);
			preparedStatement3.close();

			String[] columnNames = new String[] { "bookId" };
			PreparedStatement preparedStatement4 = connection.prepareStatement(sql, columnNames);
			PrintUtil.two(
					"41.Connection.prepareStatement(String sql, String[] columnNames)：创建一个PreparedStatement能够返回给定数组指定的自动生成的键的默认对象",
					"sql=" + sql + ", columnNames=" + columnNames + ", PreparedStatement=" + preparedStatement4);
			preparedStatement4.close();

			PreparedStatement preparedStatement5 = connection.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);
			PrintUtil.two(
					"42.Connection.prepareStatement(String sql, int resultSetType, int resultSetConcurrency)：创建一个PreparedStatement将生成 ResultSet具有给定类型和并发性的对象的对象",
					"sql=" + sql + ", resultSetType=java.sql.ResultSet.TYPE_FORWARD_ONLY=" + ResultSet.TYPE_FORWARD_ONLY
							+ ", resultSetConcurrency=java.sql.ResultSet.CONCUR_READ_ONLY=" + ResultSet.CONCUR_READ_ONLY
							+ ", PreparedStatement=" + preparedStatement5);
			preparedStatement5.close();

			PreparedStatement preparedStatement6 = connection.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			PrintUtil.two(
					"43.Connection.prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability)：创建一个PreparedStatement对象，该对象将生成ResultSet具有给定类型，并发性和可保持性的对象",
					"sql=" + sql + "resultSetType=java.sql.ResultSet.TYPE_FORWARD_ONLY=" + ResultSet.TYPE_FORWARD_ONLY
							+ ", resultSetConcurrency=java.sql.ResultSet.CONCUR_READ_ONLY=" + ResultSet.CONCUR_READ_ONLY
							+ ", resultSetHoldability=java.sql.ResultSet.HOLD_CURSORS_OVER_COMMIT="
							+ ResultSet.HOLD_CURSORS_OVER_COMMIT + ", PreparedStatement=" + preparedStatement6);
			preparedStatement6.close();
			
			PrintUtil.two("44.Connection.releaseSavepoint(Savepoint savepoint)", "从当前事务中删除指定的java.sql.Savepoint和后续的Savepoint对象");

			PrintUtil.two("45.Connection.rollback()", "撤消当前事务中所做的所有更改，并释放此Connection对象当前持有的所有数据库锁");
			
			PrintUtil.two("46.Connection.rollback(Savepoint savepoint)", "取消java.sql.Savepoint设置给定对象后所做的所有更改");
			
			PrintUtil.two("47.Connection.commit()", "使自上次提交/回滚以来所做的所有更改成为永久更改，并释放此Connection对象当前持有的所有数据库锁");

			connection.close();
			PrintUtil.two("48.Connection.close()", "立即释放此Connection对象的数据库和JDBC资源，而不是等待它们自动释放");
			
		} catch (SQLException e) {
			PrintUtil.err("演示java.sql.Connection：数据库连接出现异常，打印异常堆栈信息：");
			e.printStackTrace();
		}
	}
}
