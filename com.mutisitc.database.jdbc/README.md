# <a id="a_top">JDBC：Java数据库连接</a>
[Java 8-API](https://docs.oracle.com/javase/8/docs/api/overview-summary.html)  
[Java 10-API](https://docs.oracle.com/javase/10/docs/api/overview-summary.html)  
[JDBC](https://baike.baidu.com/item/jdbc)  
[Java数据库连接](https://baike.baidu.com/item/Java%E6%95%B0%E6%8D%AE%E5%BA%93%E8%BF%9E%E6%8E%A5)  
[JDBC-API](https://docs.oracle.com/javase/10/docs/api/java.sql-summary.html)  
[Search Maven](https://search.maven.org/search?q=search)  
[mysql:mysql-connector-java:8.0.12](https://search.maven.org/artifact/mysql/mysql-connector-java/8.0.12/jar)  

|作者|Mutistic|
|---|---|
|邮箱|mutistic@qq.com|

---
### <a id="a_catalogue">目录</a>：
1. <a href="#a_jdbc">JDBC：Java数据库连接</a>
2. <a href="#a_step">JDBC连接数据库步骤</a>
3. <a href="#a_driver">java.sql.Driver：数据库驱动</a>
4. <a href="#a_manager">java.sql.DriverManager：驱动管理</a>
5. <a href="#a_connection">java.sql.Connection：数据库连接</a>
6. <a href="#a_statement">java.sql.Statement：执行静态SQL</a>
7. <a href="#a_execute">使用Statement.execute方法实现数据操作</a>
8. <a href="#a_executeLargeUpdate">使用Statement.executeLargeUpdate方法实现数据操作</a>
9. <a href="#a_executeUpdate">使用Statement.executeUpdae方法实现数据操作</a>
10. <a href="#a_prepared">java.sql.PreparedStatement：预编译SQL语句</a>
11. <a href="#a_preparedOperation">使用PreparedStatement方法实现数据操作</a>
12. <a href="#a_resultSet">java.sql.ResultSet：数据库结果集</a>
13. <a href="#a_callable">java.sql.CallableStatement：执行SQL存储过程</a>
14. <a href="#a_types">java.sql.SQLType：JDBC类型</a>
15. <a href="#a_transaction">JDBC事务</a>

99. <a href="#a_down">down</a>

---
### <a id="a_jdbc">一、JDBC：Java数据库连接：</a> <a href="#a_catalogue">last</a> <a href="#a_step">next</a>
[JDBC数据库驱动模型](https://github.com/mutistic/mutistic.database/blob/master/com.mutisitc.database.jdbc/notes/01_JDBCPModel.png)  
一、定义：
```
JDBC：
  全称Java DataBase Connectivity，Java 数据库连接，是一种用于执行SQL的Java API，可以为多种关系型数据库提供统一访问，
它由一组用Java语言编写的类和接口组成。JDBC提供了一种基准：是Java语言中用来规范客户端程序如何来访问数据库的应用程序接口，
据此可以构建更高级的工具和接口，使数据库开发人员能够编写数据库应用程序

  JDBC也是Sun Microsystems的商标。通常说的JDBC是面向关系型数据库的
```

二、了解JDBC：  
[DBMS：Database Management System，数据库管理系统](https://baike.baidu.com/item/%E6%95%B0%E6%8D%AE%E5%BA%93%E7%AE%A1%E7%90%86%E7%B3%BB%E7%BB%9F)  
[RDBMS：Relational Database Management System，关系数据库管理系统](https://baike.baidu.com/item/RDBMS)
```
  有了JDBC，向各种关系数据发送SQL语句就是一件很容易的事。换言之，有了JDBC API，就不必为访问
Sybase数据库[一种典型的UNIX或WindowsNT平台上客户机/服务器环境下的大型数据库系统]专门写一个程序，
为访问Oracle数据库又专门写一个程序，或为访问Informix数据库[IBM公司出品的关系数据库管理系统（RDBMS）家族]又编写另一个程序等等，
程序员只需用JDBC API写一个程序就够了，它可向相应数据库发送SQL调用。同时，将Java语言和JDBC结合起来使程序员不必为不同的平台编写不同的应用程序，
只须写一遍程序就可以让它在任何平台上运行，这也是Java语言“编写一次，处处运行”的优势。

  Java数据库连接体系结构是用于Java应用程序连接数据库的标准方法。JDBC对Java程序员而言是API，对实现与数据库连接的服务提供商而言是接口模型。
作为API，JDBC为程序开发提供标准的接口，并为数据库厂商及第三方中间件厂商实现与数据库的连接提供了标准方法。
JDBC使用已有的SQL标准并支持与其它数据库连接标准，如ODBC之间的桥接。JDBC实现了所有这些面向标准的目标并且具有简单、严格类型定义且高性能实现的接口。

  Java具有坚固、安全、易于使用、易于理解和可从网络上自动下载等特性，是编写数据库应用程序的杰出语言。
所需要的只是Java应用程序与各种不同数据库之间进行对话的方法。而JDBC正是作为此种用途的机制。

  JDBC扩展了Java的功能。例如，用Java和JDBC API可以发布含有applet的网页，而该applet使用的信息可能来自远程数据库。
企业也可以用JDBC通过Intranet[企业内部网]将所有职员连到一个或多个内部数据库中（即使这些职员所用的计算机有 Windows、 Macintosh[Mac] 
和UNIX 等各种不同的操作系统）。随着越来越多的程序员开始使用Java 编程语言，对从Java中便捷地访问数据库的要求也在日益增加。

  MIS[管理信息系统，Management Information System]管理员们都喜欢Java和JDBC的结合，因为它使信息传播变得容易和经济。
企业可继续使用它们安装好的数据库，并能便捷地存取信息，即使这些信息是储存在不同数据库管理系统上。新程序的开发期很短。
安装和版本控制将大为简化。程序员可只编写一遍应用程序或只更新一次，然后将它放到服务器上，随后任何人就都可得到最新版本的应用程序。
对于商务上的销售信息服务，Java和JDBC可为外部客户提供获取信息更新的更好方法。
```

三、用途
```
JDBC 可做三件事：与数据库建立连接、发送 操作数据库的语句并处理结果：
代码示例：
    Class.forName("xxx.xxx.xxx.Driver");
    Connection con = DriverManager.getConnection("jdbcUrl", "username", "password");
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT colunm1,colunm2 FROM table");
    while (rs.next()) {
        int colunm1 = rs.getInt("colunm1");
        String colunm2 = rs.getString("colunm2");
    }
```

四、API概述：  
[JDBC-API](https://docs.oracle.com/javase/8/docs/api/java.sql-summary.html)  
[javax.sql包](https://docs.oracle.com/javase/8/docs/api/java/sql/package-summary.html)  
[javax.sql包](https://docs.oracle.com/javase/8/docs/api/javax/sql/package-summary.html)
```
4.1、JDBC API主要位于JDK中的java.sql包中（之后扩展的内容位于javax.sql包中），主要包括：
  DriverManager：负责加载各种不同驱动程序（Driver），并根据不同的请求，向调用者返回相应的数据库连接（Connection）。
  Driver：驱动程序，会将自身加载到DriverManager中去，并处理相应的请求并返回相应的数据库连接（Connection）。
  Connection：数据库连接，负责与进行数据库间通讯，SQL执行以及事务处理都是在某个特定Connection环境中进行的。可以产生用以执行SQL的Statement。
  Statement：用以执行SQL查询和更新（针对静态SQL语句和单次执行）。
  PreparedStatement：用以执行包含动态参数的SQL查询和更新（在服务器端编译，允许重复执行以提高效率）。
  CallableStatement：用以调用数据库中的存储过程。
  SQLException：代表在数据库连接的建立和关闭和SQL语句的执行过程中发生了例外情况（即错误）

4.2、JDBC是个"低级"接口，也就是说，它用于直接调用SQL命令。在这方面它的功能极佳，并比其它的数据库连接API易于使用，
但它同时也被设计为一种基础接口，在它之上可以建立高级接口和工具。高级接口是"对用户友好的"接口，
它使用的是一种更易理解和更为方便的 API，这种API在幕后被转换为诸如JDBC这样的低级接口。

  在关系数据库的"对象/关系"映射中，表中的每行对应于类的一个实例，而每列的值对应于该实例的一个属性。
于是，程序员可直接对 Java 对象进行操作；存取数据所需的SQL调用将在"掩盖下"自动生成。此外还可提供更复杂的映射，
例如将多个表中的行结合进一个 Java 类中。

  随着人们对JDBC的兴趣日益增涨，越来越多的开发人员一直在使用基于JDBC的工具，以使程序的编写更加容易。
程序员也一直在编写力图使最终用户对数据库的访问变得更为简单的应用程序。例如应用程序可提供一个选择数据库任务的菜单。
任务被选定后，应用程序将给出提示及空白供填写执行选定任务所需的信息。所需信息输入应用程序将自动调用所需的SQL命令。
在这样一种程序的协助下，即使用户根本不懂SQL的语法，也可以执行数据库任务
```
4.3、从SQL到Java数据类型映射的JDBC规范：

|SQL类型|Java类型|
|---|---|
|CHAR|java.lang.String|
|VARCHAR|java.lang.String|
|LONGVARCHAR|java.lang.String|
|NUMERIC|java.math.BigDecimal|
|DECIMAL|java.math.BigDecimal|
|BIT|boolean|
|TINYINT|byte|
|SMALLINT|short|
|INTEGER|int|
|BIGINT|long|
|REAL|float|
|FLOAT|double|
|DOUBLE|double|
|BINARY|byte[]|
|VARBINARY|byte[]|
|LONGVARBINARY|byte[]|
|DATE|java.sql.Date|
|TIME|java.sql.Time|
|TIMESTAMP|java.sql.Timestamp|
|BLOB|java.sql.Blob|
|CLOB|java.sql.Clob|
|Array|java.sql.Array|
|REF|java.sql.Ref|
|Struct|java.sql.Struct|
|Struct|java.sql.Struct|

```
注意：这种类型匹配不是强制性标准，特定的JDBC厂商可能会改变这种类型匹配。
例如Oracle中的DATE类型是包含时分秒，而java.sql.Date仅仅支持年月日
```

五、程序类型：  
[ODBC：开放数据库互连](https://baike.baidu.com/item/%E5%BC%80%E6%94%BE%E6%95%B0%E6%8D%AE%E5%BA%93%E4%BA%92%E8%BF%9E/10418782)  
```
JDBC驱动程序共分四种类型：
5.1、JDBC-ODBC桥加ODBC驱动程序：
  JavaSoft桥产品利用ODBC驱动程序提供JDBC访问。注意，必须将ODBC二进制代码（许多情况下还包括数据库客户机代码）加载到使用该驱动程序的每个客户机上。
因此，这种类型的驱动程序最适合于企业网（这种网络上客户机的安装不是主要问题），或者是用Java编写的三层结构的应用程序服务器代码。

5.2、本地API：
  这种类型的驱动程序把客户机API上的JDBC调用转换为Oracle、Sybase、Informix、DB2或其它DBMS的调用。注意，象桥驱动程序一样，
这种类型的驱动程序要求将某些二进制代码加载到每台客户机上。

5.3、JDBC网络纯Java驱动程序：
  这种驱动程序将JDBC转换为与DBMS无关的网络协议，之后这种协议又被某个服务器转换为一种DBMS协议。这种网络服务器中间件能够将它的
纯Java客户机连接到多种不同的数据库上。所用的具体协议取决于提供者。通常，这是最为灵活的JDBC驱动程序。
有可能所有这种解决方案的提供者都提供适合于Intranet用的产品。为了使这些产品也支持Internet访问，
它们必须处理Web所提出的安全性、通过防火墙的访问等方面的额外要求。几家提供者正将JDBC驱动程序加到他们现有的数据库中间件产品中。

5.4、本地协议纯Java驱动程序：
  这种类型的驱动程序将JDBC调用直接转换为DBMS所使用的网络协议。这将允许从客户机机器上直接调用DBMS服务器，
是Intranet访问的一个很实用的解决方法。由于许多这样的协议都是专用的，因此数据库提供者自己将是主要来源，有几家提供者已在着手做这件事了。
获取驱动：目前已有几十个（1）类的驱动程序，即可与Javasoft桥联合使用的ODBC驱动程序的驱动程序。
有大约十多个属于种类（2）的驱动程序是以DBMS的本地API为基础编写的。只有几个属于种类（3）的驱动程序，
其首批提供者是SCO、OpenHorizon、Visigenic和WebLogic。
此外，JavaSoft和数据库连接的领先提供者Intersolv还合作研制了JDBC-ODBC桥和JDBC驱动程序测试工具包。
```

六、SQL一致性：
```
  结构化查询语言（SQL） 是访问关系数据库的标准语言。困难之处在于：虽然大多数的 DBMS（数据库管理系统对其基本功能都使用了标准形式的SQL，
但它们却不符合最近为更高级的功能定义的标准SQL语法或语义。例如，并非所有的数据库都支持储存程序或外部连接，那些支持这一功能的数据库又相互不一致。
人们希望SQL中真正标准的那部份能够进行扩展以包括越来越多的功能。但同时JDBC API又必须支持现有的SQL。

  JDBC API解决这个问题的一种方法是允许将任何查询字符串一直传到所涉及的DBMS驱动程序上。这意味着应用程序可以使用任意多的SQL功能，
但它必须冒这样的风险：有可能在某些DBMS上出错。事实上，应用程序查询甚至不一定要是SQL，
或者说它可以是个为特定的DBMS设计的SQL的专用派生物（例如，文档或图象查询）。
  
  JDBC处理SQL一致性问题的第二种方法是提供ODBC风格的转义子句，转义语法为几个常见的SQL分歧提供了一种标准的JDBC语法。
例如，对日期文字和已储存过程的调用都有转义语法。

  对于复杂的应用程序，JDBC用第三种方法来处理SQL的一致性问题它利用DatabaseMetaData接口来提供关于DBMS的描述性信息，
从而使应用程序能适应每个DBMS的要求和功能。

  由于JDBC API将用作开发高级数据库访问工具和API的基础API，因此它还必须注意其所有上层建筑的一致性。"符合JDBC标准TM"
代表用户可依赖的JDBC 功能的标准级别。要使用这一说明，驱动程序至少必须支持 
ANSISQL-2 Entry Level（ANSISQL-2 代表美国国家标准局1992 年所采用的标准。Entry Level代表SQL功能的特定清单）。
驱动程序开发人员可用 JDBCAPI所带的测试工具包来确定他们的驱动程序是否符合这些标准。

  "符合JDBC标准TM" 表示提供者的JDBC实现已经通过了JavaSoft提供的一致性测试。这些一致性测试将检查JDBCAPI中定义的所有类和方法是否都存在，
并尽可能地检查程序是否具有SQL Entry Level 功能。当然，这些测试并不完全，而且JavaSoft目前也无意对各提供者的实现进行标级。
但这种一致性定义的确可对JDBC实现提供一定的可信度。随着越来越多的数据库提供者、连接提供者、Internet提供者和应用程序编程员对JDBC API的接受，
JDBC也正迅速成为Java数据库访问的标准
```

七、层次结构：
```
根据处理对象的不同，数据库管理系统的层次结构由高级到低级依次为应用层、语言翻译处理层、数据存取层、数据存储层、操作系统。
7.1、应用层：应用层是DBMS与终端用户和应用程序的界面层，处理的对象是各种各样的数据库应用。
7.2、语言翻译：语言翻译处理层是对数据库语言的各类语句进行语法分析、视图转换、授权检查、完整性检查等。
7.3、数据存取层：数据存取层处理的对象是单个元组，它将上层的集合操作转换为单记录操作。
7.4、数据存储层：数据存储层处理的对象是数据页和系统缓冲区。
7.5、操作系统：操作系统是DBMS的基础。操作系统提供的存取原语和基本的存取方法通常是作为和DBMS存储层的接口。
```

八、不足
```
尽管JDBC在JAVA语言层面实现了统一，但不同数据库仍旧有许多差异。为了更好地实现跨数据库操作，
于是诞生了Hibernate、Mybatis等项目，Hibernate是对JDBC的再封装，实现了对数据库操作更宽泛的统一和更好的可移植性
```

JDBCUtil.java：
```Java
package com.mutisitc.utils;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * JDBC工具类
 */
public class JDBCUtil {
  /** Database ip:port */
  private final static String IP_PORT = "127.0.0.1:3306";
  /** Database 名称 */
  private final static String DB_NAME = "study";
  /** Database user name */
  private final static String USER_NAME = "root";
  /** Database 密码 */
  private final static String PASS_WORD = "root";
  /** JDBC 驱动类名 */
  private final static String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
  /** JDBC URL */
  private final static String JDBC_URL = "jdbc:mysql://" + IP_PORT + "/" + DB_NAME
      + "?useSSL=false&serverTimezone=GMT%2B8";

  /**
   * 获取数据库连接
   * @return 创建后的数据库连接
   */
  public static Connection getConnection() {
    Connection connection = null;
    try {
      PrintUtil.two("0.Mysql数据库连接信息：", "JDBC URL=" + JDBC_URL + ", userName=" + USER_NAME + ", password="
          + PASS_WORD + "，driver class name=" + DRIVER_CLASS_NAME);

      // 加载驱动-JDBC 4.0新特性可以不用显示加载
      Class.forName(DRIVER_CLASS_NAME);

      connection = DriverManager.getConnection(JDBC_URL, USER_NAME, PASS_WORD);
      PrintUtil.two("1.通过DriverManager.getConnection()获取数据库连接（会话）", "Connection=" + connection);
      return connection;
    } catch (ClassNotFoundException e) {
      PrintUtil.err("加载驱动" + DRIVER_CLASS_NAME + "出现异常，打印异常堆栈信息：");
      e.printStackTrace();
    } catch (SQLException e) {
      PrintUtil.err("获取数据库连接出现异常，打印异常堆栈信息：");
      e.printStackTrace();
    }
    return connection;
  }

  /**
   * 创建Statement对象
   * @return 创建后的Statement对象
   */
  public static Statement createStatement() {
    try {
      PrintUtil.two("0.Mysql数据库连接信息：", "JDBC URL=" + JDBC_URL + ", userName=" + USER_NAME + ", password="
          + PASS_WORD + "，driver class name=" + DRIVER_CLASS_NAME);

      // 加载驱动-JDBC 4.0新特性可以不用显示加载
      Class.forName(DRIVER_CLASS_NAME);

      Connection connection = DriverManager.getConnection(JDBC_URL, USER_NAME, PASS_WORD);
      PrintUtil.two("1.通过DriverManager.getConnection()获取数据库连接（会话）", "Connection=" + connection);

      Statement statement = connection.createStatement();
      PrintUtil.two("2.通过Connection.createStatement()：创建Statement对象", "Statement=" + statement);
      return statement;
    } catch (ClassNotFoundException e) {
      PrintUtil.err("加载驱动" + DRIVER_CLASS_NAME + "出现异常，打印异常堆栈信息：");
      e.printStackTrace();
    } catch (SQLException e) {
      PrintUtil.err("获取数据库连接出现异常，打印异常堆栈信息：");
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 创建PreparedStatement对象
   * @return 创建后的PreparedStatement对象
   */
  public static PreparedStatement prepareStatement(String sql) {
    try {
      PrintUtil.two("0.Mysql数据库连接信息：", "JDBC URL=" + JDBC_URL + ", userName=" + USER_NAME + ", password="
          + PASS_WORD + "，driver class name=" + DRIVER_CLASS_NAME);

      // 加载驱动-JDBC 4.0新特性可以不用显示加载
      Class.forName(DRIVER_CLASS_NAME);

      Connection connection = DriverManager.getConnection(JDBC_URL, USER_NAME, PASS_WORD);
      PrintUtil.two("1.通过DriverManager.getConnection()获取数据库连接（会话）", "Connection=" + connection);

      PreparedStatement prepared = connection.prepareStatement(sql);
      PrintUtil.two("2.通过Connection.prepareStatement(String sql)：创建PreparedStatement对象",
          "PreparedStatement=" + prepared + ", sql=" + sql);
      return prepared;
    } catch (ClassNotFoundException e) {
      PrintUtil.err("加载驱动" + DRIVER_CLASS_NAME + "出现异常，打印异常堆栈信息：");
      e.printStackTrace();
    } catch (SQLException e) {
      PrintUtil.err("获取数据库连接出现异常，打印异常堆栈信息：");
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 获取CallableStatement对象
   * @return 创建后的CallableStatement对象
   */
  public static CallableStatement prepareCall(String sql) {
    try {
      PrintUtil.two("0.Mysql数据库连接信息：", "JDBC URL=" + JDBC_URL + ", userName=" + USER_NAME + ", password="
          + PASS_WORD + "，driver class name=" + DRIVER_CLASS_NAME);

      // 加载驱动-JDBC 4.0新特性可以不用显示加载
      Class.forName(DRIVER_CLASS_NAME);

      Connection connection = DriverManager.getConnection(JDBC_URL, USER_NAME, PASS_WORD);
      PrintUtil.two("1.通过DriverManager.getConnection()获取数据库连接（会话）", "Connection=" + connection);

      CallableStatement callable = connection.prepareCall(sql);
      PrintUtil.two("2.通过Connection.prepareCall(String sql)：创建CallableStatement对象",
          "CallableStatement=" + callable + ", sql=" + sql);
      return callable;
    } catch (ClassNotFoundException e) {
      PrintUtil.err("加载驱动" + DRIVER_CLASS_NAME + "出现异常，打印异常堆栈信息：");
      e.printStackTrace();
    } catch (SQLException e) {
      PrintUtil.err("获取数据库连接出现异常，打印异常堆栈信息：");
      e.printStackTrace();
    }
    return null;
  }
  
  /**
   * 关闭数据库连接
   * @param connection 需要关闭的数据库连接
   */
  public static void close(Connection connection) {
    try {
      if (connection != null && !connection.isClosed()) {
        connection.close();
        PrintUtil.two("成功关闭数据库连接", "Connection.close()");
      }
    } catch (SQLException e) {
      PrintUtil.err("关闭数据库连接出现异常，打印异常堆栈信息：");
      e.printStackTrace();
    }
  }

  /**
   * 关闭Statement对象
   * @param statement 需要关闭的Statement对象
   */
  public static void close(Statement statement) {
    try {
      Connection connection = statement.getConnection();
      if (statement != null && !statement.isClosed()) {
        statement.close();
        PrintUtil.two("成功关闭Statement对象", "Statement.close()");
      }
      close(connection);
    } catch (SQLException e) {
      PrintUtil.err("关闭Statement对象出现异常，打印异常堆栈信息：");
      e.printStackTrace();
    }
  }

  /**
   * 关闭PreparedStatement对象
   * @param prepared  需要关闭的PreparedStatement对象
   */
  public static void close(PreparedStatement prepared) {
    try {
            Connection connection = prepared.getConnection();
      if (prepared != null && !prepared.isClosed()) {
        prepared.close();
        PrintUtil.two("成功关闭PreparedStatement对象", "PreparedStatement.close()");
      }
      close(connection);
    } catch (SQLException e) {
      PrintUtil.err("关闭PreparedStatement对象出现异常，打印异常堆栈信息：");
      e.printStackTrace();
    }
  }

  /**
   * 关闭CallableStatement对象
   * @param callable 需要关闭的CallableStatement对象
   */
  public static void close(CallableStatement callable) {
    try {
      Connection connection = callable.getConnection();
      if (callable != null && !callable.isClosed()) {
        callable.close();
        PrintUtil.two("成功关闭CallableStatement对象", "Statement.close()");
      }
      close(connection);
    } catch (SQLException e) {
      PrintUtil.err("关闭CallableStatement对象出现异常，打印异常堆栈信息：");
      e.printStackTrace();
    }
  }
}
```
CommonUtil.java：
```Java
package com.mutisitc.utils;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 普通工具类 
 * @date 2018年9月25日
 */
public class CommonUtil {
  /** 日期格式：yyyy-MM-dd hh24:mi:ss*/
  public final static String YDMS = "yyyy-MM-dd HH:mm:ss";
  /**
   * 获取当前日期字符串
   * @return 当前日期字符串
   */
  public static String getCurrentTime() {
    return new SimpleDateFormat(YDMS).format(new Date());
  }
  
  /**
   * 数组转换成字符串
   * @param result
   * @return
   */
  public static String toString(int[] result) {
    String str = "";
    if(null == result || result.length == 0) {
      return str;
    }
    
    for (int i : result) {
      str += i+",";
    }
    return str.substring(0, str.length()-1);
  }
  
  /**
   * 数组转换成字符串
   * @param result
   * @return
   */
  public static String toString(String[] result) {
    String str = "";
    if(null == result || result.length == 0) {
      return str;
    }
    
    for (String i : result) {
      str += i+",";
    }
    return str.substring(0, str.length()-1);
  }
}
```

---
### <a id="a_step">二、JDBC连接数据库步骤：</a> <a href="#a_jdbc">last</a> <a href="#a_driver">next</a>
[mysql:mysql-connector-java:8.0.12](https://search.maven.org/artifact/mysql/mysql-connector-java/8.0.12/jar)  
一、配置数据库驱动（以Mysql数据库创建一张表为例）：  
```
1、项目直接依赖mysql相关包：
  可以从Maven中央仓库下载mysql相关包，这里选用最新版本：mysql:mysql-connector-java:8.0.12
  Eclipse常规添加JAR包方式：
  右键项目 > Build Path > Configure Build Path > Libraries > Add JARs > 选择mysql-connector-java-8.0.11.jar

2、项目使用Maven统一配置依赖：
  pom.xml文件添加mysql依赖：
  <dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.12</version>
  </dependency>
```

二、 装载驱动程序：  
```
1、普通Java项目：
  使用Class.forName()加载驱动类：
  Class.forName("com.mysql.jdbc.Driver");

说明1：如果选用mysql-connector-java-8.x版本加载驱动会提示：
  Loading class `com.mysql.jdbc.Driver'. This is deprecated. The new driver class is `com.mysql.cj.jdbc.Driver'. 
The driveris automatically registered via the SPI and manual loading of the driver class is generally unnecessary.
  建议使用：com.mysql.cj.jdbc.Driver驱动类

1.2、JDBC 4.0新特性：
  JDBC 4.0 Drivers必须包括META-INF/services/java.sql.Driver文件。此文件包含java.sql.Driver的JDBC驱动程序实现的名称。
例如，要加载my.sql.Driver类，META-INF/services/java.sql.Driver 文件需要包含下面的条目： my.sql.Driver
应用程序不再需要使用Class.forName()显式地加载JDBC驱动程序。当前使用Class.forName()加载JDBC驱动程序的现有程序将在不作修改的情况下继续工作
  在mysql-connector-java-8.0.11.jar中包含java.sql.Driver，具体位置：
  mysql-connector-java-8.0.11.jar\META-INF\services\java.sql.Driver

2、Spring、Spring boot等项目：
  可以在application.properties配置datasource参数信息：
  ## 配置jdbc驱动：使用mysql驱动
  spring.datasource.driver-class-name=com.mysql.jdbc.Driver
  ## 配置 jdbc url
  spring.datasource.url=jdbc:mysql://127.0.0.1:3306/study
  ## 配置 jdbc 用户名
  spring.datasource.username=root
  ## 配置 jdbc 密码
  spring.datasource.password=root
```

三、建立连接(会话)：
[URL：统一资源定位符](https://baike.baidu.com/item/%E7%BB%9F%E4%B8%80%E8%B5%84%E6%BA%90%E5%AE%9A%E4%BD%8D%E7%AC%A6)
```
3.1、与数据库建立连接的标准方法是调用DriverManager.getConnection方法。
  该方法接受含有某个URL的字符串。DriverManager类（即所谓的JDBC管理层）将尝试找到可与那个URL所代表的数据库进行连接的驱动程序。
DriverManager类存有已注册的Driver类的清单。当调用方法getConnection时，它将检查清单中的每个驱动程序，
直到找到可与URL中指定的数据库进行连接的驱动程序为止。Driver的方法connect使用这个URL来建立实际的连接。
  用户可绕过JDBC管理层直接调用Driver方法。这在以下特殊情况下将很有用：当两个驱动器可同时连接到数据库中，
而用户需要明确地选用其中特定的驱动器。但一般情况下，让DriverManager类处理打开连接这种事将更为简单
  
  使用DriverManager.getConnection()方法获取数据库连接：
  Connection connection = DriverManager.getConnection("jdbcUrl", "username", "password");

3.2、使用完毕需要关闭数据库连接:
connection.close();

3.3、普通URL：
  URL[统一资源定位符]提供在Internet上定位资源所需的信息。
  可将其想象为一个地址。URL的第一部份指定了访问信息所用的协议，后面总是跟着冒号。常用的协议有"ftp[文件传输协议]"和"http[超文本传输协议]"。
如果协议是"file"，表示资源是在某个本地文件系统上而非在Internet上（下例用于表示我们所描述的部分；它并非URL的组成部分）。

  URL的其余部份（冒号后面的）给出了数据资源所处位置的有关信息。如果协议是file，则URL的其余部份是文件的路径。对于ftp和http协议，
URL的其余部份标识了主机并可选地给出某个更详尽的地址路径。

3.4、JDBC URL：
  JDBC URL提供了一种标识数据库的方法，可以使相应的驱动程序能识别该数据库并与之建立连接。
  实际上，驱动程序编程员将决定用什么JDBC URL来标识特定的驱动程序。
用户不必关心如何来形成JDBC URL；他们只须使用与所用的驱动程序一起提供的URL即可。JDBC的作用是提供某些约定，驱动程序编程员在构造他们的JDBC URL时应该遵循这些约定。
  由于JDBC URL要与各种不同的驱动程序一起使用，因此这些约定应非常灵活。首先，它们应允许不同的驱动程序使用不同的方案来命名数据库。
例如，odbc子协议允许（但并不是要求）URL含有属性值。

其次，JDBC URL应允许驱动程序编程员将一切所需的信息编入其中。这样就可以让要与给定数据库对话的applet打开数据库连接，而无须要求用户去做任何系统管理工作。

最后，JDBC URL应允许某种程度的间接性。也就是说，JDBC URL可指向逻辑主机或数据库名，而这种逻辑主机或数据库名将由网络命名系统动态地转换为实际的名称。
这可以使系统管理员不必将特定主机声明为JDBC名称的一部份。网络命名服务（例如DNS、NIS和DCE）有多种，而对于使用哪种命名服务并无限制。 JDBC URL的标准语法如下所示。
它由三部分组成，各部分间用冒号分隔，JDBC URL的三个部分可分解如下：
  jdbc协议：JDBC URL中的协议总是jdbc。

  <子协议>：驱动程序名或数据库连接机制（这种机制可由一个或多个驱动程序支持）的名称。子协议名的典型示例是"odbc"，
  该名称是为用于指定ODBC风格的数据资源名称的URL专门保留的。例如，为了通过JDBC-ODBC桥来访问某个数据库，可以用如下所示的URL：jdbc:odbc:book。本例中，
子协议为"odbc"，子名称"book"是本地ODBC数据资源。如果要用网络命名服务（这样JDBC URL中的数据库名称不必是实际名称），则命名服务可以作为子协议。

  <子名称>：一种标识数据库的方法。子名称可以依不同的子协议而变化。它还可以有子名称的子名称（含有驱动程序编程员所选的任何内部语法）。
使用子名称的目的是为定位数据库提供足够的信息。前例中，因为ODBC将提供其余部份的信息，因此用"book"就已足够。
然而，位于远程服务器上的数据库需要更多的信息。例如，如果数据库是通过Internet来访问的，则在JDBC URL中应将网络地址作为子名称的一部份包括进去，
且必须遵循如下所示的标准URL命名约定：//主机名:端口/子协议


3.5、Mysql数据库的链接地址格式：
  jdbc:mysql://IP地址:端口号/数据库名称
  jdbc协议：JDBU URL中的协议总是 jdbc
  子协议：驱动程序或数据列链接机制（这种机制可由一个或多个驱动程序支持）的名称，如mysql，oracle
  子名称：一种标识数据库的方法。必须遵循“//主机名:端口/子协议”的标准URL命名约定，mysql默认端口为3306
如：//localhost:3306/study           //127.0.0.1:3306/study

3.6、获取数据库连接完整示例：
Connection connection = null;
try {
     connection = DriverManager.getConnection(
         "jdbc:mysql://127.0.0.1:3306/study?useSSL=false&serverTimezone=GMT%2B8", "root", "root");
} catch (SQLException e) {
     e.printStackTrace();
} finally {
     if (null != connection) {
          try {
               connection.close();
          } catch (SQLException e) {
               e.printStackTrace();
          }
     }
}

说明1：Mysql5.5.x以上版本，jdbc url上未取消SSL验证时会提示：
  Tue Sep 18 14:15:07 CST 2018 WARN: Establishing SSL connection without server's identity verification is not recommended. 
According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit 
option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property 
is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and 
provide truststore for server certificate verification.
  解决提示：可以在jdbc url添加参数：useSSL=false
  eg：jdbc:mysql://127.0.0.1:3306/study?useSSL=false

说明2：jdbc url如果未指定时区，会报java.sql.SQLException异常：
  java.sql.SQLException: The server time zone value '' is unrecognized or represents more than one time zone. 
You must configure either the server or JDBC driver(via the serverTimezone configuration property) 
to use a more specifc time zone value if you want to utilize time zone support.
  解决异常：在jdbc url添加参数serverTimezone=GMT%2B8   
  eg：jdbc:mysql://127.0.0.1:3306/study?useSSL=false&serverTimezone=GMT%2B8
```

四、发送SQL语句：
```
  4.1、创建Statement对象：
  Statement对象用于把SQL语句发送到DBMS。使用一个活跃的Connection连接的来创建Statement对象的实例
  Statement statement = connection.createStatement();

  4.2、执行语句：
  使用 executeUpdate 方法是因为在createTableCoffees中的SQL语句是DDL（数据定义语言）语句。
创建表，改变表，删除表都是DDL语句的，要用executeUpdate方法来执行。可以从它的名字里看出，方法executeUpdate也被用于执行更新表SQL语句。
实际上，相对于创建表来说，executeUpdate 用于更新表的操作更多，因为表只需要创建一次，但经常被更新。
被使用最多的执行SQL语句的方法是executeQuery。这个方法被用来执行SELECT语句，它几乎是使用最多的SQL语句
  statement.executeQuery("SELECT bookId,title,author,remark,createrTime FROM book");

  4.3、获取查询的数据：
  ResultSet rs = statement.executeQuery("SELECT bookId,title,author,remark,createrTime FROM book");
  while (rs.next()) {
     Long bookId = rs.getLong("bookId");
     String title = rs.getString("title");
     String author = rs.getString("author");
     String remark = rs.getString("remark");
     Date createrTime = rs.getDate("createrTime");
  }

  4.4、关闭Statement对象：
  Statement对象将由Java垃圾收集程序自动关闭。而作为一种好的编程风格，应在不需要Statement对象时显式地关闭它们。
这将立即释放DBMS资源，有助于避免潜在的内存问题。
  statement.close();
```
StepMain.java：
```Java
package com.mutisitc.step;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mutisitc.utils.PrintUtil;
// JDBC连接数据库步骤
public class StepMain {
    public static void main(String[] args) {
        PrintUtil.one("JDBC连接数据库步骤");
        
        Connection connection = null;
        PrintUtil.two("0.创建Connection数据库连接引用", connection);
        
        try {
            Class<?> driver = Class.forName("com.mysql.cj.jdbc.Driver");
            PrintUtil.two("1.通过Class.forName()加载Driver驱动类：com.mysql.cj.jdbc.Driver", driver);

            connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/study?useSSL=false&serverTimezone=GMT%2B8", "root", "root");
            PrintUtil.two("2.通过DriverManager.getConnection()，获取数据库连接Connection", connection);

            Statement statement = connection.createStatement();
            PrintUtil.two("3.通过Connection.createStatement()连接，创建Statement对象", statement);
            
            ResultSet rs = statement.executeQuery("SELECT bookId,title,author,remark,createrTime FROM book");
            PrintUtil.two("4.通过Statement.executeQuery()，执行查询SQL，获取查询结果集ResultSet对象", rs);
            
            PrintUtil.println();
            while (rs.next()) {
                Long bookId = rs.getLong("bookId");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String remark = rs.getString("remark");
                Date createrTime = rs.getDate("createrTime");
                PrintUtil.three("5.通过ResultSet结果集，获取数据列信息", "bookId="+bookId+", title="+title+
                        ",author="+author+"remark="+remark+",createrTime="+createrTime);
            }
            statement.close();
            PrintUtil.two("6.通过Statement.close()","关闭Statement对象，释放资源");
        } catch (ClassNotFoundException e) {
            PrintUtil.err("Class.forName()加载Dirver驱动类出现异常，打印异常堆栈信息：");
            e.printStackTrace();
        } catch (SQLException e) {
            PrintUtil.err("DriverManager.getConnection()获取连接Connection出现异常，打印异常堆栈信息：");
            e.printStackTrace();
        } finally {
            if (null != connection) {
                try {
                    connection.close();
                    PrintUtil.two("7.通过Connection.close()","关闭数据库连接，释放资源");
                } catch (SQLException e) {
                    PrintUtil.err("Connection.close()关闭连接出现异常，打印异常堆栈信息：");
                    e.printStackTrace();
                }
            }
        }
    }
}
```

### <a id="a_driver">三、java.sql.Driver：数据库驱动：</a> <a href="#a_step">last</a> <a href="#a_manager">next</a>
[java.sql.Driver](https://docs.oracle.com/javase/8/docs/api/java/sql/Driver.html)  
一、描述：
```
  每个驱动程序类必须实现的接口。 
  Java SQL 框架允许多个数据库驱动程序。 
  每个驱动程序都应该提供一个实现 Driver接口的类。 

  DriverManager 会试着加载尽可能多的它可以找到的驱动程序，然后，对于任何给定连接请求，它会让每个驱动程序依次试着连接到目标 URL。 
  强烈建议每个 Driver类应该是小型的并且是单独的，这样就可以在不必引入大量支持代码的情况下加载和查询 Driver类。 

  在加载某一 Driver类时，它应该创建自己的实例并向 DriverManager 注册该实例。这意味着用户可以通过调用以下程序加载和注册一个驱动程序 
  Class.forName("foo.bah.Driver")
```
二、理解：
```
  加载Driver类，然后自动在DriverManager中注册的方式有两种：
  2.1、调用方法Class.forName：
  这将显式地加载驱动程序类。由于这与外部设置无关，因此推荐使用这种加载驱动程序的方法。如果加载Mysql驱动类com.mysql.cj.jdbc.Driver：
  Class.forName("com.mysql.cj.jdbc.Driver")。

如果将com.mysql.cj.jdbc.Driver编写为加载时创建实例，并调用以该实例为参数的DriverManager.registerDriver()，
则它在DriverManager的驱动程序列表中，并可用于创建连接。

  2.2、将驱动程序添加到Java.lang.System的属性jdbc.drivers中：
  这是一个由DriverManager类加载的驱动程序类名的列表，由冒号分隔：初始化DriverManager类时，它搜索系统属性jdbc.drivers，
如果用户已输入了一个或多个驱动程序，则DriverManager类将试图加载它们。以下代码说明程序员如何在~/.hotJava/properties
中输入三个驱动程序类（启动时，HotJava将ivers=com.mysql.cj.jdbc.Driver;xxx.xxx.Driver;xxx.xxx.Driver)
对DriverManager方法的第一次调用将自动加载这些驱动程序类。

  注意：加载驱动程序的第二种方法需要持久的预设环境。如果对这一点不能保证，则调用方法Class.forName显式地加载每个驱动程序就显得更为安全。
这也是引入特定驱动程序的方法，因为一旦DriverManager类被初始化，它将不再检查jdbc.drivers属性列表
```

三、方法说明：
[java.util.Properties](https://docs.oracle.com/javase/8/docs/api/java/util/Properties.html)

|返回类型|方法|说明|
|---|---|---|
|boolean|acceptsURL(String url)|获取驱动程序是否认为它可以打开与给定URL的连接|
|Connection|connect(String url, Properties info)|尝试与给定的URL建立数据库连接|
|int|getMajorVersion()|获取驱动程序的主要版本号|
|int|getMinorVersion()|获取驱动程序的次要版本号|
|Logger|getParentLogger()|返回此驱动程序使用的所有记录器的父记录器|
|DriverPropertyInfo[]|getPropertyInfo(String url, Properties info)|获取有关此驱动程序的可能属性的信息|
|boolean|jdbcCompliant()|报告此驱动程序是否为真正的JDBC Compliant驱动程序|

四、[java.sql.DriverPropertyInfo](https://docs.oracle.com/javase/8/docs/api/java/sql/DriverPropertyInfo.html)  
4.1、描述：
```
用于建立连接的驱动程序属性。只有那些需要通过getDriverProperties方法与Driver交互来发现
并提供用于连接的属性的高级编程人员，才需要对类DriverPropertyInfo感兴趣。 
```

4.2、方法说明：

|返回类型|方法|说明|
|---|---|---|
|String[]|choices|如果DriverPropertyInfo.value可以从特定值集中选择字段的值，则可以是值的数组;否则为null|
|String|description|属性的简要描述，可能为 null|
|String|name|属性的名称|
|boolean|required|如果在 Driver.connect 期间必须为此属性提供一个值，则 required 字段为 true，否则为 false|
|String|value|value 字段通过综合为 getPropertyInfo 方法提供的信息、Java 环境和驱动程序提供的默认值来指定当前属性值|

DriverMain.java：
```Java
package com.mutisitc.driver;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Properties;
import com.mutisitc.utils.PrintUtil;
// java.sql.Driver：数据库驱动
public class DriverMain {
    public static void main(String[] args) {
        PrintUtil.one("java.sql.Driver：数据库驱动：");
        try {
            String jdbcURL = "jdbc:mysql://127.0.0.1:3306/study?useSSL=false&serverTimezone=GMT%2B8";
            String userName = "root";
            String password = "root";
            PrintUtil.three("0.Mysql数据库连接信息：", null);
            PrintUtil.three("JDBC URL", jdbcURL);
            PrintUtil.three("userName", userName);
            PrintUtil.three("password", password);
            
            Driver driver = DriverManager.getDriver(jdbcURL);
            PrintUtil.two("1.通过DriverManager.getDriver()：获取到的数据库驱动", "Driver="+driver);
            
            boolean isacceptsURL = driver.acceptsURL(jdbcURL);
            PrintUtil.two("2.Driver.isacceptsURL()：获取驱动程序是否认为它可以打开与给定URL的连接：获取结果", "isacceptsURL="+isacceptsURL);
            
            PrintUtil.two("3.使用Driver获取数据库连接", "Driver.connect(String url, Properties info)");
            Properties properties = new Properties();
            properties.put("user", userName); 
            properties.put("password", password);
            PrintUtil.three("3.1.配置Properties属性，至少包含user和password信息", properties.getClass()+"="+properties);
            
            Connection connection = driver.connect(jdbcURL, properties);
            PrintUtil.three("3.2.Driver.connect()：尝试与给定的URL建立数据库连接", "Connection="+connection);
            connection.close();
            
            int majorVersion = driver.getMajorVersion();
            PrintUtil.two("4.通过Driver.getMajorVersion()：获取驱动程序的主要版本号", "MajorVersion="+majorVersion);
            
            int minorVersion = driver.getMinorVersion();
            PrintUtil.two("5.通过Driver.getMinorVersion()：获取驱动程序的次要版本号", "MinorVersion="+minorVersion);
            
            boolean jdbcCompliant = driver.jdbcCompliant();
            PrintUtil.two("6.通过Driver.jdbcCompliant()：报告此驱动程序是否为真正的JDBC Compliant驱动程序", "jdbcCompliant="+jdbcCompliant);
            
            DriverPropertyInfo[] driverPropertyInfos = driver.getPropertyInfo(jdbcURL, properties);
            PrintUtil.two("7.通过Driver.getPropertyInfo()：获取有关此驱动程序的可能属性的信息", "DriverPropertyInfo="+Arrays.asList(driverPropertyInfos));
            int index = 0;
            for (DriverPropertyInfo driverPropertyInfo : driverPropertyInfos) {
                index++;
                PrintUtil.two("7."+index+".驱动程序的属性信息：DriverPropertyInfo", driverPropertyInfo);
                PrintUtil.three("DriverPropertyInfo.name：属性的名称", driverPropertyInfo.name);
                PrintUtil.three("DriverPropertyInfo.required：是否在Driver.connect期间必须为此属性提供一个值", driverPropertyInfo.required);
                PrintUtil.three("DriverPropertyInfo.value：value 字段通过综合为 getPropertyInfo 方法提供的信息、Java 环境和驱动程序提供的默认值来指定当前属性值", driverPropertyInfo.value);
                PrintUtil.three("DriverPropertyInfo.choices：可以从特定值集中选择字段的值：", driverPropertyInfo.choices == null ? null : Arrays.asList(driverPropertyInfo.choices));
                PrintUtil.three("DriverPropertyInfo.description：属性的名称",driverPropertyInfo.description); 
            }
        } catch (SQLException e) {
            PrintUtil.err("演示 java.sql.Driver：数据库驱动，打印异常堆栈信息：");
            e.printStackTrace();
        }
    }
}
```

### <a id="a_manager">四、java.sql.DriverManager：驱动管理：</a> <a href="#a_driver">last</a> <a href="#a_connection">next</a>
[java.sql.DriverManager](https://docs.oracle.com/javase/8/docs/api/java/sql/DriverManager.html)   
一、描述：
```
  管理一组 JDBC 驱动程序的基本服务。
  注：DataSource 接口是JDBC 2.0 API中的新增内容，它提供了连接到数据源的另一种方法。使用 DataSource 对象是连接到数据源的首选方法。 
作为初始化的一部分，DriverManager 类会尝试加载在 "jdbc.drivers" 系统属性中引用的驱动程序类。这允许用户定制由他们的应用程序使用的 JDBC Driver。
例如，在 ~/.hotjava/properties 文件中，用户可以指定： 
jdbc.drivers=foo.bah.Driver:wombat.sql.Driver:bad.taste.ourDriver
  
  DriverManager 类的方法getConnection和getDrivers已经得到提高以支持Java Standard Edition Service Provider机制。 
 
  在调用getConnection方法时，DriverManager会试着从初始化时加载的那些驱动程序以及使用与当前applet或应用程序相同的类加载器显式加载的那些驱动程序中查找合适的驱动程序。 
  
  从Java 2 SDK标准版本1.3版开始，只有当已授予适当权限时设置日志流。通常这将使用工具PolicyTool完成，该工具可用于授予permission java.sql.SQLPermission "setLog"权限
```
二、理解：
```
  DriverManager 类是 JDBC 的管理层，作用于用户和驱动程序之间。它跟踪可用的驱动程序，并在数据库和相应驱动程序之间建立连接。
另外，DriverManager类也处理诸如驱动程序登录时间限制及登录和跟踪消息的显示等事务。 　

  对于简单的应用程序，一般程序员需要在此类中直接使用的唯一方法是DriverManager.getConnection。正如名称所示，该方法将建立与数据库的连接。
JDBC允许用户调用DriverManager的方法getDriver、getDrivers和registerDriver及Driver的方法connect。但多数情况下，让DriverManager类管理建立连接的细节为上策

  由于安全方面的原因，JDBC管理层将跟踪哪个类加载器提供哪个驱动程序。这样，当DriverManager类打开连接时，
它仅使用本地文件系统或与发出连接请求的代码相同的类加载器提供的驱动程序

  加载Driver类并在DriverManager类中注册后，它们即可用来与数据库建立连接。当调用DriverManager.getConnection方法发出连接请求时，
DriverManager将检查每个驱动程序，查看它是否可以建立连接

  有时可能有多个JDBC驱动程序可以与给定的URL连接。例如，与给定远程数据库连接时，可以使用JDBC-ODBC桥驱动程序、JDBC到通用网络协议驱动程序或数据库厂商提供的驱动程序。
在这种情况下测试驱动程序的顺序至关重要，因为DriverManager将使用它所找到的第一个可以成功连接到给定URL的驱动程序。
  首先DriverManager试图按注册的顺序使用每个驱动程序（jdbc.drivers中列出的驱动程序总是先注册）。它将跳过代码不可信任的驱动程序，
除非加载它们的源与试图打开连接的代码的源相同。它通过轮流在每个驱动程序上调用方法Driver.connect，并向它们传递用户开始传递给方法
DriverManager.getConnection的URL来对驱动程序进行测试，然后连接第一个认出该URL的驱动程序。这种方法初看起来效率不高，
但由于不可能同时加载数十个驱动程序，因此每次连接实际只需几个过程调用和字符串比较
```
三、方法说明：  
[java.sql.DriverAction](https://docs.oracle.com/javase/8/docs/api/java/sql/DriverAction.html)  
[java.util.Enumeration](https://docs.oracle.com/javase/8/docs/api/java/util/Enumeration.html)  
[java.io.File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html)  
[java.io.PrintStream](https://docs.oracle.com/javase/8/docs/api/java/io/PrintStream.html)  
[java.io.PrintWriter](https://docs.oracle.com/javase/8/docs/api/java/io/PrintWriter.html)

|返回类型|方法|说明|
|---|---|---|
|static void|deregisterDriver(Driver driver)|从已DriverManager注册的驱动程序列表中删除指定的驱动程序|
|static Connection|getConnection(String url)|尝试建立与给定数据库URL的连接|
|static Connection|getConnection(String url, Properties info)|尝试建立与给定数据库URL的连接|
|static Connection|getConnection(String url, String user, String password)|尝试建立与给定数据库URL的连接|
|static Driver|getDriver(String url)|尝试查找理解给定URL的驱动程序|
|static Enumeration<Driver>|getDrivers()|获取当前调用者可以访问的所有当前加载的JDBC驱动程序的枚举|
|static int|getLoginTimeout()|获取驱动程序在尝试登录数据库时可以等待的最长时间（以秒为单位）|
|static PrintStream|getLogStream()|~~已过时~~。使用 getLogWriter，获取由DriverManager和所有驱动程序使用的日志记录/跟踪PrintStream|
|static PrintWriter|getLogWriter()|获取日志编写器|
|static void|println(String message)|将消息打印到当前JDBC日志流|
|static void|registerDriver(Driver driver)|注册给定的驱动程序DriverManager|
|static void|registerDriver(Driver driver, DriverAction da)|注册给定的驱动程序DriverManager|
|static void|setLoginTimeout(int seconds)|设置驱动程序在识别驱动程序后尝试连接数据库时等待的最长时间（以秒为单位）|
|static void|setLogStream(PrintStream out)|~~已过时。~~使用 setLogWriter，获取由DriverManager 和所有驱动程序使用的日志记录/跟踪PrintStream|
|static void|setLogWriter(PrintWriter out)|设置和所有驱动程序PrintWriter使用的日志记录/跟踪对象DriverManager|

DriverManagerMain.java：
```Java
package com.mutisitc.drivermanager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import com.mutisitc.utils.PrintUtil;
// java.sql.DriverManager：驱动管理
public class DriverManagerMain {
    public static void main(String[] args) {
        PrintUtil.one("java.sql.DriverManager：驱动管理：");
        try {
            String jdbcURL = "jdbc:mysql://127.0.0.1:3306/study?useSSL=false&serverTimezone=GMT%2B8";
            String userName = "root";
            String password = "root";
            PrintUtil.two("0.Mysql数据库连接信息：", null);
            PrintUtil.three("0.1.JDBC URL", jdbcURL);
            PrintUtil.three("0.2.userName", userName);
            PrintUtil.three("0.3.password", password);
            
            PrintUtil.two("1.使用DriverManager.getConnection()获取数据库连接", "DriverManager.getConnection(String url, Properties info)");
            Properties properties = new Properties();
            properties.put("user", userName); 
            properties.put("password", password);
            PrintUtil.three("1.1.配置Properties属性，至少包含user和password信息", properties.getClass()+"="+properties);
            
            Connection connection = DriverManager.getConnection(jdbcURL, properties);
            PrintUtil.three("1.2.DriverManager.getConnection(String url, Properties info)：尝试建立与给定数据库URL的连接", "Connection="+connection);
            connection.close();
            
            Connection connection2 = DriverManager.getConnection(jdbcURL, userName, password);
            PrintUtil.two("2.DriverManager.getConnection(String url, String user, String password)：尝试建立与给定数据库URL的连接", "Connection="+connection2);
            connection2.close();
            
            Driver driver = DriverManager.getDriver(jdbcURL);
            PrintUtil.two("3.DriverManager.getDriver()：获取到的数据库驱动", "Driver="+driver);
            
            com.mysql.jdbc.Driver dirver2 = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(dirver2);
            PrintUtil.two("4.DriverManager.registerDriver(Driver driver)：注册给定的驱动程序DriverManager", "Driver="+dirver2);
            
            Enumeration<Driver> driverEnums = DriverManager.getDrivers();
            PrintUtil.two("5.DriverManager.getDrivers()：获取当前调用者可以访问的所有当前加载的JDBC驱动程序的枚举", "Enumeration<Driver>="+driverEnums);
            int index = 0;
            while(driverEnums.hasMoreElements()) {
                index++;
                Driver driverTemp = driverEnums.nextElement();
                PrintUtil.three("5."+index+".当前加载的JDBC驱动程序", "Driver="+driverTemp);
            }
            
            DriverManager.deregisterDriver(dirver2);
            PrintUtil.two("6.DriverManager.deregisterDriver(Driver driver)：从已DriverManager注册的驱动程序列表中删除指定的驱动程序", "Driver="+dirver2);
            
            int loginTimeout = DriverManager.getLoginTimeout();
            PrintUtil.two("7.DriverManager.getLoginTimeout()：获取驱动程序在尝试登录数据库时可以等待的最长时间（以秒为单位）", "loginTimeout="+loginTimeout);
            
            loginTimeout = 10000;
            DriverManager.setLoginTimeout(loginTimeout);
            PrintUtil.two("8.DriverManager.setLoginTimeout(int seconds)：设置驱动程序在识别驱动程序后尝试连接数据库时等待的最长时间（以秒为单位）", "loginTimeout="+loginTimeout);
            
            PrintStream printStream = DriverManager.getLogStream();
            PrintUtil.two("9.DriverManager.getLogStream()：已过时，获取由DriverManager和所有驱动程序使用的日志记录/跟踪PrintStream", "PrintStream="+printStream);
            
            File file = new File("src/com/mutisitc/drivermanager/logStream.txt");
            PrintUtil.two("10.加载文件", "File="+file);
            
            PrintStream newPrintStream = new PrintStream(file);
            DriverManager.setLogStream(newPrintStream);
            PrintUtil.three("10.1.通过DriverManager.setLogStream(PrintStream out)：已过时，获取由DriverManager 和所有驱动程序使用的日志记录/跟踪PrintStream", "PrintStream="+newPrintStream);
            
            PrintWriter printWriter = DriverManager.getLogWriter();
            PrintUtil.two("11.通过DriverManager.getLogStream()：获取日志编写器", "PrintWriter="+printWriter);
            
            PrintUtil.two("12.加载文件", "File="+file);
            PrintWriter newPrintWriter = new PrintWriter(file);
            DriverManager.setLogWriter(newPrintWriter);
            PrintUtil.three("12.1.通过DriverManager.setLogWriter(PrintWriter out)：设置和所有驱动程序PrintWriter使用的日志记录/跟踪对象DriverManager", "PrintWriter="+newPrintWriter);
        
            String message = "测试打印数据";
            DriverManager.println(message);
            PrintUtil.two("13.DriverManager.println(String message)：将消息打印到当前JDBC日志流", "Message="+message);
            
        } catch (SQLException e) {
            PrintUtil.err("演示 java.sql.DriverManager：驱动管理出现异常，打印异常堆栈信息：");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            PrintUtil.err("加载文件路径出现异常，打印异常堆栈信息：");
            e.printStackTrace();
        }
    }
}
```

### <a id="a_connection">五、java.sql.Connection：数据库连接：</a> <a href="#a_manager">last</a> <a href="#a_statement">next</a>
[java.sql.Connection](https://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html)  
[UDT：互联网数据传输协议](https://baike.baidu.com/item/UDT)  
一、描述：
```
  与特定数据库的连接（会话）。在连接上下文中执行SQL语句并返回结果。 
  
  Connection 对象的数据库能够提供描述其表、所支持的SQL语法、存储过程、此连接功能等等的信息。此信息是使用 getMetaData 方法获得的。 
  注：在配置Connection时，JDBC应用程序应该使用适当的Connection方法，比如setAutoCommit或 setTransactionIsolation。
在有可用的JDBC方法时，应用程序不能直接调用SQL命令更改连接的配置。默认情况下，Connection对象处于自动提交模式下，
这意味着它在执行每个语句后都会自动提交更改。如果禁用了自动提交模式，那么要提交更改就必须显式调用 commit 方法；否则无法保存数据库更改。 
  
  使用JDBC 2.1核心API创建的新Connection对象有一个与之关联的最初为空的类型映射。用户可以为此类型映射中的
UDT[数据传输协议（UDP-based Data Transfer Protocol，简称UDT）是一种互联网数据传输协议]输入一个自定义映射关系。
在使用ResultSet.getObject 方法从数据源中获取UDT时，getObject方法将检查该连接的类型映射是否有对应该UDT的条目。如果有，
那么getObject方法将该UDT映射到所指示的类。如果没有条目，则使用标准映射关系映射该UDT。 
  
  用户可以创建一个新的类型映射，该映射是一个 java.util.Map 对象，可在其中创建一个条目，并将该条目传递给可以执行自定义映射关系的 java.sql 方法。
在这种情况下，该方法将使用给定的类型映射，而不是与连接关联的映射。 
```
二、字段说明：

|数据类型|方法|说明|
|---|---|---|
|static int|TRANSACTION_NONE=0|一个常量，指示不支持事务|
|static int|TRANSACTION_READ_COMMITTED=2|一个常量，表示防止脏读; 可以发生不可重复的读取和幻像读取|
|static int|TRANSACTION_READ_UNCOMMITTED=1|一个常量，表示可以发生脏读，不可重复读和幻像读|
|static int|TRANSACTION_REPEATABLE_READ=4|一个常量，表示防止脏读和不可重复读; 可以发生幻像读取|
|static int|TRANSACTION_SERIALIZABLE=8|一个常量，表示禁止脏读，不可重复读和幻像读|

三、方法说明：

|返回类型|方法|说明|
|---|---|---|
|void|abort(Executor executor)|终止打开的连接|
|void|clearWarnings()|清除为此Connection对象报告的所有警告|
|void|close()|立即释放此Connection对象的数据库和JDBC资源，而不是等待它们自动释放|
|void|commit()|使自上次提交/回滚以来所做的所有更改成为永久更改，并释放此Connection对象当前持有的所有数据库锁|
|Array|createArrayOf(String typeName, Object[] elements)|用于创建Array对象的工厂方法|
|Blob|createBlob()|构造一个实现Blob接口的对象|
|Clob|createClob()|构造一个实现Clob接口的对象|
|NClob|createNClob()|构造一个实现NClob接口的对象|
|SQLXML|createSQLXML()|构造一个实现SQLXML接口的对象|
|Statement|createStatement()|创建Statement用于将SQL语句发送到数据库的对象|
|Statement|createStatement(int resultSetType, int resultSetConcurrency)|创建一个Statement将生成 ResultSet具有给定类型和并发性的对象的对象|
|Statement|createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)|创建一个Statement对象，该对象将生成ResultSet具有给定类型，并发性和可保存性的对象|
|Struct|createStruct(String typeName, Object[] attributes)|用于创建Struct对象的工厂方法|
|boolean|getAutoCommit()|获取此Connection 对象的当前自动提交模式|
|String|getCatalog()|获取此Connection对象的当前目录名称|
|Properties|getClientInfo()|返回一个列表，其中包含驱动程序支持的每个客户端信息属性的名称和当前值|
|String|getClientInfo(String name)|返回name指定的客户端信息属性的值|
|int|getHoldability()|获取ResultSet使用此Connection对象创建的对象的当前可保存性|
|DatabaseMetaData|getMetaData()|获取DatabaseMetaData包含有关此Connection对象表示连接的数据库的元数据的对象|
|int|getNetworkTimeout()|获取驱动程序等待数据库请求完成的毫秒数|
|String|getSchema()|获取此Connection对象的当前架构名称|
|int|getTransactionIsolation()|获取此Connection对象的当前事务隔离级别|
|Map<String,Class<?>>|getTypeMap()|获取Map与此Connection对象关联的对象|
|SQLWarning|getWarnings()|获取此Connection对象上的调用报告的第一个警告|
|boolean|isClosed()|获取此Connection对象是否已关闭|
|boolean|isReadOnly()|获取此Connection对象是否处于只读模式|
|boolean|isValid(int timeout)|如果连接尚未关闭且仍然有效，则返回true|
|String|nativeSQL(String sql)|将给定的SQL语句转换为系统的本机SQL语法|
|CallableStatement|prepareCall(String sql)|创建一个CallableStatement用于调用数据库存储过程的对象|
|CallableStatement|prepareCall(String sql, int resultSetType, int resultSetConcurrency)|创建一个CallableStatement将生成 ResultSet具有给定类型和并发性的对象的对象|
|CallableStatement|prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability)|创建一个CallableStatement将生成 ResultSet具有给定类型和并发性的对象的对象|
|PreparedStatement|prepareStatement(String sql)|创建PreparedStatement用于将参数化SQL语句发送到数据库的对象|
|PreparedStatement|prepareStatement(String sql, int autoGeneratedKeys)|创建一个PreparedStatement能够获取自动生成的密钥的默认对象|
|PreparedStatement|prepareStatement(String sql, int[] columnIndexes)|创建一个PreparedStatement能够返回给定数组指定的自动生成的键的默认对象|
|PreparedStatement|prepareStatement(String sql, int resultSetType, int resultSetConcurrency)|创建一个PreparedStatement将生成 ResultSet具有给定类型和并发性的对象的对象|
|PreparedStatement|prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability)|创建一个PreparedStatement对象，该对象将生成ResultSet具有给定类型，并发性和可保存性的对象|
|PreparedStatement|prepareStatement(String sql, String[] columnNames)|创建一个PreparedStatement能够返回给定数组指定的自动生成的键的默认对象|
|void|releaseSavepoint(Savepoint savepoint)|从当前事务中删除指定的Savepoint和后续的Savepoint对象|
|void|rollback()|撤消当前事务中所做的所有更改，并释放此Connection对象当前持有的所有数据库锁|
|void|rollback(Savepoint savepoint)|取消Savepoint设置给定对象后所做的所有更改|
|void|setAutoCommit(boolean autoCommit)|将此连接的自动提交模式设置为给定状态|
|void|setCatalog(String catalog)|设置给定的目录名称，以便选择要Connection在其中工作的此对象的数据库的子空间|
|void|setClientInfo(Properties properties)|设置连接的客户端信息属性的值|
|void|setClientInfo(String name, String value)|将name指定的客户端信息属性的值设置为value指定的值|
|void|setHoldability(int holdability)|将ResultSet使用此Connection对象创建的对象的默认可保存性更改为给定的可保存性|
|void|setNetworkTimeout(Executor executor, int milliseconds)|设置从连接创建的连接或对象的最长时间，将等待数据库回复任何一个请求|
|void|setReadOnly(boolean readOnly)|将此连接置于只读模式，作为驱动程序的提示以启用数据库优化|
|Savepoint|setSavepoint()|在当前事务中创建一个未命名的保存点，并返回Savepoint表示它的新对象|
|Savepoint|setSavepoint(String name)|在当前事务中创建具有给定名称的保存点，并返回Savepoint表示它的新对象|
|void|setSchema(String schema)|设置要访问的给定模式名称|
|void|setTransactionIsolation(int level)|尝试将此Connection对象的事务隔离级别更改为给定的对象|
|void|setTypeMap(Map<String,Class<?>> map)|将给定TypeMap对象安装为此Connection对象的类型映射|

四、其他：  
[java.sql.ResultSet](https://docs.oracle.com/javase/8/docs/api/java/sql/ResultSet.html)
```
4.1、一些方法参数中sql，resultSetType, resultSetConcurrency, resultSetHoldability：
  sql - 一个 String 对象，它是将被发送到数据库的SQL语句，可以包含一个或多个 '?' IN 参数
  resultSetType - 结果集类型，它是 ResultSet.TYPE_FORWARD_ONLY、ResultSet.TYPE_SCROLL_INSENSITIVE 或 ResultSet.TYPE_SCROLL_SENSITIVE 之一
  resultSetConcurrency - 并发类型，它是 ResultSet.CONCUR_READ_ONLY 或 ResultSet.CONCUR_UPDATABLE 之一
  resultSetHoldability - 以下 ResultSet 常量之一：ResultSet.HOLD_CURSORS_OVER_COMMIT 或 ResultSet.CLOSE_CURSORS_AT_COMMIT 

4.2、常用方法：
  void close()：立即释放此Connection对象的数据库和JDBC资源，而不是等待它们自动释放
  Statement createStatement()：创建Statement用于将SQL语句发送到数据库的对象 
  CallableStatement prepareCall(String sql)：创建一个CallableStatement用于调用数据库存储过程的对象 
  PreparedStatement prepareStatement(String sql)：创建PreparedStatement用于将参数化SQL语句发送到数据库的对象 
  boolean isClosed()：获取此Connection对象是否已关闭 
  void setReadOnly(boolean readOnly)：将此连接置于只读模式，作为驱动程序的提示以启用数据库优化 
  boolean isReadOnly()：获取此Connection对象是否处于只读模式 
  boolean isValid(int timeout)：如果连接尚未关闭且仍然有效，则返回true 
  void setAutoCommit(boolean autoCommit)：将此连接的自动提交模式设置为给定状态 
  void commit()：使自上次提交/回滚以来所做的所有更改成为永久更改，并释放此Connection对象当前持有的所有数据库锁 
  void rollback()：撤消当前事务中所做的所有更改，并释放此Connection对象当前持有的所有数据库锁 
  void setNetworkTimeout(Executor executor, int milliseconds)：设置从连接创建的连接或对象的最长时间，将等待数据库回复任何一个请求 
  void setTransactionIsolation(int level)：尝试将此Connection对象的事务隔离级别更改为给定的对象 
```
ConnectionMain.java：
```Java
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
// java.sql.Connection：数据库连接
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
          "11.Connection.createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)：创建一个Statement对象，该对象将生成ResultSet具有给定类型，并发性和可保存性的对象",
          "resultSetType=java.sql.ResultSet.TYPE_FORWARD_ONLY=" + ResultSet.TYPE_FORWARD_ONLY
              + ", resultSetConcurrency=java.sql.ResultSet.CONCUR_READ_ONLY=" + ResultSet.CONCUR_READ_ONLY
              + ", resultSetHoldability=java.sql.ResultSet.HOLD_CURSORS_OVER_COMMIT="
              + ResultSet.HOLD_CURSORS_OVER_COMMIT + ", Statement=" + statement3);

      PrintUtil.two("12.Connection.createStruct(String typeName, Object[] attributes)：用于创建java.sql.Struct对象的工厂方法",
          "Mysql没有具体实现改方法，会抛出java.sql.SQLFeatureNotSupportedException异常");

      boolean autoCommit = connection.getAutoCommit();
      PrintUtil.two("13.Connection.getAutoCommit()：获取此Connection 对象的当前是否是自动提交模式", "AutoCommit=" + autoCommit);

      autoCommit = false;
      connection.setAutoCommit(autoCommit);
      PrintUtil.two("14.Connection.setAutoCommit(boolean autoCommit)：将此连接的自动提交模式设置为给定状态",
          "autoCommit=" + autoCommit);

      String catalog = connection.getCatalog();
      PrintUtil.two("15.Connection.getCatalog()：获取此Connection对象的当前目录名称", "Catalog=" + catalog);

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
      PrintUtil.two("20.Connection.getHoldability()：获取ResultSet使用此Connection对象创建的对象的当前可保存性",
          "Holdability=固定值java.sql.ResultSet.CLOSE_CURSORS_AT_COMMIT=" + holdability);

      PrintUtil.two("21.Connection.setHoldability(int holdability)：将ResultSet使用此Connection对象创建的对象的默认可保存性更改为给定的可保持性",
          "com.mysql.cj.jdbc.ConnectionImpl空实现该方法，即该常量指示提交当前事务时，具有此可保存性的打开的 ResultSet 对象将被关闭");

      DatabaseMetaData databaseMetaData = connection.getMetaData();
      PrintUtil.two("22.Connection.getMetaData()：获取DatabaseMetaData包含有关此Connection对象表示连接的数据库的元数据的对象",
          "DatabaseMetaData=" + databaseMetaData);

      int networkTimeout = connection.getNetworkTimeout();
      PrintUtil.two("23.Connection.getNetworkTimeout()：获取驱动程序等待数据库请求完成的毫秒数", "NetworkTimeout=" + networkTimeout);

      PrintUtil.two("24.Connection.setNetworkTimeout(Executor executor, int milliseconds)",
          "设置从连接创建的连接或对象的最长时间，将等待数据库回复任何一个请求");

      String schema = connection.getSchema();
      PrintUtil.two("25.Connection.getSchema()：获取此Connection对象的当前架构名称", "Schema=" + schema);

      int transactionIsolation = connection.getTransactionIsolation();
      PrintUtil.two("26.Connection.getTransactionIsolation()：获取此Connection对象的当前事务隔离级别",
          "transactionIsolation=java.sql.Connection.TRANSACTION_REPEATABLE_READ=" + transactionIsolation);

      connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
      PrintUtil.two("27.Connection.setTransactionIsolation(int level)：尝试将此Connection对象的事务隔离级别更改为给定的对象",
          "level=java.sql.Connection.TRANSACTION_READ_COMMITTED=" + Connection.TRANSACTION_READ_COMMITTED);

      Map<String, Class<?>> typeMap = connection.getTypeMap();
      PrintUtil.two("28.Connection.getTypeMap()：获取Map与此Connection对象关联的对象",
          "typeMap.keySet()=" + typeMap.keySet());

      connection.setTypeMap(typeMap);
      PrintUtil.two("29.Connection.setTypeMap(Map<String,Class<?>> map)：将给定TypeMap对象安装为此Connection对象的类型映射",
          "typeMap=" + typeMap);

      SQLWarning sqlWarning = connection.getWarnings();
      PrintUtil.two("30.Connection.getWarnings()：获取此Connection对象上的调用报告的第一个警告",
          "java.sql.SQLWarning=" + sqlWarning);

      boolean isClosed = connection.isClosed();
      PrintUtil.two("31.Connection.isClosed()：获取此Connection对象是否已关闭", "isClosed=" + isClosed);

      boolean isReadOnly = connection.isReadOnly();
      PrintUtil.two("32.Connection.isReadOnly()：获取此Connection对象是否处于只读模式", "isReadOnly=" + isReadOnly);

      connection.setReadOnly(true);
      PrintUtil.two("33.Connection.setReadOnly()：将此连接置于只读模式，作为驱动程序的提示以启用数据库优化", "isReadOnly=true");

      String sql = "select * from book";
      String nativeSQL = connection.nativeSQL(sql);
      PrintUtil.two("34.Connection.nativeSQL(String sql)：将给定的SQL语句转换为系统的本机SQL语法",
          "sql=" + sql + ", nativeSQL=" + nativeSQL);

      CallableStatement callableStatement = connection.prepareCall("test");
      PrintUtil.two("35.Connection.prepareCall(String sql)：创建一个CallableStatement用于调用数据库存储过程的对象",
          "sql=test, CallableStatement=" + callableStatement);
      callableStatement.close();

      CallableStatement callableStatement2 = connection.prepareCall("test", ResultSet.TYPE_FORWARD_ONLY,
          ResultSet.CONCUR_READ_ONLY);
      PrintUtil.two(
          "36.Connection.prepareCall(String sql, int resultSetType, int resultSetConcurrency)：创建一个CallableStatement将生成 ResultSet具有给定类型和并发性的对象的对象",
          "sql=test" + ", resultSetType=java.sql.ResultSet.TYPE_FORWARD_ONLY=" + ResultSet.TYPE_FORWARD_ONLY
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
          "39.Connection.prepareStatement(String sql, int autoGeneratedKeys)：创建一个PreparedStatement能够获取自动生成的密钥的默认对象",
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
          "43.Connection.prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability)：创建一个PreparedStatement对象，该对象将生成ResultSet具有给定类型，并发性和可保存性的对象",
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
```

### <a id="a_statement">六、java.sql.Statement：执行静态SQL：</a> <a href="#a_connection">last</a> <a href="#a_execute">next</a>
[java.sql.Statement](https://docs.oracle.com/javase/8/docs/api/java/sql/Statement.html)  
一、描述：
```
  用于执行静态SQL语句并返回它所生成结果的对象。 

  在默认情况下，同一时间每个 Statement 对象在只能打开一个ResultSet对象。
因此，如果读取一个ResultSet对象与读取另一个交叉，则这两个对象必须是由不同的 Statement 对象生成的。
如果存在某个语句的打开的当前ResultSet对象，则 Statement 接口中的所有执行方法都会隐式关闭它。 
```
二、字段说明 ：  

|数据类型|字段|说明|
|---|---|---|
|static int|CLOSE_ALL_RESULTS=3|该常量指示调用getMoreResults时应该关闭以前一直打开的所有ResultSet对象|
|static int|CLOSE_CURRENT_RESULT=1|该常量指示调用getMoreResults时应该关闭当前ResultSet对象|
|static int|EXECUTE_FAILED=-3|该常量指示在执行批量语句时发生错误|
|static int|KEEP_CURRENT_RESULT=2|该常量指示调用getMoreResults时应该关闭当前ResultSet对象|
|static int|NO_GENERATED_KEYS=2|该常量指示生成的键应该不可用于获取|
|static int|RETURN_GENERATED_KEYS=1|该常量指示生成的键应该可用于获取|
|static int|SUCCESS_NO_INFO=-2|该常量指示批量语句执行成功但不存在受影响的可用行数计数|

三、方法说明 ：  

|返回类型|方法|说明|
|---|---|---|
|void|addBatch(String sql)|将给定的SQL命令添加到此Statement对象的当前命令列表中|
|void|cancel()|Statement如果DBMS和驱动程序都支持中止SQL语句，则取消此对象|
|void|clearBatch()|清空此Statement对象的当前SQL命令列表|
|void|clearWarnings()|清除此Statement对象上报告的所有警告|
|void|close()|立即释放此Statement对象的数据库和JDBC资源，而不是等待它自动关闭时发生|
|void|closeOnCompletion()|指定Statement在关闭所有相关结果集时将关闭它|
|boolean|execute(String sql)|执行给定的SQL语句，该语句可能返回多个结果|
|boolean|execute(String sql, int autoGeneratedKeys)|执行给定的SQL语句（该语句可能返回多个结果），并通知驱动程序所有自动生成的键都应该可用于获取|
|boolean|execute(String sql, int[] columnIndexes)|执行给定的SQL语句（该语句可能返回多个结果），并通知驱动程序在给定数组中指示的自动生成的键应该可用于获取。此数组包含目标表中的列的索引，而该目标表包含应该使其可用的自动生成的键|
|boolean|execute(String sql, String[] columnNames)|执行给定的SQL语句（该语句可能返回多个结果），并通知驱动程序在给定数组中指示的自动生成的键应该可用于获取。此数组包含目标表中的列的名称，而该目标表包含应该使其可用的自动生成的键|
|int[]|executeBatch()|将一批命令提交到数据库以供执行，如果所有命令成功执行，则返回一组更新计数|
|default long[]|executeLargeBatch()|将一批命令提交到数据库以供执行，如果所有命令成功执行，则返回一组更新计数|
|default long|executeLargeUpdate(String sql)|执行给定的SQL语句，它可以是一个INSERT， UPDATE或者DELETE语句，或者不返回任何内容的SQL语句(如SQL DDL语句)，当返回的行数可能超过Integer.MAX_VALUE时，应使用此方法|
|default long|executeLargeUpdate(String sql, int autoGeneratedKeys)|执行给定的SQL语句，并通知驱动程序所有自动生成的键都应该可用于获取，当返回的行数可能超过Integer.MAX_VALUE时，应使用此方法|
|default long|executeLargeUpdate(String sql, int[] columnIndexes)|执行给定的SQL语句并向驱动程序发出信号，并通知驱动程序在给定数组中指示的自动生成的键应该可用于获取。此数组包含目标表中的列的索引，而该目标表包含应该使其可用的自动生成的键，当返回的行数可能超过Integer.MAX_VALUE时，应使用此方法|
|default long|executeLargeUpdate(String sql, String[] columnNames)|执行给定的SQL语句，通知驱动程序在给定数组中指示的自动生成的键应该可用于获取。此数组包含目标表中的列的名称，而该目标表包含应该使其可用的自动生成的键，当返回的行数可能超过Integer.MAX_VALUE时，应使用此方法|
|ResultSet|executeQuery(String sql)|执行给定的SQL语句，该语句返回单个 ResultSet对象|
|int|executeUpdate(String sql)|执行给定的SQL语句，它可以是一个INSERT， UPDATE或者DELETE语句，或者不返回任何内容的SQL语句(如SQL DDL语句)|
|int|executeUpdate(String sql, int autoGeneratedKeys)|执行给定的SQL语句，并使用给定标志向驱动程序发出信号，告知该Statement对象生成的自动生成的密钥是否应该可用于获取|
|int|executeUpdate(String sql, int[] columnIndexes)|执行给定的SQL语句并向驱动程序发出信号，指示给定数组中指示的自动生成的键应该可用于获取|
|int|executeUpdate(String sql, String[] columnNames)|执行给定的SQL语句并向驱动程序发出信号，指示给定数组中指示的自动生成的键应该可用于获取|
|Connection|getConnection()|获取Connection生成此Statement对象的对象|
|int|getFetchDirection()|获取从数据库表中获取行的方向，该方向是从此Statement对象生成的结果集的缺省值|
|int|getFetchSize()|获取结果集行的数量，该行是ResultSet从此对象生成的对象的默认提取大小Statement|
|ResultSet|getGeneratedKeys()|获取由于执行此Statement对象而创建的任何自动生成的密钥|
|default long|getLargeMaxRows()|获取此ResultSet对象生成的 Statement对象可以包含的最大行数|
|default long|getLargeUpdateCount()|获取当前结果作为更新计数; 如果结果是ResultSet对象或没有更多结果，则返回-1|
|int|getMaxFieldSize()|获取此ResultSet 对象生成的Statement对象中的字符和二进制列值可以返回的最大字节数|
|int|getMaxRows()|获取此ResultSet对象生成的 Statement对象可以包含的最大行数|
|boolean|getMoreResults()|移动到此Statement对象的下一个结果，true如果它是ResultSet对象则返回 ，并隐式关闭ResultSet 使用该方法获得的任何当前对象getResultSet|
|boolean|getMoreResults(int current)|移动到此Statement对象的下一个结果，ResultSet根据给定标志指定的指令处理任何当前对象，并true在下一个结果是ResultSet对象时返回|
|int|getQueryTimeout()|获取驱动程序等待Statement对象执行的秒数|
|ResultSet|getResultSet()|将当前结果获取为ResultSet对象|
|int|getResultSetConcurrency()|获取此ResultSet对象生成的Statement对象的结果集并发性|
|int|getResultSetHoldability()|获取此ResultSet对象生成的Statement对象的结果集可保存性|
|int|getResultSetType()|获取此ResultSet对象生成的Statement对象的结果集类型|
|int|getUpdateCount()|获取当前结果作为更新计数; 如果结果是ResultSet对象或没有更多结果，则返回-1|
|SQLWarning|getWarnings()|获取此Statement对象上的调用报告的第一个警告|
|boolean|isClosed()|获取此Statement对象是否已关闭|
|boolean|isCloseOnCompletion()|返回一个值，该值指示Statement在关闭所有相关结果集时是否将关闭此值|
|boolean|isPoolable()|返回一个值，指示是否Statement可以使用poolable|
|void|setCursorName(String name)|将SQL游标名称设置为给定的String，后续Statement对象 execute方法将使用该名称|
|void|setEscapeProcessing(boolean enable)|打开或关闭转义处理|
|void|setFetchDirection(int direction)|为驱动程序提供有关ResultSet 在使用此Statement对象创建的对象中处理行的方向的提示|
|void|setFetchSize(int rows)|为JDBC驱动程序提供有关当ResultSet由此生成的对象需要更多行时应从数据库获取的行数的提示Statement|
|default void|setLargeMaxRows(long max)|ResultSet将此Statement对象生成的任何对象可包含的最大行数限制设置为给定数字|
|void|setMaxFieldSize(int max)|设置此ResultSet对象生成的Statement对象中字符和二进制列值可返回的最大字节数限制|
|void|setMaxRows(int max)|ResultSet将此Statement对象生成的任何对象可包含的最大行数限制设置为 给定数字|
|void|setPoolable(boolean poolable)|请求将Statement池化或非池化|
|void|setQueryTimeout(int seconds)|将驱动程序等待Statement对象执行的秒数设置为给定的秒数|

StatementMain.java：
```Java
package com.mutisitc.statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import com.mutisitc.utils.CommonUtil;
import com.mutisitc.utils.JDBCUtil;
import com.mutisitc.utils.PrintUtil;
// java.sql.Statement：基本语句
public class StatementMain {
  public static void main(String[] args) {
    PrintUtil.one("java.sql.Statement：基本语句：");
    try {
      Statement statement = JDBCUtil.createStatement();

      String insertSQL = testInsertSQL(System.currentTimeMillis());
      statement.addBatch(insertSQL);
      PrintUtil.two("3.Statement.addBatch(String sql)：将给定的SQL命令添加到此Statement对象的当前命令列表中", "insertSQL=" + insertSQL);

      statement.cancel();
      PrintUtil.two("4.Statement.cancel()", "Statement如果DBMS和驱动程序都支持中止SQL语句，则取消此对象");

      statement.clearBatch();
      PrintUtil.two("5.Statement.clearBatch()", "清空此Statement对象的当前SQL命令列表");

      statement.clearWarnings();
      PrintUtil.two("6.Statement.clearWarnings()", "清除此Statement对象上报告的所有警告");

      boolean executeResult = statement.execute(insertSQL);
      PrintUtil.two("7.Statement.execute(String sql)：执行给定的SQL语句，该语句可能返回多个结果", "executeResult=" + executeResult + ",insertSQL=" + insertSQL);

      insertSQL = testInsertSQL(System.currentTimeMillis());
      boolean executeResult2 = statement.execute(insertSQL, Statement.RETURN_GENERATED_KEYS);
      PrintUtil.two("8.Statement.execute(String sql, int autoGeneratedKeys)：执行给定的SQL语句（该语句可能返回多个结果），并通知驱动程序所有自动生成的键都应该可用于获取",
          "autoGeneratedKeys=Statement.RETURN_GENERATED_KEYS=" + Statement.RETURN_GENERATED_KEYS + ", executeResult=" + executeResult2 + ",insertSQL=" + insertSQL);

      insertSQL = testInsertSQL(System.currentTimeMillis());
      boolean executeResult3 = statement.execute(insertSQL, new int[] { 0 });
      PrintUtil.two("9.Statement.execute(String sql, int[] columnIndexes)：执行给定的SQL语句（该语句可能返回多个结果），并通知驱动程序在给定数组中指示的自动生成的键应该可用于获取。此数组包含目标表中的列的索引，而该目标表包含应该使其可用的自动生成的键",
          "columnIndexes=new int[] {0}" + ", executeResult=" + executeResult3 + ",insertSQL=" + insertSQL);

      insertSQL = testInsertSQL(System.currentTimeMillis());
      boolean executeResult4 = statement.execute(insertSQL, new String[] { "bookId" });
      PrintUtil.two("10.Statement.execute(String sql, String[] columnNames)：执行给定的SQL语句（该语句可能返回多个结果），并通知驱动程序在给定数组中指示的自动生成的键应该可用于获取。此数组包含目标表中的列的名称，而该目标表包含应该使其可用的自动生成的键",
          "columnIndexes=new String[] { \"bookId\" }" + ", executeResult=" + executeResult4 + ",insertSQL=" + insertSQL);

      statement.addBatch(testInsertSQL(System.currentTimeMillis() + 1));
      statement.addBatch(testInsertSQL(System.currentTimeMillis() + 2));
      int[] executeBatchResult = statement.executeBatch();
      PrintUtil.two("11.Statement.executeBatch()：将一批命令提交到数据库以供执行，如果所有命令成功执行，则返回一组更新计数", "executeBatchResult=" + CommonUtil.toString(executeBatchResult));

      insertSQL = testInsertSQL(System.currentTimeMillis());
      long executeLargeUpdate = statement.executeLargeUpdate(insertSQL);
      PrintUtil.two("12.Statement.executeLargeUpdate(String sql)：执行给定的SQL语句，它可以是一个INSERT， UPDATE或者DELETE语句，或者不返回任何内容的SQL语句(如SQL DDL语句)",
          "executeLargeUpdate=" + executeLargeUpdate + ",insertSQL=" + insertSQL);

      insertSQL = testInsertSQL(System.currentTimeMillis());
      long executeLargeUpdate2 = statement.executeLargeUpdate(insertSQL, Statement.RETURN_GENERATED_KEYS);
      PrintUtil.two("13.Statement.executeLargeUpdate(String sql, int autoGeneratedKeys)：执行给定的SQL语句，并通知驱动程序所有自动生成的键都应该可用于获取",
          "autoGeneratedKeys=Statement.RETURN_GENERATED_KEYS=" + Statement.RETURN_GENERATED_KEYS + ", executeLargeUpdate=" + executeLargeUpdate2 + ",insertSQL=" + insertSQL);

      insertSQL = testInsertSQL(System.currentTimeMillis());
      long executeLargeUpdate3 = statement.executeLargeUpdate(insertSQL, new int[] { 0 });
      PrintUtil.two("14.Statement.executeLargeUpdate(String sql, int[] columnIndexes)：执行给定的SQL语句，并通知驱动程序在给定数组中指示的自动生成的键应该可用于获取。此数组包含目标表中的列的索引，而该目标表包含应该使其可用的自动生成的键",
          "columnIndexes=new int[] {0}" + ", executeLargeUpdate=" + executeLargeUpdate3 + ",insertSQL=" + insertSQL);

      insertSQL = testInsertSQL(System.currentTimeMillis());
      long executeLargeUpdate4 = statement.executeLargeUpdate(insertSQL, new String[] { "bookId" });
      PrintUtil.two("15.Statement.executeLargeUpdate(String sql, String[] columnNames)：执行给定的SQL语句，并通知驱动程序在给定数组中指示的自动生成的键应该可用于获取。此数组包含目标表中的列的名称，而该目标表包含应该使其可用的自动生成的键",
          "columnIndexes=new String[] { \"bookId\" }" + ", executeLargeUpdate=" + executeLargeUpdate4 + ",insertSQL=" + insertSQL);

      String querySQL = "SELECT bookId, title, author, remark, createrTime FROM book";
      ResultSet resultSet = statement.executeQuery(querySQL);
      PrintUtil.two("16.Statement.executeQuery(String sql)：执行给定的SQL语句，该语句返回单个 ResultSet对象", "ResultSet=" + resultSet + ", querySQL=" + querySQL);

      insertSQL = testInsertSQL(System.currentTimeMillis());
      long executeUpdate = statement.executeUpdate(insertSQL);
      PrintUtil.two("17.Statement.executeUpdate(String sql)：执行给定的SQL语句，它可以是一个INSERT， UPDATE或者DELETE语句，或者不返回任何内容的SQL语句(如SQL DDL语句)",
          "executeUpdate=" + executeUpdate + ",insertSQL=" + insertSQL);

      insertSQL = testInsertSQL(System.currentTimeMillis());
      long executeUpdate2 = statement.executeUpdate(insertSQL, Statement.RETURN_GENERATED_KEYS);
      PrintUtil.two("18.Statement.executeUpdate(String sql, int autoGeneratedKeys)：执行给定的SQL语句，并通知驱动程序所有自动生成的键都应该可用于获取",
          "autoGeneratedKeys=Statement.RETURN_GENERATED_KEYS=" + Statement.RETURN_GENERATED_KEYS
              + ", executeUpdate=" + executeUpdate2 + ",insertSQL=" + insertSQL);

      insertSQL = testInsertSQL(System.currentTimeMillis());
      long executeUpdate3 = statement.executeUpdate(insertSQL, new int[] { 0 });
      PrintUtil.two("19.Statement.executeUpdate(String sql, int[] columnIndexes)：执行给定的SQL语句，并通知驱动程序在给定数组中指示的自动生成的键应该可用于获取。此数组包含目标表中的列的索引，而该目标表包含应该使其可用的自动生成的键",
          "columnIndexes=new int[] {0}" + ", executeUpdate=" + executeUpdate3 + ",insertSQL=" + insertSQL);

      insertSQL = testInsertSQL(System.currentTimeMillis());
      long executeUpdate4 = statement.executeUpdate(insertSQL, new String[] { "bookId" });
      PrintUtil.two("20.Statement.executeUpdate(String sql, String[] columnNames)：执行给定的SQL语句，并通知驱动程序在给定数组中指示的自动生成的键应该可用于获取。此数组包含目标表中的列的名称，而该目标表包含应该使其可用的自动生成的键",
          "columnIndexes=new String[] { \"bookId\" }" + ", executeUpdate=" + executeUpdate4 + ",insertSQL=" + insertSQL);

      Connection connection = statement.getConnection();
      PrintUtil.two("21.Statement.getConnection()：获取Connection生成此Statement对象的对象", "Connection=" + connection);

      int fetchDirection = statement.getFetchDirection();
      PrintUtil.two("22.Statement.getFetchDirection()：获取从数据库表中获取行的方向，该方向是从此Statement对象生成的结果集的缺省值", "fetchDirection=" + fetchDirection);

      int fetchSize = statement.getFetchSize();
      PrintUtil.two("23.Statement.getFetchDirection()：获取结果集行的数量，该行是ResultSet从此对象生成的对象的默认提取大小Statement", "fetchSize=" + fetchSize);

      ResultSet generatedKeys = statement.getGeneratedKeys();
      PrintUtil.two("24.Statement.getGeneratedKeys()：获取由于执行此Statement对象而创建的任何自动生成的密钥", "generatedKeys=" + generatedKeys);

      long largeMaxRows = statement.getLargeMaxRows();
      PrintUtil.two("25.Statement.getLargeMaxRows()：获取此ResultSet对象生成的 Statement对象可以包含的最大行数", "largeMaxRows=" + largeMaxRows);

      long largeUpdateCount = statement.getLargeUpdateCount();
      PrintUtil.two("26.Statement.getLargeUpdateCount()：获取当前结果作为更新计数; 如果结果是ResultSet对象或没有更多结果，则返回-1", "largeUpdateCount=" + largeUpdateCount);

      long maxFieldSize = statement.getMaxFieldSize();
      PrintUtil.two("27.Statement.getMaxFieldSize()：获取此ResultSet 对象生成的Statement对象中的字符和二进制列值可以返回的最大字节数1", "maxFieldSize=" + maxFieldSize);

      statement.setMaxFieldSize(4);
      PrintUtil.two("28.Statement.setMaxFieldSize(int max)：设置此ResultSet 对象生成的Statement对象中字符和二进制列值可返回的最大字节数限制", "maxFieldSize=3");

      long maxRows = statement.getMaxRows();
      PrintUtil.two("29.Statement.getMaxRows()：获取此ResultSet对象生成的 Statement对象可以包含的最大行数", "maxRows=" + maxRows);

      statement.setMaxRows(3);
      PrintUtil.two("30.Statement.setMaxRows(int max)：设置此ResultSet 对象生成的Statement对象中字符和二进制列值可返回的最大字节数限制", "maxRows=3");

      boolean moreResults = statement.getMoreResults();
      PrintUtil.two("31.Statement.getMoreResults()：移动到此Statement对象的下一个结果，true如果它是ResultSet对象则返回 ，并隐式关闭ResultSet 使用该方法获得的任何当前对象getResultSet", "moreResults=" + moreResults);

      boolean moreResults2 = statement.getMoreResults(0);
      PrintUtil.two("32.Statement.getMoreResults(int current)：移动到此Statement对象的下一个结果，ResultSet根据给定标志指定的指令处理任何当前对象，并true在下一个结果是ResultSet对象时返回", "moreResults=" + moreResults2);

      int queryTimeout = statement.getQueryTimeout();
      PrintUtil.two("33.Statement.getQueryTimeout()：获取驱动程序等待Statement对象执行的秒数", "queryTimeout=" + queryTimeout);

      queryTimeout = 10;
      statement.setQueryTimeout(queryTimeout);
      PrintUtil.two("34.Statement.setQueryTimeout(int seconds)：将驱动程序等待 Statement对象执行的秒数设置为给定的秒数", "queryTimeout=" + queryTimeout);

      ResultSet resultSet2 = statement.getResultSet();
      PrintUtil.two("35.Statement.getQueryTimeout()：获取驱动程序等待Statement对象执行的秒数", "ResultSet=" + resultSet2);

      int resultSetConcurrency = statement.getResultSetConcurrency();
      PrintUtil.two("36.Statement.getResultSetConcurrency()：获取此ResultSet对象生成的Statement对象的结果集并发性", "resultSetConcurrency=" + resultSetConcurrency);

      int resultSetHoldability = statement.getResultSetHoldability();
      PrintUtil.two("37.Statement.getResultSetHoldability()：获取此ResultSet对象生成的Statement对象的结果集可保存性", "resultSetHoldability=" + resultSetHoldability);

      int resultSetType = statement.getResultSetType();
      PrintUtil.two("38.Statement.getResultSetType()：获取此ResultSet对象生成的Statement对象的结果集类型", "resultSetType=" + resultSetType);

      int updateCount = statement.getUpdateCount();
      PrintUtil.two("39.Statement.getUpdateCount()：获取当前结果作为更新计数; 如果结果是ResultSet对象或没有更多结果，则返回-1", "updateCount=" + updateCount);

      SQLWarning sqlWarning = statement.getWarnings();
      PrintUtil.two("40.Statement.getWarnings()：获取此Statement对象上的调用报告的第一个警告", "SQLWarning=" + sqlWarning);

      boolean isClosed = statement.isClosed();
      PrintUtil.two("41.Statement.isClosed()：获取此Statement对象是否已关闭", "isClosed=" + isClosed);

      boolean isCloseOnCompletion = statement.isCloseOnCompletion();
      PrintUtil.two("42.Statement.isCloseOnCompletion()：返回一个值，该值指示Statement在关闭所有相关结果集时是否将关闭此值", "isCloseOnCompletion=" + isCloseOnCompletion);

      boolean isPoolable = statement.isPoolable();
      PrintUtil.two("43.Statement.isPoolable()：返回一个值，指示是否Statement 可以使用poolable", "isPoolable=" + isPoolable);

      isPoolable = false;
      statement.setPoolable(isPoolable);
      PrintUtil.two("44.Statement.setPoolable(boolean poolable)：请求将 Statement 池化或非池化", "poolable=false");

      statement.setCursorName("test");
      PrintUtil.two("45.Statement.setCursorName(String name)：将SQL游标名称设置为给定的String，后续Statement对象 execute方法将使用该名称", "cursorName=test");

      statement.setEscapeProcessing(true);
      PrintUtil.two("46.Statement.setEscapeProcessing(boolean enable)：打开或关闭转义处理", "enable=true");
      
      statement.setFetchDirection(ResultSet.FETCH_FORWARD);
      PrintUtil.two("47.Statement.setFetchDirection(int direction)：为驱动程序提供有关ResultSet在使用此Statement对象创建的对象中处理行的方向的提示", "direction=ResultSet.FETCH_FORWARD="+ResultSet.FETCH_FORWARD);
      
      statement.setFetchSize(3);
      PrintUtil.two("48.Statement.setFetchSize(int rows)：为JDBC驱动程序提供有关当ResultSet由此生成的对象需要更多行时应从数据库获取的行数的提示Statement", "rows=3");
      
      statement.setLargeMaxRows(3);
      PrintUtil.two("49.Statement.setLargeMaxRows(int max)：ResultSet将此Statement对象生成的任何对象可包含的最大行数限制设置为给定数字", "largeMaxRows=3");

      statement.closeOnCompletion();
      PrintUtil.two("50.Statement.closeOnCompletion()：", "指定Statement在关闭所有相关结果集时将关闭它");
      
      statement.close();
      PrintUtil.two("51.Statement.close()：", "立即释放此Statement对象的数据库和JDBC资源，而不是等待它自动关闭时发生");
    } catch (SQLException e) {
      PrintUtil.err("演示 java.sql.Statement：基本语句，打印异常堆栈信息：");
      e.printStackTrace();
    }
  }
  // 测试insert sql语句
  private static String testInsertSQL(Long bookId) {
    return "INSERT INTO `book` (`bookId`, `title`, `author`, `remark`, `createrTime`) VALUES (" + bookId
        + ", 'test by jdbc', 'test author', 'test remark', '" + CommonUtil.getCurrentTime() + "')";
  }
}
```

### <a id="a_execute">七、使用Statement.execute方法实现数据操作：</a> <a href="#a_statement">last</a> <a href="#a_executeLargeUpdate">next</a>
```
一、使用java.sql.Statement.execute()方法实现数据的操作方法，主要实现新增、删除、更新功能：
  1、Statement.execute(String sql)
  2、Statement.execute(String sql, int autoGeneratedKeys)
  3、Statement.execute(String sql, int[] columnIndexes)
  4、Statement.execute(String sql, String[] columnNames)

二、根据mysql-connector-java-8.0.11.jar源码分析：
2.1、参数autoGeneratedKeys、columnIndexes、columnNames，都是执行insert操作时，都是用并通知驱动程序在给定数组中指示的自动生成的键应该可用于获取，
后续获取自动生成的键步骤基本一致，此外并没有其他的不同：
com.mysql.cj.jdbc.StatementImpl：具体方法代码：

public boolean execute(String sql, int returnGeneratedKeys) throws SQLException {
   return executeInternal(sql, returnGeneratedKeys == java.sql.Statement.RETURN_GENERATED_KEYS);
}

public boolean execute(String sql, int[] generatedKeyIndices) throws SQLException {
   return executeInternal(sql, generatedKeyIndices != null && generatedKeyIndices.length > 0);
}

public boolean execute(String sql, String[] generatedKeyNames) throws SQLException {
   return executeInternal(sql, generatedKeyNames != null && generatedKeyNames.length > 0);
}

2.2、参数autoGeneratedKeys、columnIndexes、columnNames在非执行insert语句时，将会被忽略：
具体源码分析：
com.mysql.cj.jdbc.StatementImpl.executeInternal(String sql, boolean returnGeneratedKeys)：方法具体代码段：

this.lastQueryIsOnDupKeyUpdate = returnGeneratedKeys && firstNonWsChar == 'I' && containsOnDuplicateKeyInString(sql);
```
ExecuteMain.java：
```Java
package com.mutisitc.statement.execute;
import java.sql.SQLException;
import java.sql.Statement;
import com.mutisitc.utils.JDBCUtil;
import com.mutisitc.utils.PrintUtil;
// 使用Statement.execute接口实现数据新增
public class ExecuteMain {
  public static void main(String[] args) {
    PrintUtil.one("使用Statement.execute接口实现数据操作：");
    try {
      Statement statement = JDBCUtil.createStatement();
      
      // 使用Statement.execute接口实现数据新增
      ExecuteByInsert.mainByInsert(statement);
      // 使用Statement.execute接口实现数据删除
      ExecuteByDelete.mainByDelete(statement);
      // 使用Statement.execute接口实现数据更新
      ExecuteByUpdate.mainByUpdate(statement);
      
      JDBCUtil.close(statement);
    } catch (SQLException e) {
      PrintUtil.err("使用Statement.execute接口实现，打印异常堆栈信息：");
      e.printStackTrace();
    }
  }
}
```
ExecuteByInsert.java：
```Java
package com.mutisitc.statement.execute;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mutisitc.utils.CommonUtil;
import com.mutisitc.utils.PrintUtil;
// 使用Statement.execute接口实现数据新增
public class ExecuteByInsert {
  // 使用Statement.execute接口实现数据新增
  public static void mainByInsert(Statement statement) throws SQLException {
    mianByExecute(statement);
    mianByAutoGeneratedKeys(statement);
    mainByColumnIndexes(statement);
    mainByColumnNames(statement);
    
    PrintUtil.two("\n五、根据mysql-connector-java-8.0.11.jar源码分析",
        "参数autoGeneratedKeys、columnIndexes、columnNames，都是执行insert操作时，用于并通知驱动程序在给定数组中指示的自动生成的键应该可用于获取，后续获取自动生成的键步骤基本一致，此外并没有其他的不同：\n"
        + "com.mysql.cj.jdbc.StatementImpl：方法具体代码：\n"
        + "public boolean execute(String sql, int returnGeneratedKeys) throws SQLException {\r\n"
        + "   return executeInternal(sql, returnGeneratedKeys == java.sql.Statement.RETURN_GENERATED_KEYS);\r\n"
        + "}\r\n"
        + "public boolean execute(String sql, int[] generatedKeyIndices) throws SQLException {\r\n"
        + "   return executeInternal(sql, generatedKeyIndices != null && generatedKeyIndices.length > 0);\r\n"
        + "}\r\npublic boolean execute(String sql, String[] generatedKeyNames) throws SQLException {\r\n"
        + "   return executeInternal(sql, generatedKeyNames != null && generatedKeyNames.length > 0);\r\n"
        + "}");
  }
  // 使用 Statement.execute(String sql)：数据新增
  private static void mianByExecute(Statement statement) throws SQLException {
    PrintUtil.one("一、使用 Statement.execute(String sql)：数据新增：");
    
    String insertSQL = testSQL(System.currentTimeMillis());
    PrintUtil.two("3.数据新增SQL语句：", insertSQL);

    boolean executeResult = statement.execute(insertSQL);
    PrintUtil.two("4.Statement.execute(String sql)：数据新增执行结果", "executeResult=" + executeResult);
    PrintUtil.three("4.1：具体描述:", "执行给定的 SQL 语句，该语句可能返回多个结果。在某些（不常见）情形下，单个 SQL 语句可能返回多个结果集合和/或更新计数。"
        + "  \n 这一点通常可以忽略，除非正在 执行已知可能返回多个结果的存储过程或者动态执行未知 SQL 字符串。  execute 方法执行 SQL 语句并指示第一个结果的形式。"
        + "  \n 然后，必须使用方法 getResultSet 或 getUpdateCount 来获取结果，使用 getMoreResults 来移动后续结果");
    PrintUtil.three("4.2：方法返回结果：", "如果第一个结果为 ResultSet 对象，则返回 true；如果其为更新计数或者不存在任何结果，则返回 false");

    PrintUtil.two("5.使用Statement.getResultSet()获取执行SQL后的结果", "java.sql.ResultSet=" + statement.getResultSet());
    PrintUtil.two("6.使用Statement.getUpdateCount()获取执行SQL后的结果", "updateCount=" + statement.getUpdateCount());
  }
  // Statement.execute(String sql, int autoGeneratedKeys)：数据新增
  private static void mianByAutoGeneratedKeys(Statement statement) throws SQLException {
    PrintUtil.one("二、使用  Statement.execute(String sql, int autoGeneratedKeys)：数据新增：");
    
    String insertSQL = testSQL(null);
    PrintUtil.two("3.数据新增SQL语句：", insertSQL);

    boolean executeResult = statement.execute(insertSQL, Statement.RETURN_GENERATED_KEYS);
    PrintUtil.two("4.Statement.execute(String sql, int autoGeneratedKeys)：数据新增执行结果", "executeResult=" + executeResult);
    PrintUtil.three("4.1：具体描述:", "执行给定的 SQL 语句（该语句可能返回多个结果），并通知驱动程序所有自动生成的键都应该可用于获取。\n"
        + "如果该 SQL 语句不是 INSERT 语句，或者不是可以返回自动生成键的 SQL 语句（这些语句的列表是特定于供应商的），则驱动程序将忽略此信号。\n" + 
        "在某些（不常见）情形下，单个 SQL 语句可能返回多个结果集合和/或更新计数。这一点通常可以忽略，除非正在 执行已知可能返回多个结果的存储过程或者 动态执行未知 SQL 字符串。 " + 
        "execute 方法执行 SQL 语句并指示第一个结果的形式。然后，必须使用方法 getResultSet 或 getUpdateCount 来获取结果，使用 getMoreResults 来移动后续结果");
    PrintUtil.three("4.2：参数描述：autoGeneratedKeys", "指示是否应该使用 getGeneratedKeys 方法使自动生成的键可用于获取的常量；"
        + "  \n 以下常量之一：Statement.RETURN_GENERATED_KEYS 或 Statement.NO_GENERATED_KEYS ");
    PrintUtil.three("4.3：方法返回结果：", "如果第一个结果为 ResultSet 对象，则返回 true；如果其为更新计数或者不存在任何结果，则返回 false");

    PrintUtil.two("5.使用Statement.getResultSet()获取执行SQL后的结果", "java.sql.ResultSet=" + statement.getResultSet());
    PrintUtil.two("6.使用Statement.getUpdateCount()获取执行SQL后的结果", "updateCount=" + statement.getUpdateCount());
    
    ResultSet generatedKeys = statement.getGeneratedKeys();
    PrintUtil.two("7.当指定参数autoGeneratedKeys时，且autoGeneratedKeys=Statement.RETURN_GENERATED_KEYS"+Statement.RETURN_GENERATED_KEYS, 
        "可以使用Statement.sgetGeneratedKeys()获取由于执行此 Statement对象而创建的所有自动生成的键(可能执行多个insert sql)");
    PrintUtil.three("7.1：当参数指autoGeneratedKeys定为Statement.NO_GENERATED_KEYS或不为Statement.RETURN_GENERATED_KEYS的其他值",
        "则不能调用Statement.sgetGeneratedKeys()，否者会抛出异常：\n"
        + "异常信息为：java.sql.SQLException: Generated keys not requested. You need to specify Statement.RETURN_GENERATED_KEYS to Statement.executeUpdate(), Statement.executeLargeUpdate() or Connection.prepareStatement().");
    PrintUtil.three("7.2：由于可能执行多个insert sql：", "所以使用ResultSet作为接受自动生成主键的集合，通过循环获取每个执行结果的主键，获取索引从1开始");
    PrintUtil.three("7.3：如果表主键没有设置为自增主键的情况下", "是要在insert 指定每条数据的主键ID，且不重复，故不需要通过Statement.sgetGeneratedKeys()再次获取主键，当然获取的主键的集合也insert 数据记录的集合主键一致");
    PrintUtil.three("7.4：通过ResultSet.getXXX(1)获取主键", "注意要和表主键的数据类型映射到java数据一致");
    if(generatedKeys != null) {
      while (generatedKeys.next()) {
        PrintUtil.three("7.5.通过循环获取到的执行结果后的主键ID", "bookId="+generatedKeys.getLong(1));
      }
    }
  }
  // 使用  Statement.execute(String sql, int[] columnIndexes)：数据新增
  private static void mainByColumnIndexes(Statement statement) throws SQLException {
    PrintUtil.one("三、使用  Statement.execute(String sql, int[] columnIndexes)：数据新增：");
    
    String insertSQL = testSQL(null);
    PrintUtil.two("3.数据新增SQL语句：", insertSQL);

    int[] columnIndexes = new int[] {0};
    boolean executeResult = statement.execute(insertSQL, columnIndexes);
    PrintUtil.two("4.Statement.execute(String sql, int[] columnIndexes)：数据新增执行结果", "executeResult=" + executeResult+", columnIndexes="+ CommonUtil.toString(columnIndexes));
    PrintUtil.three("4.1：具体描述:", "执行给定的 SQL 语句（该语句可能返回多个结果），并通知驱动程序所有自动生成的键都应该可用于获取。\n"
        + "如果该 SQL 语句不是 INSERT 语句，或者不是可以返回自动生成键的 SQL 语句（这些语句的列表是特定于供应商的），则驱动程序将忽略此信号。\n" + 
        "在某些（不常见）情形下，单个 SQL 语句可能返回多个结果集合和/或更新计数。这一点通常可以忽略，除非正在 执行已知可能返回多个结果的存储过程或者 动态执行未知 SQL 字符串。 " + 
        "execute 方法执行 SQL 语句并指示第一个结果的形式。然后，必须使用方法 getResultSet 或 getUpdateCount 来获取结果，使用 getMoreResults 来移动后续结果");
    PrintUtil.three("4.2：参数描述：columnIndexes", "通过调用方法 getGeneratedKeys 应该可用于获取的插入行中的列索引数组");
    PrintUtil.three("4.3：方法返回结果：", "如果第一个结果为 ResultSet 对象，则返回 true；如果其为更新计数或者不存在任何结果，则返回 false");

    PrintUtil.two("5.使用Statement.getResultSet()获取执行SQL后的结果", "java.sql.ResultSet=" + statement.getResultSet());
    PrintUtil.two("6.使用Statement.getUpdateCount()获取执行SQL后的结果", "updateCount=" + statement.getUpdateCount());
    
    ResultSet generatedKeys = statement.getGeneratedKeys();
    PrintUtil.two("7.当指定参数columnIndexes时，且columnIndexes不为null且空数组时，", 
        "可以使用Statement.sgetGeneratedKeys()获取由于执行此 Statement对象而创建的所有自动生成的键(可能执行多个insert sql)");
    PrintUtil.three("7.1：当参数指columnIndexes定为null或空数组", "则不能调用Statement.sgetGeneratedKeys()，否者会抛出异常：\n"
        + "异常信息为：java.sql.SQLException: Generated keys not requested. You need to specify Statement.RETURN_GENERATED_KEYS to Statement.executeUpdate(), Statement.executeLargeUpdate() or Connection.prepareStatement().");
    PrintUtil.three("7.2：由于可能执行多个insert sql：", "所以使用ResultSet作为接受自动生成主键的集合，通过循环获取每个执行结果的主键，获取索引从1开始");
    PrintUtil.three("7.3：如果表主键没有设置为自增主键的情况下", "是要在insert 指定每条数据的主键ID，且不重复，故不需要通过Statement.sgetGeneratedKeys()再次获取主键，当然获取的主键的集合也insert 数据记录的集合主键一致");
    PrintUtil.three("7.4：通过ResultSet.getXXX(1)获取主键", "注意要和表主键的数据类型映射到java数据一致");
    if(generatedKeys != null) {
      while (generatedKeys.next()) {
        PrintUtil.three("7.5.通过循环获取到的执行结果后的主键ID", "bookId="+generatedKeys.getLong(1));
      }
    }
  }
  // 使用Statement.execute(String sql, String[] columnNames)：数据新增
  private static void mainByColumnNames(Statement statement) throws SQLException {
    PrintUtil.one("四、使用  Statement.execute(String sql, String[] columnNames)：数据新增：");
    
    String insertSQL = testSQL(null);
    PrintUtil.two("3.数据新增SQL语句：", insertSQL);

    String[] columnNames = new String[] {"bookId"};
    boolean executeResult = statement.execute(insertSQL, columnNames);
    PrintUtil.two("4.Statement.execute(String sql, int[] columnIndexes)：数据新增执行结果", "executeResult=" + executeResult+", columnNames="+ CommonUtil.toString(columnNames));
    PrintUtil.three("4.1：具体描述:", "执行给定的 SQL 语句（该语句可能返回多个结果），并通知驱动程序所有自动生成的键都应该可用于获取。\n"
        + "如果该 SQL 语句不是 INSERT 语句，或者不是可以返回自动生成键的 SQL 语句（这些语句的列表是特定于供应商的），则驱动程序将忽略此信号。\n" + 
        "在某些（不常见）情形下，单个 SQL 语句可能返回多个结果集合和/或更新计数。这一点通常可以忽略，除非正在 执行已知可能返回多个结果的存储过程或者 动态执行未知 SQL 字符串。 " + 
        "execute 方法执行 SQL 语句并指示第一个结果的形式。然后，必须使用方法 getResultSet 或 getUpdateCount 来获取结果，使用 getMoreResults 来移动后续结果");
    PrintUtil.three("4.2：参数描述：columnNames", "通过调用方法 getGeneratedKeys 应该可用于获取的插入行中的列名称数组");
    PrintUtil.three("4.3：方法返回结果：", "如果第一个结果为 ResultSet 对象，则返回 true；如果其为更新计数或者不存在任何结果，则返回 false");

    PrintUtil.two("5.使用Statement.getResultSet()获取执行SQL后的结果", "java.sql.ResultSet=" + statement.getResultSet());
    PrintUtil.two("6.使用Statement.getUpdateCount()获取执行SQL后的结果", "updateCount=" + statement.getUpdateCount());
    
    ResultSet generatedKeys = statement.getGeneratedKeys();
    PrintUtil.two("7.当指定参数columnNames时，且columnNames不为null且空数组时，", 
        "可以使用Statement.sgetGeneratedKeys()获取由于执行此 Statement对象而创建的所有自动生成的键(可能执行多个insert sql)");
    PrintUtil.three("7.1：当参数指columnNames定为null或空数组", "则不能调用Statement.sgetGeneratedKeys()，否者会抛出异常：\n"
        + "异常信息为：java.sql.SQLException: Generated keys not requested. You need to specify Statement.RETURN_GENERATED_KEYS to Statement.executeUpdate(), Statement.executeLargeUpdate() or Connection.prepareStatement().");
    PrintUtil.three("7.2：由于可能执行多个insert sql：", "所以使用ResultSet作为接受自动生成主键的集合，通过循环获取每个执行结果的主键，获取索引从1开始");
    PrintUtil.three("7.3：如果表主键没有设置为自增主键的情况下", "是要在insert 指定每条数据的主键ID，且不重复，故不需要通过Statement.sgetGeneratedKeys()再次获取主键，当然获取的主键的集合也insert 数据记录的集合主键一致");
    PrintUtil.three("7.4：通过ResultSet.getXXX(1)获取主键", "注意要和表主键的数据类型映射到java数据一致");
    if(generatedKeys != null) {
      while (generatedKeys.next()) {
        PrintUtil.three("7.5.通过循环获取到的执行结果后的主键ID", "bookId="+generatedKeys.getLong(1));
      }
    }
  }
  // 测试新增SQL
  private static String testSQL(Long bookId) {
    return "INSERT INTO `book` (`bookId`, `title`, `author`, `remark`, `createrTime`) VALUES (" + bookId
        + "," + "'test by jdbc', 'test author', '使用Statement.execute接口实现数据新增', '" + CommonUtil.getCurrentTime() + "')";
  }
}
```
ExecuteByDelete.java：
```Java
package com.mutisitc.statement.execute;
import java.sql.SQLException;
import java.sql.Statement;
import com.mutisitc.utils.PrintUtil;
// 使用Statement.execute接口实现数据删除
public class ExecuteByDelete {
  // 使用Statement.execute接口实现数据删除
  public static void mainByDelete(Statement statement) throws SQLException {
    PrintUtil.one("一、使用 Statement.execute(String sql)：数据删除：");
    
    String deleteSQL = testSQL(1537930111701L);
    PrintUtil.two("3.数据删除SQL语句：", deleteSQL);

    boolean executeResult = statement.execute(deleteSQL);
    PrintUtil.two("4.Statement.execute(String sql)：数据删除执行结果", "executeResult=" + executeResult);
    PrintUtil.three("4.1：具体描述:", "执行给定的 SQL 语句，该语句可能返回多个结果。在某些（不常见）情形下，单个 SQL 语句可能返回多个结果集合和/或更新计数。"
        + "  \n 这一点通常可以忽略，除非正在 执行已知可能返回多个结果的存储过程或者动态执行未知 SQL 字符串。  execute 方法执行 SQL 语句并指示第一个结果的形式。"
        + "  \n 然后，必须使用方法 getResultSet 或 getUpdateCount 来获取结果，使用 getMoreResults 来移动后续结果");
    PrintUtil.three("4.2：方法返回结果：", "如果第一个结果为 ResultSet 对象，则返回 true；如果其为更新计数或者不存在任何结果，则返回 false");
    PrintUtil.three("4.3：方法参数说明：", "参数autoGeneratedKeys、columnIndexes、columnNames在非insert语句时，将会被忽略，具体源码分析：\n"
        + "com.mysql.cj.jdbc.StatementImpl.executeInternal(String sql, boolean returnGeneratedKeys)：方法具体代码段：\n"
        + "this.lastQueryIsOnDupKeyUpdate = returnGeneratedKeys && firstNonWsChar == 'I' && containsOnDuplicateKeyInString(sql);");
    
    PrintUtil.two("5.使用Statement.getResultSet()获取执行SQL后的结果", "java.sql.ResultSet=" + statement.getResultSet());
    PrintUtil.two("6.使用Statement.getUpdateCount()获取执行SQL后的结果", "updateCount=" + statement.getUpdateCount());
  }
  // 测试删除SQL
  private static String testSQL(Long bookId) {
    return "DELETE FROM book WHERE bookId = "+ bookId;
  }
}
```
ExecuteByUpdate.java：
```Java
package com.mutisitc.statement.execute;
import java.sql.SQLException;
import java.sql.Statement;
import com.mutisitc.utils.CommonUtil;
import com.mutisitc.utils.PrintUtil;
// 使用Statement.execute接口实现数据更新
public class ExecuteByUpdate {
  // 使用Statement.execute接口实现数据更新
  public static void mainByUpdate(Statement statement) throws SQLException {
    PrintUtil.one("一、使用 Statement.execute(String sql)：数据更新：");
    
    String updateSQL = testSQL(1537846522352L);
    PrintUtil.two("3.数据更新SQL语句：", updateSQL);

    boolean executeResult = statement.execute(updateSQL);
    PrintUtil.two("4.Statement.execute(String sql)：数据更新执行结果", "executeResult=" + executeResult);
    PrintUtil.three("4.1：具体描述:", "执行给定的 SQL 语句，该语句可能返回多个结果。在某些（不常见）情形下，单个 SQL 语句可能返回多个结果集合和/或更新计数。"
        + "  \n 这一点通常可以忽略，除非正在 执行已知可能返回多个结果的存储过程或者动态执行未知 SQL 字符串。  execute 方法执行 SQL 语句并指示第一个结果的形式。"
        + "  \n 然后，必须使用方法 getResultSet 或 getUpdateCount 来获取结果，使用 getMoreResults 来移动后续结果");
    PrintUtil.three("4.2：方法返回结果：", "如果第一个结果为 ResultSet 对象，则返回 true；如果其为更新计数或者不存在任何结果，则返回 false");
    PrintUtil.three("4.3：方法参数说明：", "参数autoGeneratedKeys、columnIndexes、columnNames在非insert语句时，将会被忽略，具体源码分析：\n"
        + "com.mysql.cj.jdbc.StatementImpl.executeInternal(String sql, boolean returnGeneratedKeys)：方法具体代码段：\n\n"
        + "this.lastQueryIsOnDupKeyUpdate = returnGeneratedKeys && firstNonWsChar == 'I' && containsOnDuplicateKeyInString(sql);");
    
    PrintUtil.two("5.使用Statement.getResultSet()获取执行SQL后的结果", "java.sql.ResultSet=" + statement.getResultSet());
    PrintUtil.two("6.使用Statement.getUpdateCount()获取执行SQL后的结果", "updateCount=" + statement.getUpdateCount());
  }
  // 测试更新SQL
  private static String testSQL(Long bookId) {
    return "UPDATE book SET remark = '使用Statement.execute接口实现数据更新', createrTime ='"+ CommonUtil.getCurrentTime() + 
        "' WHERE bookId = "+ bookId;
  }
}
```

### <a id="a_executeLargeUpdate">八、使用Statement.executeLargeUpdate方法实现数据操作</a> <a href="#a_execute">last</a> <a href="#a_executeUpdate">next</a>
```
一、使用java.sql.Statement.executeLargeUpdate()方法使实现数据操作，主要实现新增、删除、更新功能：
  default long  executeLargeUpdate(String sql)
  default long  executeLargeUpdate(String sql, int autoGeneratedKeys)
  default long  executeLargeUpdate(String sql, int[] columnIndexes)
  default long  executeLargeUpdate(String sql, String[] columnNames)

二、根据mysql-connector-java-8.0.11.jar源码分析：
2.1、executeLargeUpdate方法底层调用的是executeUpdateInternal方法
com.mysql.cj.jdbc.StatementImpl：方法具体代码：
public long executeLargeUpdate(String sql) throws SQLException {
    return executeUpdateInternal(sql, false, false);
}

public long executeLargeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
    return executeUpdateInternal(sql, false, autoGeneratedKeys == java.sql.Statement.RETURN_GENERATED_KEYS);
}

public long executeLargeUpdate(String sql, int[] columnIndexes) throws SQLException {
    return executeUpdateInternal(sql, false, columnIndexes != null && columnIndexes.length > 0);
}

public long executeLargeUpdate(String sql, String[] columnNames) throws SQLException {
    return executeUpdateInternal(sql, false, columnNames != null && columnNames.length > 0);
}

2.2、方法参数autoGeneratedKeys、columnIndexes、columnNames：用法和execute()方法的用法一致

2.3、executeLargeUpdate方法返回的实际上是this.updateCount，也就是Statement.getUpdateCount()

2.4、executeLargeUpdate方法一般用于执行当返回的行数可能超过Integer.MAX_VALUE时
```
ExecuteLargeUpdateMain.java：
```Java
package com.mutisitc.statement.executelargeupdate;
import java.sql.SQLException;
import java.sql.Statement;
import com.mutisitc.utils.JDBCUtil;
import com.mutisitc.utils.PrintUtil;
// 使用Statement.executeLargeUpdate接口实现数据操作
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
```
ExecuteLargeUpdateByInsert.java：
```Java
package com.mutisitc.statement.executelargeupdate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mutisitc.utils.CommonUtil;
import com.mutisitc.utils.PrintUtil;
// 使用Statement.executeLargeUpdate接口实现数据新增
public class ExecuteLargeUpdateByInsert {
  // 使用Statement.executeLargeUpdate接口实现数据新增
  public static void mainByInsert(Statement statement) throws SQLException {
    mianByExecute(statement);
    mianByAutoGeneratedKeys(statement);
    mainByColumnIndexes(statement);
    mainByColumnNames(statement);
    
    PrintUtil.two("五、根据mysql-connector-java-8.0.11.jar源码分析：", "executeLargeUpdate方法底层调用的是executeUpdateInternal方法");
    PrintUtil.three("5.1、com.mysql.cj.jdbc.StatementImpl：方法具体代码：", null);
    PrintUtil.two("public long executeLargeUpdate(String sql) throws SQLException {\r\n" + 
        "    return executeUpdateInternal(sql, false, false);\r\n" + 
        "}\r\n" + 
        "\r\n" + 
        "public long executeLargeUpdate(String sql, int autoGeneratedKeys) throws SQLException {\r\n" + 
        "    return executeUpdateInternal(sql, false, autoGeneratedKeys == java.sql.Statement.RETURN_GENERATED_KEYS);\r\n" + 
        "}\r\n" + 
        "\r\n" + 
        "public long executeLargeUpdate(String sql, int[] columnIndexes) throws SQLException {\r\n" + 
        "    return executeUpdateInternal(sql, false, columnIndexes != null && columnIndexes.length > 0);\r\n" + 
        "}\r\n" + 
        "\r\n" + 
        "public long executeLargeUpdate(String sql, String[] columnNames) throws SQLException {\r\n" + 
        "    return executeUpdateInternal(sql, false, columnNames != null && columnNames.length > 0);\r\n" + 
        "}", null);
    
    PrintUtil.two("\n六、方法参数autoGeneratedKeys、columnIndexes、columnNames", "用法和execute()方法的用法一致");
  }
  // 使用 Statement.executeLargeUpdate(String sql)：数据新增
  private static void mianByExecute(Statement statement) throws SQLException {
    PrintUtil.one("一、使用 Statement.executeLargeUpdate(String sql)：数据新增：");
    
    String insertSQL = testSQL(System.currentTimeMillis());
    PrintUtil.two("3.数据新增SQL语句：", insertSQL);

    long executeLargeUpdateResult = statement.executeLargeUpdate(insertSQL);
    PrintUtil.two("4.Statement.executeLargeUpdate(String sql)：数据新增执行结果", "executeLargeUpdateResult=" + executeLargeUpdateResult);
    PrintUtil.three("4.1：具体描述:", "执行给定的SQL语句，它可以是一个INSERT， UPDATE或者DELETE语句，或者不返回任何内容的SQL语句(如SQL DDL语句)\n"
        + "当返回的行数可能超过Integer.MAX_VALUE时，应使用此方法");
    PrintUtil.three("4.2：方法返回结果：", "SQL数据操作语言（DML）语句的行数 或 0表示不返回任何内容的SQL语句");
  }
  // 使用  Statement.executeLargeUpdate(String sql, int autoGeneratedKeys)：数据新增
  private static void mianByAutoGeneratedKeys(Statement statement) throws SQLException {
    PrintUtil.one("二、使用  Statement.executeLargeUpdate(String sql, int autoGeneratedKeys)：数据新增：");
    
    String insertSQL = testSQL(null);
    PrintUtil.two("3.数据新增SQL语句：", insertSQL);

    long executeLargeUpdateResult = statement.executeLargeUpdate(insertSQL, Statement.RETURN_GENERATED_KEYS);
    PrintUtil.two("4.Statement.executeLargeUpdate(String sql, int autoGeneratedKeys)：数据新增执行结果", "executeLargeUpdateResult=" + executeLargeUpdateResult);
    PrintUtil.three("4.1：具体描述:", "执行给定的SQL语句，它可以是一个INSERT， UPDATE或者DELETE语句，或者不返回任何内容的SQL语句(如SQL DDL语句)");
    PrintUtil.three("4.2：方法返回结果：", "SQL数据操作语言（DML）语句的行数 或 0表示不返回任何内容的SQL语句");

    ResultSet generatedKeys = statement.getGeneratedKeys();
    PrintUtil.two("5.当指定参数autoGeneratedKeys时，且autoGeneratedKeys=Statement.RETURN_GENERATED_KEYS"+Statement.RETURN_GENERATED_KEYS, 
        "可以使用Statement.sgetGeneratedKeys()获取由于执行此 Statement对象而创建的所有自动生成的键(可能执行多个insert sql)");
    PrintUtil.three("5.1：当参数指autoGeneratedKeys定为Statement.NO_GENERATED_KEYS或不为Statement.RETURN_GENERATED_KEYS的其他值",
        "则不能调用Statement.sgetGeneratedKeys()，否者会抛出异常：\n"
        + "异常信息为：java.sql.SQLException: Generated keys not requested. You need to specify Statement.RETURN_GENERATED_KEYS to Statement.executeLargeUpdateUpdate(), Statement.executeLargeUpdateLargeUpdate() or Connection.prepareStatement().");
    PrintUtil.three("5.2：由于可能执行多个insert sql：", "所以使用ResultSet作为接受自动生成主键的集合，通过循环获取每个执行结果的主键，获取索引从1开始");
    PrintUtil.three("5.3：如果表主键没有设置为自增主键的情况下", "是要在insert 指定每条数据的主键ID，且不重复，故不需要通过Statement.sgetGeneratedKeys()再次获取主键，当然获取的主键的集合也insert 数据记录的集合主键一致");
    PrintUtil.three("5.4：通过ResultSet.getXXX(1)获取主键", "注意要和表主键的数据类型映射到java数据一致");
    if(generatedKeys != null) {
      while (generatedKeys.next()) {
        PrintUtil.three("5.5.通过循环获取到的执行结果后的主键ID", "bookId="+generatedKeys.getLong(1));
      }
    }
  }
  // 使用  Statement.executeLargeUpdate(String sql, int[] columnIndexes)：数据新增
  private static void mainByColumnIndexes(Statement statement) throws SQLException {
    PrintUtil.one("三、使用  Statement.executeLargeUpdate(String sql, int[] columnIndexes)：数据新增：");
    
    String insertSQL = testSQL(null);
    PrintUtil.two("3.数据新增SQL语句：", insertSQL);

    int[] columnIndexes = new int[] {0};
    long executeLargeUpdateResult = statement.executeLargeUpdate(insertSQL, columnIndexes);
    PrintUtil.two("4.Statement.executeLargeUpdate(String sql, int[] columnIndexes)：数据新增执行结果", "executeLargeUpdateResult=" + executeLargeUpdateResult+", columnIndexes="+ CommonUtil.toString(columnIndexes));
    PrintUtil.three("4.1：具体描述:", "执行给定的SQL语句，它可以是一个INSERT， UPDATE或者DELETE语句，或者不返回任何内容的SQL语句(如SQL DDL语句)\n"
        + "当返回的行数可能超过Integer.MAX_VALUE时，应使用此方法");
    PrintUtil.three("4.2：方法返回结果：", "SQL数据操作语言（DML）语句的行数 或 0表示不返回任何内容的SQL语句");

    ResultSet generatedKeys = statement.getGeneratedKeys();
    PrintUtil.two("5.当指定参数columnIndexes时，且columnIndexes不为null且空数组时，", 
        "可以使用Statement.sgetGeneratedKeys()获取由于执行此 Statement对象而创建的所有自动生成的键(可能执行多个insert sql)");
    PrintUtil.three("5.1：当参数指columnIndexes定为null或空数组", "则不能调用Statement.sgetGeneratedKeys()，否者会抛出异常：\n"
        + "异常信息为：java.sql.SQLException: Generated keys not requested. You need to specify Statement.RETURN_GENERATED_KEYS to Statement.executeLargeUpdateUpdate(), Statement.executeLargeUpdateLargeUpdate() or Connection.prepareStatement().");
    PrintUtil.three("5.2：由于可能执行多个insert sql：", "所以使用ResultSet作为接受自动生成主键的集合，通过循环获取每个执行结果的主键，获取索引从1开始");
    PrintUtil.three("5.3：如果表主键没有设置为自增主键的情况下", "是要在insert 指定每条数据的主键ID，且不重复，故不需要通过Statement.sgetGeneratedKeys()再次获取主键，当然获取的主键的集合也insert 数据记录的集合主键一致");
    PrintUtil.three("5.4：通过ResultSet.getXXX(1)获取主键", "注意要和表主键的数据类型映射到java数据一致");
    if(generatedKeys != null) {
      while (generatedKeys.next()) {
        PrintUtil.three("5.5.通过循环获取到的执行结果后的主键ID", "bookId="+generatedKeys.getLong(1));
      }
    }
  }
  // 使用  Statement.executeLargeUpdate(String sql, String[] columnNames)：数据新增
  private static void mainByColumnNames(Statement statement) throws SQLException {
    PrintUtil.one("四、使用  Statement.executeLargeUpdate(String sql, String[] columnNames)：数据新增：");
    
    String insertSQL = testSQL(null);
    PrintUtil.two("3.数据新增SQL语句：", insertSQL);

    String[] columnNames = new String[] {"bookId"};
    long executeLargeUpdateResult = statement.executeLargeUpdate(insertSQL, columnNames);
    PrintUtil.two("4.Statement.executeLargeUpdate(String sql, int[] columnIndexes)：数据新增执行结果", "executeLargeUpdateResult=" + executeLargeUpdateResult+", columnNames="+ CommonUtil.toString(columnNames));
    PrintUtil.three("4.1：具体描述:", "执行给定的SQL语句，它可以是一个INSERT， UPDATE或者DELETE语句，或者不返回任何内容的SQL语句(如SQL DDL语句)\n"
        + "当返回的行数可能超过Integer.MAX_VALUE时，应使用此方法");
    PrintUtil.three("4.2：方法返回结果：", "SQL数据操作语言（DML）语句的行数 或 0表示不返回任何内容的SQL语句");
    
    ResultSet generatedKeys = statement.getGeneratedKeys();
    PrintUtil.two("5.当指定参数columnNames时，且columnNames不为null且空数组时，", 
        "可以使用Statement.sgetGeneratedKeys()获取由于执行此 Statement对象而创建的所有自动生成的键(可能执行多个insert sql)");
    PrintUtil.three("5.1：当参数指columnNames定为null或空数组", "则不能调用Statement.sgetGeneratedKeys()，否者会抛出异常：\n"
        + "异常信息为：java.sql.SQLException: Generated keys not requested. You need to specify Statement.RETURN_GENERATED_KEYS to Statement.executeLargeUpdateUpdate(), Statement.executeLargeUpdateLargeUpdate() or Connection.prepareStatement().");
    PrintUtil.three("5.2：由于可能执行多个insert sql：", "所以使用ResultSet作为接受自动生成主键的集合，通过循环获取每个执行结果的主键，获取索引从1开始");
    PrintUtil.three("5.3：如果表主键没有设置为自增主键的情况下", "是要在insert 指定每条数据的主键ID，且不重复，故不需要通过Statement.sgetGeneratedKeys()再次获取主键，当然获取的主键的集合也insert 数据记录的集合主键一致");
    PrintUtil.three("5.4：通过ResultSet.getXXX(1)获取主键", "注意要和表主键的数据类型映射到java数据一致");
    if(generatedKeys != null) {
      while (generatedKeys.next()) {
        PrintUtil.three("5.5.通过循环获取到的执行结果后的主键ID", "bookId="+generatedKeys.getLong(1));
      }
    }
  }
  // 测试新增SQL
  private static String testSQL(Long bookId) {
    return "INSERT INTO `book` (`bookId`, `title`, `author`, `remark`, `createrTime`) VALUES (" + bookId
        + "," + "'test by jdbc', 'test author', '使用Statement.executeLargeUpdate接口实现数据新增', '" + CommonUtil.getCurrentTime() + "')";
  }
}
```
ExecuteLargeUpdateByDelete.java：
```Java
package com.mutisitc.statement.executelargeupdate;
import java.sql.SQLException;
import java.sql.Statement;
import com.mutisitc.utils.PrintUtil;
// 使用Statement.executeUpdate接口实现数据删除
public class ExecuteLargeUpdateByDelete {
  // 使用Statement.executeUpdate接口实现数据删除
  public static void mainByDelete(Statement statement) throws SQLException {
    PrintUtil.one("一、使用 Statement.executeUpdate(String sql)：数据删除：");
    
    String deleteSQL = "DELETE FROM book WHERE bookId = "+ 1537949128633L;
    PrintUtil.two("3.数据删除SQL语句：", deleteSQL);

    int executeUpdateResult = statement.executeUpdate(deleteSQL);
    PrintUtil.two("4.Statement.executeUpdate(String sql)：数据删除执行结果", "executeUpdateResult=" + executeUpdateResult);
    PrintUtil.three("4.1：具体描述:", "执行给定的SQL语句，它可以是一个INSERT， UPDATE或者DELETE语句，或者不返回任何内容的SQL语句(如SQL DDL语句)\n"
        + "当返回的行数可能超过Integer.MAX_VALUE时，应使用此方法");
    PrintUtil.three("4.2：方法返回结果：", "SQL数据操作语言（DML）语句的行数 或 0表示不返回任何内容的SQL语句");
  }
}
```
ExecuteLargeUpdateByUpdate.java：
```Java
package com.mutisitc.statement.executelargeupdate;
import java.sql.SQLException;
import java.sql.Statement;
import com.mutisitc.utils.CommonUtil;
import com.mutisitc.utils.PrintUtil;
// 使用Statement.executeUpdate接口实现数据更新
public class ExecuteLargeUpdateByUpdate {
  // 使用Statement.executeUpdate接口实现数据更新
  public static void mainByUpdate(Statement statement) throws SQLException {
    PrintUtil.one("一、使用 Statement.executeUpdate(String sql)：数据更新：");
    
    String updateSQL = "UPDATE book SET remark = '使用Statement.executeUpdate接口实现数据更新', createrTime ='"+ CommonUtil.getCurrentTime() + 
        "' WHERE bookId = "+ 1537846522352L;
    PrintUtil.two("3.数据更新SQL语句：", updateSQL);

    int executeUpdateResult = statement.executeUpdate(updateSQL);
    PrintUtil.two("4.Statement.executeUpdate(String sql)：数据更新执行结果", "executeUpdateResult=" + executeUpdateResult);
    PrintUtil.three("4.1：具体描述:", "执行给定的SQL语句，它可以是一个INSERT， UPDATE或者DELETE语句，或者不返回任何内容的SQL语句(如SQL DDL语句)\n"
        + "当返回的行数可能超过Integer.MAX_VALUE时，应使用此方法");
    PrintUtil.three("4.2：方法返回结果：", "SQL数据操作语言（DML）语句的行数 或 0表示不返回任何内容的SQL语句");
  }
}
```

### <a id="a_executeUpdate">九、使用Statement.executeUpdae方法实现数据操作</a> <a href="#a_executeLargeUpdate">last</a> <a href="#a_prepared">next</a>
```
一、使用java.sql.Statement.executeUpdate()方法使实现数据操作，主要实现新增、删除、更新功能：
  int executeUpdate(String sql)
  int executeUpdate(String sql, int autoGeneratedKeys)
  int executeUpdate(String sql, int[] columnIndexes)
  int executeUpdate(String sql, String[] columnNames)

二、根据mysql-connector-java-8.0.11.jar源码分析：
2.1、executeUpdate方法底层还是调用executeLargeUpdate方法
com.mysql.cj.jdbc.StatementImpl：方法具体代码：

public int executeUpdate(String sql) throws SQLException {
    return Util.truncateAndConvertToInt(executeLargeUpdate(sql));
}

public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
    return Util.truncateAndConvertToInt(executeLargeUpdate(sql, autoGeneratedKeys));
}

public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
    return Util.truncateAndConvertToInt(executeLargeUpdate(sql, columnIndexes));
}

public int executeUpdate(String sql, String[] columnNames) throws SQLException {
    return Util.truncateAndConvertToInt(executeLargeUpdate(sql, columnNames));
}

2.2、方法参数autoGeneratedKeys、columnIndexes、columnNames：用法和execute()方法的用法一致
```
ExecuteUpdateMain.java：
```Java
package com.mutisitc.statement.executeupdate;
import java.sql.SQLException;
import java.sql.Statement;
import com.mutisitc.utils.JDBCUtil;
import com.mutisitc.utils.PrintUtil;
/**
 * 使用Statement.execute接口实现数据操作
 */
public class ExecuteUpdateMain {
  public static void main(String[] args) {
    PrintUtil.one("使用Statement.execute接口实现数据操作：");
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
```
ExecuteUpdateByInsert.java：
```Java
package com.mutisitc.statement.executeupdate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mutisitc.utils.CommonUtil;
import com.mutisitc.utils.PrintUtil;
/**
 * 使用Statement.executeUpdate接口实现数据新增
 */
public class ExecuteUpdateByInsert {
  /**
   * 使用Statement.executeUpdate接口实现数据新增
   * @param statement
   * @throws SQLException
   */
  public static void mainByInsert(Statement statement) throws SQLException {
    mianByExecute(statement);
    mianByAutoGeneratedKeys(statement);
    mainByColumnIndexes(statement);
    mainByColumnNames(statement);
    
    PrintUtil.two("五、根据mysql-connector-java-8.0.11.jar源码分析：", "executeUpdate方法底层还是调用executeLargeUpdate方法");
    PrintUtil.three("5.1、com.mysql.cj.jdbc.StatementImpl：方法具体代码：", null);
    PrintUtil.two("public int executeUpdate(String sql) throws SQLException {\r\n" + 
        "    return Util.truncateAndConvertToInt(executeLargeUpdate(sql));\r\n" + 
        "}\r\n"+ 
        "\r\n" + 
        "public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {\r\n" + 
        "    return Util.truncateAndConvertToInt(executeLargeUpdate(sql, autoGeneratedKeys));\r\n" + 
        "}\r\n" + 
        "\r\n" + 
        "public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {\r\n" + 
        "    return Util.truncateAndConvertToInt(executeLargeUpdate(sql, columnIndexes));\r\n" + 
        "}\r\n" + 
        "\r\n" + 
        "public int executeUpdate(String sql, String[] columnNames) throws SQLException {\r\n" + 
        "    return Util.truncateAndConvertToInt(executeLargeUpdate(sql, columnNames));\r\n" + 
        "}", null);
    
    PrintUtil.two("\n六、方法参数autoGeneratedKeys、columnIndexes、columnNames", "用法和execute()方法的用法一致");
  }
  /**
   * 使用 Statement.executeUpdate(String sql)：数据新增
   * @param statement
   * @throws SQLException
   */
  private static void mianByExecute(Statement statement) throws SQLException {
    PrintUtil.one("一、使用 Statement.executeUpdate(String sql)：数据新增：");
    
    String insertSQL = testSQL(System.currentTimeMillis());
    PrintUtil.two("3.数据新增SQL语句：", insertSQL);

    int executeUpdateResult = statement.executeUpdate(insertSQL);
    PrintUtil.two("4.Statement.executeUpdate(String sql)：数据新增执行结果", "executeUpdateResult=" + executeUpdateResult);
    PrintUtil.three("4.1：具体描述:", "执行给定的SQL语句，它可以是一个INSERT， UPDATE或者DELETE语句，或者不返回任何内容的SQL语句(如SQL DDL语句)");
    PrintUtil.three("4.2：方法返回结果：", "SQL数据操作语言（DML）语句的行数 或 0表示不返回任何内容的SQL语句");
  }
  /**
   * 使用  Statement.executeUpdate(String sql, int autoGeneratedKeys)：数据新增
   * @param statement
   * @throws SQLException
   */
  private static void mianByAutoGeneratedKeys(Statement statement) throws SQLException {
    PrintUtil.one("二、使用  Statement.executeUpdate(String sql, int autoGeneratedKeys)：数据新增：");
    
    String insertSQL = testSQL(null);
    PrintUtil.two("3.数据新增SQL语句：", insertSQL);

    int executeUpdateResult = statement.executeUpdate(insertSQL, Statement.RETURN_GENERATED_KEYS);
    PrintUtil.two("4.Statement.executeUpdate(String sql, int autoGeneratedKeys)：数据新增执行结果", "executeUpdateResult=" + executeUpdateResult);
    PrintUtil.three("4.1：具体描述:", "执行给定的SQL语句，它可以是一个INSERT， UPDATE或者DELETE语句，或者不返回任何内容的SQL语句(如SQL DDL语句)");
    PrintUtil.three("4.2：方法返回结果：", "SQL数据操作语言（DML）语句的行数 或 0表示不返回任何内容的SQL语句");

    ResultSet generatedKeys = statement.getGeneratedKeys();
    PrintUtil.two("5.当指定参数autoGeneratedKeys时，且autoGeneratedKeys=Statement.RETURN_GENERATED_KEYS"+Statement.RETURN_GENERATED_KEYS, 
        "可以使用Statement.sgetGeneratedKeys()获取由于执行此 Statement对象而创建的所有自动生成的键(可能执行多个insert sql)");
    PrintUtil.three("5.1：当参数指autoGeneratedKeys定为Statement.NO_GENERATED_KEYS或不为Statement.RETURN_GENERATED_KEYS的其他值",
        "则不能调用Statement.sgetGeneratedKeys()，否者会抛出异常：\n"
        + "异常信息为：java.sql.SQLException: Generated keys not requested. You need to specify Statement.RETURN_GENERATED_KEYS to Statement.executeUpdateUpdate(), Statement.executeUpdateLargeUpdate() or Connection.prepareStatement().");
    PrintUtil.three("5.2：由于可能执行多个insert sql：", "所以使用ResultSet作为接受自动生成主键的集合，通过循环获取每个执行结果的主键，获取索引从1开始");
    PrintUtil.three("5.3：如果表主键没有设置为自增主键的情况下", "是要在insert 指定每条数据的主键ID，且不重复，故不需要通过Statement.sgetGeneratedKeys()再次获取主键，当然获取的主键的集合也insert 数据记录的集合主键一致");
    PrintUtil.three("5.4：通过ResultSet.getXXX(1)获取主键", "注意要和表主键的数据类型映射到java数据一致");
    if(generatedKeys != null) {
      while (generatedKeys.next()) {
        PrintUtil.three("5.5.通过循环获取到的执行结果后的主键ID", "bookId="+generatedKeys.getLong(1));
      }
    }
  }
  /**
   * 使用  Statement.executeUpdate(String sql, int[] columnIndexes)：数据新增
   * @param statement
   * @throws SQLException 
   */
  private static void mainByColumnIndexes(Statement statement) throws SQLException {
    PrintUtil.one("三、使用  Statement.executeUpdate(String sql, int[] columnIndexes)：数据新增：");
    
    String insertSQL = testSQL(null);
    PrintUtil.two("3.数据新增SQL语句：", insertSQL);

    int[] columnIndexes = new int[] {0};
    int executeUpdateResult = statement.executeUpdate(insertSQL, columnIndexes);
    PrintUtil.two("4.Statement.executeUpdate(String sql, int[] columnIndexes)：数据新增执行结果", "executeUpdateResult=" + executeUpdateResult+", columnIndexes="+ CommonUtil.toString(columnIndexes));
    PrintUtil.three("4.1：具体描述:", "执行给定的SQL语句，它可以是一个INSERT， UPDATE或者DELETE语句，或者不返回任何内容的SQL语句(如SQL DDL语句)");
    PrintUtil.three("4.2：方法返回结果：", "SQL数据操作语言（DML）语句的行数 或 0表示不返回任何内容的SQL语句");

    ResultSet generatedKeys = statement.getGeneratedKeys();
    PrintUtil.two("5.当指定参数columnIndexes时，且columnIndexes不为null且空数组时，", 
        "可以使用Statement.sgetGeneratedKeys()获取由于执行此 Statement对象而创建的所有自动生成的键(可能执行多个insert sql)");
    PrintUtil.three("5.1：当参数指columnIndexes定为null或空数组", "则不能调用Statement.sgetGeneratedKeys()，否者会抛出异常：\n"
        + "异常信息为：java.sql.SQLException: Generated keys not requested. You need to specify Statement.RETURN_GENERATED_KEYS to Statement.executeUpdateUpdate(), Statement.executeUpdateLargeUpdate() or Connection.prepareStatement().");
    PrintUtil.three("5.2：由于可能执行多个insert sql：", "所以使用ResultSet作为接受自动生成主键的集合，通过循环获取每个执行结果的主键，获取索引从1开始");
    PrintUtil.three("5.3：如果表主键没有设置为自增主键的情况下", "是要在insert 指定每条数据的主键ID，且不重复，故不需要通过Statement.sgetGeneratedKeys()再次获取主键，当然获取的主键的集合也insert 数据记录的集合主键一致");
    PrintUtil.three("5.4：通过ResultSet.getXXX(1)获取主键", "注意要和表主键的数据类型映射到java数据一致");
    if(generatedKeys != null) {
      while (generatedKeys.next()) {
        PrintUtil.three("5.5.通过循环获取到的执行结果后的主键ID", "bookId="+generatedKeys.getLong(1));
      }
    }
  }
  /**
   * 使用  Statement.executeUpdate(String sql, String[] columnNames)：数据新增
   * @param statement
   * @throws SQLException 
   */
  private static void mainByColumnNames(Statement statement) throws SQLException {
    PrintUtil.one("四、使用  Statement.executeUpdate(String sql, String[] columnNames)：数据新增：");
    
    String insertSQL = testSQL(null);
    PrintUtil.two("3.数据新增SQL语句：", insertSQL);

    String[] columnNames = new String[] {"bookId"};
    int executeUpdateResult = statement.executeUpdate(insertSQL, columnNames);
    PrintUtil.two("4.Statement.executeUpdate(String sql, int[] columnIndexes)：数据新增执行结果", "executeUpdateResult=" + executeUpdateResult+", columnNames="+ CommonUtil.toString(columnNames));
    PrintUtil.three("4.1：具体描述:", "执行给定的SQL语句，它可以是一个INSERT， UPDATE或者DELETE语句，或者不返回任何内容的SQL语句(如SQL DDL语句)");
    PrintUtil.three("4.2：方法返回结果：", "SQL数据操作语言（DML）语句的行数 或 0表示不返回任何内容的SQL语句");
    
    ResultSet generatedKeys = statement.getGeneratedKeys();
    PrintUtil.two("5.当指定参数columnNames时，且columnNames不为null且空数组时，", 
        "可以使用Statement.sgetGeneratedKeys()获取由于执行此 Statement对象而创建的所有自动生成的键(可能执行多个insert sql)");
    PrintUtil.three("5.1：当参数指columnNames定为null或空数组", "则不能调用Statement.sgetGeneratedKeys()，否者会抛出异常：\n"
        + "异常信息为：java.sql.SQLException: Generated keys not requested. You need to specify Statement.RETURN_GENERATED_KEYS to Statement.executeUpdateUpdate(), Statement.executeUpdateLargeUpdate() or Connection.prepareStatement().");
    PrintUtil.three("5.2：由于可能执行多个insert sql：", "所以使用ResultSet作为接受自动生成主键的集合，通过循环获取每个执行结果的主键，获取索引从1开始");
    PrintUtil.three("5.3：如果表主键没有设置为自增主键的情况下", "是要在insert 指定每条数据的主键ID，且不重复，故不需要通过Statement.sgetGeneratedKeys()再次获取主键，当然获取的主键的集合也insert 数据记录的集合主键一致");
    PrintUtil.three("5.4：通过ResultSet.getXXX(1)获取主键", "注意要和表主键的数据类型映射到java数据一致");
    if(generatedKeys != null) {
      while (generatedKeys.next()) {
        PrintUtil.three("5.5.通过循环获取到的执行结果后的主键ID", "bookId="+generatedKeys.getLong(1));
      }
    }
  }
  /**
   * 测试新增SQL
   * @param bookId 主键ID
   * @return
   */
  private static String testSQL(Long bookId) {
    return "INSERT INTO `book` (`bookId`, `title`, `author`, `remark`, `createrTime`) VALUES (" + bookId
        + "," + "'test by jdbc', 'test author', '使用Statement.executeUpdate接口实现数据新增', '" + CommonUtil.getCurrentTime() + "')";
  }
}

```
ExecuteUpdateByDelete.java：
```Java
package com.mutisitc.statement.executeupdate;
import java.sql.SQLException;
import java.sql.Statement;
import com.mutisitc.utils.PrintUtil;
/**
 * 使用Statement.executeUpdate接口实现数据删除
 * @date 2018年9月26日
 */
public class ExecuteUpdateByDelete {
  /**
   * 使用Statement.executeUpdate接口实现数据删除
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
```
ExecuteUpdateByUpdate.java：
```Java
package com.mutisitc.statement.executeupdate;
import java.sql.SQLException;
import java.sql.Statement;
import com.mutisitc.utils.CommonUtil;
import com.mutisitc.utils.PrintUtil;
/**
 * 使用Statement.executeUpdate接口实现数据更新
 */
public class ExecuteUpdateByUpdate {
  /**
   * 使用Statement.executeUpdate接口实现数据更新
   * @param statement
   * @throws SQLException
   */
  public static void mainByUpdate(Statement statement) throws SQLException {
    PrintUtil.one("一、使用 Statement.executeUpdate(String sql)：数据更新：");
    
    String updateSQL ="UPDATE book SET remark = '使用Statement.executeUpdate接口实现数据更新', createrTime ='"+ CommonUtil.getCurrentTime() + 
        "' WHERE bookId = "+ 1537846522352L;
    PrintUtil.two("3.数据更新SQL语句：", updateSQL);

    int executeUpdateResult = statement.executeUpdate(updateSQL);
    PrintUtil.two("4.Statement.executeUpdate(String sql)：数据更新执行结果", "executeUpdateResult=" + executeUpdateResult);
    PrintUtil.three("4.1：具体描述:", "执行给定的SQL语句，它可以是一个INSERT， UPDATE或者DELETE语句，或者不返回任何内容的SQL语句(如SQL DDL语句)");
    PrintUtil.three("4.2：方法返回结果：", "SQL数据操作语言（DML）语句的行数 或 0表示不返回任何内容的SQL语句");
  }
}
```

### <a id="a_prepared">十、java.sql.PreparedStatement：预编译SQL语句</a> <a href="#a_executeUpdate">last</a> <a href="#a_preparedOperation">next</a>
[java.sql.PreparedStatement](https://docs.oracle.com/javase/8/docs/api/java/sql/PreparedStatement.html)  
一、描述：
```
  表示预编译的 SQL 语句的对象。 继承 java.sql.Statement对象
SQL 语句被预编译并存储在 PreparedStatement 对象中。然后可以使用此对象多次高效地执行该语句。 
注：用于设置 IN 参数值的设置方法（setShort、setString 等等）必须指定与输入参数的已定义 SQL 类型兼容的类型。
例如，如果 IN 参数具有 SQL 类型 INTEGER，那么应该使用 setInt 方法。 

如果需要任意参数类型转换，使用 setObject 方法时应该将目标 SQL 类型作为其参数。 
```

二、方法说明：

|返回类型|方法|说明|
|---|---|---|
|void|addBatch()|向该PreparedStatement 对象的一批命令添加一组参数|
|void|clearParameters()|立即清除当前参数值|
|boolean|execute()|在此PreparedStatement对象中执行SQL语句，该语句可以是任何类型的SQL语句|
|default long|executeLargeUpdate()|在此PreparedStatement对象中执行SQL语句，该语句必须是SQL数据操作语言（DML）语句，例如INSERT，UPDATE或 DELETE; 或者不返回任何内容的SQL语句，例如DDL语句|
|ResultSet|executeQuery()|在此PreparedStatement对象中执行SQL查询并返回ResultSet查询生成的对象|
|int|executeUpdate()|在此PreparedStatement对象中执行SQL语句，该语句必须是SQL数据操作语言（DML）语句，例如INSERT，UPDATE或 DELETE; 或者不返回任何内容的SQL语句，例如DDL语句|
|ResultSetMetaData|getMetaData()|获取一个ResultSetMetaData对象，该对象包含有关执行ResultSet此PreparedStatement对象时将返回的对象列的信息|
|ParameterMetaData|getParameterMetaData()|获取此 PreparedStatement 对象的参数的编号、类型和属性|
|void|setArray(int parameterIndex, Array x)|将指定参数设置为给定java.sql.Array对象|
|void|setAsciiStream(int parameterIndex, InputStream x)|将指定参数设置为给定输入流|
|void|setAsciiStream(int parameterIndex, InputStream x, int length)|将指定参数设置为给定输入流，该输入流将具有指定的字节数|
|void|setAsciiStream(int parameterIndex, InputStream x, long length)|将指定参数设置为给定输入流，该输入流将具有指定的字节数|
|void|setBigDecimal(int parameterIndex, BigDecimal x)|将指定参数设置为给定java.math.BigDecimal值|
|void|setBinaryStream(int parameterIndex, InputStream x)|将指定参数设置为给定输入流|
|void|setBinaryStream(int parameterIndex, InputStream x, int length)|将指定参数设置为给定输入流，该输入流将具有指定的字节数|
|void|setBinaryStream(int parameterIndex, InputStream x, long length)|将指定参数设置为给定输入流，该输入流将具有指定的字节数|
|void|setBlob(int parameterIndex, Blob x)|将指定参数设置为给定java.sql.Blob对象|
|void|setBlob(int parameterIndex, InputStream inputStream)|将指定参数设置为InputStream对象|
|void|setBlob(int parameterIndex, InputStream inputStream, long length)|将指定参数设置为InputStream对象|
|void|setBoolean(int parameterIndex, boolean x)|将指定参数设置为给定的Java boolean值|
|void|setByte(int parameterIndex, byte x)|将指定参数设置为给定的Java byte值|
|void|setBytes(int parameterIndex, byte[] x)|将指定参数设置为给定的Java字节数组|
|void|setCharacterStream(int parameterIndex, Reader reader)|将指定参数设置为给定Reader 对象|
|void|setCharacterStream(int parameterIndex, Reader reader, int length)|将指定参数设置为给定Reader 对象，即给定的字符长度|
|void|setCharacterStream(int parameterIndex, Reader reader, long length)|将指定参数设置为给定Reader 对象，即给定的字符长度|
|void|setClob(int parameterIndex, Clob x)|将指定参数设置为给定java.sql.Clob对象|
|void|setClob(int parameterIndex, Reader reader)|将指定参数设置为Reader对象|
|void|setClob(int parameterIndex, Reader reader, long length)|将指定参数设置为Reader对象|
|void|setDate(int parameterIndex, Date x)|java.sql.Date使用运行应用程序的虚拟机的默认时区将指定参数设置为给定值|
|void|setDate(int parameterIndex, Date x, Calendar cal)|java.sql.Date使用给定Calendar对象将指定参数设置为给定值|
|void|setDouble(int parameterIndex, double x)|将指定参数设置为给定的Java double值|
|void|setFloat(int parameterIndex, float x)|将指定参数设置为给定的Java float值|
|void|setInt(int parameterIndex, int x)|将指定参数设置为给定的Java int值|
|void|setLong(int parameterIndex, long x)|将指定参数设置为给定的Java long值|
|void|setNCharacterStream(int parameterIndex, Reader value)|将指定参数设置为Reader对象|
|void|setNCharacterStream(int parameterIndex, Reader value, long length)|将指定参数设置为Reader对象|
|void|setNClob(int parameterIndex, NClob value)|将指定参数设置为java.sql.NClob对象|
|void|setNClob(int parameterIndex, Reader reader)|将指定参数设置为Reader对象|
|void|setNClob(int parameterIndex, Reader reader, long length)|将指定参数设置为Reader对象|
|void|setNString(int parameterIndex, String value)|将指定参数设置为给定String对象|
|void|setNull(int parameterIndex, int sqlType)|将指定参数设置为SQL NULL|
|void|setNull(int parameterIndex, int sqlType, String typeName)|将指定参数设置为SQL NULL|
|void|setObject(int parameterIndex, Object x)|使用给定对象设置指定参数的值|
|void|setObject(int parameterIndex, Object x, int targetSqlType)|使用给定对象设置指定参数的值|
|void|setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength)|使用给定对象设置指定参数的值|
|default void|setObject(int parameterIndex, Object x, SQLType targetSqlType)|使用给定对象设置指定参数的值|
|default void|setObject(int parameterIndex, Object x, SQLType targetSqlType, int scaleOrLength)|使用给定对象设置指定参数的值|
|void|setRef(int parameterIndex, Ref x)|将指定参数设置为给定 REF(<structured-type>)值|
|void|setRowId(int parameterIndex, RowId x)|将指定参数设置为给定java.sql.RowId对象|
|void|setShort(int parameterIndex, short x)|将指定参数设置为给定的Java short值|
|void|setSQLXML(int parameterIndex, SQLXML xmlObject)|将指定参数设置为给定java.sql.SQLXML对象|
|void|setString(int parameterIndex, String x)|将指定参数设置为给定的Java String值|
|void|setTime(int parameterIndex, Time x)|将指定参数设置为给定java.sql.Time值|
|void|setTime(int parameterIndex, Time x, Calendar cal)|java.sql.Time使用给定Calendar对象将指定参数设置为给定值|
|void|setTimestamp(int parameterIndex, Timestamp x)|将指定参数设置为给定java.sql.Timestamp值|
|void|setTimestamp(int parameterIndex, Timestamp x, Calendar cal)|java.sql.Timestamp使用给定Calendar对象将指定参数设置为给定值|
|void|setUnicodeStream(int parameterIndex, InputStream x, int length)|~~已过时~~使用 setCharacterStream|
|void|setURL(int parameterIndex, URL x)|将指定参数设置为给定java.net.URL值|

PreparedStatementMain.java：
```Java
package com.mutisitc.preparedstatement;
import java.sql.Date;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import com.mutisitc.utils.JDBCUtil;
import com.mutisitc.utils.PrintUtil;
// java.sql.PreparedStatement：预编译SQL语句
public class PreparedStatementMain {
  public static void main(String[] args) {
    PrintUtil.one("java.sql.PreparedStatement：预编译SQL语句：");
    try {
      String preparedSQL = "SELECT bookId, title, author, remark, createrTime FROM book WHERE bookId = ? AND title = ?";
      PreparedStatement preparedStatement = JDBCUtil.prepareStatement(preparedSQL);
      preparedStatement.setLong(1, 1538104150648L);
      preparedStatement.setString(2, "test by jdbc");
      PrintUtil.two("3.PreparedStatement.setXXX(int int parameterIndex, XXX xxx)", "将指定参数设置为给定的值");
      PrintUtil.two("4.PreparedStatement.clearParameters()", "立即清除当前参数值");
      
      ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();
      PrintUtil.two("5.PreparedStatement.getParameterMetaData()：获取此 PreparedStatement 对象的参数的编号、类型和属性", "ParameterMetaData="+parameterMetaData);
      if(null != parameterMetaData) {
        int parameterCount = parameterMetaData.getParameterCount();
        PrintUtil.three("5.1.使用ParameterMetaData.getParameterCount()获取此 PreparedStatement 此ParameterMetaData对象包含信息的对象中的参数数", "parameterCount="+parameterCount);
        PrintUtil.three("5.2.如果要使用ParameterMetaData参数元数据需要在URL开启参数：", "generateSimpleParameterMetadata=true");
        PrintUtil.three("  5.2.1.如果不开启参数generateSimpleParameterMetadata=true在使用ParameterMetaData会抛出异常：",
            "SQL java.sql.SQLException: Parameter metadata not available for the given statement");
        for (int i = 1; i <= parameterCount; i++) {
          PrintUtil.three("5."+(i+2)+".获取参数信息：", "ClassName="+parameterMetaData.getParameterClassName(i)
          +", TypeName="+ parameterMetaData.getParameterTypeName(i) +", Type="+ parameterMetaData.getParameterType(i)
          +", Mode="+ parameterMetaData.getParameterMode(i));
        }
      }
      
      ResultSet resultSet = preparedStatement.executeQuery();
      PrintUtil.two("6.PreparedStatement.executeQuery()：在此PreparedStatement对象中执行SQL查询并返回ResultSet查询生成的对象", "ResultSet="+resultSet);
      int index = 1;
      while(resultSet.next()) {
        Long bookId = resultSet.getLong("bookId");
        String title = resultSet.getString("title");
        String author = resultSet.getString("author");
        String remark = resultSet.getString("remark");
        Date createrTime = resultSet.getDate("createrTime");
        PrintUtil.three("6."+index+".通过ResultSet结果集，获取数据列信息", "bookId="+bookId+", title="+title+
            ", author="+author+", remark="+remark+", createrTime="+createrTime);
        index++;
      }
      
      PrintUtil.two("7.PreparedStatement.execute()", "在此PreparedStatement对象中执行SQL语句，该语句可以是任何类型的SQL语句");
      PrintUtil.two("8.PreparedStatement.executeLargeUpdate()", "在此PreparedStatement对象中执行SQL语句，该语句必须是SQL数据操作语言（DML）语句，例如INSERT，UPDATE或 DELETE; 或者不返回任何内容的SQL语句，例如DDL语句");
      PrintUtil.two("9.PreparedStatement.executeUpdate()", "在此PreparedStatement对象中执行SQL语句，该语句必须是SQL数据操作语言（DML）语句，例如INSERT，UPDATE或 DELETE; 或者不返回任何内容的SQL语句，例如DDL语句");
      
      ResultSetMetaData resultSetMetaData = preparedStatement.getMetaData();
      PrintUtil.two("10.PreparedStatement.getMetaData()：获取一个ResultSetMetaData对象，该对象包含有关执行ResultSet此PreparedStatement对象时将返回的对象列的信息", "ResultSetMetaData="+resultSetMetaData.getClass()+"@"+resultSetMetaData.hashCode());
      if(null != resultSetMetaData) {
        int columnCount = resultSetMetaData.getColumnCount();
        PrintUtil.three("10.1.使用ResultSetMetaData.getColumnCount()返回此ResultSet对象中的列数", "columnCount="+columnCount);
        for (int i = 1; i <= columnCount; i++) {
          PrintUtil.three("10."+(i+1)+".使用ResultSetMetaData获取返回列信息：", "CatalogName="+resultSetMetaData.getCatalogName(i)+" ,TableName="+resultSetMetaData.getTableName(i)
            +" ,ColumnLabel="+resultSetMetaData.getColumnLabel(i)+" ,ColumnName="+resultSetMetaData.getColumnName(i)
            +" ,TypeName="+resultSetMetaData.getColumnTypeName(i)+" ,ClassName="+resultSetMetaData.getColumnClassName(i)+" ,Type="+resultSetMetaData.getColumnType(i)
            +" ,SchemaName="+resultSetMetaData.getSchemaName(i)+" ,Scale="+resultSetMetaData.getScale(i)
            +" ,Precision="+resultSetMetaData.getPrecision(i)+" , DisplaySize="+resultSetMetaData.getColumnDisplaySize(i)+"");
        }
      }
      
      JDBCUtil.close(preparedStatement);
    } catch (SQLException e) {
      PrintUtil.err("演示 java.sql.PreparedStatement：预编译SQL语句，打印异常堆栈信息：");
      e.printStackTrace();
    }
  }
}
```

### <a id="a_preparedOperation">十一、使用PreparedStatement方法实现数据操作</a> <a href="#a_prepared">last</a> <a href="#a_resultSet">next</a>
11.1、使用PreparedStatement实现数据新增：  
InsertByMain.java：
```Java
package com.mutisitc.preparedstatement.operation;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.mutisitc.utils.CommonUtil;
import com.mutisitc.utils.JDBCUtil;
import com.mutisitc.utils.PrintUtil;
// 使用PreparedStatement实现数据新增
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
```
11.2、使用PreparedStatement实现数据删除：  
DeleteByMain.java：
```Java
package com.mutisitc.preparedstatement.operation;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.mutisitc.utils.JDBCUtil;
import com.mutisitc.utils.PrintUtil;
// 使用PreparedStatement实现数据删除
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
```
11.3、使用PreparedStatement实现数据更新：  
UpdateByMain.java：
```Java
package com.mutisitc.preparedstatement.operation;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.mutisitc.utils.CommonUtil;
import com.mutisitc.utils.JDBCUtil;
import com.mutisitc.utils.PrintUtil;
// 使用PreparedStatement实现数据更新
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
```
11.4、使用PreparedStatement实现数据查询：   
QueryByMain.java：
```Java
package com.mutisitc.preparedstatement.operation;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mutisitc.utils.JDBCUtil;
import com.mutisitc.utils.PrintUtil;
// 使用PreparedStatement实现数据查询
public class QueryByMain {
  public static void main(String[] args) {
    PrintUtil.one("使用PreparedStatement实现数据更新：");
    try {
      String preparedSQL = "SELECT bookId, title, author, remark, createrTime FROM book WHERE bookId >= ?";
      PreparedStatement preparedStatement = JDBCUtil.prepareStatement(preparedSQL);
      preparedStatement.setLong(1, 1538105479358l);
      ResultSet resultSet = preparedStatement.executeQuery();
      
      PrintUtil.two("3.PreparedStatement.executeQuery()：执行SQL返回结果：", "ResultSet="+resultSet);
      int index = 1;
      while(resultSet.next()) {
        Long bookId = resultSet.getLong("bookId");
        String title = resultSet.getString("title");
        String author = resultSet.getString("author");
        String remark = resultSet.getString("remark");
        Date createrTime = resultSet.getDate("createrTime");
        PrintUtil.three("3."+index+".通过ResultSet结果集，获取数据列信息", "bookId="+bookId+", title="+title+
            ", author="+author+", remark="+remark+", createrTime="+createrTime);
        index++;
      }
      JDBCUtil.close(preparedStatement);
    } catch (SQLException e) {
      PrintUtil.err("使用PreparedStatement实现数据更新，打印异常堆栈信息：");
      e.printStackTrace();
    }
  }
}
```

### <a id="a_resultSet">十二、java.sql.ResultSet：数据库结果集</a> <a href="#a_preparedOperation">last</a> <a href="#a_callable">next</a>
一、描述：
```
  表示数据库结果集的数据表，通常通过执行查询数据库的语句生成。 

  ResultSet 对象具有指向其当前数据行的光标。最初，光标被置于第一行之前。next 方法将光标移动到下一行；
因为该方法在 ResultSet 对象没有下一行时返回 false，所以可以在 while 循环中使用它来迭代结果集。 

  默认的 ResultSet 对象不可更新，仅有一个向前移动的光标。因此，只能迭代它一次，并且只能按从第一行到最后一行的顺序进行。
可以生成可滚动和/或可更新的 ResultSet 对象。以下代码片段（其中 con 为有效的 Connection 对象）演示了如何生成可滚动且不受其他更新影响的可更新结果集。
  Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
  ResultSet rs = stmt.executeQuery("SELECT a, b FROM TABLE2");
  // rs will be scrollable, will not show changes made by others,
  // and will be updatable
  ResultSet 接口提供用于从当前行获取列值的获取 方法（getBoolean、getLong 等）。可以使用列的索引编号或列的名称获取值。一般情况下，使用列索引较为高效。
列从 1 开始编号。为了获得最大的可移植性，应该按从左到右的顺序读取每行中的结果集列，每列只能读取一次。 
对于获取方法，JDBC 驱动程序尝试将底层数据转换为在获取方法中指定的 Java 类型，并返回适当的 Java 值。JDBC 规范有一个表，
显示允许的从 SQL 类型到 ResultSet 获取方法所使用的 Java 类型的映射关系。 

  用作获取方法的输入的列名称不区分大小写。用列名称调用获取方法时，如果多个列具有这一名称，则返回第一个匹配列的值。
在生成结果集的 SQL 查询中使用列名称时，将使用列名称选项。对于没有在查询中显式指定的列，最好使用列编号。
如果使用列名称，则程序员应该注意保证名称唯一引用预期的列，这可以使用 SQL AS 子句确定。 

  在 JDBC 2.0 API（JavaTM 2 SDK 标准版 1.2 版）中，此接口添加了一组更新方法。关于获取方法参数的注释同样适用于更新方法的参数。 
可以用以下两种方式使用更新方法： 
更新当前行中的列值。在可滚动的 ResultSet 对象中，可以向前和向后移动光标，将其置于绝对位置或相对于当前行的位置。
以下代码片段更新 ResultSet 对象 rs 第五行中的 NAME 列，然后使用方法 updateRow 更新导出 rs 的数据源表。 
  rs.absolute(5); // moves the cursor to the fifth row of rs
  rs.updateString("NAME", "AINSWORTH"); // updates the NAME column of row 5 to be AINSWORTH
  rs.updateRow(); // updates the row in the data source
将列值插入到插入行中。可更新的 ResultSet 对象具有一个与其关联的特殊行，该行用作构建要插入的行的暂存区域 (staging area)。以下代码片段将光标移动到插入行，构建一个三列的行，并使用方法 insertRow 将其插入到 rs 和数据源表中。 
       rs.moveToInsertRow(); // moves cursor to the insert row
       rs.updateString(1, "AINSWORTH"); // updates the  first column of the insert row to be AINSWORTH
       rs.updateInt(2,35); // updates the second column to be 35
       rs.updateBoolean(3, true); // updates the third column to true
       rs.insertRow();
       rs.moveToCurrentRow();
当生成 ResultSet 对象的 Statement 对象关闭、重新执行或用来从多个结果的序列获取下一个结果时，ResultSet 对象将自动关闭。 

  ResultSet 对象的列的编号、类型和属性由 ResultSet.getMetaData 方法返回的 ResulSetMetaData 对象提供。
```

二、字段说明 ：

|数据类型|字段|说明|
|---|---|---|
|static int|HOLD_CURSORS_OVER_COMMIT=1|该常量指示提交当前事务时，具有此可保存性的打开的 ResultSet 对象将保持开放|
|static int|CLOSE_CURSORS_AT_COMMIT=2|该常量指示提交当前事务时，具有此可保存性的打开的 ResultSet 对象将被关闭|
|static int|CONCUR_READ_ONLY=1007|该常量指示不可以更新的 ResultSet 对象的并发模式|
|static int|CONCUR_UPDATABLE=1008|该常量指示可以更新的 ResultSet 对象的并发模式|
|static int|FETCH_FORWARD=1000|该常量指示将按正向（即从第一个到最后一个）处理结果集中的行|
|static int|FETCH_REVERSE=1001|该常量指示将按逆向（即从最后一个到第一个）处理结果集中的行处理|
|static int|FETCH_UNKNOWN=1002|该常量指示结果集中的行的处理顺序未知|
|static int|TYPE_FORWARD_ONLY=1003|该常量指示光标只能向前移动的 ResultSet 对象的类型|
|static int|TYPE_SCROLL_INSENSITIVE=1004|该常量指示可滚动但通常不受 ResultSet 底层数据更改影响的 ResultSet 对象的类型|
|static int|TYPE_SCROLL_SENSITIVE=1005|该常量指示可滚动并且通常受 ResultSet 底层数据更改影响的 ResultSet 对象的类型|

三、方法说明 ：

|返回类型|方法|说明|
|---|---|---|
|boolean|absolute(int row)|将光标移动到此ResultSet对象中的给定行号|
|void|afterLast()|将光标移动到此ResultSet对象的末尾，紧接在最后一行之后|
|void|beforeFirst()|将光标移动到此ResultSet对象的前面，就在第一行之前|
|void|cancelRowUpdates()|取消对此ResultSet对象中当前行所做的更新 |
|void|clearWarnings()|清除此ResultSet对象上报告的所有警告|
|void|close()|立即释放此ResultSet对象的数据库和JDBC资源，而不是等待它自动关闭时发生|
|void|deleteRow()|从此ResultSet对象和基础数据库中删除当前行|
|int|findColumn(String columnLabel)|将给定ResultSet列标签映射到其 ResultSet列索引|
|boolean|first()|将光标移动到此ResultSet对象的第一行|
|int|getFetchDirection()|获取此ResultSet对象的获取方向|
|void|setFetchDirection(int direction)|提供有关此ResultSet对象中的行的处理方向的提示|
|int|getFetchSize()|获取此ResultSet对象的提取大小|
|void|setFetchSize(int rows)|为JDBC驱动程序提供有关此ResultSet对象需要更多行时应从数据库中提取的行数的提示|
|int|getHoldability()|获取此ResultSet对象的可保存性|
|ResultSetMetaData|getMetaData()|获取此ResultSet对象列的数量，类型和属性|
|int|getRow()|获取当前行号|
|Statement|getStatement()|获取Statement生成此 ResultSet对象的对象|
|int|getType()|获取此ResultSet对象的类型|
|int|getConcurrency()|获取此ResultSet对象的并发模式|
|String|getCursorName()|获取此ResultSet 对象使用的SQL游标的名称|
|Array|getArray(int columnIndex)|获取此ResultSet对象的当前行中指定列的值，作为ArrayJava编程语言中的对象|
|Array|getArray(String columnLabel)|获取此ResultSet对象的当前行中指定列的值，作为ArrayJava编程语言中的对象|
|InputStream|getAsciiStream(String columnLabel)|ResultSet以ASCII字符流的形式获取此对象的当前行中指定列的值|
|BigDecimal|getBigDecimal(String columnLabel)|以全精度获取此ResultSet对象 的当前行中指定列的值java.math.BigDecimal|
|InputStream|getBinaryStream(String columnLabel)|ResultSet以未解释的bytes 流的形式获取此对象 的当前行中指定列的值|
|boolean|getBoolean(String columnLabel)|以Java编程语言中的形式获取此ResultSet对象的当前行中指定列的值boolean|
|byte|getByte(String columnLabel)|以Java编程语言中的形式获取此ResultSet对象的当前行中指定列的值byte|
|byte[]|getBytes(String columnLabel)|以Java编程语言中数组的形式获取此ResultSet对象的当前行中指定列的值byte|
|Reader|getCharacterStream(int columnIndex)|以对象的形式获取此ResultSet对象 的当前行中指定列的值java.io.Reader|
|Clob|getClob(int columnIndex)|获取此ResultSet对象的当前行中指定列的值，作为ClobJava编程语言中的对象|
|Date|getDate(String columnLabel, Calendar cal)|获取此ResultSet对象的当前行中指定列的值，作为java.sql.DateJava编程语言中的对象|
|double|getDouble(String columnLabel)|以Java编程语言中的形式获取此ResultSet对象的当前行中指定列的值double|
|float|getFloat(String columnLabel)|以Java编程语言中的形式获取此ResultSet对象的当前行中指定列的值float|
|int|getInt(String columnLabel)|以Java编程语言中的形式获取此ResultSet对象的当前行中指定列的值int|
|long|getLong(String columnLabel)|以Java编程语言中的形式获取此ResultSet对象的当前行中指定列的值long|
|Reader|getNCharacterStream(String columnLabel)|以对象的形式获取此ResultSet对象 的当前行中指定列的值java.io.Reader|
|NClob|getNClob(String columnLabel)|获取此ResultSet对象的当前行中指定列的值，作为NClobJava编程语言中的对象|
|String|getNString(String columnLabel)|以Java编程语言中的形式获取此ResultSet对象的当前行中指定列的值String|
|<T> T|getObject(String columnLabel, Class<T> type)|ResultSet如果支持转换，则获取此对象的当前行中指定列的值，并将从列的SQL类型转换为请求的Java数据类型|
|Object|getObject(String columnLabel, Map<String,Class<?>> map)|以Java编程语言中的形式获取此ResultSet对象的当前行中指定列的值Object|
|Ref|getRef(String columnLabel)|获取此ResultSet对象的当前行中指定列的值，作为RefJava编程语言中的对象|
|RowId|getRowId(String columnLabel)|获取此ResultSet对象的当前行中指定列的值， 作为java.sql.RowIdJava编程语言中的对象|
|short|getShort(String columnLabel)|以Java编程语言中的形式获取此ResultSet对象的当前行中指定列的值short|
|SQLXML|getSQLXML(String columnLabel)|获取此当前行中指定列的值，ResultSet作为java.sql.SQLXMLJava编程语言中的 对象|
|String|getString(String columnLabel)|以Java编程语言中的形式获取此ResultSet对象的当前行中指定列的值String|
|Time|getTime(String columnLabel, Calendar cal)|获取此ResultSet对象的当前行中指定列的值，作为java.sql.TimeJava编程语言中的对象|
|Timestamp|getTimestamp(String columnLabel, Calendar cal)|获取此ResultSet对象的当前行中指定列的值，作为java.sql.TimestampJava编程语言中的对象|
|URL|getURL(String columnLabel)|获取此ResultSet对象的当前行中指定列的值，作为java.net.URL Java编程语言中的对象|
|SQLWarning|getWarnings()|获取此ResultSet对象上的调用报告的第一个警告|
|void|insertRow()|将插入行的内容插入此 ResultSet对象并插入数据库|
|boolean|isAfterLast()|获取光标是否在此ResultSet对象的最后一行之后|
|boolean|isBeforeFirst()|获取光标是否在此ResultSet对象的第一行之前|
|boolean|isClosed()|获取此ResultSet对象是否已关闭|
|boolean|isFirst()|获取光标是否在此ResultSet对象的第一行|
|boolean|isLast()|获取光标是否在此ResultSet对象的最后一行|
|boolean|last()|将光标移动到此ResultSet对象的最后一行|
|void|moveToCurrentRow()|将光标移动到记住的光标位置，通常是当前行|
|void|moveToInsertRow()|将光标移动到插入行|
|boolean|next()|将光标从当前位置向前移动一行|
|boolean|previous()|将光标移动到此ResultSet对象中的上一行|
|void|refreshRow()|使用数据库中的最新值刷新当前行|
|boolean|relative(int rows)|将光标移动相对的行数（正数或负数）|
|boolean|rowDeleted()|获取是否已删除行|
|boolean|rowInserted()|获取当前行是否已插入|
|boolean|rowUpdated()|获取当前行是否已更新|
|boolean|wasNull()|报告最后一列读取的值是否为SQL NULL|
|void|updateArray(String columnLabel, Array x)|使用java.sql.Array值更新指定的列|
|void|updateRow()|使用此ResultSet对象的当前行的新内容更新基础数据库|
|void|updateRowId(String columnLabel, RowId x)|使用RowId值更新指定的列|
|void|updateTimestamp(String columnLabel, Timestamp x)|使用java.sql.Timestamp 值更新指定的列|
|void|updateTime(String columnLabel, Time x)|使用java.sql.Time值更新指定的列|
|void|updateString(String columnLabel, String x)|使用String值更新指定的列|
|void|updateSQLXML(String columnLabel, SQLXML xmlObject)|使用java.sql.SQLXML值更新指定的列|
|void|updateShort(String columnLabel, short x)|使用short值更新指定的列|
|void|updateRef(String columnLabel, Ref x)|使用java.sql.Ref值更新指定的列|
|default void|updateObject(String columnLabel, Object x, SQLType targetSqlType, int scaleOrLength)|使用Object值更新指定的列|
|void|updateNull(String columnLabel)|使用null值更新指定的列|
|void|updateNString(String columnLabel, String nString)|使用String值更新指定的列|
|void|updateNClob(String columnLabel, Reader reader, long length)|使用给定Reader 对象更新指定列，该对象是给定的字符长度|
|void|updateNCharacterStream(String columnLabel, Reader reader, long length)|使用字符流值更新指定的列，该值将具有指定的字节数|
|void|updateLong(String columnLabel, long x)|使用long值更新指定的列|
|void|updateInt(String columnLabel, int x)|使用int值更新指定的列|
|void|updateInt(String columnLabel, int x)|使用int值更新指定的列|
|void|updateFloat(String columnLabel, float x)|使用float值更新指定的列|
|void|updateDouble(String columnLabel, double x)|使用double值更新指定的列|
|void|updateDate(String columnLabel, Date x)|使用java.sql.Date值更新指定的列|
|void|updateClob(String columnLabel, Reader reader, long length)|使用给定Reader 对象更新指定列，该对象是给定的字符长度|
|void|updateCharacterStream(String columnLabel, Reader reader, int length)|使用字符流值更新指定的列，该值将具有指定的字节数|
|void|updateBytes(String columnLabel, byte[] x)|使用字节数组值更新指定的列|
|void|updateByte(String columnLabel, byte x)|使用byte值更新指定的列|

ResultSetMain.java：
```Java
package com.mutisitc.resultset;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import com.mutisitc.utils.JDBCUtil;
import com.mutisitc.utils.PrintUtil;
// java.sql.ResultSet：数据库结果集
public class ResultSetMain {
  public static void main(String[] args) {
    PrintUtil.one("java.sql.PreparedStatement：预编译SQL语句：");
    try {
      String preparedSQL = "SELECT bookId, title, author, remark, createrTime FROM book WHERE title = ?";
      PreparedStatement preparedStatement = JDBCUtil.prepareStatement(preparedSQL);
      preparedStatement.setString(1, "test by result");
      
      ResultSet resultSet = preparedStatement.executeQuery();
      PrintUtil.two("3.使用PreparedStatement.executeQuery()获取数据库查询结果集", "ResultSet="+resultSet);
      
      int fetchDirection = resultSet.getFetchDirection();
      PrintUtil.two("4.ResultSet.getFetchDirection()获取此ResultSet对象的获取方向", "fetchDirection=ResultSet.FETCH_FORWARD="+fetchDirection);

      fetchDirection = ResultSet.FETCH_FORWARD;
      resultSet.setFetchDirection(fetchDirection);
      PrintUtil.two("5.ResultSet.setFetchDirection(int direction)提供有关此ResultSet对象中的行的处理方向的提示", "fetchDirection=ResultSet.FETCH_FORWARD="+fetchDirection);
      
      int fetchSize = resultSet.getFetchSize();
      PrintUtil.two("6.ResultSet.getFetchSize()获取此ResultSet对象的提取大小", "fetchSize="+fetchSize);
      
      fetchSize = 0;
      resultSet.setFetchSize(fetchSize);
      PrintUtil.two("7.ResultSet.setFetchSize(int rows)为JDBC驱动程序提供有关此ResultSet对象需要更多行时应从数据库中提取的行数的提示", "fetchSize="+fetchSize);
      
      PrintUtil.two("8.ResultSet.getHoldability()", "通过Connection中设置holdability属性，com.mysql.cj.jdbc.result.ResultSetImpl不支持获取通过getHoldability()获取");
      
      ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
      PrintUtil.two("9.ResultSet.getMetaData()获取此ResultSet对象列的数量，类型和属性对象", "ResultSetMetaData="+resultSetMetaData);

      Statement statement = resultSet.getStatement();
      PrintUtil.two("10.ResultSet.getStatement()获取Statement生成此 ResultSet对象的对象", "Statement="+statement.getClass()+"@"+statement.hashCode());

      int type = resultSet.getType();
      PrintUtil.two("11.ResultSet.getType()获取此ResultSet对象的类型", "type="+type);

      int concurrency = resultSet.getConcurrency();
      PrintUtil.two("12.ResultSet.getConcurrency()获取此ResultSet对象的并发模式", "concurrency=ResultSet.CONCUR_READ_ONLY="+concurrency);
      
      PrintUtil.two("13.ResultSet.getCursorName()", "com.mysql.cj.jdbc.result.ResultSetImpl不支持此方法");

      PrintUtil.two("14.ResultSet.next()", "将光标从当前位置向前移动一行，用于获取数据");
      int index = 1;
      while(resultSet.next()) {
        int row = resultSet.getRow();
        Long bookId = resultSet.getLong(1);
        String title = resultSet.getString("title");
        String author = resultSet.getString("author");
        String remark = resultSet.getString("remark");
        Date createrTime = resultSet.getDate("createrTime");
        PrintUtil.three("14."+index+".通过getXXX(int columnIndex)或getXXX(String columnLabel)获取数据列信息", 
            "row="+row+" ,bookId="+bookId+", title="+title+
            ", author="+author+", remark="+remark+", createrTime="+createrTime);
        index++;
      }
      PrintUtil.two("15.com.mysql.cj.jdbc.result.ResultSetImpl", "不支持根据列索引更新数据updateXXX(int columnIndex, XXX xxx)、"
          + "insertRow()、updateRowId()、updateRow()等方法");
      
      PrintUtil.two("16.由于Mysql的holdability属性值固定为：ResultSet.CLOSE_CURSORS_AT_COMMIT=2", "所以不支持通过ResultSet对象的updateXXX方法直接更新数据");
      
      JDBCUtil.close(preparedStatement);
    } catch (SQLException e) {
      PrintUtil.err("演示 java.sql.PreparedStatement：预编译SQL语句，打印异常堆栈信息：");
      e.printStackTrace();
    }
  }
}
```
GetResultSetMain.java:获取ResultSet的方法：
```Java
package com.mutisitc.resultset;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mutisitc.utils.JDBCUtil;
import com.mutisitc.utils.PrintUtil;
// 获取ResultSet的方法
public class GetResultSetMain {
  public static void main(String[] args) {
    try {
      getByStatement();
      getByPreparedStatement();
    } catch (SQLException e) {
      PrintUtil.err("获取ResultSet的方法，打印异常堆栈信息：");
      e.printStackTrace();
    }
  }
  // 通过Statement.executeQuery(String sql)
  private static void getByStatement() throws SQLException {
    String selectSQL = "SELECT bookId, title, author, remark, createrTime FROM book WHERE title = 'test by result'";
    Statement statement = JDBCUtil.createStatement();
    ResultSet resultSet = statement.executeQuery(selectSQL);;
    PrintUtil.two("3.使用Statement.executeQuery(String sql)获取数据库查询结果集", "ResultSet="+resultSet);
  }
  // 通过PreparedStatement.executeQuery()
  private static void getByPreparedStatement() throws SQLException {
    String preparedSQL = "SELECT bookId, title, author, remark, createrTime FROM book WHERE title = ?";
    PreparedStatement preparedStatement = JDBCUtil.prepareStatement(preparedSQL);
    preparedStatement.setString(1, "test by result");
    
    ResultSet resultSet = preparedStatement.executeQuery();
    PrintUtil.two("3.使用PreparedStatement.executeQuery()获取数据库查询结果集", "ResultSet="+resultSet);
  }
}
```

### <a id="a_callable">十三、java.sql.CallableStatement：执行SQL存储过程</a> <a href="#a_resultSet">last</a> <a href="#a_types">next</a>
[java.sql.CallableStatement](https://docs.oracle.com/javase/8/docs/api/java/sql/CallableStatement.html)  
一、描述：
```
继承java.sql.PreparedStatement
用于执行 SQL 存储过程的接口。JDBC API 提供了一个存储过程 SQL 转义语法，
该语法允许对所有 RDBMS 使用标准方式调用存储过程。此转义语法有一个包含结果参数的形式和一个不包含结果参数的形式。
如果使用结果参数，则必须将其注册为 OUT 参数。其他参数可用于输入、输出或同时用于二者。参数是根据编号按顺序引用的，第一个参数的编号是 1。 
   {?= call <procedure-name>[(<arg1>,<arg2>, ...)]}
   {call <procedure-name>[(<arg1>,<arg2>, ...)]}
 IN 参数值是使用继承自 PreparedStatement 的 set 方法设置的。在执行存储过程之前，必须注册所有 OUT 参数的类型；
它们的值是在执行后通过此类提供的 get 方法获取的。 

CallableStatement 可以返回一个 ResultSet 对象或多个 ResultSet 对象。多个 ResultSet 对象是使用继承自 Statement 的操作处理的。 
为了获得最大的可移植性，某一调用的 ResultSet 对象和更新计数应该在获得输出参数的值之前处理。

CallableStatement调用存储过程时，如果存在IN参数(入参)着必须指定具体的值(可以为null)、如果存在OUT参数(返参)则必须注册JDBC类型
```

二、[Mysql存储过程：](https://www.cnblogs.com/mark-chan/p/5384139.html)
```SQL
-- 删除存储过程
DROP PROCEDURE IF EXISTS pro_testByQuery;
-- 创建存储过程
CREATE PROCEDURE pro_testByQuery(IN p_bookId BIGINT(20), OUT p_title VARCHAR(50))
  BEGIN
    SELECT title INTO p_title FROM book WHERE bookId = p_bookId;
  END;
-- 执行存储过程
CALL pro_testByQuery(1, @p_title);
-- 查询返回参数OUT
SELECT @p_title;
```

三、方法说明 ：

|返回类型|方法|说明|
|---|---|---|
|void|registerOutParameter(String parameterName, int sqlType)|注册名为parameterName JDBC类型 的OUT参数 sqlType|
|void|registerOutParameter(String parameterName, int sqlType, int scale)|注册名为parameterName JDBC类型 的参数 sqlType|
|void|registerOutParameter(String parameterName, int sqlType, String typeName)|注册指定的输出参数|
|default void|registerOutParameter(String parameterName, SQLType sqlType)|注册名为parameterName JDBC类型 的OUT参数 sqlType|
|default void|registerOutParameter(String parameterName, SQLType sqlType, int scale)|注册名为parameterName JDBC类型 的参数 sqlType|
|default void|registerOutParameter(String parameterName, SQLType sqlType, String typeName)|注册指定的输出参数|
|Array|getArray(String parameterName)|获取JDBC ARRAY参数的值作为ArrayJava编程语言中的 对象|
|BigDecimal|getBigDecimal(String parameterName)|获取JDBC NUMERIC参数的值，作为 java.math.BigDecimal对象，其值包含小数点右侧的位数|
|Blob|getBlob(String parameterName)|获取JDBC BLOB参数的值作为Blob Java编程语言中的 对象|
|boolean|getBoolean(String parameterName)|以Java编程语言的形式获取JDBC BIT或BOOLEAN 参数 的值boolean|
|byte|getByte(String parameterName)|以Java编程语言的形式获取JDBC TINYINT参数的值byte|
|byte[]|getBytes(String parameterName)|以Java编程语言中 的值数组的形式获取JDBC BINARY或VARBINARY参数的byte值|
|Reader|getCharacterStream(String parameterName)|获取指定参数的值作为java.io.ReaderJava编程语言中的 对象|
|Clob|getClob(String parameterName)|获取JDBC CLOB参数的值作为java.sql.ClobJava编程语言中的 对象|
|Date|getDate(String parameterName)|获取JDBC DATE参数的值作为 java.sql.Date对象|
|Date|getDate(String parameterName, Calendar cal)|使用给定对象构造日期DATE，以java.sql.Date对象的形式获取JDBC 参数 的值Calendar|
|double|getDouble(String parameterName)|以Java编程语言的形式获取JDBC DOUBLE参数的值double|
|float|getFloat(String parameterName)|以Java编程语言的形式获取JDBC FLOAT参数的值float|
|int|getInt(String parameterName)|以Java编程语言中的形式获取JDBC INTEGER参数的值int|
|long|getLong(String parameterName)|以Java编程语言的形式获取JDBC BIGINT参数的值long|
|Reader|getNCharacterStream(String parameterName)|获取指定参数的值作为java.io.ReaderJava编程语言中的 对象|
|NClob|getNClob(String parameterName)|获取JDBC NCLOB参数的值作为java.sql.NClobJava编程语言中的 对象|
|String|getNString(String parameterName)|获取指定的值NCHAR， NVARCHAR 或LONGNVARCHAR参数作为StringJava编程语言|
|Object|getObject(String parameterName)|Object以Java编程语言中的形式获取参数的值|
|<T> T|getObject(String parameterName, Class<T> type)|返回表示OUT参数值的对象parameterName，如果支持转换，则将从参数 的SQL类型转换为请求的Java数据类型|
|Object|getObject(String parameterName, Map<String,Class<?>> map)|返回表示OUT参数值的对象， parameterName并map用于参数值的自定义映射|
|Ref|getRef(String parameterName)|获取JDBC REF(<structured-type>) 参数的值作为RefJava编程语言中的对象|
|RowId|getRowId(String parameterName)|获取指定的JDBC ROWID参数的值作为 java.sql.RowId对象|
|short|getShort(String parameterName)|以Java编程语言的形式获取JDBC SMALLINT参数的值short|
|SQLXML|getSQLXML(String parameterName)|获取指定SQL XML参数的值作为java.sql.SQLXMLJava编程语言中的 对象|
|String|getString(String parameterName)|获取JDBC的值CHAR，VARCHAR或LONGVARCHAR参数作为StringJava编程语言|
|Time|getTime(int parameterIndex, Calendar cal)|获取指定的JDBC TIME参数的值作为 java.sql.Time对象，使用给定的Calendar对象构造时间|
|Time|getTime(String parameterName, Calendar cal)|使用给定对象构造时间TIME，以java.sql.Time对象的形式获取JDBC 参数 的值Calendar|
|Timestamp|getTimestamp(String parameterName)|获取JDBC TIMESTAMP参数的值作为 java.sql.Timestamp对象|
|Timestamp|getTimestamp(String parameterName, Calendar cal)|使用给定对象构造对象，TIMESTAMP以java.sql.Timestamp对象的形式获取JDBC 参数 的值。CalendarTimestamp|
|URL|getURL(String parameterName)|获取JDBC DATALINK参数的值作为 java.net.URL对象|
|void|setAsciiStream(String parameterName, InputStream x)|将指定参数设置为给定输入流|
|void|setAsciiStream(String parameterName, InputStream x, int length)|将指定参数设置为给定输入流，该输入流将具有指定的字节数|
|void|setAsciiStream(String parameterName, InputStream x, long length)|将指定参数设置为给定输入流，该输入流将具有指定的字节数|
|void|setBigDecimal(String parameterName, BigDecimal x)|将指定参数设置为给定 java.math.BigDecimal值|
|void|setBinaryStream(String parameterName, InputStream x)|将指定参数设置为给定输入流|
|void|setBinaryStream(String parameterName, InputStream x, int length)|将指定参数设置为给定输入流，该输入流将具有指定的字节数|
|void|setBinaryStream(String parameterName, InputStream x, long length)|将指定参数设置为给定输入流，该输入流将具有指定的字节数|
|void|setBlob(String parameterName, Blob x)|将指定参数设置为给定java.sql.Blob对象|
|void|setBlob(String parameterName, InputStream inputStream)|将指定参数设置为InputStream对象|
|void|setBlob(String parameterName, InputStream inputStream, long length)|将指定参数设置为InputStream对象|
|void|setBoolean(String parameterName, boolean x)|将指定参数设置为给定的Java boolean值|
|void|setByte(String parameterName, byte x)|将指定参数设置为给定的Java byte值|
|void|setBytes(String parameterName, byte[] x)|将指定参数设置为给定的Java字节数组|
|void|setCharacterStream(String parameterName, Reader reader)|将指定参数设置为给定Reader 对象|
|void|setCharacterStream(String parameterName, Reader reader, int length)|将指定参数设置为给定Reader 对象，即给定的字符长度|
|void|setCharacterStream(String parameterName, Reader reader, long length)|将指定参数设置为给定Reader 对象，即给定的字符长度|
|void|setClob(String parameterName, Clob x)|将指定参数设置为给定java.sql.Clob对象|
|void|setClob(String parameterName, Reader reader)|将指定参数设置为Reader对象|
|void|setClob(String parameterName, Reader reader, long length)|将指定参数设置为Reader对象|
|void|setDate(String parameterName, Date x)|java.sql.Date使用运行应用程序的虚拟机的默认时区将指定参数设置为给定值|
|void|setDate(String parameterName, Date x, Calendar cal)|java.sql.Date使用给定Calendar对象将指定参数设置为给定值|
|void|setDouble(String parameterName, double x)|将指定参数设置为给定的Java double值|
|void|setFloat(String parameterName, float x)|将指定参数设置为给定的Java float值|
|void|setInt(String parameterName, int x)|将指定参数设置为给定的Java int值|
|void|setLong(String parameterName, long x)|将指定参数设置为给定的Java long值|
|void|setNCharacterStream(String parameterName, Reader value)|将指定参数设置为Reader对象|
|void|setNCharacterStream(String parameterName, Reader value, long length)|将指定参数设置为Reader对象|
|void|setNClob(String parameterName, NClob value)|将指定参数设置为java.sql.NClob对象|
|void|setNClob(String parameterName, Reader reader)|将指定参数设置为Reader对象|
|void|setNClob(String parameterName, Reader reader, long length)|将指定参数设置为Reader对象|
|void|setNString(String parameterName, String value)|将指定参数设置为给定String对象|
|void|setNull(String parameterName, int sqlType)|将指定参数设置为SQL NULL|
|void|setNull(String parameterName, int sqlType, String typeName)|将指定参数设置为SQL NULL|
|void|setObject(String parameterName, Object x)|使用给定对象设置指定参数的值|
|void|setObject(String parameterName, Object x, int targetSqlType)|使用给定对象设置指定参数的值|
|void|setObject(String parameterName, Object x, int targetSqlType, int scale)|使用给定对象设置指定参数的值|
|default void|setObject(String parameterName, Object x, SQLType targetSqlType)|使用给定对象设置指定参数的值|
|default void|setObject(String parameterName, Object x, SQLType targetSqlType, int scaleOrLength)|使用给定对象设置指定参数的值|
|void|setRowId(String parameterName, RowId x)|将指定参数设置为给定java.sql.RowId对象|
|void|setShort(String parameterName, short x)|将指定参数设置为给定的Java short值|
|void|setSQLXML(String parameterName, SQLXML xmlObject)|将指定参数设置为给定java.sql.SQLXML对象|
|void|setString(String parameterName, String x)|将指定参数设置为给定的Java String值|
|void|setTime(String parameterName, Time x)|将指定参数设置为给定java.sql.Time值|
|void|setTime(String parameterName, Time x, Calendar cal)|java.sql.Time使用给定Calendar对象将指定参数设置为给定值|
|void|setTimestamp(String parameterName, Timestamp x)|将指定参数设置为给定java.sql.Timestamp值|
|void|setTimestamp(String parameterName, Timestamp x, Calendar cal)|java.sql.Timestamp使用给定Calendar对象将指定参数设置为给定值|
|void|setURL(String parameterName, URL val)|将指定参数设置为给定java.net.URL对象|
|boolean|wasNull()|获取读取的最后一个OUT参数是否具有SQL的值NULL|

CallableStatementMain.java：
```Java
package com.mutisitc.callablestatement;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import com.mutisitc.utils.JDBCUtil;
import com.mutisitc.utils.PrintUtil;
// java.sql.CallableStatement：执行SQL存储过程
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
      PrintUtil.three("6.1.也可以通过CallableStatement.getXXX(String parameterName)：获取JDBC参数的值", "parameterIndex=p_title，p_title="+p_title);

      PrintUtil.two("7.CallableStatement调用存储过程时", "如果存在IN参数(入参)着必须指定具体的值(可以为null)、如果存在OUT参数(返参)则必须注册JDBC类型");
      
      JDBCUtil.close(callableStatement);
    } catch (SQLException e) {
      PrintUtil.err("演示 java.sql.CallableStatement：执行SQL存储过程，打印异常堆栈信息：");
      e.printStackTrace();
    }
  }
}
```

### <a id="a_types">十四、java.sql.SQLType：JDBC类型</a> <a href="#a_callable">last</a> <a href="#a_transaction">next</a>
[java.sql.Types](https://docs.oracle.com/javase/8/docs/api/java/sql/Types.html)  
[java.sql.SQLType](https://docs.oracle.com/javase/8/docs/api/java/sql/SQLType.html)  
[java.sql.JDBCType](https://docs.oracle.com/javase/8/docs/api/java/sql/JDBCType.html)  

一、描述：
```
java.sql.Types：定义用于标识通用SQL类型的常量的类，称为JDBC类型。
java.sql.SQLType：用于标识通用SQL类型的对象接口，称为JDBC类型或特定于供应商的数据类型。
java.sql.JDBCType：定义用于标识通用SQL类型的常量枚举，称为JDBC类型。实现SQLType接口。
Mysql中定义的JDBC的相关的类是：com.mysql.cj.MysqlType，实现SQLType接口
```

二、以java.sql.Types为例：字段说明：

|数据类型|字段|说明|
|---|---|---|
|static int|ARRAY|标识通用SQL类型 ARRAY| 
|static int|BIGINT|标识通用SQL类型 BIGINT| 
|static int|BINARY|标识通用SQL类型 BINARY| 
|static int|BIT|标识通用SQL类型 BIT| 
|static int|BLOB|标识通用SQL类型 BLOB| 
|static int|BOOLEAN|标识通用SQL类型 BOOLEAN| 
|static int|CHAR|标识通用SQL类型 CHAR| 
|static int|CLOB|标识通用SQL类型 CLOB| 
|static int|DATALINK|标识通用SQL类型 DATALINK| 
|static int|DATE|标识通用SQL类型 DATE| 
|static int|DECIMAL|标识通用SQL类型 DECIMAL| 
|static int|DISTINCT|标识通用SQL类型 DISTINCT| 
|static int|DOUBLE|标识通用SQL类型 DOUBLE| 
|static int|FLOAT|标识通用SQL类型 FLOAT| 
|static int|INTEGER|标识通用SQL类型 INTEGER| 
|static int|JAVA_OBJECT|表示SQL类型是特定于数据库的，并且映射到可以通过getObject和setObject方法访问的Java对象| 
|static int|LONGNVARCHAR|标识通用SQL类型 LONGNVARCHAR| 
|static int|LONGVARBINARY|标识通用SQL类型 LONGVARBINARY| 
|static int|LONGVARCHAR|标识通用SQL类型 LONGVARCHAR| 
|static int|NCHAR|标识通用SQL类型 NCHAR|
|static int|NCLOB|标识通用SQL类型 NCLOB| 
|static int|NULL|Java编程语言中的常量，用于标识通用SQL值 NULL| 
|static int|NUMERIC|标识通用SQL类型 NUMERIC| 
|static int|NVARCHAR|标识通用SQL类型 NVARCHAR| 
|static int|OTHER|表示SQL类型是特定于数据库的，并且映射到可以通过getObject和setObject方法访问的Java对象| 
|static int|REAL|标识通用SQL类型 REAL| 
|static int|REF|标识通用SQL类型 REF| 
|static int|REF_CURSOR|标识通用SQL类型 REF CURSOR| 
|static int|ROWID|标识通用SQL类型 ROWID|
|static int|SMALLINT|标识通用SQL类型 SMALLINT| 
|static int|SQLXML|标识通用SQL类型 XML| 
|static int|STRUCT|标识通用SQL类型 STRUCT| 
|static int|TIME|标识通用SQL类型 TIME| 
|static int|TIME_WITH_TIMEZONE|标识通用SQL类型 TIME WITH TIMEZONE| 
|static int|TIMESTAMP|标识通用SQL类型 TIMESTAMP| 
|static int|TIMESTAMP_WITH_TIMEZONE|标识通用SQL类型 TIMESTAMP WITH TIMEZONE| 
|static int|TINYINT|标识通用SQL类型 TINYINT| 
|static int|VARBINARY|标识通用SQL类型 VARBINARY| 
|static int|VARCHAR|标识通用SQL类型 VARCHAR| 

### <a id="a_transaction">十五、JDBC事务</a> <a href="#a_types">last</a> <a href="#a_transaction">next</a>
[漫谈MySql中的事务](https://www.cnblogs.com/maypattis/p/5628355.html)  
一、数据库事务的定义：
```
  数据库事务(Database Transaction) ：是指作为单个逻辑工作单元执行的一系列操作，要么完全地执行，要么完全地不执行。 
事务处理可以确保除非事务性单元内的所有操作都成功完成，否则不会永久更新面向数据的资源。
通过将一组相关操作组合为一个要么全部成功要么全部失败的单元，可以简化错误恢复并使应用程序更加可靠。
一个逻辑工作单元要成为事务，必须满足所谓的ACID（原子性、一致性、隔离性和持久性）属性。
事务是数据库运行中的逻辑工作单位，由DBMS中的事务管理子系统负责事务的处理
```
二、事务的ACID特性：
```
事务应该具有4个属性：原子性、一致性、隔离性、持久性。这四个属性通常称为ACID特性。
1、原子性 atomicity：一个事务是一个不可分割的工作单位，事务中包括的诸操作要么都做，要么都不做
  事务必须是原子工作单元；对于其数据修改，要么全都执行，要么全都不执行。通常，与某个事务关联的操作具有共同的目标，
并且是相互依赖的。如果系统只执行这些操作的一个子集，则可能会破坏事务的总体目标。原子性消除了系统处理操作子集的可能性。

2、一致性 consistency：事务必须是使数据库从一个一致性状态变到另一个一致性状态。一致性与原子性是密切相关的。
  事务在完成时，必须使所有的数据都保持一致状态。在相关数据库中，所有规则都必须应用于事务的修改，以保持所有数据的完整性。
事务结束时，所有的内部数据结构（如 B 树索引或双向链表）都必须是正确的。某些维护一致性的责任由应用程序开发人员承担，
他们必须确保应用程序已强制所有已知的完整性约束

3、隔离性 isolation：一个事务的执行不能被其他事务干扰。即一个事务内部的操作及使用的数据对并发的其他事务是隔离的，并发执行的各个事务之间不能互相干扰。
  由并发事务所作的修改必须与任何其它并发事务所作的修改隔离。事务查看数据时数据所处的状态，要么是另一并发事务修改它之前的状态，
要么是另一事务修改它之后的状态，事务不会查看中间状态的数据。这称为隔离性，因为它能够重新装载起始数据，并且重播一系列事务，
以使数据结束时的状态与原始事务执行的状态相同。当事务可序列化时将获得最高的隔离级别。在此级别上，
从一组可并行执行的事务获得的结果与通过连续运行每个事务所获得的结果相同。由于高度隔离会限制可并行执行的事务数，所以一些应用程序降低隔离级别以换取更大的吞吐量

4、持久性 durability：持久性也称永久性（permanence），指一个事务一旦提交，它对数据库中数据的改变就应该是永久性的。接下来的其他操作或故障不应该对其有任何影响
```
三、事务的类型：
```
1、手动事务：
  手动事务允许显式处理若干过程，这些过程包括：开始事务、控制事务边界内的每个连接和资源登记、确定事务结果（提交或中止）以及结束事务。
尽管此模型提供了对事务的标准控制，但它缺少一些内置于自动事务模型的简化操作。例如，在手动事务中数据存储区之间没有自动登记和协调。
此外，与自动事务不同，手动事务中事务不在对象间流动。
如果选择手动控制分布式事务，则必须管理恢复、并发、安全性和完整性。也就是说，必须应用维护与事务处理关联的 ACID 属性所需的所有编程方法。

2、自动事务：
  XML Web services方法或 .NET Framework 类一旦被标记为参与事务，它们将自动在事务范围内执行。您可以通过在页、XML Web services 方法或类中
设置一个事务属性值来控制对象的事务行为。特性值反过来确定实例化对象的事务性行为。因此，根据声明特性值的不同，对象将自动参与现有事务或正在进行的事务，
成为新事务的根或者根本不参与事务。声明事务属性的语法在 .NET Framework 类、.NET 页和 XML Web services 方法中稍有不同。
  声明性事务特性指定对象如何参与事务，如何以编程方式被配置。尽管此声明性级别表示事务的逻辑，但它是一个已从物理事务中移除的步骤。
物理事务在事务性对象访问数据库或消息队列这样的数据资源时发生。与对象关联的事务自动流向合适的资源管理器，
诸如 OLE DB、开放式数据库连接 (ODBC) 或 ActiveX 数据对象 (ADO) 的关联驱动程序在对象的上下文中查找事务，
并通过分布式事务处理协调器 (DTC) 在此事务中登记。整个物理事务自动发生。

3、隐式事务：是指每一条数据操作语句都自动地成为一个事务，事务的开始是隐式的，事务的结束有明确的标记
```
四、事务的隔离级别：
```
1、READ UNCOMMITTED(未提交读)：
  在RU的隔离级别下，事务A对数据做的修改，即使没有提交，对于事务B来说也是可见的，这种问题叫脏读。
这是隔离程度较低的一种隔离级别，在实际运用中会引起很多问题，因此一般不常用。

2、READ COMMITTED(提交读)：
  在RC的隔离级别下，不会出现脏读的问题。事务A对数据做的修改，提交之后会对事务B可见，
举例，事务B开启时读到数据1，接下来事务A开启，把这个数据改成2，提交，B再次读取这个数据，会读到最新的数据2。
在RC的隔离级别下，会出现不可重复读的问题。这个隔离级别是许多数据库的默认隔离级别。

3、REPEATABLE READ(可重复读)：
  在RR的隔离级别下，不会出现不可重复读的问题。事务A对数据做的修改，提交之后，对于先于事务A开启的事务是不可见的。
举例，事务B开启时读到数据1，接下来事务A开启，把这个数据改成2，提交，B再次读取这个数据，仍然只能读到1。在RR的隔离级别下，会出现幻读的问题。
幻读的意思是，当某个事务在读取某个范围内的值的时候，另外一个事务在这个范围内插入了新记录，那么之前的事务再次读取这个范围的值，会读取到新插入的数据。
Mysql默认的隔离级别是RR，然而mysql的innoDB引擎间隙锁成功解决了幻读的问题。

4、SERIALIZABLE(可串行化)：
可串行化是最高的隔离级别。这种隔离级别强制要求所有事物串行执行，在这种隔离级别下，读取的每行数据都加锁，会导致大量的锁征用问题，性能最差
```

五、Mysql对事务的支持：
```
 事务的实现是基于数据库的存储引擎。不同的存储引擎对事务的支持程度不一样。mysql中支持事务的存储引擎有innoDB和NDB。
innoDB是mysql默认的存储引擎，默认的隔离级别是RR，并且在RR的隔离级别下更进一步，
通过多版本并发控制（MVCC，Multiversion Concurrency Control ）解决不可重复读问题，加上间隙锁（也就是并发控制）解决幻读问题。
因此innoDB的RR隔离级别其实实现了串行化级别的效果，而且保留了比较好的并发性能
```

|命令|描述|
|---|---|
|SET AUTOCOMMIT=0|取消自动提交处理，开启事务处理|
|SET AUTOCOMMIT=1|打开自动提交处理，关闭事务处理|
|STARTTRANSACTION|启动事务|
|BEGIN|启动事务，相当于执行STARTTRANSACTION|
|COMMIT|提交事务|
|ROLLBACK|回滚全部事务|
|SAVEPOINT 事务保存点名称|提交事务保存点|
|ROLLBACK TO SAVEPOINT 事务保存点名称|回滚操作到保存点|

六、事务保存点：  
[java.sql.Savepoint](https://docs.oracle.com/javase/8/docs/api/java/sql/Savepoint.html)  
```
保存点的表示形式，它是当前事务中可从该Connection.rollback方法引用的一个点 。
当事务回滚到保存点时，撤消该保存点之后所做的所有更改。

保存点可以是命名的，也可以是未命名的。未命名的保存点由基础数据源生成的ID标识

方法说明：
int getSavepointId()
  检索此Savepoint对象表示的保存点的生成ID 。

String  getSavepointName()
  检索此Savepoint 对象表示的保存点的名称。
```

TransactionMain.java：
```Java
package com.mutisitc.transaction;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import com.mutisitc.utils.CommonUtil;
import com.mutisitc.utils.JDBCUtil;
import com.mutisitc.utils.PrintUtil;
// JDBC事务
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
//          connection.rollback();
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
```

---
<a id="a_down"></a>  
<a href="#a_top">Top</a> 
<a href="#a_catalogue">Catalogue</a>
